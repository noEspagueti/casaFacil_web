let pathName = window.location.pathname;

const parentOption = document.querySelector('.option_contain');
const options = document.querySelectorAll('.options');
const newContent = document.querySelector('.update_content');
const dniTargetUser = document.querySelector('.dniUsuario').textContent;


parentOption.addEventListener('click', (event) => {
    event.preventDefault();
    document.querySelectorAll('.select').forEach(itemDelete => itemDelete.classList.remove('select'));
    if (event.target.classList[0] === 'option_contain') return;
    event.target.classList.add('select');
    const targetPath = event.target.href.split('/');
    console.log(targetPath[targetPath.length - 1]);
    window.history.pushState({ page: targetPath[targetPath.length - 1] }, '', `/${targetPath[targetPath.length - 1]}`);

});


options.forEach(item => {
    if (item.textContent.includes(pathName.replace('/', ''))) {
        item.classList.add('select')
        updateContent(pathName.replace('/', ''));
    }
})




function updateContent(path) {
    switch (path) {
        case 'publicaciones':
            publicacion(newContent);
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
    getFetch("http://localhost:8080/me/publicaciones").then(response => {
        content.innerHTML = response;
    });
    insertToParent.appendChild(content);
}