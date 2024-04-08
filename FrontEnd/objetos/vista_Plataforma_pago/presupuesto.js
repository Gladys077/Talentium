export class Presupuesto {
    constructor(dato, divPadre) {
        console.log(dato)
        this.dato = dato;
        this.divPadre = document.querySelector(divPadre) ?? document.querySelector(".contenidoRecuadro");
        this.orden = `
                <div class="resumen-compra">            
                    <h3>Resumen de Compra</h3>
                    <div class="avatarProfesional">
                        <img class="img_Profesional" src="img/NoAvatar.png" alt="Foto del Profesional">
                        <h4>${dato.professional.lastname}, ${dato.professional.name}<br>${dato.professional.rating} stars</h4>
                    </div>
                    <p>Servicio: ${dato.professional.profession}</p>
                    <p>Precio: ${dato.order.price}</p>
                </div>
                <div class="metodo-pago">
                            <div>
                                <h3>Medios de Pagos Habilitados</h3>
                            </div>
                            <div>
                                <h4><input type="radio" name="check" id="credito"> Crédito</input></h4>
                                <h4><input type="radio" name="check" id="debito"> Débito</input></h4>
                                <h4><input type="radio" name="check" id="efectivo"> Efectivo</input></h4>
                                <h4><input type="radio" name="check" id="transferencia"> Transferencia</input></h4>
                            </div>
                            <div>
                                <button class="boton_centrado">Continuar</button>
                            </div>
                </div>
                `;    

                this.mostrarPresupuesto();
    }

    mostrarPresupuesto() {
        const front = document.createElement('div');
        front.className = "contenido-metodo-pago"
        front.innerHTML = this.orden;
        this.divPadre.append(front);
    }
}
