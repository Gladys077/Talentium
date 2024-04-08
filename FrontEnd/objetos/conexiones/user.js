import { cartelAviso } from '../cartel_aceptar_cancelar/cartelAviso.js';
import { Util } from '../Util.js';


export class User {
    constructor(emai, passwor, avata) {
        this.email = emai ?? NaN;
        this.password = passwor ?? NaN;
        this.avatar = avata ?? '';

    }
    conexionApi() {


        const url = `${Util.conexionBase()}/usuarios`;
        const userData = {
            email: this.email,
            password: this.password,
            avatar: this.avatar
        };



        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        }).then(response => response.json())
            .then(data => {
                Util.guardarLogin(data);
                Util.guardarAuthorization(data.password);
                Util.guardarImagen(data.avatar);
                Util.cambiarDePagina('invitaAregistrar.html');
            }
            ).catch(err => {
                new cartelAviso('Ups!! Algo salió mal, intenta más tarde');
            });



    }

    login() {

        const url = `${Util.conexionBase()}/usuarios/login`;
        const userData = {
            email: this.email,
            password: this.password
        };

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        }).then(response => response.json())
            .then(data => {
                
                Util.guardarLogin(data);
               Util.guardarAuthorization(data.password);
                Util.guardarImagen(data.avatar);
                const usuariodb = data.name ?? null;
                if (usuariodb !== null) {
                  
                    if (data.cuit !== undefined) {
                        Util.guardarAuthorization(data.lastname);
                        Util.guardarImagen(data.user.avatar ?? '');
                        Util.cambiarDePagina('sitio_Prof.html');
                    } else {
                        Util.guardarAuthorization(data.lastname);
                        Util.guardarImagen(data.user.avatar ?? '');
                        Util.cambiarDePagina('sitio_del_cliente.html');
                    }
                } else {

                    Util.guardarAuthorization(data.lastname);
              
                    Util.cambiarDePagina('invitaAregistrar.html');
                }
            }
            ).catch(err => {
                new cartelAviso('Ups!! Algo salió mal, intenta más tarde');
            });
        /* buscando el jwt en el header
               for ([key, value] of response.headers.entries()) {
                   console.log("Clave: ", key, "valor: ", value);
                 }*/

    }
}
