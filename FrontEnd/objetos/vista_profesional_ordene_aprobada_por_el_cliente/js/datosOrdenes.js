import { Cartel } from './Cartel.js';
import { CartelPuntuar } from './cartelPuntuar.js';

export class datosOrdenes {
    constructor(ordenDto, profesionalDto, aprobada, divPadre) {
        this.orden = ordenDto;
        this.vistaDatos;
        this.div;
        this.divPadre = divPadre ?? '#front';
        this.description = ordenDto.description;
        this.profesional = profesionalDto;
        this.aprobada = aprobada ?? false;
    }

    crearOrden() {
        this.div = document.createElement('div');
        this.div.classList.add("CrearOrden");

        const buttonX = document.createElement('button');
        buttonX.innerText = "X";
        buttonX.addEventListener('click', () => {
            this.div.classList.remove('aceptarOrden');
            this.div.classList.add("CrearOrden");
        });

        this.vistaDatos = document.createElement('div');
        this.vistaDatos.innerText = `Nombre: ${this.profesional.name}
                                     Apellido: ${this.profesional.lastname}
                                     Telefono: 3234981742`;

        const cancelarTrabajo = document.createElement('button');
        cancelarTrabajo.innerText = "Cancelar trabajo";
        cancelarTrabajo.addEventListener('click', () => {

            const cartel = new Cartel('¿Estás seguro de eliminar la orden?', this.orden, this.div);
            cartel.agregarALFront();

        });

        const aceptarTrabajo = document.createElement('button');
        aceptarTrabajo.innerText = "Trabajo realizado";
        aceptarTrabajo.addEventListener('click', () => {

            const cartel = new CartelPuntuar(this.orden);
            cartel.agregarALFront();
            this.cerrar();

        });

        const descripcionOrden = document.createElement('div');
        descripcionOrden.innerText = this.description;
        descripcionOrden.addEventListener('click', () => {
            this.div.classList.remove('CrearOrden');
            this.div.classList.add("aceptarOrden");
        });

        this.div.append(buttonX, this.vistaDatos, cancelarTrabajo, aceptarTrabajo, descripcionOrden);
        return this.div;
    }
    agregarAlFront() {
        const main = document.querySelector(this.divPadre);
        main.appendChild(this.crearOrden());
    }

    cerrar() {
        const padre = this.div.parentNode;
        padre.removeChild(this.div);
    }
}