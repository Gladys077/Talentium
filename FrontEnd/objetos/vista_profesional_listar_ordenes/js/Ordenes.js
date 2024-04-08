export class Ordenes {
    constructor(datoOrden, profesionalDto, divPadre) {
        this.datos = datoOrden;
        this.textarea;
        this.divPadre = divPadre ?? '#front';
        this.comentario = datoOrden.comentarios ?? "";
        this.description = datoOrden.description;
        this.precio;
        this.profesional = profesionalDto;
        this.orden = {
            "id": 1,
            "profecional": this.profesional.id,
            "precio": NaN,
            "comentarios": this.comentario,
            "orderstatus": "PENDIENTE"

        }
    }

    crearOrden() {
        const div = document.createElement('div');
        div.classList.add("CrearOrden");

        this.textarea = document.createElement('textarea');
        this.textarea.name = "descripcion";
        this.textarea.placeholder = 'comentario';
        this.textarea.cols = "30";
        this.textarea.rows = "8";
        this.textarea.innerText = this.comentario;

        this.precio = document.createElement('input');
        this.precio.type = "number";
        this.precio.placeholder = '$$$';

        const button = document.createElement('button');
        button.innerText = "Crear";
        button.addEventListener('click', () => {

            this.orden.comentarios = this.textarea.value;
            this.orden.precio = this.precio.value;

            console.log(this.orden);

        });

        const buttonX = document.createElement('button');
        buttonX.innerText = "X";
        buttonX.addEventListener('click', () => {
            div.classList.remove('aceptarOrden');
            div.classList.add("CrearOrden");
        });

        const descripcionOrden = document.createElement('div');
        descripcionOrden.innerText = this.description;
        descripcionOrden.addEventListener('click', ()=>{
            div.classList.remove('CrearOrden');
            div.classList.add("aceptarOrden");
        });

            div.append(buttonX, this.textarea, this.precio, button, descripcionOrden);
        return div;
    }
    agregarAlFront() {
        const main = document.querySelector(this.divPadre);
        main.appendChild(this.crearOrden());
    }
}