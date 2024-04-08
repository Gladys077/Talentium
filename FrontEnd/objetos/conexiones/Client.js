import { Util } from '../Util.js';
import { cartelAviso } from '../cartel_aceptar_cancelar/cartelAviso.js';

export class Client {
    constructor(nameDto, lastnameDto, dniDto, phoneDto, userId, street, number, province, location) {
        this.name = nameDto;
        this.lastname = lastnameDto;
        this.dni = dniDto;
        this.phone = phoneDto;
        this.user = {
            "id": userId
        }
        this.direction = {
            "street": street,
            "number": number,
            "province": province,
            "location": location
        }
    }


    conexionApi() {

        const url = `${Util.conexionBase()}/api/client`;
        const userData = {
            name: this.name,
            lastname: this.lastname,
            dni: this.dni,
            phone: this.phone,
            user: this.user.id,
            direction: this.direction
        };

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer${Util.reuperarAuthorization()}`
            },
            body: JSON.stringify(userData)
        }).then(response => response.json())
            .then(data => {
                Util.guardarLogin(data);
                Util.cambiarDePagina('sitio_del_cliente.html');
            }
            ).catch(err => {
                new cartelAviso('Ups!! algo salio mal, intenta más tarde', 'h2');
            });

    }

    acrualizarCliente() {
        const url = `${Util.conexionBase()}/api/client/${Util.reuperarLogin().id}`;
        const userData = {
            phone: this.phone,
            dni: this.dni,
            direction: this.direction,
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
                Util.guardarLogin(data);
                new cartelAviso('Datos actualizados', 'h2');
                //Util.cambiarDePagina('sitio_del_cliente.html'); 
            }
            ).catch(err => {

                new cartelAviso('Ups!! algo salio mal, intenta más tarde', 'h2');
            });
    }
}


