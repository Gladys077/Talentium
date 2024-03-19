import {Util} from './Util.js'

Util.existLogin();

if(Util.reuperarImagen() !== null){
const imagen = Util.reuperarImagen() ;

const logoUser = document.querySelector('#logoUser');
logoUser.src = imagen;
}

const volverbutton = document.querySelector('#volverbutton');
volverbutton.addEventListener('click', ()=> Util.volverUltimaPagina());

const cerrarSesion = document.querySelector('#cerrarSesion');
cerrarSesion.addEventListener('click', ()=> Util.borrarSession() );