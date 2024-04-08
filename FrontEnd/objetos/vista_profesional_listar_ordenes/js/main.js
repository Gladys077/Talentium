import {datos} from './Datos.js';
import {Ordenes} from './Ordenes.js';

const profecional1 = datos.orden();

for(let i=0; i<10; i++){
const nuevaOrden = new Ordenes(datos.orden(), datos.profesional());
nuevaOrden.agregarAlFront();
}


//alert(profecional1.description)

