/**
 * JAVASCRIPT
 */

const option = {
    method: "GET",
    headers: {
        "Content-type": "application/json",
        "Authorization": "Lbqajb4cH6RIBdvTbvUd5pjDudAKXpdv3IpIAYEf0b0MPH7XiRY8QwmD"
    }
}


let imgURL = document.querySelector(".principalContainer");
fetch("https://api.pexels.com/v1/search?query=living%20room&width=1000", option)
        .then(response => response.json())
        .then(({ photos }) => {
            imgURL.style.backgroundImage = `url(${photos[1].src["original"]})`;
        })

