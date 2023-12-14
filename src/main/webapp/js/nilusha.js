/**
 * 
 */

function callCheckoutServlet() {
	var fname = document.getElementById('fname').value
	var lname = document.getElementById('lname').value
	var address = document.getElementById('address').value
	var phone = document.getElementById('phone').value
	var email = document.getElementById('email').value
	var pcode = document.getElementById('pcode').value

	$.post("http://localhost:8080/LankaHardware/CheckoutServlet", { fname: fname, lname: lname, address: address, phone: phone, email: email, pcode: pcode }, function(response) {

		
	})
}