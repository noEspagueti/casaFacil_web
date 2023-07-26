const tipoInmueble = document.querySelector('select[name="tipoInmueble"]');
const data = ['Casa', 'Departamento', 'Terreno'];



tipoInmueble.addEventListener('change', () => {
    if (tipoInmueble.value === null) return;
    data.forEach(item => {
        if (item === tipoInmueble.value) {
            updateContent(item);
        }
    });
});


async function getFetch(url) {
    let options = {
        method: "GET",
        headers: {
            "Content-type": "text/html"
        }
    };
    const response = await fetch(url, options);
    const getData = response.text();
    return getData;
}


const updateContent = (data) => {
    getFetch(`http://localhost:8080/repertorio/${data}`).then(response => {
        document.querySelector('.update_content').innerHTML = response;
    });
};


updateContent(tipoInmueble.value);