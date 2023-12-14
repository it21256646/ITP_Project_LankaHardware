/**
 * 
 */
//call GetNewQuestionsServlet
var newQuestions = []
var newQuestionsListElement
var newMessagesCount = document.getElementById('newMessagesCount')

function callGetNewQuestionsServlet(newQuestionsList) {
	$.get("http://localhost:8080/LankaHardware/GetNewQuestionsServlet", function(response) {

		newQuestions = response
		newQuestionsListElement = newQuestionsList
		buildNewQuestions(newQuestionsList, newQuestions)
		buildNewMessagesCount()
		buildSearch('new')
	})
}

//Build New Questions
function buildNewQuestions(newQuestionsList, newQuestions) {
	newQuestionsList.innerHTML = ''

	for (var i = 0; i < newQuestions.length; i++) {
		var question = `<tr>
	                        <td>
	                          <i class="fab fa-bootstrap fa-lg text-primary me-3"></i> <strong>${newQuestions[i].questionID}</strong>
	                        </td>
	                        <td style="display: flex;">
	                            <ul class="list-unstyled users-list m-0 avatar-group d-flex align-items-center">
	                                <li
	                                data-bs-toggle="tooltip"
	                                data-popup="tooltip-custom"
	                                data-bs-placement="top"
	                                class="avatar avatar-xs pull-up"
	                                title="${newQuestions[i].customer.email}"
	                                >
	                                    <img src="../assets/img/avatars/5.png" alt="Avatar" class="rounded-circle" />
	                                    
	                                </li>
	                            </ul>
	                            <span class="cutoff-text">${newQuestions[i].customer.email}</span>
	                        </td>
	                        
	                        <td><span class="badge bg-label-success me-1">${newQuestions[i].item.itemID}</span></td>
	
							<td><span>${newQuestions[i].questionDate}</span></td>
	
	                        <td>
	                            <span class="cutoff-text">${newQuestions[i].question}</span>
	                        </td>
	                        
	                        
	                        <td>
	                          <div class="dropdown">
	                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
	                              <i class="bx bx-dots-vertical-rounded"></i>
	                            </button>
	                            <div class="dropdown-menu">
	                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#modalCenter" id="openAnswerModal"
	                                onclick="createAnswerModal('${newQuestions[i].question}', '${newQuestions[i].questionID}', '${newQuestions[i].customer.email}', '${newQuestions[i].item.name}', '${newQuestions[i].item.mainImg}');"><i class="bx bx-edit-alt me-2"></i> Answer</a
	                              >
	                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#deleteModal"
	                                onclick="createDeleteModal('${newQuestions[i].questionID}', 'new')"><i class="bx bx-trash me-2"></i> Delete</a
	                              >
	                            </div>
	                          </div>
	                        </td>
                      </tr>`

		newQuestionsList.innerHTML += question
	}
}

//build new messages count
function buildNewMessagesCount() {
	newMessagesCount.innerHTML = `${newQuestions.length}`
}

//call GetAnsweredQuestionsServlet
var answeredQuestions = []
var answeredQuestionsListElement

function callGetAnsweredQuestionsServlet(answeredQuestionsList) {
	$.get("http://localhost:8080/LankaHardware/GetAnsweredQuestionsServlet", function(response) {

		answeredQuestions = response
		answeredQuestionsListElement = answeredQuestionsList

		buildAnsweredQuestions(answeredQuestionsList, answeredQuestions)
		buildSearch('answered')
	})
}

function buildAnsweredQuestions(answeredQuestionsList, answeredQuestions) {
	answeredQuestionsList.innerHTML = ''

	for (var i = 0; i < answeredQuestions.length; i++) {
		var answer = `<tr>
                          <td>
                            <i class="fab fa-bootstrap fa-lg text-primary me-3"></i> <strong>${answeredQuestions[i].questionID}</strong>
                          </td>
                          <td style="display: flex;">
                              <ul class="list-unstyled users-list m-0 avatar-group d-flex align-items-center">
                                  <li
                                  data-bs-toggle="tooltip"
                                  data-popup="tooltip-custom"
                                  data-bs-placement="top"
                                  class="avatar avatar-xs pull-up"
                                  title="${answeredQuestions[i].customer.email}"
                                  >
                                      <img src="../assets/img/avatars/5.png" alt="Avatar" class="rounded-circle" />
                                      
                                  </li>
                              </ul>
                              <span class="cutoff-text">${answeredQuestions[i].customer.email}</span>
                          </td>
                          
                          <td><span class="badge bg-label-success me-1">${answeredQuestions[i].item.itemID}</span></td>
                          
                          <td><span>${answeredQuestions[i].answerDate}</span></td>
                          
                          <td>
                              <span class="cutoff-text">${answeredQuestions[i].question}</span>
                          </td>
                          
                          <td>
                              <span class="cutoff-text">${answeredQuestions[i].answer}</span>
                          </td>
                          
                          <td>
                            <div class="dropdown">
                              <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                <i class="bx bx-dots-vertical-rounded"></i>
                              </button>
                              <div class="dropdown-menu">
                                <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#modalCenter2"
                                  onclick="createViewAnswerModal('${answeredQuestions[i].question}', '${answeredQuestions[i].answer}', '${answeredQuestions[i].questionID}')"><i class="bx bx-edit-alt me-2"></i> View</a
                                >
                                <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#deleteModal"
                                  onclick="createDeleteModal('${answeredQuestions[i].questionID}', 'answered')"><i class="bx bx-trash me-2"></i> Delete</a
                                >
                              </div>
                            </div>
                          </td>
                    </tr>`

		answeredQuestionsList.innerHTML += answer
	}
}

//build search
function buildSearch(from) {
	var dynamicSearch = document.getElementById('dynamicSearch')

	dynamicSearch.innerHTML = `<div class="nav-item d-flex align-items-center">
				                  <i class="bx bx-search fs-4 lh-0"></i>
				                  <input
				                    type="text"
				                    class="form-control border-0 shadow-none"
				                    placeholder="Search..."
				                    aria-label="Search..."
				                    id="${from}Search"
				                    oninput="buildSearchResults('${from}')"
				                  />
				                </div>`
}

//create viewAnswerModal
var viewAnswerModalHeader = document.getElementById('viewAnswerModalHeader')
var viewAnswerModalBody = document.getElementById('viewAnswerModalBody')
var viewAnswerModalFooter = document.getElementById('viewAnswerModalFooter')
var currentAnswer

function createViewAnswerModal(question, answer, questionID) {
	currentAnswer = answer

	viewAnswerModalHeader.innerHTML = `<h5 class="modal-title" id="modalCenterTitle">View the Answer</h5>
					              <button
					                type="button"
					                class="btn-close"
					                data-bs-dismiss="modal"
					                aria-label="Close"
					              ></button>`
	viewAnswerModalHeader.style.display = ""

	viewAnswerModalBody.innerHTML = `<span>${question}</span>
					              <div class="row mt-3">
					                <div class="mb-3">
					                    <label class="form-label" for="answerTextArea">Your Answer</label>
					                    <textarea id="editAnswerTextArea" class="form-control" placeholder="">${answer}</textarea>
					                </div>
					              </div>`
	viewAnswerModalBody.style.padding = ""

	viewAnswerModalFooter.innerHTML = `<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
					                Close
					              </button>
					              <button type="button" class="btn btn-primary" onclick="toEditAnswerServlet('${questionID}')">Submit</button>`
	viewAnswerModalFooter.style.display = ""
}

//call AnswerQuestionServlet
var openAnswerModal = document.getElementById('openAnswerModal')
var answerModalHeader = document.getElementById('answerModalHeader')
var answerModalBody = document.getElementById('answerModalBody')
var answerModalFooter = document.getElementById('answerModalFooter')

function createAnswerModal(question, questionID, customerEmail, itemName, mainImg) {
	answerModalHeader.innerHTML = `<h5 class="modal-title" id="modalCenterTitle">Type the Answer</h5>
					              <button
					                type="button"
					                class="btn-close"
					                data-bs-dismiss="modal"
					                aria-label="Close"
					              ></button>`
	answerModalHeader.style.display = ""

	answerModalBody.innerHTML = `<span>${question}</span>
					              <div class="row mt-3">
					                <div class="mb-3">
					                    <label class="form-label" for="answerTextArea">Answer</label>
					                    <textarea id="answerTextArea" class="form-control" placeholder=""></textarea>
					                </div>
					              </div>`
	answerModalBody.style.padding = ""

	answerModalFooter.innerHTML = `<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
					                Close
					              </button>
					              <button type="button" class="btn btn-primary" onclick="toAnswerServlet('${question}', '${questionID}', '${customerEmail}', '${itemName}', '${mainImg}');">Submit</button>`
	answerModalFooter.style.display = ""
}

function toAnswerServlet(question, questionID, customerEmail, itemName, mainImg) {
	var answer = document.getElementById('answerTextArea').value
	answer = answer.trim()

	if (answer.length > 0) callAnswerQuestionServlet(question, answer, questionID, customerEmail, itemName, mainImg)
}

function callAnswerQuestionServlet(question, answer, questionID, customerEmail, itemName, mainImg) {
	answerModalHeader.style = "display: none;"
	answerModalBody.style = "text-align: center;"
	answerModalBody.innerHTML = `<div class="spinner-border text-warning" role="status" style="width: 2.5rem; height: 2.5rem;">
		                          <span class="visually-hidden">Loading...</span>
		                        </div>`
	answerModalFooter.style = "display: none;"
	
	$.post("http://localhost:8080/LankaHardware/AnswerQuestionServlet", { question: question, answer: answer, questionID: questionID, customerEmail: customerEmail, itemName: itemName, mainImg: mainImg }, function(response) {

		answerModalBody.style = "padding: 1rem; text-align: initial;"
		answerModalBody.innerHTML = `<div style="display: flex; justify-content: center; align-items: center; column-gap: 10px;">
									        <lottie-player src="https://assets3.lottiefiles.com/packages/lf20_q7hiluze.json"  background="transparent"  speed="1"  style="width: 50px; height: 50px;" autoplay></lottie-player>
									        <span style="font-size: x-large;">${response}</span>
									 </div>`
		callGetNewQuestionsServlet(newQuestionsListElement)

		setTimeout(function() {
			$('#modalCenter').modal('hide')
		}, 2500);
	})
}

//call EditAnsweredQuestionsServlet
function toEditAnswerServlet(questionID) {
	var newAnswer = document.getElementById('editAnswerTextArea').value
	newAnswer = newAnswer.trim()

	if (newAnswer != currentAnswer) callEditAnsweredQuestionsServlet(newAnswer, questionID)
}

function callEditAnsweredQuestionsServlet(answer, questionID) {
	viewAnswerModalHeader.style = "display: none;"
	viewAnswerModalBody.style = "text-align: center;"
	viewAnswerModalBody.innerHTML = `<div class="spinner-border text-warning" role="status" style="width: 2.5rem; height: 2.5rem;">
			                          <span class="visually-hidden">Loading...</span>
			                        </div>`
	viewAnswerModalFooter.style = "display: none;"
	$.post("http://localhost:8080/LankaHardware/EditAnsweredQuestionsServlet", { answer: answer, questionID: questionID }, function(response) {

		viewAnswerModalBody.style = "padding: 1rem;"
		viewAnswerModalBody.innerHTML = `<div style="display: flex; justify-content: center; align-items: center; column-gap: 10px;">
									        <lottie-player src="https://assets3.lottiefiles.com/packages/lf20_q7hiluze.json"  background="transparent"  speed="1"  style="width: 50px; height: 50px;" autoplay></lottie-player>
									        <span style="font-size: x-large;">${response}</span>
									    </div>`

		callGetAnsweredQuestionsServlet(answeredQuestionsListElement)

		setTimeout(function() {
			$('#modalCenter2').modal('hide')
		}, 2500);
	})
}

//call DeleteQuestionServlet
var deleteModalHeader = document.getElementById('deleteModalHeader')
var deleteModalBody = document.getElementById('deleteModalBody')
var deleteModalFooter = document.getElementById('deleteModalFooter')

function createDeleteModal(questionID, from) {
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
					              <button type="button" class="btn btn-danger" onclick="callDeleteQuestionServlet('${questionID}', '${from}')">Delete</button>`
	deleteModalFooter.style.display = ""
}

function callDeleteQuestionServlet(questionID, from) {
	deleteModalHeader.style = "display: none;"
	deleteModalBody.style = "text-align: center;"
	deleteModalBody.innerHTML = `<div class="spinner-border text-warning" role="status" style="width: 2.5rem; height: 2.5rem;">
			                          <span class="visually-hidden">Loading...</span>
			                        </div>`
	deleteModalFooter.style = "display: none;"
	$.post("http://localhost:8080/LankaHardware/DeleteQuestionServlet", { questionID: questionID }, function(response) {

		deleteModalBody.style = "padding: 1rem;"
		deleteModalBody.innerHTML = `<div style="display: flex; justify-content: center; align-items: center; column-gap: 10px;">
									        <lottie-player src="https://assets3.lottiefiles.com/packages/lf20_q7hiluze.json"  background="transparent"  speed="1"  style="width: 50px; height: 50px;" autoplay></lottie-player>
									        <span style="font-size: x-large;">${response}</span>
									    </div>`

		if (from == 'answered') callGetAnsweredQuestionsServlet(answeredQuestionsListElement)
		else if (from == 'new') callGetNewQuestionsServlet(newQuestionsListElement)

		setTimeout(function() {
			$('#deleteModal').modal('hide')
		}, 2500);
	})
}

//build search results
var searchResults = []

function buildSearchResults(from) {
	searchResults = []
	var search = document.getElementById(`${from}Search`).value.toLowerCase()
	search = search.trim()

	if (from == 'new') {
		for (var i = 0; i < newQuestions.length; i++) {
			if(newQuestions[i].questionID.toLowerCase().includes(search)) searchResults.push(newQuestions[i])
		}
		
		buildNewQuestions(newQuestionsList, searchResults)
		
	} else if (from == 'answered') {
		for (var i = 0; i < answeredQuestions.length; i++) {
			if(answeredQuestions[i].questionID.toLowerCase().includes(search)) searchResults.push(answeredQuestions[i])
		}
		
		buildAnsweredQuestions(answeredQuestionsList, searchResults)
	}


}