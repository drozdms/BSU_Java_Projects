function openLocation() {
    document.getElementById("tools").classList.toggle("show");
};


window.onclick = function(e) {
  if (!e.target.matches('.dropbtn')) {
    var myDropdown = document.getElementById("tools");
      if (myDropdown.classList.contains('show')) {
        myDropdown.classList.remove('show');
      }
  }
};


function mordor() {
	document.getElementById("content").style.backgroundImage =  "url('mordor.png')";
}

function shire() {
	document.getElementById("content").style.backgroundImage =  "url('background.png')";
}