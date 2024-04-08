import { Util } from './Util.js'
import { cartelAviso } from './cartel_aceptar_cancelar/cartelAviso.js';
import { Professional } from './conexiones/Professional.js';


//try {

Util.existLogin();

//saludo al usuario
const saludo = document.querySelector('span');
saludo.innerText = Util.reuperarLogin().name ?? 'usuario';

//datos de los input
const inputs = document.querySelectorAll('input');
const especialidad = document.querySelector('#especialidad');
inputs[2].value = Util.reuperarLogin().name;
inputs[3].value = 'XXXXXXX';
inputs[4].value = Util.reuperarLogin().cuit;
inputs[5].value = Util.reuperarLogin().phone;
inputs[6].value = Util.reuperarLogin().direction.street;
inputs[7].value = Util.reuperarLogin().direction.number;
inputs[8].value = Util.reuperarLogin().direction.province;
inputs[9].value = Util.reuperarLogin().direction.location;
inputs[10].value = Util.reuperarLogin().cuit;
especialidad.value = Util.reuperarLogin().profession;

//guardar pagina actual para poder volver con la flecha
const buttons = document.querySelectorAll('li');
buttons.forEach(li => li.addEventListener('click', () => Util.guardarPaginaActual()));

const volverbutton = document.querySelector('.volver');
volverbutton.addEventListener('click', () => Util.volverUltimaPagina());

if (Util.reuperarImagen() !== null) {
    const imagen = Util.reuperarImagen();

    const logoUser = document.querySelector('#logoUser');
    logoUser.src = imagen;
}

const cerrarSesion = document.querySelector('.cerrar');
cerrarSesion.addEventListener('click', () => Util.borrarSession());




const actualizarPerfil = document.querySelector('.boton_centrado');

actualizarPerfil.addEventListener('click', () => {


    let professional = new Professional(Util.reuperarLogin().id, Util.reuperarLogin().name, Util.reuperarLogin().lastname, inputs[4].value,
        inputs[5].value,
        inputs[6].value, inputs[7].value, inputs[8].value, inputs[9].value, inputs[10].value, especialidad.value);


    //verificando que los campos no esten vacios
    if (Util.reuperarLogin().id !== '' && Util.reuperarLogin().name !== '' && Util.reuperarLogin().lastname !== '' && inputs[4].value !== '' &&
        inputs[5].value !== '' &&
        inputs[6].value !== '' && inputs[7].value !== '' && inputs[8].value !== '' && inputs[9].value !== '' && inputs[10].value !== '' && especialidad.value !== '') {
        professional.acrualizarProfessional();
    } else {
        new cartelAviso('Los campos deben estar todos completos', 'h2');
    }
});

/*
} catch (error) {
    new cartelAviso('Ups!! algo salio mal', 'h2');
}*/
