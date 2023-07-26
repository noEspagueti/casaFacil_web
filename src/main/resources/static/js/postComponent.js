//SELECTORES CIUDAD / DISTRITOS
let selectorCiudad = document.getElementById("comboCiudad");

async function getFetch(url) {
    let options = {
        method: "GET",
        headers: {
            "Content-type": "application/json"
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
    const contenedoresPrincipales = ["img_container", "description-container"];
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
            const contenido = ["precio", "titulo_description", "detalles_container"];
            contenido.map(item => {
                let contenidoBody = document.createElement("DIV");
                contenidoBody.classList.add(item);
                content.appendChild(contenidoBody);
                if (item === "precio") {
                    let precio = document.createElement("p");
                    precio.textContent = `S/. ${new Intl.NumberFormat('en-US', { style: 'decimal' }).format(publicacion.precio)}`;
                    contenidoBody.appendChild(precio);
                }
                if (item === "titulo_description") {
                    let titulo = document.createElement("p");
                    titulo.textContent = (publicacion.titulo).toUpperCase();
                    contenidoBody.appendChild(titulo);
                }
                if (item === "detalles_container") {
                    let ubicacion = document.createElement("p");
                    ubicacion.textContent = `${publicacion.ciudad}`;
                    contenidoBody.append(ubicacion);
                }
            });
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


export function changeDistrito() {
    const publicacionContainer = document.querySelector(".publicacion_post_container");
    const URL_POST = "http://localhost:8050/api/publicacion/ciudad/" + selectorCiudad.value;
    while (publicacionContainer.firstChild) {
        publicacionContainer.removeChild(publicacionContainer.firstChild);
    }
    //Mostrar post por ciudad
    getPost(URL_POST);
}
