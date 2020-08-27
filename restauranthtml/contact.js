function validateItems() {
  var name = document.getElementById("name").value;
  var email = document.getElementById("email").value;
  var phone = document.getElementById("phone").value;
  var contact = document.getElementById("contact").value;
  var errormessage = document.getElementById("error_message").value
  var text;
  error_message.style.padding = "10px"

  if (name.length < 2) {
    text = "Please Enter A Valid Name"
    error_message.innerHTML = text;
    return false;

  }

  if (email.indexOf("@") == -1) {
    text = "Please Enter Valid Email"
    error_message.innerHTML = text;
    return false;
  }

  if (isNaN(phone)) {
    text = "Please Enter Valid Phone Number"
    error_message.innerHTML = text;
    return false;

  }
  alert("Form Submitted Succesfully!")
  return false;


}
