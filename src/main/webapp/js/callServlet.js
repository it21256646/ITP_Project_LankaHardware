/**
 * 
 */


function callLoginServlet() {

	var email = document.getElementById('emailInput').value
	var password = document.getElementById('passwordInput').value
	var loginError = document.getElementById('loginError')

	$.post("http://localhost:8080/LankaHardware/LoginServlet", { email: email, password: password }, function(response) {

		console.log(response)

		if (response == "customer") {
			window.location.href = "index.jsp";

		}
		else if (response == "sysAdmin") {
			window.location.href = "Admin/html/ViewAdmin.jsp";
		}
		else if (response == "supplierManager") {
			window.location.href = "Admin/html/ViewSupplier.jsp";
		}
		else if (response == "employerManager") {
			window.location.href = "Admin/html/ViewEmp.jsp";
		}
		else if (response == "stockManager") {
			window.location.href = "Admin/html/ViewStock.jsp";
		}
		else if (response == "orderManager") {
			window.location.href = "Admin/html/ViewOrder.jsp";
		}
		else if (response == "productManager") {
			window.location.href = "Admin/html/productManagerCharts.jsp";
		}
		else if (response == "Email or Password is incorrect") {
			loginError.innerHTML = response
			loginError.style = "display: block;"
		}


	})

}
/**registration process */
/**
 * 
 */
var customer = []
var customertable = document.getElementById('customer')

function callGetAllCustomerServlet() {
	$.get("http://localhost:8080/LankaHardware/GetAllCustomerServlet", function(response) {

		customers = response

		console.log(customers)

		buildAllCustomers();
	})
}

function buildAllCustomer() {
	customertable.innerHTML = ''
	for (var i = 0; i < customer.length; i++) {
		var customer = `<tr>
						<td>
							${customer[i].email}
						</td>
						<td>
							${ecustomer[i].Password}
						</td>
						<td>
						    ${customer[i].Name}
						</td>	
						<td>
							${customer[i].phone}
						</td>
						<td>
							${customer[i].address}
						</td>
						
						
						</tr>`


		customertable.innerHTML += customer
	}
}


//Insert Customer

var isNew = true;


function callAddCustomerServlet() {
	var loginError = document.getElementById('loginError')

	var email = document.getElementById('email').value
	var Password = document.getElementById('Password').value
	var con_Password = document.getElementById('con_Password').value
	var phone = document.getElementById('phone').value
	var name = document.getElementById('Name').value
	var address = document.getElementById('address').value


	if (con_Password != Password) {
		loginError.innerHTML = "Password and Confirm password should match"
		loginError.style = "display: block;"
		
	}
//	if (!validateEmail(email)) {
//    document.getElementById("loginError").innerHTML = "Invalid email address";
//    document.getElementById("loginError").style.display = "block";
//    valid = false;
//    }
//  
//    if (!validatePassword(Password)) {
//    document.getElementById("loginError").innerHTML = "Password must be at least 8 characters long";
//    document.getElementById("loginError").style.display = "block";
//    valid = false;
//    }
//    if (!validatePhone(phone)) {
//    document.getElementById("loginError").innerHTML = "Invalid phone number";
//    document.getElementById("loginError").style.display = "block";
//    valid = false;
//    }
//  
//    if (!valid) {
//      return;
//    }

	console.log(address)
	console.log(name)



	var endpoint = "http://localhost:8080/LankaHardware/AddCustomerServlet"

	//	var formData = new FormData();
	//	
	//	formData.append('email',email)
	//	formData.append('password',Password)
	//	formData.append('phone',phone)
	//	formData.append('name',name)
	//	formData.append('address',address)
	//
	//
	//	
	//	fetch(endpoint, {
	//		method: "post",
	//		body: formData
	//	}).then(res => res.json()
	//	.then(data => window.location.href = data)
	//	)

	$.post(endpoint, { email: email, Password: Password, phone: phone, name: name, address: address }, function(response) {

		window.location.href = "http://localhost:8080/LankaHardware/Login.jsp";
	})
}
function validateEmail(email) {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
}

function validatePassword(Password) {
  return Password.length >= 8;
}

function validatePhone(phone) {
  const phoneRegex = /^\d{10}$/;
  return phoneRegex.test(phone);
}

//update Customer
var isNew = true;

var editCustomerModalHeader = document.getElementById('EditCustomerModalHeader')
var editCustomerModalBody = document.getElementById('EditCustomerModalBody')
var editCustomerModalFooter = document.getElementById('EditCustomerModalFooter')
var editCard = document.getElementById('card-body-edit')

function BuildEditCustomerModal(email,Password,phone,name,address){
	editCustomerModalHeader.innerHTML = `<h5 class="modal-title" id="modalCenterTitle">Edit Customer</h5>
							              <button
							                type="button"
							                class="btn-close"
							                data-bs-dismiss="modal"
							                aria-label="Close"
							              ></button>`

	
	editCustomerModalBody.innerHTML = `<div>
						              	 <div class="card-body">
                      <div class="d-flex align-items-start align-items-sm-center gap-4">
                        <img src="../assets/img/avatars/1.png" alt="user-avatar" class="d-block rounded" height="100" width="100" id="uploadedAvatar">
                        <div class="button-wrapper">
                          <label for="upload" class="btn btn-primary me-2 mb-4" tabindex="0">
                            <span class="d-none d-sm-block">Upload new photo</span>
                            <i class="bx bx-upload d-block d-sm-none"></i>
                            <input type="file" id="upload" class="account-file-input" hidden="" accept="image/png, image/jpeg">
                          </label>
                          <button type="button" class="btn btn-outline-secondary account-image-reset mb-4">
                            <i class="bx bx-reset d-block d-sm-none"></i>
                            <span class="d-none d-sm-block">Reset</span>
                          </button>

                          <p class="text-muted mb-0">Allowed JPG, GIF or PNG. Max size of 800K</p>
                        </div>
                      </div>
                    </div>
						                      </div>`

	editCard.innerHTML = `<form id="formAccountSettings" method="POST" onsubmit="return false">
                        <div class="row">
                          <div class="mb-3 col-md-6">
                            <label for="firstName" class="form-label">Email.</label>
                            <input
                              class="form-control"
                              type="text"
                              id="emailModal"
                              name="email"
                   				value="${email}"
                   				 placeholder="123@gmail.com"
                           
                            />
                          </div>
                          <div class="mb-3 col-md-6">
                            <label for="lastName" class="form-label">password</label>
                            <input class="form-control" type="text"  id="PasswordModal" name="password" value="${Password}" id="Password"/>
                          </div>
                          <div class="mb-3 col-md-6">
                            <label for="email" class="form-label">Phone</label>
                            <div class="input-group input-group-merge">
                            <span class="input-group-text">LK (+94)</span>
                            <input
                              class="form-control"
                              type="text"
                              id="phoneModal"
                              name="Phone"
								value="${phone}"
						      placeholder="07********"
                             
                              
                            />
                          </div>
                          <div class="mb-3 col-md-6">
                            <label for="organization" class="form-label">Name</label>
                            <input
                              type="text"
                              class="form-control"
                              id="nameModal"
                              name="name"
								value="${name}"
                              placeholder = "Assistant Manager"
                            />
                          </div>
                          
                          <div class="mb-3 col-md-6">
                            <label for="address" class="form-label">Address</label>
                            <input type="text" class="form-control" id="addressModal" name="address" value="${address}"placeholder="Address" />
                          </div>
               
                       
                        <div class="mt-2">
                          <button type="submit" class="btn btn-primary me-2" id = "save" onclick ="callupdateCustomer()">Edit Customer</button>
                          <button type="reset" class="btn btn-outline-secondary" id ="clear" data-bs-dismiss="modal">Cancel</button>
                        </div>
                      </form>`

	editCustomerModalFooter.innerHTML = ``
	
}

function callupdateCustomer(){
	var inputFile = document.getElementById('inputFileModal')
	
	var email = document.getElementById('emailModal').value
	var Password = document.getElementById('PasswordModal').value
	var phone = document.getElementById('phoneModal').value
	var name = document.getElementById('designationModal').value
	var email = document.getElementById('emailModal').value
	var address = document.getElementById('addressModal').value

	
	
	if(email == null)
	{
		email = "null";
	}
	if(Password == null)
	{
		Password = "null";
	}
	if(phone == null)
	{
		phone = "null";
	}
	
	if(name == null)
	{
		name = "null";
	}
	if(address == null)
	{
		address = "null";
	}

	
	
	//console.log(empNo+name+email+designation+phoneNum+address+date+salary )
	
	var endpoint = "http://localhost:8080/LankaHardware/UpdateCustomer"
	var formData = new FormData();
	
//	for(const file of inputFile.files){
//		formData.append('inputFileModal', file)
//	}

//	formData.append('empNoModal',empNo)
//	formData.append('nameModal',name)
//	formData.append('emailModal',email)
//	formData.append('designationModal',designation)
//	formData.append('addressModal',address)
//	formData.append('dateModal',date)
//	formData.append('salaryModal',salary)

	
//	fetch(endpoint, {
//		method: "post",
//		body: formData
//	}).then(res => {
//		callGetAllEmployeesServlet()
//		setTimeout(function() {
//				$('#AddEmoloyeeModal').modal('hide')
//		}, 2500);	
//	}
//	)
	
	$.post(endpoint, {emailModal : email,PasswordModal : Password,phoneModal : phone,nameModal : name,addressModal : address }, function(response) {
		
		callGetAllCustomersServlet()
		setTimeout(function() {
				$('#EditCustomerModal').modal('hide')
		}, 1500);	
	})
}
//delete Customer
var deleteModalHeader = document.getElementById('deleteModalHeader')
var deleteModalBody = document.getElementById('deleteModalBody')
var deleteModalFooter = document.getElementById('deleteModalFooter')

function createDeleteModal(email) {
	deleteModalHeader.innerHTML = `<button
					                type="button"
					                class="btn-close"
					                data-bs-dismiss="modal"
					                aria-label="Close"
					              ></button>`
	deleteModalHeader.style.display = ""

	deleteModalBody.innerHTML = `<div style="display: flex; flex-direction: column; text-align: center;">
					                <div class="icon-box">
					                  <i class="material-icons">&times;</i>
					                </div>						
					                <h4 class="modal-title w-100">Are you sure?</h4>
					                <p style="margin-top: 10px;">Do you really want to delete these records? This process cannot be undone.</p>
					              </div>`
	deleteModalBody.style.padding = ""

	deleteModalFooter.innerHTML = `<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
					                Close
					              </button>
					              <button type="button" class="btn btn-danger" onclick="callDeleteEmployeeServlet('${email}')">Delete</button>`
	deleteModalFooter.style.display = ""
}

function callDeleteCustomerServlet(email) {
	deleteModalHeader.style = "display: none;"
	deleteModalBody.style = "text-align: center;"
	deleteModalBody.innerHTML = `<div class="spinner-border text-warning" role="status" style="width: 2.5rem; height: 2.5rem;">
			                          <span class="visually-hidden">Loading...</span>
			                        </div>`
	deleteModalFooter.style = "display: none;"
	$.post("http://localhost:8080/LankaHardware/RemoveCustomer", { email: email }, function(response) {

		deleteModalBody.style = "padding: 1rem;"
		deleteModalBody.innerHTML = `<div style="display: flex; justify-content: center; align-items: center; column-gap: 10px;">
									        <lottie-player src="https://assets3.lottiefiles.com/packages/lf20_q7hiluze.json"  background="transparent"  speed="1"  style="width: 50px; height: 50px;" autoplay></lottie-player>
									        <span style="font-size: x-large;">${response}</span>
									    </div>`

		callGetAllCustomersServlet()

		setTimeout(function() {
			$('#deleteModal').modal('hide')
		}, 2500);
	})
}

var otpmessage

function callSendCustomeremailServlet() {

	var email = document.getElementById('otpemail').value

	$.post("http://localhost:8080/LankaHardware/SendCustomeremail", { email: email }, function(response) {

		otpmessage = response
		console.log(otpmessage)
	})
}