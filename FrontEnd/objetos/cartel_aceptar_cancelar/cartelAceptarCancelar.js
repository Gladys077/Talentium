export class cartelAceptarCancelar {

    constructor(mensaje, divPadre) {
        this.mensaje = mensaje ?? '¿Estás seguro?';
        this.divPadre = divPadre ?? 'main';
        this.div;
    }

    crearCartel() {
        this.div = document.createElement('div');
        this.div.id = 'cartel';
   

        const vista = document.createElement('div');
        vista.innerText = this.mensaje;

        const cancelar = document.createElement('button');
        cancelar.innerText = 'Cancelar';
        cancelar.addEventListener('click', ()=>{
            this.cerrar();
        });

        const aceptar = document.createElement('button');
        aceptar.innerText = 'Aceptar';
        aceptar.addEventListener('click', ()=>{
            alert('aca va la logica');
            this.cerrar();
        });

        this.div.append(vista, cancelar, aceptar);

        return this.div;
    }

    agregarALFront() {
        const padre = document.querySelector(this.divPadre);
        padre.appendChild(this.crearCartel());

    }

    cerrar() {
        const padre = this.div.parentNode;
        padre.removeChild(this.div);
    }
}