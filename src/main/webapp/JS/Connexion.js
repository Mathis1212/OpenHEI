var getLogin=function(){
    let requete=new XMLHttpRequest();
    requete.open("POST","connection",true);
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
    requete.open("POST","connection",true);
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
    requete.send("le password est: "+response);
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