import { cartelAviso } from './cartel_aceptar_cancelar/cartelAviso.js';

export class Util {

    static esPromesa(variable) {
        return Promise.resolve(variable) instanceof Promise;
    }

    static guardarSesionStorage(nombre, dato, padre) {
        try {
            const datoParce = JSON.stringify(dato);
            // console.log('datoParce: '+ datoParce);
            sessionStorage.setItem(nombre, datoParce);
        } catch (error) {
            new cartelAviso('Error al guardar el usuario', padre);

        }
    }


    static recuperarSesionStorage(nombre, padre) {
        try {
            const recuperarUsuario = sessionStorage.getItem(nombre);
            //console.log('recuperarUsuario: '+ recuperarUsuario);
            return recuperarUsuario;
        } catch (error) {
            new cartelAviso('error al recuperar el usuario', padre);

        }
    }

    static guardarUsuario(dato, padre) {
        Util.guardarSesionStorage('usuario', dato, padre);
    }


    static reuperarUsuario(padre) {
        return Util.recuperarSesionStorage('usuario', padre);
    }

    static guardarAuthorization(dato, padre) {
        try {
            let dat = dato ?? null;
            if (dat !== null) sessionStorage.setItem('Authorization', dato);
        } catch (error) {
            new cartelAviso('error al guardar el usuario', padre);

        }
    }


    static reuperarAuthorization(padre) {
        return Util.recuperarSesionStorage('Authorization', padre);
    }

    static guardarLogin(dato, padre) {
        try {
            sessionStorage.setItem('cliente', JSON.stringify(dato));
        } catch (error) {
            new cartelAviso('error al guardar el usuario', padre);

        }
    }

    static reuperarLogin(padre) {
        return JSON.parse(Util.recuperarSesionStorage('cliente', padre));
    }

    static cliente() {
        return {
            "clienteId": 1,
            "name": "Juan40",
            "lastname": "perez",
            "dni": "77234567",
            "rating": 1,

            "user": {
                "id": 48,
                "email": "email25@emails47.com",
                "avatar": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/female/25.png",
            },

            "direction": {
                "street": "calle 1",
                "number": "258",
                "province": "chaco",
                "location": "montaña"
            }

        }
    }

    static profesional() {
        return {
            "id": 10,
            "name": "Puan",
            "lastname": "perez",
            "cuit": "20-77234567-0",
            "cbu": "20772345670",
            "rating": 5,
            "profession": "ELECTRICIAN",


            "direction": {
                "street": "calle falsa",
                "number": "123",
                "province": "chaco",
                "location": "lago"
            },

            "user": {
                "id": 133,
                "email": "emai555@emails.com",
                "avatar": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/female/25.png",
            }

        }
    }
    static orden() {
        return {
            "id": 1,
            "description": "nueva orden 5",
            "profecional": 1,
            "precio": 0.00,
            "fecha": "",
            "estado": "INICIAL",
            "cliente_id": 15
        }
    }
    static cambiarDePagina(pageUrl) {
        window.location.href = pageUrl;
    }

    static existLogin() {
        if (Util.reuperarLogin() === null) Util.cambiarDePagina('index.html');
    }

    static existLoginClient() {
        Util.existLogin();
        const clientDb = Util.reuperarLogin();
        const VerificarclienteDB = clientDb.lastname ?? null;
        if (VerificarclienteDB !== null) Util.cambiarDePagina('sitio_del_cliente.html');
    }


    static updateFileName() {
        const fileInput = document.getElementById('file-input');
        const fileNameContainer = document.getElementById('file-name-container');

        // Verificar si se seleccionó un archivo
        if (fileInput.files.length > 0) {
            // Mostrar el nombre del archivo seleccionado
            fileNameContainer.textContent = `Archivo: ${fileInput.files[0].name}`;

        } else {
            // Limpiar el contenido si no se seleccionó ningún archivo
            fileNameContainer.textContent = '';
        }
    }

    static guardarImagen64(input) {

        const fileInput = input;

        const file = fileInput.files[0];
        const reader = new FileReader();
        reader.onloadend = function () {

            const base64String = reader.result;
            Util.guardarImagen(base64String);
        }
        reader.readAsDataURL(file);
    }

    static guardarImagen(imagen) {
        if(imagen !== undefined)sessionStorage.setItem('imagen', imagen);
    }


    static reuperarImagen() {
        let avatar = sessionStorage.getItem('imagen');
        if (avatar === undefined) avatar = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACgAAAAoCAYAAACM/rhtAAAACXBIWXMAAAsTAAALEwEAmpwYAAADYklEQVR4nO2YXU/TYBTHp0a91jtj9AsYr/wKulfbDcPAxGi8EYF7bkwUoyhd1w6BmKAihJcLhYTBEip6ZULWPk83MWwwRkRmSIAiXjDoGG5kjzkGEgI6NtYyMJ7kn/TitP3lnD7nf1KD4V8OZ+3YCeNTqdzREPDZeXnOwko/QXDt8AQGTIxYBjlFgTMzqNTOYaWqPRzvlhSCviVI9Mf6b0mxBOmSFFLZHo7TnDxvYqRr+wbm7Ok5RnO4ubw5qH6YWCKxpUxWQU55U1ClONRYW0uO6g4IcHdfh9XxhdSucJsaW0iRitaQSvH4me5thcrlAxfbAlnWHFSNLr9DFzj42GlOVoYiu7c1lqXdNIcVXQ4OnNbq9tDyXuFiG6psC8fN9aJTc0AHj33dSCkILraUIZ2iQkp4uV9zQHsDnoNRUiigFEsQh0ee1RzQxqK16GK6YMDoYprAsw4s4MT3NLG6UfLAtljUq8V2T2CgU1QyhQJ2+OcB0Ks5IBh/lSZjZnT5CoNKNQe89CJ4nObx3LvxvQ/q95ElQvNoXrcNB7YSMH6wrXzhwkqKOJuCqqke2Q16BmwlYPz5QIaVFLnTGlKvcrjBoHfAygRbCRj/UA6+DJ8EVA7g9mXd2gzYSmgOz4G3gn3B+IA5CRKnE6RTnCeVbaE45Oje1mwHx1wvOkt42QuzDYY5CK4dvOyF0wo5RYH7H/+jGGHkxdNml1RN8QG/xYXSJkYiuQhyaU/Ab2SkKlv98CnNwSyMeJFyy14bi5PVXZNq48dFMhBNE2Eqk5MgF+6p6oiq8AzKLfeZGHyhYLDLzPB5isODFI/VusHZtDeSyhlK+Iv6IinySJhdpzzyKuWRB42P5XP5kxFyxOISK21uvPLQN5P2RdcLBhO2V3VynTzwzaThHWYXqoB35sRmbhRO2njZe/3555XukYTmYMI2dX1SSXnziGplce+uWw781oCW3n4ZSupRNSFLNW+1hFSbG/dn9WsbK92/2RJS9xNO2AJ5AyBd6N6f4erEszYWrb4NJfcdTtjQm9EksbJ41VQXPLMD0MIipqZ3eq1YcMKGanq+rlnd+MkOQNojf2lF8aLCCVMZ8grFCe0JTO4AtLIo6Z3IffjqJW8kRSwsSuwABEsqNpywIWA5nICmAyTDYYlf50RNlShHaWwAAAAASUVORK5CYII=';
        return avatar
    }

    static guardarPaginaActual() {
        var currentURL = window.location.href;
        sessionStorage.setItem('ultimaPaginaVisitada', currentURL);
    }

    static volverUltimaPagina() {
        var ultimaPaginaVisitada = sessionStorage.getItem('ultimaPaginaVisitada');

        if (ultimaPaginaVisitada !== null) {
            window.location.href = ultimaPaginaVisitada;
        } else {
            window.location.href = 'index.html';
        }
    }

    static borrarSession() {
        sessionStorage.clear();

    }

    static conexionBase() {
        return 'http://localhost:8080';
    }
}










