import { User } from './conexiones/user.js';
import { cartelAviso } from './cartel_aceptar_cancelar/cartelAviso.js';
import { Util } from './Util.js';
import { EfectosPassword } from './efectosPassword.js';

// acción para sacar la imagen
const fileInput = document.getElementById('file-input');
fileInput.addEventListener('change',  ()=> Util.updateFileName());
fileInput.addEventListener('change', ()=> Util.guardarImagen64(fileInput));






const registrarse = document.querySelector('.login_button');

registrarse.addEventListener('click',  () => {

  const inputs = document.querySelectorAll('input');



  if (inputs[0].value != '' && inputs[1].value != '') {

    const userCreado = new User(inputs[0].value,inputs[1].value, Util.reuperarImagen());

    userCreado.conexionApi();
  
  } else {
    new cartelAviso('Los campos email y contraseña no deben estar vacíos')

  }

});







