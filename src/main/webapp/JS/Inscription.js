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

    requete.onload=function (){
        window.onload=function(){
            if (this.status===200){
                let repPseudo=this.response;
                let repLogin=this.response;
                let repPassword=this.response;

                var Pseudo=document.getElementById("Pseudo");
                Pseudo.innerText=repPseudo;
                var Login=document.getElementById("Login");
                Login.innerText=repLogin;
                var Password=document.getElementById("Password");
                Password.innerText=repPassword;
            }else{
                console.log("échec de la requête");
            }
        }
    }
    requete.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    requete.send(repPseudo);
    requete.send(repLogin);
    requete.send(repPassword);
}
