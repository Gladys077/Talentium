import { User } from './conexiones/user.js';
import { cartelAviso } from './cartel_aceptar_cancelar/cartelAviso.js';
import { Util } from './Util.js';
import { EfectosPassword } from './efectosPassword.js';


const registrarse = document.querySelector('.login_button');

registrarse.addEventListener('click',  () => {
  
  const inputs = document.querySelectorAll('input');
  const a = document.querySelector('#login');


  if (inputs[0].value != '' && inputs[1].value != '') {

    const userCreado = new User(inputs[0].value, inputs[1].value);

     userCreado.login();
    

    
    //alert(Util.reuperarUsuario());


  } else {
    new cartelAviso('Los campos email y contraseña no deben estar vacíos')

  }

});



const volverbutton = document.querySelector('#volverbutton');
volverbutton.addEventListener('click', ()=> Util.volverUltimaPagina());




