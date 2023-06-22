
export function elementUI(theme) {
    const imgIntroduccion = document.getElementById("imgIntroduccion");
    if (imgIntroduccion) {
        imgIntroduccion.src = (theme === "light") ? "/public/home.png" : "/public/darkhome.png";
    }
    document.getElementById("logo").src = (theme === "light") ? "/public/logoweb-01.png" : "/public/logo.png";
    const iconTheme = (theme === "light") ? "dark_mode" : "light_mode";
    document.getElementById("switch_theme").innerHTML = `<span class="material-symbols-outlined">${iconTheme}</span>`;
}


    
export function changeTheme(element) {
    let classTheme = element.classList[0];
    if (classTheme === "light") {
        element.classList.replace("light", "dark");
    } else {
        element.classList.replace("dark", "light");
    }
    classTheme = (classTheme === "light") ? 'dark' : 'light';
    localStorage.setItem("tema", classTheme)
    document.documentElement.setAttribute('theme', localStorage.getItem("tema"));
    elementUI(localStorage.getItem("tema"))
}


