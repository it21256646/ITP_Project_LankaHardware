/**
 * 
 */
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
                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#FeedbackdeleteModal"  onclick="createFeedBackDeleteModal('${feedbacks[i].feedid}')"
                                ><i class="bx bx-trash me-1"></i> Delete</a
                              >
                            </div>
                          </div>
                        </td>
						
						</tr>`
						
		
		feedbackstable.innerHTML += feedback		
	}
} 

//Add Feedbacks

var isNew = true;

function test123(){
	console.log('test')
}

function callAddFeedbackServlet(){
	
	
	var subject = document.getElementById('subject').value
	var feedback = document.getElementById('feedback').value

	
	var endpoint = "http://localhost:8080/LankaHardware/AddFeedbackServlet"
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
	
	$.post(endpoint, {subject : subject,feedback : feedback }, function(response) {
		
		console.log(response)
		
	})
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

