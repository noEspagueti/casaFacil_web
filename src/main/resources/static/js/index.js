import { changeDistrito } from './postComponent.js';
import { changeTheme, elementUI } from './changeTheme.js';
import { onFile } from './uploadImages.js';
import { onHandleStickyNavbar } from './ui/navSticky.js';

//CIUDAD / DISTRITOS
const selectorCiudad = document.getElementById('comboCiudad');
//CAMBIAR THEME COLOR
const themeSwitch = document.getElementById('switch_theme');
//PERFIL LOGING
const profile = document.querySelector('.profile');
//BOTON COMPRAR NAVBAR
const btnComprar = document.querySelector('.btnComprar');
//BOTON ALQUILAR NAVBAR
const btnAlquilar = document.querySelector('.btnAlquilar');

let upload = document.getElementById('upload');



function onFadeIntroduccion() {
    let fade = document.querySelectorAll('.pause');
    fade.forEach(item => {
        item.style.opacity = 1;
        item.classList.replace('pause', 'fade_left');
    });
}

const onEventClik = (element, callBack) => {
    if (element) {
        element.addEventListener('click', callBack);
    }
};


//RECARGAR TODOS LOS ELEMENTOS DEL DOCUMENT
window.addEventListener('DOMContentLoaded', () => {

    if (profile) {
        profile.addEventListener('click', (event) => {
            if (event.target.classList[0] !== 'bi')
                return;
            let profileContain = profile.children[1];
            profileContain.classList.toggle('active');
        });
    }

    onFadeIntroduccion();

    onEventClik(btnComprar, (event) => {
        document.querySelector('.deploy_section_comprar').classList.toggle('active');
    });

    onEventClik(btnAlquilar, () => {
        document.querySelector('.deploy_section_alquilar').classList.toggle('active');
    });

    let theme = localStorage.getItem('tema') ? localStorage.getItem('tema') : themeSwitch.classList[0];
    onEventClik(themeSwitch, () => changeTheme(themeSwitch));
    document.documentElement.setAttribute('theme', theme);

    elementUI(theme);

    if (selectorCiudad) {
        changeDistrito();
        selectorCiudad.addEventListener('change', changeDistrito);
    }

    if (upload) {
        onFile();
    }
    onHandleStickyNavbar();
});




