var getPseudo=function(){
    let requete=new XMLHttpRequest();
    requete.open("POST","inscription",true);
    requete.responseType="text";
    var Pseudo=document.getElementById("Pseudo");
    response=Pseudo.innerText;

        window.onload=function(){
            if (this.status===200){
            }else{
                console.log("échec de la requête");
            }
        }
    requete.send(response);
    }




var getLogin=function(){
    let requete=new XMLHttpRequest();
    requete.open("POST","inscription",true);
    requete.responseType="text";

    requete.onload=function (){
        window.onload=function(){
            if (this.status===200){
                let response=this.response;
                console.log(response);
                var Login=document.getElementById("Login");
                Login.innerText=response;
            }else{
                console.log("échec de la requête");
            }
        }
    }
    requete.send(response);
}

var getPassword=function(){
    let requete=new XMLHttpRequest();
    requete.open("POST","inscription",true);
    requete.responseType="text";

    requete.onload=function (){
        window.onload=function(){
            if (this.status===200){
                let response=this.response;
                console.log(response);
                var Password=document.getElementById("Password");
                Password.innerText=response;
            }else{
                console.log("échec de la requête");
            }
        }
    }
    requete.send(response);
}

var getNewPersonne=function(){
    let requete=new XMLHttpRequest();
    requete.open("POST","inscription",true);
    requete.responseType="text";
    var Pseudo=document.getElementById("Pseudo");
    let repPseudo=Pseudo.innerText;
    var Login=document.getElementById("Login");
    let repLogin=Login.innerText;
    var Password=document.getElementById("Password");
    let repPassword=Password.innerText;
        window.onload=function(){
            if (this.status===200){
                console.log("yo");
            }else{
                console.log("échec de la requête");
            }
        }

    requete.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    requete.send(repPseudo);
    requete.send(repLogin);
    requete.send(repPassword);
}
