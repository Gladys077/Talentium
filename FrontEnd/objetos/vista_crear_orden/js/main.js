import {datos} from './Datos.js';
import {Ordenes} from './Ordenes.js';

const body = document.querySelector('body');

//console.log(datos.cliente());

//console.log(datos.profesional());

const nuevaOrden = new Ordenes(datos.cliente());
nuevaOrden.agregarAlFront();

