export class CartelPuntuar {
    constructor(ordenDto, mensaje, divPadre) {
        this.orden = ordenDto;
        this.mensaje = mensaje ?? 'Tu opinión nos importa, ¿cómo fue tu experiencia?';
        this.divPadre = divPadre ?? 'main';
        this.rating;
        this.div;
    }

    crearCartel() {

        this.div = document.createElement('div');
        this.div.id = 'CartelPuntuar';

        const divMensaje = document.createElement('div');
        divMensaje.innerText = this.mensaje;


        const divbuttons = document.createElement('div');

        const btn1 = document.createElement('button');
        btn1.innerText = '1'
        btn1.addEventListener('click', () => {
            this.puntuar(1)
        });

        const btn2 = document.createElement('button');
        btn2.innerText = '2'
        btn2.addEventListener('click', () => {
            this.puntuar(2)
        });

        const btn3 = document.createElement('button');
        btn3.innerText = '3'
        btn3.addEventListener('click', () => {
            this.puntuar(3)
        });

        const btn4 = document.createElement('button');
        btn4.innerText = '4'
        btn4.addEventListener('click', () => {
            this.puntuar(4)
        });
        const btn5 = document.createElement('button');
        btn5.innerText = '5'
        btn5.addEventListener('click', () => {
            this.puntuar(5)
        });


        const Botonera = document.createElement('div');
        Botonera.append(btn1, btn2, btn3, btn4, btn5);

        this.div.append(divMensaje, Botonera);
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

    puntuar(estrellas) {

        this.rating = estrellas;

        this.orden.orderstatus = 'FINALIZADA';
        console.log(this.orden);

        const clientePuntuado = {
            'id': this.orden.cliente_id,
            "rating": this.rating,
        }
        console.log(clientePuntuado);

        const profesionalDto = {
            'id': this.orden.profecional,
            "wallet": this.orden.precio
        }
        console.log(profesionalDto);

        this.cerrar(this.div);
    }
}

