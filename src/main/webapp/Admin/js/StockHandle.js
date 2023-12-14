console.log("this is the stock handle");


function validation() {
	
	console.log("THis is the validation")
	//var image = document.getElementById('inputFile').value
	var Sname = document.getElementById('stockName').value
	var category = document.getElementById('stockCat').value
	var brand = document.getElementById('stockBrand').value
	var price = document.getElementById('stockPrice').value
	var quantity = document.getElementById('stockQ').value
	var descript = document.getElementById('stockDes').value
	var mf = document.getElementById('stockMf').value
	var exp = document.getElementById('stockExp').value
	var warNone = document.getElementById('WorNone').value
	var warAva = document.getElementById('WorAvail').value

	
	
	var errorName = document.getElementById('name-error');
	var errorCat = document.getElementById('cat-error');
	var errorBrand = document.getElementById('Brand-error');
	var errorPrice = document.getElementById('price-error');
	var errorQ = document.getElementById('quantity-error');
	var errorDes = document.getElementById('disc-error');
	var errorMF = document.getElementById('mf-error');
	var errorExp = document.getElementById('exp-error');
	var errorwartype = document.getElementById('wartype-error');
	var errorsize = document.getElementById('size-error');
	var errorSub = document.getElementById('sub-error');
  

  	//const nameRegex = /^[A-Za-z\s]+$/;
   // const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
   
  
   
       var valiName = false;
       var valiCat = false;
       var valiBrand = false;
       var valiPrice = false;
       var valiQ = false;
       var valiDes = false;
       var valiSub = false;
		
	  if (Sname == "") {	
	     errorName.innerHTML = 'Please enter a valid name';
	     valiName = false;
	   }
	  
	   if (!Sname == "") {
	     errorName.innerHTML = '';
	     valiName = true;
	   }
		
	    if(category == ""){
			
			errorCat.innerHTML ='Please select valid category';
			valiCat = false;
		}
		
		if(!category == ""){
			
			errorCat.innerHTML ='';
			valiCat = true;
		}	
		
		
		if(brand == "" || brand == null){
			errorBrand.innerHTML ='Please enter a valid brand name';
			valiBrand = false;
		}
		
		if(!brand == ""){
			errorBrand.innerHTML ='';
			valiBrand = true;
		}
		
		//if(brand.length>20){
		//	e.preventDefault();
		//	errorBrand.innerHTML ='Brand name must be shorter than 25 characters';
		//}
		
		if(price == "" || price == null){
			errorPrice.innerHTML ='Please enter a valid price (empty)';	
			valiPrice = false;
		}
		
		
		if(isNaN(price)){
			errorPrice.innerHTML ='Please add a valid number (format Error)';
			valiPrice = false;
			  
		}
		
		
		if (price < 0){
			errorPrice.innerHTML ='Price must be higher than 0';
			valiPrice = false;
		}
		

		if(price > 1000000){
			errorPrice.innerHTML = 'Please enter a valid price less than 1000000';
			valiPrice = false;
		}
		
		if(!price == "" && price > 0 && !isNaN(price) && price < 1000000){
			errorPrice.innerHTML ='';	
			valiPrice = true;
		}
		
		
		if(quantity == "" || quantity == null){
			errorQ.innerHTML = 'Please enter a valid quantity (empty)';
			valiQ = false;
		}
		
		if(isNaN(quantity)){
			errorQ.innerHTML = 'Please Enter a number value'
		}
		if(quantity < 1){
			errorQ.innerHTML = 'quantity must be higher than 0';
			valiQ = false;
		}
		
		if(quantity > 0 && !quantity == "" && !isNaN(quantity)){
			errorQ.innerHTML = '';
			valiQ = true;
		}
		
		if(descript == "" || descript == null){
			errorDes.innerHTML = 'Please enter a valid discription';
			valiDes = false;
		}
		
		if(!descript == ""){
			errorDes.innerHTML = '';
			valiDes = true;
		}
		
		
		
		//if(descript.length > 200){
		//	e.preventDefault();
		//	errorDes.innerHTML = 'Description must be lower than 200 characters';
		//}
				 
		//callAddStockServlet(Sname, category, brand, price, quantity, description, mf, exp, warrentyType, warNum, warPeriod );
		if(valiName == true && valiCat == true&& valiPrice == true && valiQ == true && valiDes == true && valiBrand == true){
			
			var warrentyType;
			
			if(mf == ""){
				mf = null;
			}
			if(exp == ""){
				exp = null;
			}
			if(img == ""){
				img == "None";
			}
			
			if(document.getElementById('WorNone').checked == true){
				warrentyType = 'None';
				warNum = 0;
				warPeriod = 'None';
			}
	
			else if(document.getElementById('WorAvail').checked == true){
				
				var warNum = document.getElementById('warNum').value
				var warPeriod = document.getElementById('warPeriod').value
				
				warrentyType = 'Available';
			}
			console.log("Date in validation: " + mf)
			console.log(warrentyType)
			console.log(warNum);
			console.log(warPeriod);
			
			callGetAllStockServlet()
			setTimeout(function() {
						$('#AddStockModal').modal('hide')
					}, 1000);
					
			
				
			callAddStockServlet(Sname, category, brand, price, quantity, descript, mf, exp, warrentyType, warNum, warPeriod);
			GenerateBarCode();
			
		}
		
		
	}


function GenerateBarCode(){
	
	callGetAllStockServlet()
	setTimeout(function() {
		$('#BarCodeModal').modal('show')
	}, 1000);
					
	var BarModalHeader = document.getElementById('BarCodeModalHeader')
	var BarStockModalBody = document.getElementById('BarCodeModalBody')
	var BarStockModalFooter = document.getElementById('BarCodeModalFooter')
	var BarCodeModalTitle = document.getElementById('BarCodeModalTitle')
	
	$.get("http://localhost:8081/LankaHardware/GetAllItemsServlet", function(response) {
				
	stock = response
	var stockLen = stock.length;
	
	var last_Item = stock[stockLen-1].itemID;
	
							JsBarcode('#barcode', last_Item,{
									format: 'code128',
									displayValue: true,
								});			
								              					
	BarModalHeader.style = '';
	BarModalHeader.innerHTML = `
						              <button
						                type="button"
						                class="btn-close"
						                data-bs-dismiss="modal"
						                aria-label="Close"
						              ></button>`;
	
	BarCodeModalTitle.style =''; 
	BarCodeModalTitle.innerHTML = '<div style="display:block; justify-content: center; margin-left: auto; margin-right: auto; width: 50%;"><h2 style="color: #00b300; position:relative; left:60px">Success</h2> <h6 style="color: #b2beb5">Congrats! new item successfully added to the Lanka Hardware database</h6></div>';
	
	BarStockModalBody.style = "display:block;  margin-left: auto; margin-right: auto; width: 50%;";
	
	//viewStockModalBody.innerHTML += 'This is the Barcode for This store Item' ;	
	BarStockModalFooter.style = "padding:10px; display:block; justify-content: center; margin-left: 160px; margin-right: auto; width: 50%;";	
	BarStockModalFooter.innerHTML = `<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal" onclick="callGetAllStockServlet()">Close</button>  <button type="button" class="btn btn-primary me-2" data-bs-dismiss="modal" onclick="callGetAllStockServlet()">View Store</button>`;		
	
	
	})	
}

	
function callwarrentyDetails(){
	 
	var WarrentyDetails = [];
	var WarrentyNone = [];
	var WarrentyDetailstoPage = document.getElementById('WarrentyDetailstoPage');
	
	var WarrentyDetails = '<input class="form-control" type="number" min="1" value="0" id="warNum" name="warNum"> <br> <select class="form-select" id="warPeriod" aria-label="Default select example" name="warPeriod"> <option>Day</option><option>Week</option><option>Month</option><option>Year</option>  </select>  <span id="wartype-error" style="color:red; font-size:13px"></span>';
	var WarrentyNone = '';	

	
	if(document.getElementById('WorNone').checked == true){
		WarrentyDetailstoPage.innerHTML = WarrentyNone;
		console.log("noneCheked")
	}
	
	else if(document.getElementById('WorAvail').checked == true){
			WarrentyDetailstoPage.innerHTML = WarrentyDetails;
			console.log("available checked")
	}
	
}

function callEditwarrentyDetails(warrentyType){
	
	var war = warrentyType;
	var WarrentyDetailstoPage = document.getElementById('WarrentyDetailstoPage');
	
	var WarrentyNone = '';	

	
	if(war == "None"){
		WarrentyDetailstoPage.innerHTML = WarrentyNone;
		console.log("noneCheked")
	}
	
	else if(war == "Available"){
			var WarrentyDetailstoPage = document.getElementById('WarrentyDetailstoPage');
			var WarrentyDetails = '<input class="form-control" type="number" min="1" value="0" id="warNum" name="warNum"> <br> <select class="form-select" id="warPeriod" aria-label="Default select example" name="warPeriod"> <option>Day</option><option>Week</option><option>Month</option><option>Year</option></select>';
			
			WarrentyDetailstoPage.innerHTML = WarrentyDetails;
			console.log("available checked")
	}
	
	
}


function isNoneChecked(warrentyType){
	if(warrentyType == 'None' || warrentyType=="" || warrentyType == "undefined"){
		return true;
	}
	else{
		return false;
	}
	
}

function isAvailableChecked(warrentyType){
	if(warrentyType == 'Available'){
		return true;
	}
	else{
		return false;
	}
}
/*function callUpdatewarrentyDetails(warrentyType, warNum, WarrantyPeriod){
	
	var WarrentyDetailstoPage = document.getElementById('WarrentyDetailstoPage');
	
	if(warrentyType == 'None'){
		WarrentyDetailstoPage.innerHTML = '';
	}
	
	else if(warrentyType == 'Available') {
		WarrentyDetailstoPage.innerHTML ='<input class="form-control" type="number" value="'+warNum+'" id="html5-number-input" name="warNum"> <br> <select class="form-select" id="exampleFormControlSelect1" aria-label="Default select example" name="warPeriod"> <option selected="">'+WarrantyPeriod+'</option> <option>Day</option><option>Week</option><option>Month</option><option>Year</option>  </select>';
		}
	
}
*/


var stock = []
var stocktable = document.getElementById('stock')

// view stock items
function callGetAllStockServlet(){
	
	$.get("http://localhost:8081/LankaHardware/GetAllItemsServlet", function(response) {
				
		stock = response
		var stockLen = stock.length;
		buildAllStock(stock, stockLen);
		
	})
}

function buildAllStock(stock, stockLen){
	stocktable.innerHTML = '';
	var showItemCount = document.getElementById('ItemCount');
	showItemCount.innerHTML = stockLen + ' Records are available';
	
	//console.log(stock[1].itemID)
	for(var i = 0; i < stockLen; i++){
		 			var stockInFo = `<tr>
								<td>
									${stock[i].itemID}
								</td>
								<td>
									${stock[i].name}
								</td>
								<td>
									${stock[i].type}
								</td>
								
								<td>
									${stock[i].brand}
								</td>
								<td>
									${stock[i].quantity}
								</td>
								<td>
									LKR. ${stock[i].price}
								</td>
								
								<td>
		                          <div class="dropdown">
		                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
		                              <i class="bx bx-dots-vertical-rounded"></i>
		                            </button>
                            

		                            	<div class="dropdown-menu">
				                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#ViewStockModal" onclick="BuildViewStockModal('${stock[i].itemID}', '${stock[i].name}','${stock[i].type}','${stock[i].brand}','${stock[i].price}','${stock[i].quantity}','${stock[i].description}','${stock[i].mfDate}','${stock[i].expDate}','${stock[i].warrentyType}' ,'${stock[i].warNum}','${stock[i].WarrantyPeriod}')">
				                              <i class="fa-regular fa-eye"></i> View</a> 
				                               
				                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#ViewStockModal" onclick="BuildEditStockModal('${stock[i].itemID}', '${stock[i].name}','${stock[i].type}','${stock[i].brand}','${stock[i].price}','${stock[i].quantity}','${stock[i].description}','${stock[i].mfDate}','${stock[i].expDate}','${stock[i].warrentyType}' ,'${stock[i].warNum}','${stock[i].WarrantyPeriod}')">
				                              <i class="bx bx-edit-alt me-1"></i>Edit</a> 
		
				                              <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" data-bs-target="#deleteModal" onclick="createDeleteModal('${stock[i].itemID}')">
				                              <i class="bx bx-trash me-1"></i>Delete</a>
		                            	</div>
                          		  </div>
                       			 </td>
							</tr>`;
							//console.log(stock[i].itemID);
							stocktable.innerHTML += stockInFo;	
	}
}



//sortBYview

function callSortbyServlet(sort){
	var showType = document.getElementById('sortType');
	
	if(sort == 1){
		showType.innerHTML = 'Default';
	}
	if(sort == 2){
		showType.innerHTML = 'Name';
	}
	if(sort == 3){
		showType.innerHTML = 'Category';
	}
	if(sort == 4){
		showType.innerHTML = 'Mf-Date';
	}
	if(sort == 5){
		showType.innerHTML = 'Exp-Date';
	}
	
	var endpoint = "http://localhost:8081/LankaHardware/GetAllStoreItemSortByServlet";
	$.post(endpoint,{sort:sort}, function(response){
			stock = response
		var stockLen = stock.length;
		
		buildAllStock(stock, stockLen);
	
	})
			
		
}





//Insert Stock

function callAddStockServlet(name, category, brand, price, quantity, description, mf_date, exp_date, warrentyType, warNum, warPeriod){
		
		var endpoint = "http://localhost:8081/LankaHardware/AddStoreItemsServlet";
		
		console.log("This is before addstoreitems")
		console.log("Inputs: " + name + category+brand+price+quantity+ description+mf_date+exp_date+ warrentyType+ warNum+ warPeriod);
	
		
		$.post(endpoint,{ name: name, category: category, brand: brand, price: price, quantity: quantity, description: description, mf_date: mf_date, exp_date: exp_date, warrentyType: warrentyType , warNum : warNum, warPeriod: warPeriod}, function(response){
			console.log("this is after add response")
		});
}	
		
	
	
	//console.log(name);
	
	
	//var formData = new FormData();
	
	/*for(const file of image.file){
		formData.append('update', file)
	}
	

	formData.append('name',name)
	formData.append('Type',Type)
	formData.append('brand',brand)
	formData.append('unit_price',unit_price)
	formData.append('quantity',quantity)
	formData.append('description', description)
	formData.append('mf_date',mf_date)
	formData.append('exp_date',exp_date)
	formData.append('warrentyType',warrentyType)
	formData.append('warrentyNum',warrentyNum)
	formData.append('warrentyPeriod',warrentyPeriod)
	
	fetch(endpoint, {
//		method: "post",
//		body: formData
//	}).then(res => {
//		callGetAllEmployeesServlet()
//		setTimeout(function() {
//				$('#AddEmoloyeeModal').modal('hide')
//		}, 2500);	
//	}
//	)

*/

//update Stock
var isNew = true;

var editStockModalHeader = document.getElementById('ViewStockModalHeader')
var editStockModalBody = document.getElementById('EditStockModalBody')
var editStockModalFooter = document.getElementById('EditStockModalFooter')
var editCard = document.getElementById('card-body-edit')

function BuildEditStockModal(itemID,name,type,brand,price,quantity,description,mfDate,expDate, warrentyType, warNum, WarrantyPeriod ){
	console.log("Hey this is the build edit stock modal")
	
	var checkNone = isNoneChecked(warrentyType);
	var checkAva = isAvailableChecked(warrentyType);
	
	editStockModalHeader.innerHTML = '';
	editStockModalHeader.style = '';	
	editStockModalHeader.innerHTML = `<h5 class="modal-title" id="modalCenterTitle">EDIT ITEMS</h5>
							              <button
							                type="button"
							                class="btn-close"
							                data-bs-dismiss="modal"
							                aria-label="Close"
							              ></button>`;
							              
	editStockModalBody.innerHTML = `<div>
						              	 <div class="card-body">
                      <div class="d-flex align-items-start align-items-sm-center gap-4">
                        <img src="../assets/img/elements/lankaLogo.png" alt="user-avatar" class="d-block rounded" height="100%" width="100%" id="uploadedAvatar">
                        
                      </div>
                    </div>
						                      </div>`
	
	

	editCard.innerHTML = `<form id="formAccountSettings" method="POST" onsubmit="return false">
                        <div class="row">
                          <div class="mb-3 col-md-6">
                            <label for="stockID" class="form-label">Item ID.</label>
                            <input
                              class="form-control"
                              type="text"
                              id="StockIDModal"
                              name="stockID"
                   				value="${itemID}"
							readonly
                            />
                          </div>
                          
                          
                          <div class="mb-3 col-md-6">
                            <label for="lastName" class="form-label">Name</label>
                            <input class="form-control" type="text"  id="nameModal" name="stockName" value="${name}" id="stockName" autofocus/>
                          </div>
                          
                          
                          <div class="mb-3 col-md-6">
                            <label for="stockCat" class="form-label">Category</label>
                            <select id="TypeModal" name = "stockCat" class="select2 form-select">
                            	 <option>${type}</option>
                               <option value="mechanical">mechanical</option>
		                        <option value="building">building</option>
		                        <option value="electrical">electronics & electrical</option>
		                        <option value="tools">tools</option>
		                        <option value="general">general</option>
		                            
                            </select>
                            
                            
                          </div>
                          
                          <div class="mb-3 col-md-6">
                            <label for="firstName" class="form-label">Sub-Type</label>
                            <input
                              class="form-control"
                              type="text"
                              id="subType"
                              name="subType" 
                   
                              autofocus
                            />
                            <span id="sub-error" style="color:red; font-size:13px"></span>
                            </div>
                          
                          
                             <div class="mb-3 col-md-6">
                            <label for="stockCat" class="form-label">Size</label>
                            <select id="StockSize" name = "StockSize" class="select2 form-select">
                            	 <option value="None">None</option>
                                <option value="Small">Small</option>
		                        <option value="Medium">Medium</option>
		                        <option value="Large">Large</option>
		                        <option value="XXL">XXL</option>
		                        <option value="XXXL">XXXL</option>
		                            
                            </select>
                             <span id="size-error" style="color:red; font-size:13px"></span>

                          </div>
                          
                          
                          
                          <div class="mb-3 col-md-6">
                            <label for="brand" class="form-label">Brand</label>
                            <input
                              type="text"
                              class="form-control"
                              id="brandModal"
                              name="stockBrand"
								value="${brand}"
                              placeholder = "Unbrand."
                            />
                          </div>
                          <div class="mb-3 col-md-6">
                            <label class="form-label" for="basic-default-phone">Unit_Price</label>
                            <div class="input-group input-group-merge">
                              <span class="input-group-text">Rs.</span>
                              <input
                                type="text"
                                id="unit_priceModal"
                                name="stockPrice"
                                class="form-control"
								value="${price}"
                                placeholder="Rs.xxxx"
                              />
                            </div>
                          </div>
                          
                          <div class="mb-3 col-md-6">
                            <label for="quantity" class="form-label">Quantity</label>
                            <input type="text" class="form-control" id="quantityModal" name="stockQ" value="${quantity}"placeholder="0000" />
                          </div>
                          
                           <div class="mb-3 col-md-6">
                            <label for="description" class="form-label">Description</label>
                            <textarea class="form-control" cols="30" rows="3" id="DescriptionModal" name="stockDes" placeholder="${description}">${description}</textarea>
                          </div>
               
               
                          <div class="mb-3 col-md-6">
                            <label for="mf" class="form-label">Modify Date</label><br>
                           <input type="date" class="form-control" id="mfModal" name="stockMf" value="${mfDate}" placeholder="None">
                          </div>
                          
                          <div class="mb-3 col-md-6">
                            <label for="exp" class="form-label">Expiry Date</label>
                         <input
                              type="date"
                              class="form-control"
                              id="expModal"
                              name="stockExp"
							  value="${expDate}"
                              placeholder = "None"
                            />
                          </div>
                          
                           <div class="card-body">
	                      <div class="d-flex align-items-start align-items-sm-center gap-4">
	                        <img src="../assets/img/elements/lankaHardwareLogo.png" alt="lanka_hardware" class="d-block rounded" height="100" width="100" id="uploadedAvatar">
	                        <div class="button-wrapper">
	                          <label for="upload" class="btn btn-primary me-2 mb-4" tabindex="0">
	                            <span class="d-none d-sm-block">Product Image</span>
	                            <i class="bx bx-upload d-block d-sm-none"></i>
	                            <input type="file" id="upload" class="account-file-input" hidden="" accept="image/png, image/jpeg" onchange="buildStockImage();">
	                          </label>
	                          <button type="button" class="btn btn-outline-secondary account-image-reset mb-4">
	                            <i class="bx bx-reset d-block d-sm-none"></i>
	                            <span class="d-none d-sm-block">Reset</span>
	                          </button>
	
	                          <p class="text-muted mb-0"><small>Allowed JPG, GIF or PNG. Max size of 800K</small></p>
	                        </div>
	                      </div>
                    	</div>
                    	
                          
                            <div class="mb-3 col-md-6" >
                         <div class="col-md">
                          <label class="form-label" for="basic-default-message" name="warranty">Warranty</label>
                          
                          <div class="form-check mt-3 col-md-6">
                            <input name="default-radio-1" class="form-check-input" type="radio" value="None" id="WorNoneModal" checked="isNoneChecked(${checkNone})" onclick="callEditwarrentyDetails('None')">
                            
                            <label class="form-check-label" for="defaultRadio1"> None </label>
                          </div>
                       
                          <div class="form-check">
                            <input name="default-radio-1" class="form-check-input" type="radio" value="Available" id="WorAvailModal" checked="isNoneChecked(${checkAva})" onclick="callEditwarrentyDetails('Available')">
                            <label class="form-check-label" for="defaultRadio1"> Available </label>
                          </div>
                         
                          <br>
                          
                          <div id="WarrentyDetailstoPage"></div>
                           <input min="0" class="form-control" type="number" value="${warNum}" id="warrentyNumModal" name="warNum"> <br> <select class="form-select" id="warrentyPeriodModal" aria-label="Default select example" name="warPeriod"> <option selected>${WarrantyPeriod}</option> <option>Day</option><option>Week</option><option>Month</option><option>Year</option>  </select>

			 				</div>
                           
		                         </div> 
                          </div>
                             
                            
                            
                        <div class="mt-2">
                          <button type="submit" class="btn btn-primary me-2" id = "save" onclick ="callupdateItem(); callGetAllStockServlet();')">Save</button>
                          <button type="reset" onclick ="BuildViewStockModal('${itemID}','${name}','${type}','${brand}','${price}','${quantity}','${description}','${mfDate}','${expDate}','${warrentyType}','${warNum}','${WarrantyPeriod}')" class="btn btn-outline-secondary" id ="clear" >Cancel</button>
                        </div>
                        </div>
                        
                      </form> `

	
}

		
                          

var viewModalHeader = document.getElementById('ViewStockModalHeader')
var viewStockModalBody = document.getElementById('ViewStockModalBody')
var viewStockModalFooter = document.getElementById('ViewStockModalFooter')
var viewCard = document.getElementById('card-body-edit')


function BuildViewStockModal(itemID,name,type,brand,price,quantity,description,mfDate,expDate, warrentyType, warNum, WarrantyPeriod ){
	
	console.log(itemID)
	viewModalHeader.style = '';
	viewModalHeader.innerHTML = '';
	viewModalHeader.innerHTML = `<h5 class="modal-title" id="modalCenterTitle">VIEW ITEMS</h5>
							              <button
							                type="button"
							                class="btn-close"
							                data-bs-dismiss="modal"
							                aria-label="Close"
							              ></button>`

	
	viewStockModalBody.innerHTML = `<div>
						              	 <div class="card-body">
                      <div class="d-flex align-items-start align-items-sm-center gap-4">
                        <img src="../assets/img/elements/lankaLogo.png" alt="user-avatar" class="d-block rounded" height="100%" width="100%" id="uploadedAvatar">
                        
                      </div>
                    </div>
						                      </div>`

	editCard.innerHTML = `<form id="formAccountSettings" method="POST" onsubmit="return false">
                        <div class="row">
                          <div class="mb-3 col-md-6">
                            <label for="stockID" class="form-label">Item ID.</label>
                            <input
                              class="form-control"
                              type="text"
                              id="StockIDModal"
                              name="stockID"
                   				value="${itemID}"
                              autofocus
							readonly
                            />
                          </div>
                          
                          
                          <div class="mb-3 col-md-6">
                            <label for="lastName" class="form-label">Name</label>
                            <input class="form-control" type="text"  id="nameModal" name="stockName" value="${name}" id="stockName" readonly/>
                          </div>
                          
                          
                          <div class="mb-3 col-md-6">
                            <label for="stockCat" class="form-label">Category</label>
                            <input
                              type="text"
                              class="form-control"
                              id="TypeModal"
                              name="stockType"
								value="${type}"
                              placeholder = "None" readonly
                            />
                            
                          </div>
                               
                               
                          
                          <div class="mb-3 col-md-6">
                            <label for="brand" class="form-label">Brand</label>
                            <input
                              type="text"
                              class="form-control"
                              id="brandModal"
                              name="stockBrand"
								value="${brand}"
                              placeholder = "Unbrand." readonly
                            />
                          </div>
                          <div class="mb-3 col-md-6">
                            <label class="form-label" for="basic-default-phone">Unit_Price</label>
                            <div class="input-group input-group-merge">
                              <span class="input-group-text">Rs.</span>
                              <input
                                type="text"
                                id="unit_priceModal"
                                name="stockPrice"
                                class="form-control"
								value="   ${price}"
                                placeholder="Rs.xxxx" readonly
                              />
                            </div>
                          </div>
                          
                          <div class="mb-3 col-md-6">
                            <label for="quantity" class="form-label">Quantity</label>
                            <input type="text" class="form-control" id="quantityModal" name="stockQ" value="${quantity}"placeholder="0000" readonly/>
                          </div>
                          
                           <div class="mb-3 col-md-6">
                            <label for="description" class="form-label">Description</label>
                            <input type="text" class="form-control" id="DescriptionModal" cols="30" rows="3" name="stockDes" value="${description}"placeholder="None" readonly/>
                          </div>
               
               
                          <div class="mb-3 col-md-6">
                            <label for="mf" class="form-label">Modify Date</label><br>
                           <input type="text" class="form-control" id="mfModal" name="stockMf" value="${mfDate}" placeholder="None" readonly>
                          </div>
                          
                          <div class="mb-3 col-md-6">
                            <label for="exp" class="form-label">Expiry Date</label>
                         <input
                              type="text"
                              class="form-control"
                              id="expModal"
                              name="stockExp"
							  value="${expDate}"
                              placeholder = "None" readonly
                            />
                          </div>
                          
                          <div class="mb-3 col-md-6">
                            <label for="organization" class="form-label">Warrenty type</label>
                            <input
                              type="text"
                              class="form-control"
                              id="warrentyTypeModal"
                              name="warrentyType"
								value="${warrentyType}"readonly
                             	placeholder="None"
                            /></div>
                            
                          
                            
                             <div class="mb-3">
                            <label for="exp" class="form-label">Warrenty Number</label>
                          <input
                              type="text"
                              class="form-control"
                              id="warrentyNumModal"
                              name="warNum"
							  value="${warNum}"
                              placeholder = "None" readonly
                            />
                          </div>
                          
                              <div class="mb-3">
                            <label for="exp" class="form-label">Warrenty Period</label>
                          <input
                              type="text"
                              class="form-control"
                              id="warrentyPeriodModal"
                              name="warNum"
							  value="${WarrantyPeriod}" readonly
                              placeholder = "None"
                            />
                          </div>
                         

                          
                            
                        <div class="mt-2">
                          <button type="submit" onclick ="BuildEditStockModal('${itemID}','${name}','${type}','${brand}','${price}','${quantity}','${description}','${mfDate}','${expDate}','${warrentyType}','${warNum}','${WarrantyPeriod}')" class="btn btn-primary me-2" id = "save" >Edit</button>
                          <button type="reset"  class="btn btn-outline-secondary" id ="clear" data-bs-dismiss="modal">Cancel</button>
                        </div>
                        </div>
                        
                      </form>`

	viewStockModalFooter.innerHTML = ``
	
}

function callupdateItem(){
	//var inputFile = document.getElementById('updateModal')
	callGetAllStockServlet()
		setTimeout(function() {
				$('#EditStockModal').modal('hide')
		}, 1000);
		
		callGetAllStockServlet()
		setTimeout(function() {
				$('#ViewStockModal').modal('hide')
		}, 1000);		
	
	var id = document.getElementById('StockIDModal').value
	var name = document.getElementById('nameModal').value
	var Type = document.getElementById('TypeModal').value
	var brand = document.getElementById('brandModal').value
	var price = document.getElementById('unit_priceModal').value
	var quantity = document.getElementById('quantityModal').value
	var description = document.getElementById('DescriptionModal').value
	var mf = document.getElementById('mfModal').value
	var exp = document.getElementById('expModal').value
	
	var warNum = document.getElementById('warrentyNumModal').value
	var warPeriod = document.getElementById('warrentyPeriodModal').value
	
	var warrentyType;
	
	if(document.getElementById('WorNoneModal').checked == true){
		warrentyType = "None";
		console.log("noneCheked")
	}
	
	else if(document.getElementById('WorAvailModal').checked == true){
			warrentyType = "Available";
		}
	
	
	
	if(mf == "" || mf == null)
	{
		mf = 'NULL';
	}
	if(exp == "" || exp == null)
	{
		exp = 'NULL';
	}
	if(warNum == "" || warNum == null){
		warNum = 0;
	}
	
	
	
	console.log(id + name + Type + brand + price + quantity + description + mf + exp + warrentyType + warNum + warPeriod)
	
	
	var endpoint = "http://localhost:8081/LankaHardware/UpdateStock"
	//var formData = new FormData();
	//for(const file of inputFile.files){
	//	formData.append('updateModal', file)
	//}

	$.post(endpoint, { name: name, Type: Type, brand: brand, price: price, quantity: quantity, description: description, mf: mf, exp: exp}, $.post(endpoint, {warrentyType: warrentyType , warNum : warNum, warPeriod: warPeriod}), function(response) {
		console.log("this is after updateStock response");
		//stock = response;
		//callGetAllStockServlet(stock);
		console.log("response is: "+ response);
		
	});
		
	}
	


//delete Stock
var deleteModalHeader = document.getElementById('deleteModalHeader')
var deleteModalBody = document.getElementById('deleteModalBody')
var deleteModalFooter = document.getElementById('deleteModalFooter')

function createDeleteModal(id) {
	
	deleteModalHeader.innerHTML = '';
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
	$.post("http://localhost:8081/LankaHardware/RemoveItem", { id: id }, function(response) {
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
		  
			console.log("THis is from search: " , SearchDetails);
			
			if(SearchDetails == "" || SearchDetails == null || SearchDetails == "undefined"){
				alert("Please add some keywords to search! B )")
				console.log("failed")
				callGetAllStockServlet();
			}
			else{
					
					$.post("http://localhost:8081/LankaHardware/GetSearchedItems", {SearchDetails: SearchDetails}, function(response) {
					stock = response;	
					var stockLen = stock.length;
					console.log("Stock response: ", response)
					
					if(response == ""){
						buildNotfound(SearchDetails);
					}
					
					buildAllStock(stock, stockLen);

					
					
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
	$.get("http://localhost:8081/LankaHardware/GetAllItemsServlet", function(response) {
				
	stock = response
	var stockLen = stock.length;
	fileName = "Store_records";
	
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
    filename = filename?filename+'.xls':'Store_Records.xls';
    
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



function sendEmail() {
	Email.send({
	Host: "smtp.gmail.com",
	Username : "<sender’s email address>",
	Password : "<email password>",
	To : '<recipient’s email address>',
	From : "<sender’s email address>",
	Subject : "<email subject>",
	Body : "<email body>",
	}).then(
		//message => alert("mail sent successfully")
	);
}







/*

//var form  = document.getElementById("StockAddForm");
form.addEventListener("submit", validateForm);

function validateForm(event) {
  event.preventDefault(); // prevent the form from submitting
  

  if (name === '' || name == null) {
    errorName.innerHTML = 'Please enter a valid name';
    return false;
  }
  
  if (nameRegex.test(name)) {
    errorName.innerHTML = '';
    return false;
  }

  if (brand.length < 1) {
    errorBrand.innerHTML = 'Please enter a valid Brand Name';
    return false;
  }
  
  if (!brand.length < 1) {
    errorBrand.innerHTML = 'f';
    return false;
  }
  
  if (nameRegex.test(price)) {
    errorPrice.innerHTML = 'Please enter a valid price';
    return false;
  }
  
  if (!nameRegex.test(price)) {
    errorPrice.innerHTML = 'Please';
    return false;
  }
  
  if (!isNaN(quantity) || nameRegex.test(quantity)) {
    errorQ.innerHTML = 'Please enter a valid quantity';
    return false;
  }
  
  if (isNaN(quantity) || nameRegex.test(quantity)) {
    errorQ.innerHTML = 'Please';
    return false;
  }
  
  if (!isNaN(price) || nameRegex.test(price)) {
    errorPrice.innerHTML = 'Please enter a valid price';
    return false;
  }
  


  // if inputs are valid, send data to servlet
  sendDataToServlet(name, Type, brand, price, quantity, descript, mf, exp, warrentyType, warNum, warPeriod);
}

function sendDataToServlet(name, Type, brand, price, quantity, descript, mf, exp, warrentyType, warNum, warPeriod){
	
	  const xhr = new XMLHttpRequest();
	  xhr.open("POST", "AddStoreItemsServlet", true);
	  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  xhr.onreadystatechange = function() {
	    if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
	      alert("Data saved successfully.");
	    }
	  };
	  xhr.send("name=" + encodeURIComponent(name) + "&Type=" + encodeURIComponent(Type) + "&brand=" + encodeURIComponent(brand) + "&price=" + encodeURIComponent(price)
	  + "&quantity=" + encodeURIComponent(quantity) + "&descript=" + encodeURIComponent(descript) + "&mf=" + encodeURIComponent(mf) + "&exp=" + encodeURIComponent(exp)
	  + "&warrentyType=" + encodeURIComponent(warrentyType) + "&warNum=" + encodeURIComponent(warNum) + "&warPeriod=" + encodeURIComponent(warPeriod));
	  
	  
}

*/	
