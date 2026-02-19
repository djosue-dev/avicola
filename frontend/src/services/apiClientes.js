export async function getClientes() {
    const res = await fetch("/clientes");
    if (!res.ok) throw new Error("Error al cargar clientes");
    return await res.json();
}
