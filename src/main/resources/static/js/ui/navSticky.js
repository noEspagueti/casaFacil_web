const navBar = document.querySelector('.nav');
export function onHandleStickyNavbar() {
    window.addEventListener('scroll', () => {
        if (window.scrollY > 50) {
            navBar.classList.add("navbarSticky");
        } else {
            navBar.classList.remove("navbarSticky");
        }
    })
}