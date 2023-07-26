const navBar = document.querySelector('.nav');
export function onHandleStickyNavbar() {
    window.addEventListener('scroll', () => {
        if (window.scrollY > 50) {
            navBar.style.background = "white"
            navBar.style.boxShadow = "0px 10px 15px - 12px rgba(0, 0, 0, 0.1)"
        } else {
            navBar.style.background = ""
        }
    })
}