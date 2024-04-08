import {datos} from './Datos.js';
import {datosOrdenes} from './datosOrdenes.js';
import { CartelPuntuar } from './cartelPuntuar.js';

const profecional1 = datos.orden();

for(let i=0; i<3; i++){
const nuevaOrden = new datosOrdenes(datos.orden(), datos.profesional());
nuevaOrden.agregarAlFront();
}


const cartel = new CartelPuntuar(datos.orden());
cartel.agregarALFront();
