
var getNewPersonne=function(){
    let requete=new XMLHttpRequest();
    requete.open("POST","inscription",true);
    requete.responseType="text";
    Pseudo=document.getElementById("Pseudo");
    let repPseudo=Pseudo.value;
    Login=document.getElementById("Login");
    let repLogin=Login.value;
    Password=document.getElementById("Password");
    let repPassword=Password.value;
    requete.onload=function (){

    }
    requete.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    requete.send(repPseudo);
    requete.send(repLogin);
    requete.send(repPassword);
}

var getPersonne=function(){
    let requete=new XMLHttpRequest();
    requete.open("POST","connection",true);
    requete.responseType="text";
    var Login=document.getElementById("Login");
    let repLogin=Login.innerText;
    var Password=document.getElementById("Password");
    let repPassword=Password.innerText;
    requete.onload=function (){

    }
    requete.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    requete.send(repLogin);
    requete.send(repPassword);
}
