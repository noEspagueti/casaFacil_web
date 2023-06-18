//PROFILE
const profile = document.querySelector(".profile");
if (profile) {
    profile.addEventListener("click", (event) => {
        if (event.target.classList[0] !== "bi")
            return;
        let profileContain = profile.children[1];
        profileContain.classList.toggle("active");
    });
}

const btnComprar = document.querySelector('.btnComprar');
if (btnComprar) {
    btnComprar.addEventListener("click", () => {
        document.querySelector(".deploy_section_comprar").classList.toggle("active");
    });
}

const btnAlquilar = document.querySelector('.btnAlquilar');
if (btnAlquilar) {
    btnAlquilar.addEventListener("click", () => {
        document.querySelector(".deploy_section_alquilar").classList.toggle("active");
    });
}