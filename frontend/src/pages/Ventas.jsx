import { useState } from "react";
import { useForm, useFieldArray } from "react-hook-form";
import { useMutation, useQueryClient, useQuery } from "@tanstack/react-query";
import toast from "react-hot-toast";
import styled from "styled-components";
import { format } from "date-fns";
import { HiOutlineTrash } from "react-icons/hi2";

import { createVenta, getVentas } from "../services/apiVentas";
import { getClientes } from "../services/apiClientes";

import Form from "../ui/Form";
import FormRow from "../ui/FormRow";
import Input from "../ui/Input";
import Button from "../ui/Button";
import Heading from "../ui/Heading";
import Table from "../ui/Table";
import Spinner from "../ui/Spinner";
import ButtonIcon from "../ui/ButtonIcon";

// Helper layout component for page structure
const PageLayout = styled.div`
  display: flex;
  flex-direction: column;
  gap: 3.2rem;
`;

const RowLayout = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const StyledSelect = styled.select`
  font-size: 1.4rem;
  padding: 0.8rem 1.2rem;
  border: 1px solid var(--color-grey-300);
  border-radius: var(--border-radius-sm);
  background-color: var(--color-grey-0);
  font-weight: 500;
  box-shadow: var(--shadow-sm);
`;


function Ventas() {
    return (
        <PageLayout>
            <RowLayout>
                <Heading as="h1">Ventas</Heading>
            </RowLayout>
            <VentaForm />
            <VentaTable />
        </PageLayout>
    )
}

function VentaForm() {
    const { register, handleSubmit, reset, control, formState } = useForm({
        defaultValues: {
            detalles: [{ peso: "", precioKilo: "" }]
        }
    });
    const { errors } = formState;
    const { fields, append, remove } = useFieldArray({
        control,
        name: "detalles"
    });

    const queryClient = useQueryClient();

    // Load Clientes for dropdown
    const { data: clientes, isLoading: isLoadingClientes } = useQuery({
        queryKey: ["clientes"],
        queryFn: getClientes
    });

    const { mutate, isLoading } = useMutation({
        mutationFn: createVenta,
        onSuccess: () => {
            toast.success("Venta registrada exitosamente");
            reset({ detalles: [{ peso: "", precioKilo: "" }] });
            queryClient.invalidateQueries({ queryKey: ["ventas"] });
        },
        onError: (err) => toast.error(err.message),
    });

    function onSubmit(data) {
        // Transform data if necessary
        const payload = {
            cliente: data.clienteId,
            detalles: data.detalles.map((d, index) => ({
                numeroTina: index + 1,
                peso: parseFloat(d.peso) || 0,
                precioKilo: parseFloat(d.precioKilo) || 0
            }))
        };
        mutate(payload);
    }

    if (isLoadingClientes) return <Spinner />;

    return (
        <Form onSubmit={handleSubmit(onSubmit)}>

            <Heading as="h3">Registrar Nueva Venta</Heading>

            <FormRow label="Cliente" error={errors?.clienteId?.message}>
                <StyledSelect
                    id="clienteId"
                    disabled={isLoading}
                    {...register("clienteId", { required: "Este campo es requerido" })}
                >
                    <option value="">Seleccione un cliente</option>
                    {clientes?.map(c => (
                        <option key={c.id} value={c.id}>{c.nombreComercial}</option>
                    ))}
                </StyledSelect>
            </FormRow>

            <Heading as="h4">Detalles (Tinas)</Heading>

            {fields.map((field, index) => (
                <div key={field.id} style={{ display: 'flex', gap: '1rem', alignItems: 'center', marginBottom: '1rem' }}>
                    <Input
                        placeholder="Peso (Kg)"
                        type="number"
                        step="0.01"
                        {...register(`detalles.${index}.peso`, { required: true })}
                    />
                    <Input
                        placeholder="Precio/Kg"
                        type="number"
                        step="0.01"
                        {...register(`detalles.${index}.precioKilo`, { required: true })}
                    />
                    <ButtonIcon type="button" onClick={() => remove(index)} color="red">
                        <HiOutlineTrash />
                    </ButtonIcon>
                </div>
            ))}

            <Button type="button" variation="secondary" size="small" onClick={() => append({ peso: "", precioKilo: "" })}>
                + Agregar Tina
            </Button>

            <FormRow>
                <Button variation="secondary" type="reset" onClick={() => reset()}>
                    Cancelar
                </Button>
                <Button disabled={isLoading}>Registrar Venta</Button>
            </FormRow>
        </Form>
    );
}

function VentaTable() {
    const { isLoading, data: ventas, error } = useQuery({
        queryKey: ["ventas"],
        queryFn: getVentas,
    });

    if (isLoading) return <Spinner />;
    if (error) return <p>Error cargando ventas: {error.message}</p>

    return (
        <Table columns="0.5fr 1.5fr 1.5fr 1fr 1fr">
            <Table.Header>
                <div>ID</div>
                <div>Cliente</div>
                <div>Fecha</div>
                <div>Total Kg</div>
                <div>Importe</div>
            </Table.Header>

            <Table.Body
                data={ventas}
                render={(venta) => (
                    <Table.Row key={venta.id}>
                        <div>{venta.id}</div>
                        <div>{venta.cliente}</div>
                        <div>{format(new Date(venta.fecha), "dd/MM/yyyy HH:mm")}</div>
                        <div>{venta.totalKilos?.toFixed(2)} Kg</div>
                        <div>S/. {venta.totalImporte?.toFixed(2)}</div>
                    </Table.Row>
                )}
            />
        </Table>
    );
}

export default Ventas;
