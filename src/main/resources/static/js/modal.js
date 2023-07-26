const callToModal = document.getElementById('callToContactar');
const callToCancel = document.getElementById('buttonCancelar');
const modal = document.querySelector('.modal_contactar');

callToModal.onclick = function () {
    document.querySelector('.container_post').classList.toggle("blur");
    modal.classList.toggle("blockModal");
};


callToCancel.onclick = function (event) {
    document.querySelector('.container_post').classList.toggle("blur");
    modal.classList.toggle("blockModal");
};



