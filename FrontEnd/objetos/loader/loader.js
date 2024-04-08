window.onload = () => {
    let contenido = document.querySelector("#registro_opciones");
    let loading = document.querySelector(".loader");
    setTimeout(()=>{
        loading.style.display = 'none';
        contenido.style.display ='block';

    }, 2000)
}