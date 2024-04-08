document.addEventListener("DOMContentLoaded", function () {
    const clienteToggle = document.getElementById("clienteToggle");
    const modalCliente = document.querySelector(".modal_cliente");
    const closeModalButton = modalCliente.querySelector(".close");

    clienteToggle.addEventListener("click", function () {
        modalCliente.classList.toggle("modal_cliente_visible");
    });

    closeModalButton.addEventListener("click", function () {
        modalCliente.classList.remove("modal_cliente_visible");
    });
});

document.addEventListener("DOMContentLoaded", function () {
    const profesionalToggle = document.getElementById("profesionalToggle");
    const modalProfesional = document.querySelector(".modal_profesional");
    const closeModalButton = modalProfesional.querySelector(".close");

    profesionalToggle.addEventListener("click", function () {
        modalProfesional.classList.toggle("modal_profesional_visible");
    });

    closeModalButton.addEventListener("click", function () {
        modalProfesional.classList.remove("modal_profesional_visible");
    });
});