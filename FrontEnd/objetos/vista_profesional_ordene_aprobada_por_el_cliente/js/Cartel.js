export class Cartel {

    constructor(mensaje,ordenDato,datosOrdenes, divPadre) {
        this.mensaje = mensaje ?? '¿Estás seguro?';
        this.divPadre = divPadre ?? 'main';
        this.div;
        this.orden = ordenDato;
        this.datosOrdenes = datosOrdenes;
    }

    crearCartel() {
        this.div = document.createElement('div');
        this.div.id = 'cartel';
        this.div.classList.add("styleCartel");

        const vista = document.createElement('div');
        vista.innerText = this.mensaje;

        const cancelar = document.createElement('button');
        cancelar.innerText = 'cancelar';
        cancelar.addEventListener('click', ()=>{
            this.cerrar(this.div);
        });

        const aceptar = document.createElement('button');
        aceptar.innerText = 'aceptar';
        aceptar.addEventListener('click', ()=>{
           // alert('aca va la logica');
            this.orden.profecional = null;
            this.orden.precio = null;
            this.orden.comentarios = null;
            this.orden.orderstatus = 'INICIAL';
         
            console.log(this.orden);

            this.cerrar(this.datosOrdenes);
            this.cerrar(this.div);

        });

        this.div.append(vista, cancelar, aceptar);

        return this.div;
    }

    agregarALFront() {
        const padre = document.querySelector(this.divPadre);
        padre.appendChild(this.crearCartel());
    }

    cerrar(hijo) {
        const padre = hijo.parentNode;
        padre.removeChild(hijo);
    }
}