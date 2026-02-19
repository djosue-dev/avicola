export async function getVentas() {
    const res = await fetch("/ventas");
    if (!res.ok) throw new Error("Error al cargar ventas");
    return await res.json();
}

export async function createVenta(newVenta) {
    // 1. Create the Venta (Header)
    const res = await fetch("/ventas", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            cliente: newVenta.cliente,
            // fecha is handled by backend @PrePersist or we can send it
            // totalKilos/Importe are calculated by backend
        }),
    });

    if (!res.ok) {
        throw new Error("Error al crear la venta");
    }

    const ventaCreada = await res.json();
    const ventaId = ventaCreada.id;

    // 2. Add Details
    if (newVenta.detalles && newVenta.detalles.length > 0) {
        const resDetalles = await fetch(`/ventas/${ventaId}/detalle`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newVenta.detalles),
        });

        if (!resDetalles.ok) {
            throw new Error("Error al agregar detalles a la venta");
        }

        return await resDetalles.json();
    }

    return ventaCreada;
}
