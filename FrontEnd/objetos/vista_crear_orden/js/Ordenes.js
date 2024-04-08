export class Ordenes {
    constructor(dato, divPadre) {
        this.datos = dato;
        this.textarea;
        this.divPadre = divPadre ?? '#front';
        this.orden = {
            "description": this.textarea ?? "nueva orden test",
            "cliente_id": dato.clienteId ?? NaN
        }
    }

    crearOrden() {
        const div = document.createElement('div');
        div.id = "CrearOrden";

        const textarea = document.createElement('textarea');
        textarea.name = "descripcion";
        textarea.placeholder = "orden";
        textarea.cols = "30";
        textarea.rows = "8";
        textarea.innerText = this.textarea ?? "";

        const button = document.createElement('button');
        button.innerText = "Crear";
        button.addEventListener('click', () => {
            //  console.log(this.datos);
            this.orden.description = document.querySelector('textarea').value;
         if( this.orden.description !== '' &&this.orden.description !== undefined){
            this.crearOrdenConexionApi();
            this.cerrarOrden();
         }else{

         }
         
        });

      
        div.append( textarea, button);
        return div;
    }
    agregarAlFront() {
        const main = document.querySelector(this.divPadre);
        main.appendChild(this.crearOrden());
    }

    cerrarOrden(){
        const padre = div.parentNode;
            padre.removeChild(div);
    }

    crearOrdenConexionApi() {
        const url = `${Util.conexionBase()}/api/order}`;
        const userData = {
            cliente_id: this.datos.id,
            description: this.orden.description
        };

        fetch(url, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer${Util.reuperarAuthorization()}`
            },
            body: JSON.stringify(userData)
        }).then(response => response.json())
            .then(data => {
                if (data !== null) {
                    new cartelAviso('Datos actualizados', 'h2');
                    //Util.cambiarDePagina('sitio_del_cliente.html');
                } else {
                    throw new Error('No se puedieron guardar los datos de las ordenes');
                }
            }
            ).catch(err => {

                new cartelAviso('Ups!! algo salio mal, intenta más tarde', 'h2');
            });

    }
}