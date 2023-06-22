
export function callModal(element, modal, background) {
    element.onclick = function () {
        background.classList.toggle("blur");
        modal.classList.toggle("blockModal");
    };
}


export function closeModal(element, modal, background) {
    element.onclick = function (event) {
        background.classList.toggle("blur");
        modal.classList.toggle("blockModal");
    };
}