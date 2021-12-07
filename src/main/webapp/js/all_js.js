window.onload=function(){

    //prend pour élément la navbar d'id "myTopnav"
    navbar = document.getElementById("myTopnav");
    /*sticky indique la position de l'élément navbar par rapport au haut de la page */
    sticky = navbar.offsetTop;

    //Permet de récuper un Array de tous les liens de la navbar
    lien_navbar=navbar.querySelectorAll("a");
    //Pour chaque lien du tableau on itère dessus
    for(let lien of lien_navbar){
        //Sur le click gagne la classe "active"
        lien.onclick=function(){
            lien.classList.add("active");
        }
        //Qd perd le focus perd la classe "active"
        lien.onblur=function(){
            lien.classList.remove("active");
        }
    }

    //Sélectionne l'élément d'id "menuBar"
    button_menu=document.getElementById("menuBar");
    button_menu.onclick=function(){
        //Sur le click de l'élément d'id "menuBar", éxécute la fonction
        showAllNavBarElement();
    };

    //Pendant le scroll de la fenêtre, éxécute la fonction
    window.onscroll = function() {
        StickNavbarMenu();
    };
    /*
        // Close the dropdown if the user clicks outside of it
        window.onclick = function(event) {
            if (!event.target.matches('.search-dropbtn')) {
                var dropdowns = document.getElementsByClassName("search-content");
                var openDropdown = dropdowns[0];
                if (openDropdown.classList.contains('show-searchbar')&&!document.getElementById("testid")) {
                    openDropdown.classList.remove('show-searchbar');
                }
            }
        }

        //Permet d'afficher le champ de recherche au click sur le bouton
        search_button=document.getElementsByClassName("search-dropbtn");
        search_button.onclick = function() {
            ShowingSearchBarOnClick();
        }

        //Permet de récuper la search bar
        searchbar=navbar.querySelector("input");
        //Sur le click gagne la classe "active"
        searchbar.onclick=function(){
            searchbar.classList.add("active");
        }
        //Qd perd le focus perd la classe "active"
        searchbar.onblur=function(){
            searchbar.classList.remove("active");
        }*/


    //liste cours
    // Close the dropdown if the user clicks outside of it
    window.onclick = function(event) {
        if (!event.target.matches('.dropbtn')) {
            var dropdowns = document.getElementsByClassName("dropdown-content");
            var i;
            for (i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')&&event.target.matches('.dropdown-content')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }

    var button_delete_cours=document.getElementsByClassName("deletebtn");
    for (let delbutton of button_delete_cours) {
        delbutton.onclick=function (){
            deleteCours();
        }
    }

    var button_update_cours=document.getElementsByClassName("updatebtn");
    for (let updatebutton of button_update_cours) {
        updatebutton.onclick = function () {
            updateCours();
        }

    }
}


/*--Functions--*/


/*Permet d'afficher toute la barre de navigation
avec l'ajout de la classe responsive*/
function showAllNavBarElement() {
    if (navbar.className === "topnav"||navbar.className === "topnav sticky") {
        /*Ajoute la classe responsive a l'objet "myTopnav"*/
        navbar.classList.add("responsive");
    } else {
        navbar.classList.remove("responsive");
    }
}

/*Permet d'afficher toute la barre de navigation
lorsque l'on scroll plus que la disatnce a laquelle se situe la navbar*/
function StickNavbarMenu() {
    /*window.pageYOffset indique le nombre de pixel scroller sur l'axe vertical*/
    if (window.pageYOffset >= sticky) {
        navbar.classList.add("sticky")
    } else {
        navbar.classList.remove("sticky");
    }
}

/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function ShowingSearchBarOnClick() {
    document.getElementById("myDropdown").classList.toggle("show-searchbar");
}

function showListCours(id) {

    document.getElementById(id).classList.toggle("show");
}

function addCour(){

    let request = new XMLHttpRequest();
    request.open("POST", "/Theme/admin/add", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    let nom=document.getElementById("nom_add").value;
    let  url=document.getElementById("url_add").value;
    let nomM=document.getElementById("mat_add").value;
    request.send("nom_cour=" + nom+ "&url_cour=" +url + "&nom_mat=" + nomM);
}

let updateCours = function () {
    let updateRequest = new XMLHttpRequest();
    updateRequest.open("POST", "admin/ThemeAdmin/update", true);
    let idcours = document.querySelectorAll("cours h5");
    for (var id_cours of idcours){
        id_cours=idcours.getAttribute("value");
    }
    let nomcours = document.querySelectorAll("nom_update");
    for (var nom_cours of nomcours){
        nom_cours=nomcours.getValue();
    }
    let urlcours = document.querySelectorAll("url_update");
    for (var url_cours of urlcours){
        url_cours=urlcours.getValue();
    }

    updateRequest.onload = function () {
        if(this.status === 200) {
            console.log("Requete envoyé")
        } else {
            console.log("Echec de la requete")
        }
    }
    updateRequest.send("idcours="+idcours, "nom_cours="+nom_cours, "url_cours="+url_cours);
}


let deleteCours = function () {
    let deleteRequest = new XMLHttpRequest();
    deleteRequest.open("POST", "admin/ThemeAdmin/delete", true);
    let cours = document.querySelectorAll("cours h5");
    for (var idcours of cours){
        idcours=cours.getAttribute("value");
    }
    deleteRequest.onload = function () {
        if(this.status === 200) {
            console.log("Requete envoyé")
        } else {
            console.log("Echec de la requete")
        }
    }
    deleteRequest.send("id="+idcours);
}
