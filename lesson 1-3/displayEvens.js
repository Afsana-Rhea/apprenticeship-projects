function validateItems() {

    var num1 = Number(document.forms["numberFun"]["num1"].value);
    var num2 = Number(document.forms["numberFun"]["num2"].value);
    var num3 = Number(document.forms["numberFun"]["num3"].value);

    if (num1 == "" || isNaN(num1)) {
        alert("Num1 must be filled in with a number.");
        document.forms["numberFun"]["num1"]
           .parentElement.className = "form-group has-error";
        document.forms["numberFun"]["num1"].focus();
        return false;
    }
   if (num2 == "" || isNaN(num2) || num2 <= num1) {
       alert("Num2 must be filled in with a number greater than the starting number.");
       document.forms["numberFun"]["num2"]
          .parentElement.className = "form-group has-error"
       document.forms["numberFun"]["num2"].focus();
       return false;
   }
   if (num3 == "" || isNaN(num3) || num3 < 0) {
       alert("Num3 must be filled in with a positive number greater than the starting number.");
       document.forms["numberFun"]["num3"]
          .parentElement.className = "form-group has-error"
       document.forms["numberFun"]["num3"].focus();
       return false;
   }

   for (var i = num1; i <= num2; i+=num3){
if ( i % 2 === 0 ) {
  console.log(i);
  document.getElementById("results").innerHTML += i + "<br>";
}
}

   document.getElementById("results").style.display = "block";
   document.getElementById("displaynum1").innerHTML = num1;
   document.getElementById("displaynum2").innerHTML = num2;
   document.getElementById("displaynum3").innerHTML = num3;


   return false;

}
