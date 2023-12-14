/**
 * 
 */
 
 console.log("this is the voucher handle");


function validation() {
	

	//var image = document.getElementById('inputFile').value
	var code = document.getElementById('Vcode').value
	var amount = document.getElementById('Vamount').value
	var exp = document.getElementById('Vexp').value
	
	
	var codeError = document.getElementById('Vcode-error');
	var amountError = document.getElementById('Vamount-error');
	
	

  	//const nameRegex = /^[A-Za-z\s]+$/;
   // const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
   
  
   
       var valiCode = false;
       var valiAmount = false;
       var valiExp = false;
       
		
	  if (code == "") {	
	     codeError.innerHTML = 'Please enter a valid code';
	     valiCode = false;
	   }
	  
	   if (!code == "") {
	     codeError.innerHTML = '';
	     valiCode = true;
	   }
		
	    if(amount == ""){
			
			amountError.innerHTML ='Please select valid amount';
			valiAmount = false;
		}
		
		if(!amount == ""){
			
			amountError.innerHTML ='';
			valiAmount = true;
		}
		
		
		if(amount < 100){
			amountError.innerHTML ='Please enter a valid amount';
			valiAmount = false;
		}
	
		
		if(amount < 1){
			amountError.innerHTML ='Please enter a valid amount';	
			valiAmount = false;
		}
		
		
		if(isNaN(amount)){
			amountError.innerHTML ='Please add a valid number';
			valiAmount = false;
			  
		}
	
		
		if(!amount == "" && amount > 0 && !isNaN(amount) && amount < 100){
			amountError.innerHTML ='';	
			valiAmount = true;
		}
		
		
		
		//if(descript.length > 200){
		//	e.preventDefault();
		//	errorDes.innerHTML = 'Description must be lower than 200 characters';
		//}
				 
		//callAddStockServlet(Sname, category, brand, price, quantity, description, mf, exp, warrentyType, warNum, warPeriod );
		if(valiCode == true && valiAmount == true){
			

			if(exp == ""){
				exp = null;
			}
			
			
			callGetAllVoucherServlet()
			setTimeout(function() {
						$('#AddStockModal').modal('hide')
					}, 1000);
						
			callAddVoucherServlet(code, amount, exp);
			
			
		}
		
		
	}
	
	
var voucher = [];
var voucherTable = document.getElementById('voucher');

// view vouchers
function callGetAllVoucherServlet(){
	
	
	$.get("http://localhost:8081/LankaHardware/GetAllVoucherServlet", function(response) {
				
		voucher = response
		var varlen = voucher.length;
		buildAllVouchers(voucher, varlen);
		
	})
}

function buildAllVouchers(voucher, varlen){
	voucherTable.innerHTML = '';
	var showVoucherCount = document.getElementById('ItemCount');
	showVoucherCount.innerHTML = varlen + ' Records are available';
	
	//console.log(stock[1].itemID)
	for(var i = 0; i < varlen; i++){
		 			var VoucherInFo = `<tr>
								<td>
									${voucher[i].id}
								</td>
								<td>
									${voucher[i].code}
								</td>
								<td>
									${voucher[i].amount}
								</td>
								
								<td>
									${voucher[i].exp}
								</td>
								
								
								<td>
		                          <div class="dropdown">
		                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
		                              <i class="bx bx-dots-vertical-rounded"></i>
		                            </button>
                            

		                            	<div class="dropdown-menu">
				                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#ViewStockModal" onclick="BuildViewStockModal('${voucher[i].id}', '${voucher[i].code}','${voucher[i].amount}','${voucher[i].exp}')">
				                              <i class="fa-regular fa-eye"></i> View</a> 
				                               
				                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#ViewStockModal" onclick="BuildEditStockModal('${voucher[i].id}', '${voucher[i].code}','${voucher[i].amount}','${voucher[i].exp}')">
				                              <i class="bx bx-edit-alt me-1"></i>Edit</a> 
		
				                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="createDeleteModal('${voucher[i].id}')">
				                              <i class="bx bx-trash me-1"></i>Delete</a>
		                            	</div>
                          		  </div>
                       			 </td>
							</tr>`;
							//console.log(voucher[i].id);
							voucherTable.innerHTML += VoucherInFo;	
	}
	
}

//sortBYview

function callSortbyServlet(sort){
	var showType = document.getElementById('sortType');
	
	if(sort == 1){
		showType.innerHTML = 'Default';
	}
	if(sort == 2){
		showType.innerHTML = 'Code';
	}
	if(sort == 3){
		showType.innerHTML = 'Amount';
	}
	if(sort == 4){
		showType.innerHTML = 'Exp-Date';
	}
	
	
	var endpoint = "http://localhost:8080/LankaHardware/GetAllVoucherServlet";
	$.post(endpoint,{sort:sort}, function(response){
			voucher = response
		var varLen = voucher.length;
		
		buildAllVouchers(voucher, varLen);
	
	})
			
		
}

//sortBYview

function callSortbyServlet(sort){
	var showType = document.getElementById('sortType');
	
	if(sort == 1){
		showType.innerHTML = 'Default';
	}
	if(sort == 2){
		showType.innerHTML = 'code';
	}
	if(sort == 3){
		showType.innerHTML = 'amount';
	}
	if(sort == 4){
		showType.innerHTML = 'Exp-Date';
	}

	
	var endpoint = "http://localhost:8081/LankaHardware/GetAllStoreItemSortByServlet";
	$.post(endpoint,{sort:sort}, function(response){
			stock = response
		var stockLen = stock.length;
		
		buildAllStock(stock, stockLen);
	
	})
			
		
}



//Insert voucher

function callAddVoucherServlet(code, amount, exp){
		
		var endpoint = "http://localhost:8080/LankaHardware/AddVoucherServlet";
		
		
		$.post(endpoint,{code: code, amount:amount, exp:exp}, function(response){
			
		});
}	




//update voucher
var isNew = true;

var editStockModalHeader = document.getElementById('ViewStockModalHeader')
var editStockModalBody = document.getElementById('ViewStockModalBody')
var editStockModalFooter = document.getElementById('EditStockModalFooter')
var editCard = document.getElementById('card-body-edit')

function BuildEditStockModal(id ,code,amount,exp){
	console.log("Hey this is the build edit voucher modal")
	
	editStockModalHeader.innerHTML = '';
	editStockModalHeader.style = '';
	editStockModalHeader.innerHTML = `
							              <button
							                type="button"
							                class="btn-close"
							                data-bs-dismiss="modal"
							                aria-label="Close"
							              ></button>`;
							              
	editStockModalBody.style = 'diplay:block; position:relative; left:190px; <br><br>'						              
	editStockModalBody.innerHTML = '<h5 class="modal-title" id="modalCenterTitle">EDIT VOUCHER</h5>'

	editCard.innerHTML = `<form id="formAccountSettings" method="POST" onsubmit="return false">
                        <div class="row">
                          <div class="mb-3 col-md-6">
                            <label for="stockID" class="form-label">Voucher ID.</label>
                            <input
                              class="form-control"
                              type="text"
                              id="idM"
                              name="id"
                   				value="${id}"
							readonly
                            />
                          </div>
                          
                          
                          <div class="mb-3 col-md-6">
                            <label for="lastName" class="form-label">Coupon Code</label>
                            <input class="form-control" type="text"  id="codeM" name="code" value="${code}" autofocus/>
                             <span id="codeM-error" style="color:red; font-size:13px"></span>
                          </div>
                          
                           <div class="mb-3 col-md-6">
                            <label for="lastName" class="form-label">Coupon Code</label>
                            <input class="form-control" type="number" min="1" max="100" name="amount" value="${amount}" id="amountM" autofocus/>
                           <span id="amountM-error" style="color:red; font-size:13px"></span>
                          </div>
               
                          
                          <div class="mb-3 col-md-6">
                            <label for="exp" class="form-label">Expiry Date</label>
                         <input
                              type="date"
                              class="form-control"
                              id="expM"
                              name="exp"
							  value="${exp}"
                              placeholder = "None"
                            />
                             
                          </div>
                                      
                            
                        <div class="mt-2">
                          <button type="submit" class="btn btn-primary me-2" id = "save" onclick ="callupdateVoucher(); callGetAllVoucherServlet();')">Save</button>
                          <button type="reset" onclick ="BuildViewStockModal('${id}','${code}','${amount}','${exp}')" class="btn btn-outline-secondary" id ="clear" >Cancel</button>
                        </div>
                        </div>
                        
                      </form> `

	
}

var viewModalHeader = document.getElementById('ViewStockModalHeader')
var viewStockModalBody = document.getElementById('ViewStockModalBody')
var viewStockModalFooter = document.getElementById('ViewStockModalFooter')
var viewCard = document.getElementById('card-body-edit')


function BuildViewStockModal(id,code,amount,exp){
	

	viewModalHeader.innerHTML = `      <button
							                type="button"
							                class="btn-close"
							                data-bs-dismiss="modal"
							                aria-label="Close"
							              ></button>`

	viewStockModalBody.innerHTML ='';
	viewStockModalBody.style = 'diplay:block; position:relative; left:190px; <br><br>'						              
	viewStockModalBody.innerHTML = '<h5 class="modal-title" id="modalCenterTitle">View VOUCHER</h5>'
	
	editCard.innerHTML = `<form id="formAccountSettings" method="POST" onsubmit="return false">
                        <div class="row">
                          <div class="mb-3 col-md-6">
                            <label for="stockID" class="form-label">ID</label>
                            <input
                              class="form-control"
                              type="text"
                              id="StockIDModal"
                              name="stockID"
                   				value="${id}"
                              autofocus
							readonly
                            />
                          </div>
                          
                          
                          <div class="mb-3 col-md-6">
                            <label for="lastName" class="form-label">Voucher-Code</label>
                            <input class="form-control" type="text"  id="nameModal" value="${code}" id="stockName" readonly/>
                          </div>
                          
                          <div class="mb-3 col-md-6">
                            <label for="lastName" class="form-label">Amount</label>
                            <input class="form-control" type="text"  id="nameModal" value="${amount}" readonly/>
                          </div>
                          
                          <div class="mb-3 col-md-6">
                            <label for="lastName" class="form-label">Exp-Date</label>
                            <input class="form-control" type="text"  id="nameModal" value="${exp}"  readonly/>
                          </div>
                          

                          
                            
                        <div class="mt-2">
                          <button type="submit" onclick ="BuildEditStockModal('${id}','${code}','${amount}','${exp}')" class="btn btn-primary me-2" id = "save" >Edit</button>
                          <button type="reset"  class="btn btn-outline-secondary" id ="clear" data-bs-dismiss="modal">Cancel</button>
                        </div>
                        </div>
                        
                      </form>`

	viewStockModalFooter.innerHTML = ``
	
}

function callupdateVoucher(){
	//var inputFile = document.getElementById('updateModal')
	callGetAllVoucherServlet();
		setTimeout(function() {
				$('#EditStockModal').modal('hide')
		}, 1000);
		
		
		setTimeout(function() {
				$('#ViewStockModal').modal('hide')
		}, 1000);		
	
	var idM = document.getElementById('idM').value
	var codeM = document.getElementById('codeM').value
	var amountM = document.getElementById('amountM').value
	var expM = document.getElementById('expM').value
	
	//var idMerror = document.getElementById('idM').value
	var codeMerror = document.getElementById('codeM-error').value
	var amountMerror = document.getElementById('amountM-error').value
	var expMerror = document.getElementById('expM-error').value
	
       var valiCode = false;
       var valiAmount = false;
       var valiExp = false;
       
		
	  if (codeM == "") {	
	     codeMerror.innerHTML = 'Please enter a valid code';
	     valiCode = false;
	   }
	  
	   if (!codeM == "") {
	     codeMerror.innerHTML = '';
	     valiCode = true;
	   }
		
	    if(amountM == ""){
			
			amountMerror.innerHTML ='Please select valid amount';
			valiAmount = false;
		}
		
		if(!amountM == ""){
			
			amountMerror.innerHTML ='';
			valiAmount = true;
		}
		
		
		if(amountM < 100){
			amountMerror.innerHTML ='Please enter a valid amount';
			valiAmount = false;
		}
	
		
		if(amountM < 1){
			amountMerror.innerHTML ='Please enter a valid amount';	
			valiAmount = false;
		}
		
		
		if(isNaN(amountM)){
			amountMerror.innerHTML ='Please add a valid number';
			valiAmount = false;
			  
		}
	
		
		if(!amountM == "" && amountM > 0 && !isNaN(amountM) && amountM < 100){
			amountMerror.innerHTML ='';	
			valiAmount = true;
		}
		
	
		if(valiCode == true && valiAmount == true){
			

			if(expM == ""){
				expM = null;
			}
	
				var endpoint = "http://localhost:8080/LankaHardware/UpdateStock";
				
				setTimeout(function() {
				$('#EditStockModal').modal('hide')
			}, 1000);
		
		
			$.post(endpoint, { idM:idM, codeM:codeM, amountM:amountM, expM:expM  }, function(response) {
				console.log("this is after updateStock response");
				//stock = response;
				//callGetAllStockServlet(stock);
				console.log("response is: "+ response);
				
			});

		
	}
}


//delete Stock
var deleteModalHeader = document.getElementById('deleteModalHeader')
var deleteModalBody = document.getElementById('deleteModalBody')
var deleteModalFooter = document.getElementById('deleteModalFooter')

function createDeleteModal(id) {
	deleteModalHeader.innerHTML = `<button 
					                type="button"
					                class="btn-close"
					                data-bs-dismiss="modal"
					                aria-label="Close"
					              ></button>`
	deleteModalHeader.style.display = ""

	deleteModalBody.innerHTML = `<div style="display: flex; flex-direction: column; text-align: center;">
					                					
					                <h4 class="modal-title w-100">Are you sure?</h4>
					                <p style="margin-top: 10px;">Do you really want to delete the ${id} record? This process cannot be undone.</p>
					              </div>`;
					              
	deleteModalBody.style.padding = ""

	deleteModalFooter.innerHTML = `<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
					                Close
					              </button>
					              <button type="button" class="btn btn-danger" onclick="callDeleteStockServlet('${id}')">Delete</button>`
	deleteModalFooter.style.display = ""
}



function callDeleteStockServlet(id) {
	deleteModalHeader.style = "display:block;  margin-left: auto; margin-right: auto; width: 50%;";
	deleteModalHeader.innerHTML = '<img src="../assets/img/elements/lankaLogo.png" alt="user-avatar" class="d-block rounded" height="100" width="100" style="margin-left: 75px;">'
	deleteModalBody.style = "text-align: center; "
	deleteModalBody.innerHTML = ` <div class="spinner-border text-warning" role="status" style="width: 2.5rem; height: 2.5rem;">
			                          <span class="visually-hidden">Loading...</span>
			                        </div>`
	deleteModalFooter.style = "display: none;"
	$.post("http://localhost:8080/LankaHardware/RemoveItem", { id: id }, function(response) {
		console.log("Hey This is the calldeleteServlet function after the response");
		console.log("response is: ", response);

		deleteModalBody.style = "padding: 1rem;"
		deleteModalBody.innerHTML = `<hr><div style="display: flex; justify-content: center; align-items: center; column-gap: 10px;">
									        <lottie-player src="https://assets3.lottiefiles.com/packages/lf20_q7hiluze.json"  background="transparent"  speed="1"  style="width: 50px; height: 50px;" autoplay></lottie-player>
									        <span style="font-size: x-large;">${response}</span>
									    </div><hr>`

		callGetAllStockServlet();
		setTimeout(function() {
			$('#deleteModal').modal('hide')
		}, 2500);
	}
	)
}




//build search


function searchItem(){
	
		    var SearchDetails = document.getElementById('SearchDetails').value;
		  
			//console.log("THis is from search: " + SearchDetails);
			
			if(SearchDetails == "" || SearchDetails == null || SearchDetails == "undefined"){
				//alert("Please add some keywords to search")
				console.log("failed")
				callGetAllVoucherServlet();
			}
			else{
					
					$.post("http://localhost:8080/LankaHardware/GetSearchedVoucher", {SearchDetails: SearchDetails}, function(response) {
					voucher = response;	
					var varLen = voucher.length;
					console.log("Stock response: " + response)
					
					if(response == ""){
						buildNotfound(SearchDetails);
					}
					
					buildAllStock(voucher, varLen);

					
					
				})
				
			}
			
				
			
				
	}
		  
		  
function buildNotfound(keyword){
					setTimeout(function() {
						$('#deleteModal').modal('show')
					}, 1);


	deleteModalHeader.innerHTML = `
									<button 
					                type="button"
					                class="btn-close"
					                data-bs-dismiss="modal"
					                aria-label="Close"
					              ></button>`

	deleteModalBody.innerHTML = `<div style="display: flex; flex-direction: column; text-align: center;">
									
									 <img src="../assets/img/admin img/OIP.jpg" alt="user-avatar" class="d-block rounded" style="display:block; margin-left: auto; margin-right: auto; width: 10%; height: 10%;">
					                <br>					
					                <h3 class="modal-title w-100" style="color:#000;">SORRY!</h3>
					                
					               
					                <p style="margin-top: 10px;">We cannot find anything related to <small style="color:red; font-size: 20px;">${keyword}</small></p>
					              </div>`
					              
	deleteModalBody.style.padding = ""

	deleteModalFooter.innerHTML = `<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal" onclick="callGetAllStockServlet()">
					                Close
					              </button>
					              `
	deleteModalFooter.style.display = ""
	
	
	
		

//
	
}
	
/*function export_data(){
	var fp = XLSX.utils.table_to_book(stock,{sheet:'Store Records'});
	XLSX.write(fp,{
		bookType:'xlsx',
		type:'base64'
	});
	XLSX.writeFile(wb, 'Store records.xlsx');

}*/

function getTable(){
	$.get("http://localhost:8080/LankaHardware/GetAllVoucherServlet", function(response) {
				
	voucher = response
	var varLen = voucher.length;
	fileName = "Voucher_records";
	
	var workbook = new ExcelJS.Workbook();
	var worksheet = workbook.addWorksheet("Sheet1");
	//var data = JSON.parse(stock);
	var data = JSON.parse(JSON.stringify(stock))
	var keys = Object.keys(data[0]);
	
	for (var i = 0; i < keys.length; i++) {
    worksheet.getCell(String.fromCharCode(65 + i) + 1).value = keys[i];
  }
	  
	  // Add the data to the worksheet
	  for (var i = 0; i < data.length; i++) {
	    var values = Object.values(data[i]);
	    
	    for (var j = 0; j < values.length; j++) {
	      worksheet.getCell(String.fromCharCode(65 + j) + (i + 2)).value = values[j];
	    }
	  }
	  
	    // Convert the workbook to a binary Excel file
	  	workbook.xlsx.writeBuffer().then(function(buffer) {
	    // Create a blob object from the buffer
	    var blob = new Blob([buffer], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });
	    
	    // Create a download link element
	    var downloadLink = document.createElement("a");
	    downloadLink.href = window.URL.createObjectURL(blob);
	    downloadLink.download = fileName + ".xlsx";
	    
	    // Append the download link to the document body
	    document.body.appendChild(downloadLink);
	    
	    // Trigger the download by simulating a click on the download link
	    downloadLink.click();
	    
	    // Remove the download link from the document body
	    document.body.removeChild(downloadLink);
	    
  });
  
  
	
	})
	
}


function exportTableToExcel(tableID, filename = ''){
    var downloadLink;
    var dataType = 'application/vnd.ms-excel';
    var tableSelect = document.getElementById(tableID);
    var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');
    
    // Specify file name
    filename = filename?filename+'.xls':'Voucher_Records.xls';
    
    // Create download link element
    downloadLink = document.createElement("a");
    
    document.body.appendChild(downloadLink);
    
    if(navigator.msSaveOrOpenBlob){
        var blob = new Blob(['\ufeff', tableHTML], {
            type: dataType
        });
        navigator.msSaveOrOpenBlob(blob, filename);
    }else{
        // Create a link to the file
        downloadLink.href = 'data:' + dataType + ', ' + tableHTML;
    
        // Setting the file name
        downloadLink.download = filename;
        
        //triggering the function
        downloadLink.click();
    }
}
		