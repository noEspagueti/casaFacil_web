let area = document.querySelector('.area');

export function onFile() {
    let onUpload = () => {
        let file = upload.files[0];
        if (file) {
            let nameFile = file.name;
            area.classList.replace('area', 'upload');
            document.querySelector('.nombre_imagen').textContent = nameFile;
        }
    }
    upload.addEventListener('change', onUpload);
}


