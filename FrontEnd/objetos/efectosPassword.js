export class EfectosPassword {
    static eyeball(event) {
        const eye = document.querySelectorAll('.eye');
        eye.forEach(function (eye) {
            let x = (eye.getBoundingClientRect().left) + (eye.clientWidth / 2);
            let y = (eye.getBoundingClientRect().top) + (eye.clientHeight / 2);
            let radian = Math.atan2(event.pageX - x, event.pageY - y);
            let rotation = (radian * (180 / Math.PI) * -1) + 90;
            eye.style.transform = "rotate(" + rotation + "deg)"

        });
    }


    // Función para cambiar el tipo de entrada del campo de contraseña y controlar los ojitos
    static togglePasswordVisibility(passwordInput, eyeContainer) {
        if (passwordInput.type === 'password') {
            passwordInput.type = 'text';
            eyeContainer.classList.add('open');
        } else {
            passwordInput.type = 'password';
            eyeContainer.classList.remove('open');
        }
    }

};
document.addEventListener('DOMContentLoaded', () => {
    const passwordInput = document.getElementById('password');
    const eyeContainer = document.getElementById('eye_container');


    // Evento para cambiar la visibilidad de la contraseña al hacer clic en los ojitos
    eyeContainer.addEventListener('click', () => {
        EfectosPassword.togglePasswordVisibility(passwordInput, eyeContainer);
    });

    // Evento para cerrar los ojitos cuando el usuario escribe en el campo de contraseña
    passwordInput.addEventListener('input', () => {
        eyeContainer.classList.remove('open');
    });

    document.body.addEventListener('mousemove', EfectosPassword.eyeball);

});
