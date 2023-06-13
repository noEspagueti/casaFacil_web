
/* global response */

//PROFILE
const profile = document.querySelector(".profile");
if (profile) {
    profile.addEventListener("click", (e) => {
        if (e.target.classList[0] !== "bi")
            return;
        let profileContain = profile.children[1];
        profileContain.classList.toggle("active");
    });
}



//SELECTORES CIUDAD / DISTRITOS

const selectorCiudad = document.getElementById("comboCiudad");

let selectorDistrito = document.getElementById("comboDistrito");




//Metodo para realizar peticiones al servidor

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


const getDistrito = () => {
    let option = "";
    const URL_DISTRITOS = "http://localhost:8050/api/publicacion/ciudadDistrito/" + selectorCiudad.value;
    while (selectorDistrito.firstChild) {
        selectorDistrito.removeChild(selectorDistrito.firstChild);
    }
    getFetch(URL_DISTRITOS).then(res => {
        let select = res[selectorCiudad.value];
        console.log(select);
        select.map(item => {
            option = document.createElement("option");
            option.value = item;
            option.text = item;
            selectorDistrito.append(option);
            option = null;
        });
    });



};

