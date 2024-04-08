import { Util } from './Util.js'
import { Client } from './conexiones/Client.js'
import { cartelAviso } from './cartel_aceptar_cancelar/cartelAviso.js';

//Util.existLogin();

//saludo al usuario
const saludo = document.querySelector('span');
saludo.innerText = Util.reuperarLogin().name ?? 'usuario';

//guardar pagina actual para poder volver con la flecha
const buttons = document.querySelectorAll('li');
buttons.forEach(li => li.addEventListener('click', () => Util.guardarPaginaActual()));

const volverbutton = document.querySelector('#volverbutton');
volverbutton.addEventListener('click', () => Util.volverUltimaPagina());

if (Util.reuperarImagen() !== null) {
    const imagen = Util.reuperarImagen();

    const logoUser = document.querySelector('#logoUser');
    logoUser.src = imagen;
}


const inputs = document.querySelectorAll('input');
inputs[2].value = Util.reuperarLogin().name;
inputs[3].value = 'xxxxx';
inputs[4].value = Util.reuperarLogin().dni;
inputs[5].value = Util.reuperarLogin().phone;
inputs[6].value = Util.reuperarLogin().direction.street;
inputs[7].value = Util.reuperarLogin().direction.number;
inputs[8].value = Util.reuperarLogin().direction.province;
inputs[9].value = Util.reuperarLogin().direction.location;


const actualizarPerfil = document.querySelector('.boton_centrado');

actualizarPerfil.addEventListener('click', () => {
    const cliente = new Client(Util.reuperarLogin().name, Util.reuperarLogin().lastname, inputs[4].value,
        inputs[5].value, Util.reuperarLogin().user.id,
        inputs[6].value, inputs[7].value, inputs[8].value, inputs[9].value);

  //verificando que los campos no esten vacios
    if (Util.reuperarLogin().name !=='' && Util.reuperarLogin().lastname!=='' &&  inputs[4].value !=='' && 
    inputs[5].value !=='' &&  Util.reuperarLogin().user.id !=='' && 
    inputs[6].value !=='' &&  inputs[7].value !=='' &&  inputs[8].value !=='' &&  inputs[9].value!=='') {
        cliente.acrualizarCliente();
    } else {
        new cartelAviso('Los campos deben estar todos completos', 'h2');
    }


});




const cerrarSesion = document.querySelector('#cerrarSesion');
cerrarSesion.addEventListener('click', () => Util.borrarSession());