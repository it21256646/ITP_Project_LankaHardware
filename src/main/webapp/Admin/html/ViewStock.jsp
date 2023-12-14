<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="../assets/"
	data-template="vertical-menu-template-free">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />

<title>Store Management - View Store</title>

<meta name="description" content="" />

<!-- Favicon -->
<link rel="icon" type="image/x-icon"
	href="../assets/img/favicon/favicon.ico" />

<!-- Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
	rel="stylesheet" />

<!-- Icons. Uncomment required icon fonts -->
<link rel="stylesheet" href="../assets/vendor/fonts/boxicons.css" />

<!-- Core CSS -->
<link rel="stylesheet" href="../assets/vendor/css/core.css"
	class="template-customizer-core-css" />
<link rel="stylesheet" href="../assets/vendor/css/theme-default.css"
	class="template-customizer-theme-css" />
<link rel="stylesheet" href="../assets/css/demo.css" />

<!-- Vendors CSS -->
<link rel="stylesheet"
	href="../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />

<!-- Page CSS -->

<!-- Helpers -->
<script src="../assets/vendor/js/helpers.js"></script>
<script src="https://kit.fontawesome.com/35f65b186d.js"
	crossorigin="anonymous"></script>

<!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
<!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
<script src="../assets/js/config.js"></script>
</head>

<body>
	<!-- Layout wrapper -->
	<div class="layout-wrapper layout-content-navbar">
		<div class="layout-container">
			<!-- Menu -->
	       <aside id="layout-menu"
				class="layout-menu menu-vertical menu bg-menu-theme"
				data-bg-class="bg-menu-theme">
				<div class="app-brand demo">
					<a href="index.html" class="app-brand-link"> <span
						class="app-brand-text demo menu-text fw-bolder ms-2">LH</span>
					</a>

					<div style="display: flex; align-items: end;">
						<span class="demo menu-text fw-bolder ms-2">Admin Panel</span>
					</div>
					<a href="javascript:void(0);"
						class="layout-menu-toggle menu-link text-large ms-auto d-xl-none">
						<i class="bx bx-chevron-left bx-sm align-middle"></i>
					</a>
				</div>

				           
				<div class="menu-inner-shadow"></div>

				           
				<ul class="menu-inner py-1 overflow-auto">
					<!-- Charts -->
					<li class="menu-header small text-uppercase"><span
						class="menu-header-text">Charts</span></li>
					<!-- charts -->
					<li class="menu-item"><a href="#" class="menu-link"> <i class="menu-icon tf-icons bx bx-table"></i>
					Charts</a></li>
					<!-- Forms & Tables -->
					<li class="menu-header small text-uppercase"><span
						class="menu-header-text">Tables</span></li>
					<!-- Forms -->
					<!-- Tables -->
					<li class="menu-item"><a href="ViewVoucher.jsp"
						class="menu-link"> <i class="menu-icon tf-icons bx bx-table"></i>
					Voucher Management
					</a></li>
					<li class="menu-item active"><a href="ViewVoucher.jsp"
						class="menu-link"> <i class="menu-icon tf-icons bx bx-table"></i>
					Store Managements
					</a></li>

				</ul>
			</aside>
			<!-- / Menu -->


        <!-- Layout container -->
        <div class="layout-page">
          <!-- Navbar -->

          <nav
            class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
            id="layout-navbar"
          >
            <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
              <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
                <i class="bx bx-menu bx-sm"></i>
              </a>
            </div>

            <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
              <!-- Search -->
              <form id="searchForm">
            
              <div class="navbar-nav align-items-center">
                <div class="nav-item d-flex align-items-center">
                  <button type="submit" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown" onclick="searchItem()"> <i class="bx bx-search fs-4 lh-0"></i></button>
                  <input
                    type="text"
                    class="form-control border-0 shadow-none"
                    placeholder="Search..."
                    aria-label="Search..."
                    id ="SearchDetails"
                  />
                 
                </div>
              </div>
              </form>
              <!-- /Search -->

              <ul class="navbar-nav flex-row align-items-center ms-auto">
                <!-- Place this tag where you want the button to render. -->
                <li class="nav-item lh-1 me-3">
                <div class="widget widget-lg">
                 <button class="btn"
                  	onclick="getTable()"    
                    data-icon="octicon-star"
                    data-size="large"
                    data-show-count="true"
                    style="background-color:rgba(67, 89, 113, 0.05);padding:7px; color:#000;font-size:12px; box-sizing: border-box;"

                    ><i class="fa-solid fa-download"></i> Download Records</button>
                    
                    
                </div>

                </li>

                <!-- User -->
                
                <!--/ User -->
              </ul>
            </div>
          </nav>

          <!-- / Navbar -->

          <!-- Content wrapper -->
          <div class="content-wrapper">
            <!-- Content -->

            <div class="container-xxl flex-grow-1 container-p-y">
              <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Store Management / </span> 
              
                <small  class="dropdown">Sort By: <small id="sortType" style="color:#9CA84A; font-weight: normal;"> </small> 
                <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown"> 
                <i class="fas fa-sort"></i>
                </button> 
                
                <small class="dropdown-menu">
     					<a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" onclick="callSortbyServlet(1)">
                       -  Id</a> 
                        
                       <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" onclick="callSortbyServlet(2)">
                       -  Name</a> 

                       <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" onclick="callSortbyServlet(3)">
                       - Category</a>
                       
                       <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" onclick="callSortbyServlet(6)">
                       - Price</a>
                       
                       <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" onclick="callSortbyServlet(4)">
                       - MF-Date</a>
                       
                       <a class="dropdown-item" href="javascript:void(0);" data-bs-toggle="modal" onclick="callSortbyServlet(5)">
                       - Exp-Date</a>
                </small>
                
                </small>
              
              
               </h4>
               
             

              <!-- Basic Bootstrap Table -->
              <div class="card">
                <h5 class="card-header"><small id="ItemCount">Store Item list</small></h5>
                <div class="table-responsive text-nowrap">
                
                  <table class="table">
                    <thead>
                      <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Brand</th>
                        <th>quantity</th>
                        <th>Unit_price</th>      
                 
                      
                      </tr>
                    </thead>
                    <tbody id="stock">
  
                    </tbody>
                    
                  </table>
                </div>
              </div>
              <!--/ Basic Bootstrap Table -->

              <hr class="my-5" />

            </div>
            <!-- / Content -->

            <!-- Footer -->
            <footer class="content-footer footer bg-footer-theme">
              <div class="container-xxl d-flex flex-wrap justify-content-between py-2 flex-md-row flex-column">
                <div class="mb-2 mb-md-0">
                  Â©
                  <script>
                    document.write(new Date().getFullYear());
                  </script>
                  , 
                  <a href="https://themeselection.com" target="_blank" class="footer-link fw-bolder">Lanka Hardware</a>
                </div>
                <div>
                  <a href="https://themeselection.com/license/" class="footer-link me-4" target="_blank">License</a>
                  <a href="https://themeselection.com/" target="_blank" class="footer-link me-4">More Themes</a>

                  <a
                    href="https://themeselection.com/demo/sneat-bootstrap-html-admin-template/documentation/"
                    target="_blank"
                    class="footer-link me-4"
                    >Documentation</a
                  >

                  <a
                    href="https://github.com/themeselection/sneat-html-admin-template-free/issues"
                    target="_blank"
                    class="footer-link me-4"
                    >Support</a
                  >
                </div>
              </div>
            </footer>
            <!-- / Footer -->

            <div class="content-backdrop fade"></div>
          </div>
          <!-- Content wrapper -->
        </div>
        <!-- / Layout page -->
      </div>

      <!-- Overlay -->
      <div class="layout-overlay layout-menu-toggle"></div>
    </div>
    <!-- / Layout wrapper -->
    
    
       <div class="buy-now">
      <a
        href="#"
        onclick="return false;"
        class="btn btn-danger btn-buy-now"
        data-bs-toggle="modal" data-bs-target="#AddStockModal"
        >Add Item</a
      >
    </div>
    
    
        
        <div class="modal fade" id="AddStockModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header" id="AddStockModalHeader">
              <h5 class="modal-title" id="modalCenterTitle">Add New Store Item</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            
            <div class="modal-body" id="AddStockModalBody">
              <div>
              	 <div class="button-wrapper">
                

                          <p class="text-muted mb-0"></p>
                        </div>
                      </div>
                    </div>
                    <hr class="my-0" />
                    
                    <div class="card-body">
                    
                    
                      <form id="StockAddForm" method="POST" onsubmit="return false">
                      
                        <div class="row">
                          <div class="mb-3 col-md-6">
                            <label for="firstName" class="form-label">Name</label>
                            <input
                              class="form-control"
                              type="text"
                              id="stockName"
                              name="stockName" 
                   
                              autofocus
                            />
                            <span id="name-error" style="color:red; font-size:13px"></span>
                          </div>
                          
                          <div class="mb-3 col-md-6">
                            <label for="stockCat" class="form-label">Category</label>
                            <select id="stockCat" name = "stockCat" class="select2 form-select">
                            	 <option></option>
		                        <option value="General">General</option>
		                        <option value="Tools">Tools</option>
                               <option value="Mechanical">Mechanical</option>
		                        <option value="Building">Building</option>
		                        <option value="Electrical">Electronics & Electrical</option>
		                        <option value="Steel">Steel</option>
		                        <option value="Roofing">Roofing</option>
		                        <option value="Paint">Paint</option>
		                        <option value="Plumbing">Plumbing</option>
		                        <option value="Tiles & Fitting">Tiles & Fitting</option>
		                        <option value="Industrial">Industrial</option>
		                        <option value="Home & Kitchen">Home & Kitchen</option>
		                            
                            </select>
                             <span id="cat-error" style="color:red; font-size:13px"></span>

                          </div>
                          
            
                          
                          <div class="mb-3 col-md-6">
                          	 <label class="form-label" for="basic-default-company">Brand</label>
                         	 <input type="text" class="form-control" placeholder="ACME Inc." name="stockBrand" id="stockBrand" autofocus/>
                         	  <span id="Brand-error" style="color:red; font-size:13px"></span>
                          </div>
                          
                          <div class="mb-3 col-md-6">
                            <label class="form-label" for="basic-default-phone">Unit_Price</label>
                          <input
                            type="text"
                            id="stockPrice"
                            class="form-control phone-mask"
                            placeholder="Rs."
                            name="stockPrice" 
                          />
                           <span id="price-error" style="color:red; font-size:13px"></span>
                          </div>
                          
                          <div class="mmb-3 col-md-6">
                          <label class="form-label" for="basic-default-phone">Quantity</label>
                          <input type="text" id="stockQ" class="form-control phone-mask" placeholder="item quantity." name="stockQ" autofocus>
                            <span id="quantity-error" style="color:red; font-size:13px"></span>
                        </div>
      
              
                           <div class="mb-3 col-md-6">
                   			<label class="form-label" for="description">Description</label>
                			<textarea name="stockDes" id="stockDes" cols="30" rows="3" class="form-control" placeholder="Description" autofocus></textarea>
                			 <span id="disc-error" style="color:red; font-size:13px"></span>
              			  </div>
              			  

              			  
                          <div class="mb-3 col-md-6">
                             <label class="form-label" for="basic-default-message">Modify Date <small style="color: #808080; text-transform: lowercase;"> (optional)</small></label>
                         	 <input class="form-control" type="date" id="stockMf" name="stockMf"> 
                         	  <span id="mf-error" style="color:red; font-size:13px"></span>
                                       
                          </div>
                          
                           <div class="mb-3 col-md-6">
                             <label class="form-label" for="basic-default-message">Expire Date <small style="color: #808080; text-transform: lowercase;"> (optional)</small></label>
                         	 <input class="form-control" type="date" id="stockExp" name="stockExp">
                         	  <span id="exp-error" style="color:red; font-size:13px"></span>
                          </div>
                          
                       <div class="card-body">
	                      <div class="d-flex align-items-start align-items-sm-center gap-4">
	                        <img src="../assets/img/elements/lankaHardwareLogo.png" alt="lanka_hardware" class="d-block rounded" height="100" width="100" id="uploadedAvatar">
	                        <div class="button-wrapper">
	                          <label for="upload" class="btn btn-primary me-2 mb-4" tabindex="0">
	                            <span class="d-none d-sm-block">Product Image</span>
	                            <i class="bx bx-upload d-block d-sm-none"></i>
	                            <input type="file" id="img" class="account-file-input" hidden="" accept="image/png, image/jpeg image/jpg" onchange="buildStockImage();">
	                          </label>
	                          <button type="button" class="btn btn-outline-secondary account-image-reset mb-4">
	                            <i class="bx bx-reset d-block d-sm-none"></i>
	                            <span class="d-none d-sm-block">Reset</span>
	                          </button>
	
	                          <p class="text-muted mb-0">Allowed JPG, GIF or PNG. Max size of 800K</p>
	                        </div>
	                      </div>
                    	</div>
                    	
                    	 
                     	 
                     	  <div class="mb-3 col-md-6" >
                         <div class="col-md">
                          <label class="form-label" for="basic-default-message" name="warranty">Warranty</label>
                          
                          <div class="form-check mt-3 col-md-6">
                            <input name="default-radio-1" class="form-check-input" type="radio" value="None" id="WorNone" name="warrentyType" onclick="callwarrentyDetails()" checked>
                            
                            <label class="form-check-label" for="defaultRadio1"> None </label>
                          </div>
                       
                          <div class="form-check">
                            <input name="default-radio-1" class="form-check-input" type="radio" value="Available" id="WorAvail" name="warrentyType" onclick="callwarrentyDetails()">
                            <label class="form-check-label" for="defaultRadio1"> Available </label>
                          </div>
                           
                          <br>
                         
                           <div id="WarrentyDetailstoPage"> </div>
		                        
		                         </div> 
                          </div>
                        
                         <div class="modal-footer" id="AddStockFooter">
			              <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal" onclick="callGetAllStockServlet()">
			                Close
			              </button>
			              <button type="submit" class="btn btn-primary" onclick="validation()">Submit</button>
			            </div>
                        </div>
                      </form>
                    </div>
                  
              </div>
            </div>
           
          </div>
          
          
                 <!-- Barcode modal -->
          
      <div class="modal fade" id="BarCodeModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header" id="BarCodeModalHeader">
              
            </div>
            
            <div id="BarCodeModalTitle">
             
            </div>
            
            <div class="modal-body" id="BarCodeModalBody">
               <svg id="barcode"></svg>
                    </div>
                    <hr class="my-0" />
                    
                    <div id="BarCodeModalFooter">
             
            	</div>
              </div>
            </div>
            
          </div>
          

       <!-- view modal -->
          
      <div class="modal fade" id="ViewStockModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header" id="ViewStockModalHeader">
              
            </div>
            <div class="modal-body" id="ViewStockModalBody"> 
                    </div>
                    <hr class="my-0" />
                    
                    <div class="card-body" id="card-body-edit">
                      
                    </div>
              </div>
            </div>
            <div class="modal-footer" id="ViewStockModalFooter">
             
            </div>
          </div>
         
          
          <!-- edit modal -->
      <div class="modal fade" id="EditStockModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header" id="EditStockModalHeader">
            
              
            </div>
            <div class="modal-body" id="EditStockModalBody">
              
                    </div>
                    <hr class="my-0" />
                    
                    <div class="card-body" id="card-body-edit">
                      
                    </div>
              </div>
            </div>
            <div class="modal-footer" id="Footer">
               
            </div>
          </div>

<!-- Delete Modal -->
      <div class="modal fade" id="deleteModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header" id="deleteModalHeader">
            </div>
            <div class="modal-body" id="deleteModalBody">
            </div>
            <div class="modal-footer" style="justify-content: center;" id="deleteModalFooter">
            </div>
          </div>
        </div>
      </div>
          
    



	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.5/dist/jquery.validate.js"></script>
	<!-- Core JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/jsbarcode@3.11.5/dist/JsBarcode.all.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.0/xlsx.full.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/exceljs/4.3.0/exceljs.min.js"></script>

	<!-- build:js assets/vendor/js/core.js -->
	<script src="https://smtpjs.com/v3/smtp.js"></script>
	<script src="../assets/vendor/libs/jquery/jquery.js"></script>
	<script src="../assets/vendor/libs/popper/popper.js"></script>
	<script src="../assets/vendor/js/bootstrap.js"></script>
	<script
		src="../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>


	<script src="../assets/vendor/js/menu.js"></script>
	<!-- endbuild -->

	<!-- Vendors JS -->

	<!-- Call JS -->
	<script src="../js/StockHandle.js"></script>

	<!-- Page JS -->
	<script>
		$(document).ready(function() {
			callGetAllStockServlet();
		});
	</script>

	<!-- Place this tag in your head or just before your close body tag. -->
	<script async defer src="https://buttons.github.io/buttons.js"></script>
</body>
</html>
