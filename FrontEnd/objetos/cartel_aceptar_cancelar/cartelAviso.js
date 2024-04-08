export class cartelAviso {

    constructor(mensaje, divPadre) {
        this.mensaje = mensaje ?? '¿Estás seguro?';
        this.divPadre = divPadre ?? 'body';
        this.div;

        this.agregarALFront();
    }

    crearCartel() {
        this.div = document.createElement('div');
        this.div.id = 'cartel';
   

        const vista = document.createElement('div');
        vista.innerText = this.mensaje;

     

        const aceptar = document.createElement('button');
        aceptar.innerText = 'Aceptar';
        aceptar.addEventListener('click', ()=>{
            this.cerrar();
        });

        this.div.append(vista, aceptar);

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