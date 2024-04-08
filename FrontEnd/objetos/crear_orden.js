import { Util } from './Util.js';
import { Ordenes } from './conexiones/Ordenes.js';

Util.existLogin();

//saludo al usuario
const saludo = document.querySelector('span');
saludo.innerText = Util.reuperarLogin().name ?? 'usuario';

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

const orden = new Ordenes(Util.reuperarLogin(),'.contenidoRecuadro');
orden.agregarAlFront();
