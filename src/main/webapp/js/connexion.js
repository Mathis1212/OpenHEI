window.onload=function(){

    toggle=document.querySelector("label.switch input[type='checkbox']");

    //les champs de saisie
    myPasswordInput = document.getElementById("Password");


    mySubmitButton=document.getElementById("submit");


    //Toggle
    toggle.onclick=function (){
        showPassword(myPasswordInput);
    }
}

//When the user click on toggle show password as a text
function showPassword(elt) {
    if (elt.type === "password") {
        elt.type = "text";
    } else {
        elt.type = "password";
    }
}