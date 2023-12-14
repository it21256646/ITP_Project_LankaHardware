/**
 * 
 */
var employees = []
var employeestable = document.getElementById('employee')

function callGetAllEmployeesServlet(){
	$.get("http://localhost:8080/LankaHardware/GetAllEmployeesServlet", function(response) {
				
		employees = response
		
		buildAllEmployees(employees);
	})
}

function buildAllEmployees(emp){
	employeestable.innerHTML = ''
	for(var i = 0; i < emp.length; i++){
		var employee = `<tr>
						<td>
							${emp[i].empNo}
						</td>
						<td>
							${emp[i].name}
						</td>
						<td>
							${emp[i].email}
						</td>
						<td>
							${emp[i].designation}
						</td>
						<td>
							${emp[i].phoneNum}
						</td>
						<td>
							${emp[i].address}
						</td>
						<td>
                          <div class="dropdown">
                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                              <i class="bx bx-dots-vertical-rounded"></i>
                            </button>
                            <div class="dropdown-menu">
                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#EditEmoloyeeModal" onclick="BuildEditEmployeeModal('${emp[i].empNo}', '${emp[i].name}','${emp[i].email}','${emp[i].designation}','${emp[i].phoneNum}','${emp[i].address}','${emp[i].gender}','${emp[i].date}','${emp[i].wage}','${emp[i].salary}');"
                                ><i class="bx bx-edit-alt me-1"></i> Edit</a
                              >
                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#deleteModal"  onclick="createDeleteModal('${emp[i].empNo}')"
                                ><i class="bx bx-trash me-1"></i> Delete</a
                              >
                            </div>
                          </div>
                        </td>
						
						</tr>`
						
		
		employeestable.innerHTML += employee		
	}
}


//Insert Employees

var isNew = true;


function callAddEmployeeServlet(){
	var inputFile = document.getElementById('inputFile')
	
	var name = document.getElementById('name').value
	var email = document.getElementById('email').value
	var designation = document.getElementById('designation').value
	var phoneNum = document.getElementById('phoneNum').value
	var address = document.getElementById('address').value
	var gender = document.getElementById('gender').value
	var date = document.getElementById('date').value
	var wage = document.getElementById('wage').value
	var salary = document.getElementById('salary').value
	
	console.log(phoneNum)
	
	var endpoint = "http://localhost:8080/LankaHardware/AddEmployeeServlet"
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

$.post(endpoint, {name : name,email : email,designation : designation,phoneNum : phoneNum,address : address,date : date,salary : salary }, function(response) {
		
		callGetAllEmployeesServlet()
		setTimeout(function() {
				$('#AddEmoloyeeModal').modal('hide')
		}, 1500);	
	})


	
}

//update Employees
var isNew = true;

var editEmployeeModalHeader = document.getElementById('EditEmoloyeeModalHeader')
var editEmployeeModalBody = document.getElementById('EditEmoloyeeModalBody')
var editEmployeeModalFooter = document.getElementById('EditEmoloyeeModalFooter')
var editCard = document.getElementById('card-body-edit')

function BuildEditEmployeeModal(empNo,name,email,designation,phoneNum,address,gender,date,wage,salary){
	editEmployeeModalHeader.innerHTML = `<h5 class="modal-title" id="modalCenterTitle">Edit Employee</h5>
							              <button
							                type="button"
							                class="btn-close"
							                data-bs-dismiss="modal"
							                aria-label="Close"
							              ></button>`

	
	editEmployeeModalBody.innerHTML = `<div>
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
                            <label for="firstName" class="form-label">Employee No.</label>
                            <input
                              class="form-control"
                              type="text"
                              id="empNoModal"
                              name="empNo"
                   				value="${empNo}"
                              autofocus
							readonly
                            />
                          </div>
                          <div class="mb-3 col-md-6">
                            <label for="lastName" class="form-label">Name</label>
                            <input class="form-control" type="text"  id="nameModal" name="name" value="${name}" id="name"/>
                          </div>
                          <div class="mb-3 col-md-6">
                            <label for="email" class="form-label">E-mail</label>
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
                            <label for="organization" class="form-label">Designation</label>
                            <input
                              type="text"
                              class="form-control"
                              id="designationModal"
                              name="designation"
								value="${designation}"
                              placeholder = "Assistant Manager"
                            />
                          </div>
                          <div class="mb-3 col-md-6">
                            <label class="form-label" for="phoneNumber">Phone Number</label>
                            <div class="input-group input-group-merge">
                              <span class="input-group-text">LK (+94)</span>
                              <input
                                type="text"
                                id="phoneNumModal"
                                name="phoneNum"
                                class="form-control"
								value="${phoneNum}"
                                placeholder="07********"
                              />
                            </div>
                          </div>
                          <div class="mb-3 col-md-6">
                            <label for="address" class="form-label">Address</label>
                            <input type="text" class="form-control" id="addressModal" name="address" value="${address}"placeholder="Address" />
                          </div>
               
                          <div class="mb-3 col-md-6">
                            <label for="language" class="form-label">Appointment Date</label><br>
                           <input name = date type="date" id="dateModal" name = "date" value="${date}">

                          </div>
                          
                          <div class="mb-3 col-md-6">
                            <label for="currency" class="form-label">Salary Amount[Rs]</label>
                         <input
                              type="text"
                              class="form-control"
                              id="salaryModal"
                              name="salary"
							  value="${salary}"
                              placeholder = "Rs 50000"
                            />
                          </div>
                        </div>
                        <div class="mt-2">
                          <button type="submit" class="btn btn-primary me-2" id = "save" onclick ="callupdateEmployee()">Edit Employee</button>
                          <button type="reset" class="btn btn-outline-secondary" id ="clear" data-bs-dismiss="modal">Cancel</button>
                        </div>
                      </form>`

	editEmployeeModalFooter.innerHTML = ``
	
}

function callupdateEmployee(){
	var inputFile = document.getElementById('inputFileModal')
	
	var empNo = document.getElementById('empNoModal').value
	var name = document.getElementById('nameModal').value
	var email = document.getElementById('emailModal').value
	var designation = document.getElementById('designationModal').value
	var phoneNum = document.getElementById('phoneNumModal').value
	var address = document.getElementById('addressModal').value
	var date = document.getElementById('dateModal').value
	var salary = document.getElementById('salaryModal').value
	
	
	if(name == null)
	{
		name = "null";
	}
	if(email == null)
	{
		email = "null";
	}
	if(designation == null)
	{
		designation = "null";
	}
	if(phoneNum == null)
	{
		phoneNum = "null";
	}
	if(address == null)
	{
		address = "null";
	}

	if(date == null)
	{
		date = "null";
	}
	
	if(salary == null)
	{
		salary = "null";
	}
	
	//console.log(empNo+name+email+designation+phoneNum+address+date+salary )
	
	var endpoint = "http://localhost:8080/LankaHardware/UpdateEmployee"
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
	
	$.post(endpoint, {empNoModal : empNo,nameModal : name,emailModal : email,designationModal : designation,phoneNumModal : phoneNum,addressModal : address,dateModal : date,salaryModal : salary }, function(response) {
		
		callGetAllEmployeesServlet()
		setTimeout(function() {
				$('#EditEmoloyeeModal').modal('hide')
		}, 1500);	
	})
}
//delete employee
var deleteModalHeader = document.getElementById('deleteModalHeader')
var deleteModalBody = document.getElementById('deleteModalBody')
var deleteModalFooter = document.getElementById('deleteModalFooter')

function createDeleteModal(empNo) {
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
					              <button type="button" class="btn btn-danger" onclick="callDeleteEmployeeServlet('${empNo}')">Delete</button>`
	deleteModalFooter.style.display = ""
}

function callDeleteEmployeeServlet(empNo) {
	deleteModalHeader.style = "display: none;"
	deleteModalBody.style = "text-align: center;"
	deleteModalBody.innerHTML = `<div class="spinner-border text-warning" role="status" style="width: 2.5rem; height: 2.5rem;">
			                          <span class="visually-hidden">Loading...</span>
			                        </div>`
	deleteModalFooter.style = "display: none;"
	$.post("http://localhost:8080/LankaHardware/RemoveEmployees", { empNo: empNo }, function(response) {

		deleteModalBody.style = "padding: 1rem;"
		deleteModalBody.innerHTML = `<div style="display: flex; justify-content: center; align-items: center; column-gap: 10px;">
									        <lottie-player src="https://assets3.lottiefiles.com/packages/lf20_q7hiluze.json"  background="transparent"  speed="1"  style="width: 50px; height: 50px;" autoplay></lottie-player>
									        <span style="font-size: x-large;">${response}</span>
									    </div>`

		callGetAllEmployeesServlet()

		setTimeout(function() {
			$('#deleteModal').modal('hide')
		}, 2500);
	})
}




//Suppliers

var suppliers = []
var supplierstable = document.getElementById('supplier')

function callGetAllSuppliersServlet(){
	$.get("http://localhost:8080/LankaHardware/GetAllSuppliersServlet", function(response) {
				
		suppliers = response
		
		console.log(suppliers)
		
		buildAllSuppliers(suppliers);
	})
}

function buildAllSuppliers(sup){
	supplierstable.innerHTML = ''
	for(var i = 0; i < sup.length; i++){
		var supplier = `<tr>
						<td>
							${sup[i].supNo}
						</td>
						<td>
							${sup[i].name}
						</td>
						<td>
							${sup[i].email}
						</td>
						<td>
							${sup[i].phoneNum}
						</td>
						<td>
							${sup[i].description}
						</td>
						<td>
							${sup[i].debit}
						</td>
						<td>
                          <div class="dropdown">
                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                              <i class="bx bx-dots-vertical-rounded"></i>
                            </button>
                            <div class="dropdown-menu">
                            
                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#EditSupplierModal" onclick="BuildEditSupplierModal('${sup[i].supNo}', '${sup[i].name}','${sup[i].email}','${sup[i].phoneNum}','${sup[i].description}','${sup[i].debit}');"
                                ><i class="bx bx-edit-alt me-1"></i> Edit</a
                              >
                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#deleteSupplierModal"  onclick="createSupplierDeleteModal('${sup[i].supNo}')"
                                ><i class="bx bx-trash me-1"></i> Delete</a
                              >
                            </div>
                          </div>
                        </td>
						
						</tr>`
						
		
		supplierstable.innerHTML += supplier		
	}
}


//Insert Suppliers
var AddSupplierModal = document.getElementById('AddSupplierModalHeader')
var isNew = true;
var AddSupplierModalHeader = document.getElementById('AddSupplierModalHeader')
var AddSupplierModalBody = document.getElementById('AddSupplierModalBody')
var AddSupplierFooter = document.getElementById('AddSupplierFooter')



function callAddSupplierServlet(){
	
	
	var name = document.getElementById('name').value
	var email = document.getElementById('email').value
	var phoneNum = document.getElementById('phoneNum').value
	var description = document.getElementById('description').value
	var debit = document.getElementById('supplier_type').value
	
	var endpoint = "http://localhost:8080/LankaHardware/AddSupplierServlet"
//	var formData = new FormData();
//
//	
//	formData.append('name',name)
//	formData.append('email',email)
//	formData.append('phoneNum',phoneNum)
//	formData.append('description',description)
//	formData.append('supplier_type', debit)
//
//
//	
//	fetch(endpoint, {
//		method: "post",
//		body: formData
//	}).then(res => {
//		callGetAllSuppliersServlet()
//		setTimeout(function() {
//				$('#AddSupplierModal').modal('hide')
//		}, 2500);	
//	}
//	)
	AddSupplierModalHeader.style = "display: none;"
	AddSupplierModalBody.style = "text-align: center;"
	AddSupplierModalBody.innerHTML = `<div class="spinner-border text-warning" role="status" style="width: 2.5rem; height: 2.5rem;">
			                          <span class="visually-hidden">Loading...</span>
			                        </div>`
	AddSupplierFooter.style = "display: none;"
	
	$.post(endpoint, {name : name,email : email,phoneNum : phoneNum,description : description,supplier_type : debit }, function(response) {
		
		AddSupplierModalBody.style = "padding: 1rem;"
		AddSupplierModalBody.innerHTML = `<div style="display: flex; justify-content: center; align-items: center; column-gap: 10px;">
									        <lottie-player src="https://assets3.lottiefiles.com/packages/lf20_q7hiluze.json"  background="transparent"  speed="1"  style="width: 50px; height: 50px;" autoplay></lottie-player>
									        <span style="font-size: x-large;">${response}</span>
									    </div>`
		
		callGetAllSuppliersServlet()
		setTimeout(function() {
				$('#AddSupplierModal').modal('hide')
		}, 2500);	
	})
}
//
//update Suppliers
var isNew = true;

var editSupplierModalHeader = document.getElementById('editSupplierModalHeader')
var editSupplierModalBody = document.getElementById('editSupplierModalBody')
var editSupplierModalFooter = document.getElementById('editSupplierModalFooter')
var editCard = document.getElementById('card-body-edit')

function BuildEditSupplierModal(supNo,name,email,phoneNum,description,debit){
	editSupplierModalHeader.innerHTML = `<h5 class="modal-title1" id="modalCenterTitle1">Edit Supplier</h5>
							              <button
							                type="button"
							                class="btn-close"
							                data-bs-dismiss="modal"
							                aria-label="Close"
							              ></button>`

	
	editSupplierModalBody.innerHTML = `<form id="formAccountSettings" onsubmit = "return false">
                        <div class="row">
                          <div class="mb-3 col-md-6">
                            <label for="supNo" class="form-label">Supplier No.</label>
                            <input
                              class="form-control"
                              type="text"
                              id="supNoModal"
                              name="supNo"
                   				value="${supNo}"
                              autofocus
							readonly
                            />
                          </div>
                          <div class="mb-3 col-md-6">
                            <label for="name" class="form-label">Name</label>
                            <input class="form-control" type="text" name="name" value="${name}" id="nameModal"/>
                          </div>
                          <div class="mb-3 col-md-6">
                            <label for="email" class="form-label">E-mail</label>
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
                            <label class="form-label" for="phoneNumber">Phone Number</label>
                            <div class="input-group input-group-merge">
                              <span class="input-group-text">LK (+94)</span>
                              <input
                                type="text"
                                id="phoneNumModal"
                                name="phoneNum"
                                class="form-control"
								value="${phoneNum}"
                                
                              />
                            </div>
                          </div>
                          
                           <div class="mb-3 col-md-6">
                   			<label class="form-label" for="description">Supplier Description</label>
                			<textarea name="description" value="${description}" id="descriptionModal" cols="30" rows="7" class="form-control" placeholder="Description">${description}</textarea>
              			  </div>
                            <div class="mb-3 col-md-6">
                            <label for="debit" class="form-label">Debit Or Credit</label>
                            <select value="${debit}" id="debitModal" name = "debit" class="select2 form-select">
                              <option value="">Select Type</option>
                              <option value="Debit">Debit</option>
                              <option value="Credit">Credit</option>
                            
                            </select>
                          </div>
                        </div>
                         
                          
                   
                        <div class="mt-2">
                          <button type="submit" class="btn btn-primary me-2" id = "save" onclick ="callupdateSupplier()">Edit Supplier</button>
                          <button type="reset" class="btn btn-outline-secondary" id ="clear" onclick = "clearemployee()">Cancel</button>
                        </div>
                      </form>`



	editSupplierModalFooter.innerHTML = `<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
							                Close
							              </button>
							              <button type="button" class="btn btn-primary">Submit</button>`
	
}

function callupdateSupplier(){

	
	var supNo = document.getElementById('supNoModal').value
	var name = document.getElementById('nameModal').value
	var email = document.getElementById('emailModal').value
	var phoneNum = document.getElementById('phoneNumModal').value
	var description = document.getElementById('descriptionModal').value
	var debit = document.getElementById('debitModal').value
	
	console.log(name+email+phoneNum+description+debit)
	

//	
//	
	if(name == null)
	{
		name = "null";
	}
	if(email == null)
	{
		email = "null";
	}
	if(phoneNum == null)
	{
		phoneNum = "null";
	}
	if(description == null)
	{
		description = "null";
	}
	if(debit == null)
	{
		debit = "null";
	}
	
	
	var endpoint = "http://localhost:8080/LankaHardware/UpdateSupplier"
//	var formData = new FormData();
//	
//	for(const file of inputFile.files){
//		formData.append('inputFile', file)
//	}
//	
//	formData.append('empNo',empNo)
//	formData.append('name',name)
//	formData.append('email',email)
//	formData.append('designation',designation)
//	formData.append('address',address)
//	formData.append('gender', gender)
//	formData.append('date',date)
//	formData.append('wage',wage)
//	formData.append('salary',salary)
//
//	
$.post(endpoint, {supNo : supNo, name : name,email : email,description : description,supplier_type : debit }, function(response) {
		
		
		callGetAllSuppliersServlet()
		setTimeout(function() {
				$('#EditSupplierModal').modal('hide')
		}, 2500);	
	})
}
//delete supplier
var deleteSupplierModalHeader = document.getElementById('deleteSupplierModalHeader')
var deleteSupplierModalBody = document.getElementById('deleteSupplierModalBody')
var deleteSupplierModalFooter = document.getElementById('deleteSupplierModalFooter')

function createSupplierDeleteModal(supNo) {
	deleteSupplierModalHeader.innerHTML = `<button
					                type="button"
					                class="btn-close"
					                data-bs-dismiss="modal"
					                aria-label="Close"
					              ></button>`
	deleteSupplierModalHeader.style.display = ""

	deleteSupplierModalBody.innerHTML = `<div style="display: flex; flex-direction: column; text-align: center;">
					                <div class="icon-box">
					                  <i class="material-icons">&times;</i>
					                </div>						
					                <h4 class="modal-title w-100">Are you sure?</h4>
					                <p style="margin-top: 10px;">Do you really want to delete these records? This process cannot be undone.</p>
					              </div>`
	deleteSupplierModalBody.style.padding = ""

	deleteSupplierModalFooter.innerHTML = `<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
					                Close
					              </button>
					              <button type="button" class="btn btn-danger" onclick="callDeleteSupplierServlet('${supNo}')">Delete</button>`
	deleteSupplierModalFooter.style.display = ""
}

function callDeleteSupplierServlet(supNo) {
	deleteSupplierModalHeader.style = "display: none;"
	deleteSupplierModalBody.style = "text-align: center;"
	deleteSupplierModalBody.innerHTML = `<div class="spinner-border text-warning" role="status" style="width: 2.5rem; height: 2.5rem;">
			                          <span class="visually-hidden">Loading...</span>
			                        </div>`
	deleteSupplierModalFooter.style = "display: none;"
	$.post("http://localhost:8080/LankaHardware/RemoveSupplier", { supNo: supNo }, function(response) {

		deleteSupplierModalBody.style = "padding: 1rem;"
		deleteSupplierModalBody.innerHTML = `<div style="display: flex; justify-content: center; align-items: center; column-gap: 10px;">
									        <lottie-player src="https://assets3.lottiefiles.com/packages/lf20_q7hiluze.json"  background="transparent"  speed="1"  style="width: 50px; height: 50px;" autoplay></lottie-player>
									        <span style="font-size: x-large;">${response}</span>
									    </div>`

		callGetAllSuppliersServlet()

		setTimeout(function() {
			$('#deleteSupplierModal').modal('hide')
		}, 2500);
	})
}
// Feedbacks
var feedbacks = []
var feedbackstable = document.getElementById('feedback')

function callGetAllFeedbacksServlet(){
	$.get("http://localhost:8080/LankaHardware/GetAllFeedbacksServlet", function(response) {
				
		feedbacks = response
		
		buildAllFeedbacks();
	})
}

function buildAllFeedbacks(){
	feedbackstable.innerHTML = ''
	for(var i = 0; i < feedbacks.length; i++){
		var feedback = `<tr>
						<td>
							${feedbacks[i].feedid}
						</td>
						<td>
							${feedbacks[i].email}
						</td>
						<td>
							${feedbacks[i].subject}
						</td>
						<td>
							${feedbacks[i].feedback}
						</td>
						
						<td>
                          <div class="dropdown">
                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                              <i class="bx bx-dots-vertical-rounded"></i>
                            </button>
                            <div class="dropdown-menu">
                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#EditFeedbackModal" onclick="BuildEditFeedbackModal('');"
                                ><i class="bx bx-edit-alt me-1"></i> Edit</a
                              >
                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#FeedbackdeleteModal"  onclick="createFeedbackDeleteModal('${feedbacks[i].feedid}')"
                                ><i class="bx bx-trash me-1"></i> Delete</a
                              >
                            </div>
                          </div>
                        </td>
						
						</tr>`
						
		
		feedbackstable.innerHTML += feedback		
	}
}

function buildProfileImage(){
	const deactivateAcc = document.querySelector('#formAccountDeactivation');

    // Update/reset user image of account page
    let accountUserImage = document.getElementById('uploadedAvatar');
    const fileInput = document.querySelector('.account-file-input'),
      resetFileInput = document.querySelector('.account-image-reset');

    if (accountUserImage) {
      const resetImage = accountUserImage.src;
      fileInput.onchange = () => {
        if (fileInput.files[0]) {
          accountUserImage.src = window.URL.createObjectURL(fileInput.files[0]);
        }
      };
      resetFileInput.onclick = () => {
        fileInput.value = '';
        accountUserImage.src = resetImage;
      };
}
}
//delete feedback
var deleteFeedbackModalHeader = document.getElementById('feedbackdeleteModalHeader')
var deleteFeedbackModalBody = document.getElementById('feedbackdeleteModalBody')
var deleteFeedbackModalFooter = document.getElementById('feedbackdeleteModalFooter')

function createFeedBackDeleteModal(feedid) {
	deleteFeedbackModalHeader.innerHTML = `<button
					                type="button"
					                class="btn-close"
					                data-bs-dismiss="modal"
					                aria-label="Close"
					              ></button>`
	deleteFeedbackModalHeader.style.display = ""

	deleteFeedbackModalBody.innerHTML = `<div style="display: flex; flex-direction: column; text-align: center;">
					                <div class="icon-box">
					                  <i class="material-icons">&times;</i>
					                </div>						
					                <h4 class="modal-title w-100">Are you sure?</h4>
					                <p style="margin-top: 10px;">Do you really want to delete these records? This process cannot be undone.</p>
					              </div>`
	deleteFeedbackModalBody.style.padding = ""

	deleteFeedbackModalFooter.innerHTML = `<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
					                Close
					              </button>
					              <button type="button" class="btn btn-danger" onclick="callDeleteFeedbackServlet('${feedid}')">Delete</button>`
	deleteFeedbackModalFooter.style.display = ""
}

function callDeleteFeedbackServlet(feedid) {
	deleteFeedbackModalHeader.style = "display: none;"
	deleteFeedbackModalBody.style = "text-align: center;"
	deleteFeedbackModalBody.innerHTML = `<div class="spinner-border text-warning" role="status" style="width: 2.5rem; height: 2.5rem;">
			                          <span class="visually-hidden">Loading...</span>
			                        </div>`
	deleteFeedbackModalFooter.style = "display: none;"
	$.post("http://localhost:8080/LankaHardware/RemoveFeedback", { feedid: feedid }, function(response) {

		deleteFeedbackModalBody.style = "padding: 1rem;"
		deleteFeedbackModalBody.innerHTML = `<div style="display: flex; justify-content: center; align-items: center; column-gap: 10px;">
									        <lottie-player src="https://assets3.lottiefiles.com/packages/lf20_q7hiluze.json"  background="transparent"  speed="1"  style="width: 50px; height: 50px;" autoplay></lottie-player>
									        <span style="font-size: x-large;">${response}</span>
									    </div>`

		callGetAllFeedbacksServlet()

		setTimeout(function() {
			$('#FeedbackdeleteModal').modal('hide')
		}, 2500);
	})
}

//build Employeee search results
var searchLists = []

function buildSearchLists() {
	searchLists = []
	var search = document.getElementById("searchemp").value.toLowerCase()
	search = search.trim()

	
		for (var i = 0; i < employees.length; i++) {
			if(employees[i].name.toLowerCase().includes(search)||employees[i].empNo.toLowerCase().includes(search))
			
			searchLists.push(employees[i])
		
		
		buildAllEmployees(searchLists)
		
	} 

}
//build supplier search results

function buildSuplierSearchLists() {
	searchLists = []
	var search = document.getElementById("searchsup").value.toLowerCase()
	search = search.trim()

	
		for (var i = 0; i < suppliers.length; i++) {
			if(suppliers[i].name.toLowerCase().includes(search)||suppliers[i].supNo.toLowerCase().includes(search))
			
			searchLists.push(suppliers[i])
		
		
		buildAllSuppliers(searchLists)
		
	} 


}



