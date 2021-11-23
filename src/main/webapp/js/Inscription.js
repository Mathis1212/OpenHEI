window.onload=function(){
    button=document.getElementById("envoi");
    XHR=new XMLHttpRequest();
    var NewInscription=function (){

        console.log(XHR);
        console.log(XHR.responseText)

        try{
            if (XHR.readyState==4){
                alert(XHR.responseText);
            }
        }catch (e){

        }
    }

    var onReadyStateChange=function () {
        try{
            if (XHR.status == 200 && XHR.readyState === 4) {
                alert("connection réussi");
                console.log(XHR.responseText);
            } else {
                alert("la requete n'a pas été transmise");
            }

        }catch (e) {
            alert("Une exception s’est produite : " + e.description);
        }

    }

    document.getElementById("envoi").addEventListener("click",test);
}








var test=function() {

    var Login=document.getElementById("Login").value;
    var Pseudo=document.getElementById("Pseudo").value;
    var Password=document.getElementById("Password").value;
    var data=new Array();
      var httpRequest;

      function makeRequest() {
        httpRequest = new XMLHttpRequest();
        console.log(httpRequest);

    if (!httpRequest) {
      alert('Abandon :( Impossible de créer une instance de XMLHTTP');
      return false;
    }
    httpRequest.onreadystatechange = alertContents;
    httpRequest.open('POST','inscription',true);
    httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    httpRequest.send();
  }

  function alertContents(httpRequest) {
      try {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
          if (httpRequest.status === 200) {
              /*data.push(Login);
              data.push(Pseudo);
              data.push(Password);
              console.log(data);*/
            alert(httpRequest.responseText);
          } else {
            alert("Un problème est survenu au cours de la requête.");
          }
        }
      }catch( e ) {
        alert("Une exception s’est produite : " + e.description);
      }
  }

  makeRequest()
};



var Inscription=function(){
    console.log("test");
    var xhr=new XMLHttpRequest();
    makeRequest('test.php', userName);
    function makeRequest(url,name) {
        httpRequest = new XMLHttpRequest();

        if (!httpRequest) {
            alert('Abandon :( Impossible de créer une instance de XMLHTTP');
            return false;
        }
        httpRequest.onreadystatechange = alertContents;
        httpRequest.open('GET', 'test.html');
        httpRequest.send();
    }
    try{
        if (xhr.status===200&&xhr.readyState===4){
            alert(xhr.responseText);
        }else{
            alert("Un problème est survenu au cours de la requête.");
        }
    }catch (e){
        alert("Une exception s’est produite : " + e.description);
    }
    xhr.open('POST',"inscription",true);
    xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    var Login=document.getElementById("login").value;
    var Pseudo=document.getElementById("Pseudo").value;
    var Password=document.getElementById("Password").value;
    var data=new Array();
    console.log(requete);
    console.log(data.Pseudo);
    console.log(data.Login);
    console.log(data.Password);
    console.log(xhr.responseText);




    xhr.onreadystatechange=function(){
        if (this.readyState===4){
            let reponse=xhr.responseText;
            console.log(reponse);
            console.log(data.Pseudo);
            console.log(data.Login);
            console.log(data.Password);
            data.push(Login);
            data.push(Pseudo);
            data.push(Password);
        }
    }
    xhr.send("test");
}



//.open() permet d'envoyer une requete sans avoir à recharger la page
//.onReadyStateChange donne les états de la requête
//.onStatus donne l'état de la réponse 200==réponse trouvé
//.onState donne l'état de la requête 0==pas envoyé, 4==réponse reçu bien envoyé
//.onRequestHeader permet de définir le content type de la requête
//l'objet data permet de stocker les différents paramtètres de la requête
//.send() permet d'envoyer la requête

/*
var getPassword=function(){
    let requete=new XMLHttpRequest();
    requete.open("POST","inscription",true);
    requete.responseType="text";
    let PseudoElement=document.getElementById("Pseudo").innerText;
    let LoginElement=document.getElementById("Login").innerText;
    let PasswordElement=document.getElementById("Password").innerText;
    console.log(requete);
    requete.onload=function(){
        if (this.status===200&&this.onreadystatechange===4){
            let response=this.response;
            console.log(requete);
            console.log(PseudoElement);
            console.log(LoginElement);
            console.log(PasswordElement);
            console.log(response);
        }
    }
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
    requete.send();
}
*/



/*
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



     try {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
      if (httpRequest.status === 200) {
        alert(httpRequest.responseText);
      } else {
        alert("Un problème est survenu au cours de la requête.");
      }
    }
  }
  catch( e ) {
    alert("Une exception s’est produite : " + e.description);
  }
}*/
