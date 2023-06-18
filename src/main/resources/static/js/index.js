//SELECTORES CIUDAD / DISTRITOS
let selectorCiudad = document.getElementById("comboCiudad");


async function getFetch(url) {
    let options = {
        method: "GET",
        headers: {
            "Content-type": "application/json",
        }
    };
    const response = await fetch(url, options);
    const getData = response.json();
    return getData;
}

const postComponent = (publicacion) => {
    const publicacionContainer = document.querySelector(".publicacion_post_container");
    let ancord = document.createElement("a");
    ancord.classList.add("post_container");
    ancord.href = `/post/${publicacion.idPublicacion}`;

    //contenedores principales
    const contenedoresPrincipales = ["img_container", "description-container", "etiquetas"];
    contenedoresPrincipales.forEach(newContent => {
        let content = document.createElement("DIV");
        content.classList.add(newContent);
        ancord.appendChild(content);
        if (newContent === "img_container") {
            const imgPost = document.createElement("img");
            imgPost.src = `assets/${publicacion.rutaImg}`;
            content.appendChild(imgPost);
        }
        if (newContent === "description-container") {
            const contenido = ["titulo_description", "detalles_container"];
            contenido.map(item => {
                let contenidoBody = document.createElement("DIV");
                contenidoBody.classList.add(item);
                content.appendChild(contenidoBody);
                if (item === "titulo_description") {
                    let titulo = document.createElement("h1");
                    titulo.textContent = publicacion.titulo;
                    contenidoBody.appendChild(titulo);
                }
                if (item === "detalles_container") {
                    let ubicacion = document.createElement("p");
                    ubicacion.textContent = `${publicacion.ciudad} - ${publicacion.distrito.toLowerCase()}`;
                    let precio = document.createElement("p");
                    precio.textContent = `S/. ${new Intl.NumberFormat('en-US', {style: 'decimal'}).format(publicacion.precio)}`;
                    contenidoBody.append(ubicacion, precio);
                }
            });
        }
        if (newContent === "etiquetas") {
            let tipoPublicacion = document.createElement("span");
            tipoPublicacion.textContent = `${publicacion.tipoPublicacion}`;
            let tipoInmueble = document.createElement("span");
            tipoInmueble.textContent = `${publicacion.tipoInmueble}`;
            content.append(tipoPublicacion, tipoInmueble);
        }
    });
    publicacionContainer.append(ancord);
};



const getPost = (urlPost) => {
    getFetch(urlPost).then(res => {
        let cantidadPost = document.querySelector(".cantidad_post");
        cantidadPost.textContent = `Cantidad de resultados: ${res.length}`;
        res.map(post => {
            postComponent(post);
        });
    });
};


const changeDistrito = () => {
    const publicacionContainer = document.querySelector(".publicacion_post_container");
    const URL_POST = "http://localhost:8050/api/publicacion/ciudad/" + selectorCiudad.value;
    while (publicacionContainer.firstChild) {
        publicacionContainer.removeChild(publicacionContainer.firstChild);
    }
    //Mostrar post por ciudad
    getPost(URL_POST);

};






//RECARGAR TODOS LOS ELEMENTOS DEL DOCUMENT
document.addEventListener("DOMContentLoaded", () => {
    changeDistrito();
    selectorCiudad.addEventListener("change", changeDistrito);

    let fade = document.querySelectorAll(".pause");
    if (document.documentElement.scrollTop >= 0) {

        fade.forEach(item => {
            item.style.opacity = 1;
            item.classList.replace("pause", "fade_left");
        });

        let introduccionDescription = document.querySelector(".description_introduccion");
        let introduccionTitulo = document.querySelector(".titulo_introduccion");
        let introduccionImg = document.querySelector(".img_container_introduccion");

        document.addEventListener("scroll", () => {
            introduccionDescription.style.transform = `translateX(-${document.documentElement.scrollTop}px)`;
            introduccionTitulo.style.transform = `translateX(${document.documentElement.scrollTop}px)`;
            introduccionImg.style.transform = `translateX(${document.documentElement.scrollTop}px)`;
        });
    }
});




