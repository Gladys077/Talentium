import { Presupuesto } from './presupuesto.js';

const resumen_compra = document.querySelector(".resumen-compra");
const btnContinuar = document.querySelector(".boton_centrado");

async function searchOrder(idOrder) {
    const url = "https://localhost:8080/api/order/allpending";
    try {
        const response = await fetch(`${url}/${idOrder}`);
        const json = await response.json();
        return json;
    } catch (error) {
        new cartelAviso('Ups!! algo salió mal, intenta más tarde', 'h2');
    }
}

const presupuesto = new Presupuesto(await searchOrder(3), resumen_compra);
presupuesto.mostrarPresupuesto();

async function updatedOrder(idOrder) {
    try {
        const response = await fetch(`http:localhost:8080/api/order/acepted/${idOrder}`, {
            method: 'PATCH',
            body: JSON.stringify({
                title: 'aceptado', // debería ser para estado
            }),
            headers: {
                'Content-type': 'application/json; charset=UTF-8',
            },
        });
        const json = await response.json();
        console.log(json);
        // Aquí deberías añadir la lógica para dirigir a la plataforma de pago y luego a la página de confirmación de pago
    } catch (error) {
        console.error('Hubo un error:', error);
    }
}

btnContinuar.addEventListener('click', () => updatedOrder(1));



