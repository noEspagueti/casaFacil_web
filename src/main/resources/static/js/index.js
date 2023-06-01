

const profile = document.querySelector(".profile");
if (profile) {
    profile.addEventListener("click", (e) => {
        if (e.target.classList[0] !== "bi")
            return;
        let profileContain = profile.children[1];
        profileContain.classList.toggle("active");
    });
}