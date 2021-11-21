function myFunction() {
    //déclaration des variables
    var input, filter, ul, li, a, i, txtValue;
    input = document.getElementById("site-search");
    filter = input.value.toUpperCase();
    ul = document.getElementById("myUL");
    li = ul.getElementsByTagName("li");

    // boucle pour lister les bons résultats et cacher les autres
    for (i = 0; i < li.length; i++) {
        a = li[i].getElementsByTagName("a")[0];
        txtValue = a.textContent || a.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = "";
        } else {
            li[i].style.display = "none";
        }
    }
}