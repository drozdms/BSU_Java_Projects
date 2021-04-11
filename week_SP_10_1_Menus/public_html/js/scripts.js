
document.querySelectorAll(".nav-link").forEach(function(e) {
    e.addEventListener("pointerenter", function(event) {
        var targetElement = event.target || event.srcElement;
        targetElement.style.backgroundImage =  "url('img/shire.png')";
    });
    e.addEventListener("pointerleave", function(event) {
        var targetElement = event.target || event.srcElement;
        targetElement.style.backgroundImage =  "url('img/eye.png')";
    });
});