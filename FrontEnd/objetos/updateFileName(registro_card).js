// Función para actualizar el nombre del archivo seleccionado
function updateFileName() {
    const fileInput = document.getElementById('file-input');
    const fileNameContainer = document.getElementById('file-name-container');

    // Verificar si se seleccionó un archivo
    if (fileInput.files.length > 0) {
        // Mostrar el nombre del archivo seleccionado
        fileNameContainer.textContent = `Archivo: ${fileInput.files[0].name}`;

    } else {
        // Limpiar el contenido si no se seleccionó ningún archivo
        fileNameContainer.textContent = '';
    }
}

