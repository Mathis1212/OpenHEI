window.onload=function(){

    //prend pour élément la navbar d'id "myTopnav"
    navbar = document.getElementById("myTopnav");
    /*sticky indique la position de l'élément navbar par rapport au haut de la page */
    //sticky = navbar.offsetTop;

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
/* Récuperation de l'url du cours à delete et appel de la fonction deleteCours()*/
    var button_delete_cours=document.getElementsByClassName("deletebtn");
    for (let delbutton of button_delete_cours) {
        delbutton.onclick=function (){
            var url=getUrlValue(delbutton);
            deleteCours(url);
        }
    }

    var Isdel_button=document.getElementsByClassName("delete");
    for (let delButton of Isdel_button){
        delButton.onclick = function(){
            var idS=delButton.parentElement.previousElementSibling.previousElementSibling.previousElementSibling.getAttribute("id");
            console.log(idS);
            supUsager(idS);
        }
    }
/*Recuperation de l'id du l'user associe au bouton setAdim et appel de la fonction setAdim()*/

    var IsAdmin_button=document.getElementsByClassName("admin");
    for (let AdminButton of IsAdmin_button){
        AdminButton.onclick = function(){
            var id=AdminButton.parentElement.previousElementSibling.previousElementSibling.previousElementSibling.previousElementSibling.getAttribute("id");
            console.log(id);
            setAdmin(id);
        }
    }
/* Récuperation de l'url de l'ancien cours à update + l'url du nouveau cours + le nom du nouveau cours et appel de la fonction "updateCours()*/
    var button_update_cours=document.getElementsByClassName("updatebtn");
    for (let updatebutton of button_update_cours) {
        updatebutton.onclick = function () {
            var url_oldcours = getUpdateUrlValue(updatebutton);
            var url_newcours = updatebutton.previousElementSibling.previousElementSibling.value;
            let nom_newcours = updatebutton.previousElementSibling.value;
        if(verif1()==true) {
            updateCours(url_oldcours, url_newcours, nom_newcours);
        }else{
            alert("Veuillez remplir tout les champs pour mettre à jour un cours");
        }
        }
    }

    var suivit_cours=document.getElementsByClassName("suivit");
    for (let suivit of suivit_cours) {
        //clique de chaque élément de follow
        suivit.onclick=function (){
            var nom_mat=suivit.previousElementSibling.innerText;
            suiviCours(suivit,nom_mat);
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

/* Fonction qui affiche la liste des cours*/
function showListCours(id) {
    document.getElementById(id).classList.toggle("show");
}

/* Fonction avec requete AJAX qui envoie l'url + le nom + la matière du cours à ajouter au back*/
function addCour(){
    let request = new XMLHttpRequest();
    request.open("POST", "/admin/ThemesAdmin", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    let nom=document.getElementById("nom_add").value;
    let  url=document.getElementById("url_add").value;
    let nomM=document.getElementById("mat_add").value;
    request.send("nom_cour=" + nom+ "&url_cour=" +url + "&nom_mat=" + nomM);
}

/* Fonction avec requete AJAX qui envoie l'url du cours à modifier + l'url du nouveau cours + le nom du nouveau cours au back*/
let updateCours = function (url_oldcours, nom_cours, url_cours) {
    console.log(url_oldcours);
    console.log(nom_cours);
    console.log(url_cours);
    let updateRequest = new XMLHttpRequest();
    updateRequest.open("POST", "/admin/ThemesAdmin", true);
    updateRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    updateRequest.onload = function () {
        if(this.status === 200) {
            console.log("Requete envoyé")
        } else {
            console.log("Echec de la requete")
        }
    }
    updateRequest.send("urlcoursToUpdate="+url_oldcours+ "&NewNomcours="+nom_cours+ "&NewUrlcours="+url_cours);
}

/* Fonction avec requete AJAX qui envoie l'url du cours à supprimer au back*/
let deleteCours = function (url) {
    let deleteRequest = new XMLHttpRequest();
    deleteRequest.open("POST", "/admin/ThemesAdmin", true);
    deleteRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    console.log("url du cours :" +url);
    deleteRequest.onload = function () {
        if(this.status === 200) {
            console.log("Requete envoyé")
        } else {
            console.log("Echec de la requete")
        }
    }
    deleteRequest.send("urlcoursToDelete="+url);
}
/*envoye l'id de l'user a change status */
let setAdmin = function(id){
    let setRequest = new XMLHttpRequest();
    setRequest.open("POST","/admin/GestionAdmin",true);
    setRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    console.log("id set:");
    console.log(id);
    setRequest.send("id_user="+id);
}
/*envoye le login de l'usager a supprimer*/
let supUsager = function(id){
    let setRequest = new XMLHttpRequest();
    setRequest.open("POST","/admin/Gestiondel",true);
    setRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    console.log("id sup:");
    console.log(id);
    setRequest.send("id_sup="+id);
}


//Requete AJAX pour le suivi d'un cours
let addMatiereToUser = function (nom_mat) {
    let addMatiereRequest = new XMLHttpRequest();
    addMatiereRequest.open("POST", "/Profil", true);
    addMatiereRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    console.log("id de la matiere :" +id);
    addMatiereRequest.onload = function () {
        if(this.status === 200) {
            console.log("Requete envoyé")
        } else {
            console.log("Echec de la requete")
        }
    }
    addMatiereRequest.send("id_mat="+id);
}



/* Fonction qui recupère l'url du cours à delete*/
function getUrlValue(bouton){
    var url=bouton.parentElement.previousElementSibling.previousElementSibling.getAttribute("href");
    return url;
}

/* Fonction qui recupère l'url du cours à update */
function getUpdateUrlValue(bouton){
    var url_oldcours=bouton.parentElement.parentElement.previousElementSibling.getAttribute("href");
    return url_oldcours;
}

/* Fonction qui verifie que les champs pour l'update d'un cours ne sont pas vident*/
function verif1()
{
    result=false;
    var newnomcours = document.getElementById("nomupdate").value;
    var url    = document.getElementById("urlupdate").value;

    if((newnomcours!="")&&(url!="")){
        result=true;
    }else{
        result= false;
    }
    return result;
}

/* Fonction qui permet le filtre des themes*/
function filterThemes() {
    //déclaration des variables
    var input, filter,classname, h4, i, txtValue;
    input = document.getElementById("site-search");
    filter = input.value.toUpperCase();
    classname=document.getElementsByClassName("liste_block");

    // boucle pour lister les bons résultats et cacher les autres
    for (i = 0; i < classname.length; i++) {
        h4 = classname[i].getElementsByTagName("h4")[0];
        txtValue = h4.textContent || h4.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            classname[i].style.display = "";
        } else {
            classname[i].style.display = "none";
        }
    }
}




/*Fonction de follow et d'unfollow des cours*/
function suiviCours(elt, nom_mat){
    if (elt.classList.contains("follow")){
        elt.classList.remove("follow");
        elt.classList.add("unfollow");
        console.log("matiere a ajouter : " +nom_mat);
        //requete AJAX de suivi de cours
        addMatiereToUser(nom_mat);
    }else if (elt.classList.contains("unfollow")) {
        elt.classList.remove("unfollow");
        elt.classList.add("follow");
        //requete AJAX d'unfollow de cours
    }
}



