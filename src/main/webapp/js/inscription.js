window.onload=function(){
    myFieldSpan=document.querySelectorAll("span.invalid");
    myConditions=document.querySelectorAll("#message p");


    toggle=document.querySelector("label.switch input[type='checkbox']");

    //les champs de saisie
    myPasswordInput = document.getElementById("Password");
    myPasswordSpan=myFieldSpan[2];

    myLoginInput = document.getElementById("Login");
    myLoginSpan=myFieldSpan[1];

    myPseudoInput = document.getElementById("Pseudo");
    myPseudoSpan=myFieldSpan[0];

    mySubmitButton=document.getElementById("submit");

    letter = document.getElementById("letter");
    capital = document.getElementById("capital");
    number = document.getElementById("number");
    length = document.getElementById("length");
    space = document.getElementById("space");
    email = document.getElementById("email");
    specialChar = document.getElementById("specialChar");


    //Password
    BlurField(myPasswordInput);
    KeyUpField(myPasswordInput,myPasswordSpan,8,0);
    FocusField(myPasswordInput,"Password",8,myPasswordSpan,0);

    //Login
    BlurField(myLoginInput);
    KeyUpField(myLoginInput,myLoginSpan,6,2);
    FocusField(myLoginInput,"Login",6,myLoginSpan,2);

    //Pseudo
    BlurField(myPseudoInput);
    KeyUpField(myPseudoInput,myPseudoSpan,6,1);
    FocusField(myPseudoInput,"Pseudo",6,myPseudoSpan,1);

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

// When the user clicks outside of the element field, hide the message box
function BlurField(elt){
    elt.onblur = function() {
        document.getElementById("message").style.display = "none";
    }
}

// When the user starts to type something inside the element field
function KeyUpField(elt,span,longueur,choix){
    elt.onkeyup = function() {
        validateField(elt,longueur,span,choix)
    }
}

// When the user clicks inside of the element field, show the message box with the text
function FocusField(elt,text,longueur,span,choix){
    elt.onfocus = function() {
        document.getElementById("message").style.display = "block";
        for (let cdt of myConditions) {
            cdt.style.display = "none";
        }
        validateField(elt,longueur,span,choix);
        document.getElementById("message").getElementsByTagName("h3").item(0).innerText=text+" must contain the following:";
        document.getElementById("message").getElementsByTagName("p").item(3).innerHTML="Minimum <b>"+longueur+" characters</b>";
    }
}

//Selectionne le champ que l'on souhaite vérifier
function validateField(elt,longueur,span,choix){
    switch (choix){
        //Password
        case 0:{
            lowerCaseLettersCheck(elt);
            upperCaseLettersCheck(elt);
            numbersCheck(elt);
            lengthCheck(longueur,elt);
            spaceCheck(elt);
            specialCharCheck(elt);
            if (letter.classList.contains("valid")&&capital.classList.contains("valid")&&number.classList.contains("valid")&&length.classList.contains("valid")&&space.classList.contains("valid")&&specialChar.classList.contains("valid")){
                span.classList.remove("invalid");
                span.classList.add("valid");
            }else{
                span.classList.remove("valid");
                span.classList.add("invalid");
            }
            break;
        }
        //Pseudo
        case 1:{
            numbersCheck(elt);
            lengthCheck(longueur,elt);
            spaceCheck(elt);
            if (number.classList.contains("valid")&&length.classList.contains("valid")&&space.classList.contains("valid")){
                span.classList.remove("invalid");
                span.classList.add("valid");
            }else{
                span.classList.remove("valid");
                span.classList.add("invalid");
            }
            break;
        }
        //Login
        case 2:{
            lengthCheck(longueur,elt);
            specialEmailCheck(elt)
            spaceCheck(elt);
            if (length.classList.contains("valid")&&space.classList.contains("valid")&&email.classList.contains("valid")){
                span.classList.remove("invalid");
                span.classList.add("valid");
            }else{
                span.classList.remove("valid");
                span.classList.add("invalid");
            }
            break;
        }
    }
}




//liste des fonctions de check individuelle
function lowerCaseLettersCheck(elt){
    myConditions[0].style.display="block";
    // Validate lowercase letters
    var lowerCaseLetters = /[a-z]/g;
    if(elt.value.match(lowerCaseLetters)) {
        letter.classList.remove("invalid");
        letter.classList.add("valid");
    } else {
        letter.classList.remove("valid");
        letter.classList.add("invalid");
    }
}
function upperCaseLettersCheck(elt){
    myConditions[1].style.display="block";
    // Validate capital letters
    var upperCaseLetters = /[A-Z]/g;
    if(elt.value.match(upperCaseLetters)) {
        capital.classList.remove("invalid");
        capital.classList.add("valid");
    } else {
        capital.classList.remove("valid");
        capital.classList.add("invalid");
    }
}

function numbersCheck(elt){
    myConditions[2].style.display="block";
    // Validate numbers
    var numbers = /[0-9]/g;
    if(elt.value.match(numbers)) {
        number.classList.remove("invalid");
        number.classList.add("valid");
    } else {
        number.classList.remove("valid");
        number.classList.add("invalid");
    }
}

function lengthCheck(longueur,elt){
    myConditions[3].style.display="block";
    // Validate length
    if(elt.value.length >= longueur) {
        length.classList.remove("invalid");
        length.classList.add("valid");
    } else {
        length.classList.remove("valid");
        length.classList.add("invalid");
    }
}

function spaceCheck(elt){
    myConditions[6].style.display="block";
    // Validate space
    var spaces = /[\s]/g;
    if(elt.value.match(spaces)) {
        space.classList.remove("valid");
        space.classList.add("invalid");

    } else {
        space.classList.remove("invalid");
        space.classList.add("valid");
    }
}
function specialCharCheck(elt){
    myConditions[4].style.display="block";
    // Validate space
    var special = /[@#$%^&+!.?=]/g;
    if(elt.value.match(special)) {
        specialChar.classList.remove("invalid");
        specialChar.classList.add("valid");
    } else {
        specialChar.classList.remove("valid");
        specialChar.classList.add("invalid");
    }
}
function specialEmailCheck(elt){
    myConditions[5].style.display="block";
    // Validate space
    var special = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]";
    if(elt.value.match(special)) {
        email.classList.remove("invalid");
        email.classList.add("valid");
    } else {
        email.classList.remove("valid");
        email.classList.add("invalid");
    }
}