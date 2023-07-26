let pathName = window.location.pathname;

const parentOption = document.querySelector('.option_contain');
const options = document.querySelectorAll('.options');
const newContent = document.querySelector('.update_content');



parentOption.addEventListener('click', (event) => {
    event.preventDefault();
    if (event.target.classList[0] === 'option_contain') return;
    document.querySelectorAll('.select').forEach(itemDelete => itemDelete.classList.remove('select'));
    newContent.innerHTML = '';
    event.target.classList.add('select');
    const targetPath = event.target.href.split('/'); // separando la ruta de href de los ancords
    window.history.pushState({ page: targetPath[targetPath.length - 1] }, '', `/${targetPath[targetPath.length - 1]}`);//cambiar la ruta sin recargar la página
    updateContent(targetPath[targetPath.length - 1]);
});


options.forEach(item => {
    if (item.textContent.includes(pathName.replace('/', ''))) {
        item.classList.add('select')
        updateContent(pathName.replace('/', ''));
    }
});


function updateContent(path) {
    switch (path) {
        case 'publicaciones':
            publicacion(newContent);
            break;

        case 'contactos':
            contactos(newContent);
            break;
        default:
            break;
    }
}



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




function publicacion(insertToParent) {
    let content = document.createElement('DIV');
    content.classList.add('mis_Publicaciones');
    getFetch("http://localhost:8080/repertorio/publicaciones").then(response => {
        content.innerHTML = response;
    });
    insertToParent.appendChild(content);
}



function contactos(insertToParent) {
    let content = document.createElement('DIV');
    content.classList.add('mis_contactos');
    getFetch("http://localhost:8080/repertorio/contactos").then(response => {
        content.innerHTML = response;
    });
    insertToParent.appendChild(content);
}