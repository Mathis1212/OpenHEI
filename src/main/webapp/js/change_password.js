window.onload=function(){

    FirstToggle=document.querySelectorAll("label.switch input[type='checkbox']")[0];
    SecondToggle=document.querySelectorAll("label.switch input[type='checkbox']")[1];

    //les champs de saisie
    myFirstPasswordInput = document.getElementById("Password");
    mySecondPasswordInput = document.getElementById("newPassword");


    mySubmitButton=document.getElementById("submit");


    //Toggle
    FirstToggle.onclick=function (){
        showPassword(myFirstPasswordInput);
    }
    SecondToggle.onclick=function (){
        showPassword(mySecondPasswordInput);
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