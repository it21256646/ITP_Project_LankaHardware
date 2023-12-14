<%@page import="model.*"%>
<%@page import="service.*"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html
  lang="en"
  class="light-style layout-menu-fixed"
  dir="ltr"
  data-theme="theme-default"
  data-assets-path="../assets/"
  data-template="vertical-menu-template-free"
>
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>Container - Layouts | Sneat - Bootstrap 5 HTML Admin Template - Pro</title>

    <meta name="description" content="" />

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="../assets/img/favicon/favicon.ico" />

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
      rel="stylesheet"
    />

    <!-- Icons. Uncomment required icon fonts -->
    <link rel="stylesheet" href="../assets/vendor/fonts/boxicons.css" />

    <!-- Core CSS -->
    <link rel="stylesheet" href="../assets/vendor/css/core.css" class="template-customizer-core-css" />
    <link rel="stylesheet" href="../assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
    <link rel="stylesheet" href="../assets/css/demo.css" />

    <!-- Vendors CSS -->
    <link rel="stylesheet" href="../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />

    <!-- Page CSS -->

    <!-- Helpers -->
    <script src="../assets/vendor/js/helpers.js"></script>

    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="../assets/js/config.js"></script>
  
  	<style>
        .cutoff-text{
            display: block;
            width: 150px;
            overflow: hidden;
            margin: 0px;
            position: relative;
        }

        .cutoff-text::before {
            position: absolute;
            top: 0;
            right: 0;
            width: 50px;
            height: 50px;
            content: '';
            background: linear-gradient(to right, transparent, #ffffff);
        }

		.icon-box {
		  display: flex;
		  justify-content: center;
		  align-items: center;
		  width: 80px;
		  height: 80px;
		  margin: 0 auto;
		  border-radius: 50%;
		  border: 3px solid #f15e5e;
		}
		
		.icon-box i {
		  font-style: initial;
		  color: #f15e5e;
		  font-size: 46px;
		  display: inline-block;
		}
    </style>
  </head>

  <body>
    <!-- Layout wrapper -->
    <div class="layout-wrapper layout-content-navbar">
      <div class="layout-container">
        <!-- Menu -->

        <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
          <div class="app-brand demo">
			<a href="index.html" class="app-brand-link">
              <span class="app-brand-text demo menu-text fw-bolder ms-2">LH</span>
            </a>
            
            <div style="display: flex; align-items: end;">
            	<span class="demo menu-text fw-bolder ms-2">Admin Panel</span>
            </div>
            
            <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
              <i class="bx bx-chevron-left bx-sm align-middle"></i>
            </a>
          </div>

          <div class="menu-inner-shadow"></div>
          
          <ul class="menu-inner py-1">
          	<!-- Charts -->
            <li class="menu-header small text-uppercase"><span class="menu-header-text">Charts</span></li>
         
            <!-- charts -->
            <li class="menu-item">
              <a href="#" class="menu-link">
                <i class="menu-icon tf-icons bx bx-table"></i>
                <div data-i18n="Tables">Charts </div>
              </a>
            </li>
          
          
            <!-- Forms & Tables -->
            <li class="menu-header small text-uppercase"><span class="menu-header-text">Tables</span></li>
            <!-- Forms -->
            <!-- Tables -->
            <li class="menu-item active" id="">
              <a href="#" class="menu-link">
                <i class="menu-icon tf-icons bx bx-table"></i>
                <div data-i18n="Tables">View Admin</div>
              </a>
            </li>
            
            <li class="menu-item" id="">
              <a href="AttendanceView.jsp" class="menu-link">
                <i class="menu-icon tf-icons bx bx-table"></i>
                <div data-i18n="Tables">Attendance</div>
              </a>
            </li>
          
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
              <div class="navbar-nav align-items-center">
                <div class="nav-item d-flex align-items-center">
                  <i class="bx bx-search fs-4 lh-0"></i>
                  <input
                  	oninput = "buildSearchLists()"
                  	id="searchemp"
                    type="text"
                    class="form-control border-0 shadow-none"
                    placeholder="Search..."
                    aria-label="Search..."
                  />
                </div>
              </div>
              <!-- /Search -->

              <ul class="navbar-nav flex-row align-items-center ms-auto">
                <!-- Place this tag where you want the button to render. -->
                <li class="nav-item lh-1 me-3">
                  <a
                    class="github-button"
                    href="https://github.com/themeselection/sneat-html-admin-template-free"
                    data-icon="octicon-star"
                    data-size="large"
                    data-show-count="true"
                    aria-label="Star themeselection/sneat-html-admin-template-free on GitHub"
                    >Star</a
                  >
                </li>

                <!-- User -->
                <li class="nav-item navbar-dropdown dropdown-user dropdown">
                  <a class="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);" data-bs-toggle="dropdown">
                    <div class="avatar avatar-online">
                      <img src="../assets/img/avatars/1.png" alt class="w-px-40 h-auto rounded-circle" />
                    </div>
                  </a>
                  <ul class="dropdown-menu dropdown-menu-end">
                    <li>
                      <a class="dropdown-item" href="#">
                        <div class="d-flex">
                          <div class="flex-shrink-0 me-3">
                            <div class="avatar avatar-online">
                              <img src="../assets/img/avatars/1.png" alt class="w-px-40 h-auto rounded-circle" />
                            </div>
                          </div>
                          <div class="flex-grow-1">
                            <span class="fw-semibold d-block">John Doe</span>
                            <small class="text-muted">Admin</small>
                          </div>
                        </div>
                      </a>
                    </li>
                    <li>
                      <div class="dropdown-divider"></div>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bx bx-user me-2"></i>
                        <span class="align-middle">My Profile</span>
                      </a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <i class="bx bx-cog me-2"></i>
                        <span class="align-middle">Settings</span>
                      </a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                        <span class="d-flex align-items-center align-middle">
                          <i class="flex-shrink-0 bx bx-credit-card me-2"></i>
                          <span class="flex-grow-1 align-middle">Billing</span>
                          <span class="flex-shrink-0 badge badge-center rounded-pill bg-danger w-px-20 h-px-20">4</span>
                        </span>
                      </a>
                    </li>
                    <li>
                      <div class="dropdown-divider"></div>
                    </li>
                    <li>
                      <a class="dropdown-item" href="auth-login-basic.html">
                        <i class="bx bx-power-off me-2"></i>
                        <span class="align-middle">Log Out</span>
                      </a>
                    </li>
                  </ul>
                </li>
                <!--/ User -->
              </ul>
            </div>
          </nav>

          <!-- / Navbar -->

          <!-- Content wrapper -->
          <div class="content-wrapper">
          <h5 class="card-header">Admin
          </h5>
            <!-- Content -->
            <div class="layout-demo-wrapper">
               <div class="card">
                
                <div class="text-nowrap">
                
                  <table class="table">
                    <thead>
                      <tr class="text-nowrap">
                     
                        <th>Email</th>
                        <th>password</th>
                        <th>phone</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Role</th>
                      	<th>Action</th>
                      
                      </tr>
                    </thead>
                    <tbody id = "admin" >
                    	
                    </tbody>
                  </table>
                </div>
              </div>
              <!--/ Responsive Table -->
            </div>

            <div class="container-xxl flex-grow-1 container-p-y">
              <!-- Layout Demo -->
              
                <div class="layout-demo-info">
                 
                
                </div>
              </div>
              <!--/ Layout Demo -->
            </div>
            <!-- / Content -->

            <!-- Footer -->
            <footer class="content-footer footer bg-footer-theme">
              <div class="container-xxl d-flex flex-wrap justify-content-between py-2 flex-md-row flex-column">
                <div class="mb-2 mb-md-0">
                  
                  <script>
                    document.write(new Date().getFullYear());
                  </script>
                  ,  Lanka Hardware 
                  <a href="https://themeselection.com" target="_blank" class="footer-link fw-bolder">ThemeSelection</a>
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
        data-bs-toggle="modal" data-bs-target="#AddEmoloyeeModal" 
        >Add Admin</a
      >
    </div>
    
    
    <div class="modal fade" id="AddEmoloyeeModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header" id="AddEmoloyeeModalHeader">
              <h5 class="modal-title" id="modalCenterTitle">Add Admin</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body" id="AddEmoloyeeModalBody">
              <div>
              	 <div class="card-body">
                      <div class="d-flex align-items-start align-items-sm-center gap-4">
                        <img src="../assets/img/avatars/1.png" alt="user-avatar" class="d-block rounded" height="100" width="100" id="uploadedAvatar">
                        <div class="button-wrapper">
                          <label for="upload" class="btn btn-primary me-2 mb-4" tabindex="0">
                            <span class="d-none d-sm-block">Upload new photo</span>
                            <i class="bx bx-upload d-block d-sm-none"></i>
                            <input type="file" id="upload" class="account-file-input" hidden="" accept="image/png, image/jpeg" onchange="buildProfileImage();">
                          </label>
                          <button type="button" class="btn btn-outline-secondary account-image-reset mb-4">
                            <i class="bx bx-reset d-block d-sm-none"></i>
                            <span class="d-none d-sm-block">Reset</span>
                          </button>

                          <p class="text-muted mb-0">Allowed JPG, GIF or PNG. Max size of 800K</p>
                        </div>
                      </div>
                    </div>
                      </div>
                    </div>
                    <hr class="my-0" />
                    
                    <div class="card-body">
                      <form id="formAccountSettings" method="POST" onsubmit="return false">
                         <div class="mb-3 col-md-6">
                            <label for="email" class="form-label">E-mail</label>
                            <input
                              class="form-control"
                              type="text"
                              id="email"
                              name="email"
                             
                              placeholder="123@gmail.com"
                            />
                          </div>
                             <div class="mb-3 col-md-6 form-password-toggle">
                    <label class="form-label" for="password">Password</label>
	                  <div class="input-group input-group-merge">
	                    <input
	                      type="password"
	                      id="password"
	                      class="form-control"
	                      name="password"
	                      placeholder="&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;"
	                      aria-describedby="password"
	                    />
	                    <span class="input-group-text cursor-pointer"><i class="bx bx-hide"></i></span>
	                  </div>
                </div>
	                  <div class="mb-3 col-md-6">
	                            <label class="form-label" for="phoneNumber">Phone Number</label>
	                            <div class="input-group input-group-merge">
	                              <span class="input-group-text">LK (+94)</span>
	                              <input
	                                type="text"
	                                id="phoneNum"
	                                name="phoneNum"
	                                class="form-control"
	                                placeholder="07********"
	                              />
	                            </div>
	                       
                         <div class="mb-3">
                 <label for="Name"class="Name">Name</label>
                 <div class="form-group">
  					<input type="text" 
 			        class="form-control" 
			         id="Name" 
			         placeholder="Name">
  
 		
                
					</div>
                 
                 </div>
                          
                              <div class="row">
                          <div class="mb-3 col-md-6">
                            <label for="address" class="form-label">Address</label>
                            <input type="text" class="form-control" id="address" name="address" placeholder="Address" />
                          </div>
                     
                          <div class="mb-3 col-md-6">
                            <label class="form-label" for="country">Role</label>
                            <select id="Role" name = "Role"class="select2 form-select">
                              <option value="select">Select</option>
                              <option value="SysAdmin">SysAdmin</option>
                              <option value="ProductManager">Product manager</option>
                              <option value="StockManager">Stock manager</option>
                              <option value="SupplierManager">supplier manager</option>
                              <option value="OrderManager">order manager</option>
                              <option value="EmployeeManager">Employee manager</option>
                            
                  
                            </select>
                          </div>
                       
                        <div class="mt-2">
                          <button type="submit" class="btn btn-primary me-2" id = "save" onclick ="callAddAdminServlet()">Add Admin</button>
                          <button type="reset" class="btn btn-outline-secondary" id ="clear" onclick = "clearadmin()">Cancel</button>
                        </div>
                      </form>
                    </div>
              </div>
            </div>
            <div class="modal-footer" id="AddEmoloyeeModalFooter">
              <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                Close
              </button>
              <button type="button" class="btn btn-primary">Submit</button>
            </div>
          </div>
        </div>
      </div>
      
      <!-- edit modal -->
      <div class="modal fade" id="EditEmoloyeeModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header" id="EditEmoloyeeModalHeader">
              
            </div>
            <div class="modal-body" id="EditEmoloyeeModalBody">
              
                    </div>
                    <hr class="my-0" />
                    
                    <div class="card-body" id="card-body-edit">
                      
                    </div>
              </div>
            </div>
            <div class="modal-footer" id="EditEmoloyeeModalFooter">
              <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                Close
              </button>
              <button type="button" class="btn btn-primary">Submit</button>
            </div>
          </div>

<!-- Modal -->
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

    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->
    <script src="../assets/vendor/libs/jquery/jquery.js"></script>
    <script src="../assets/vendor/libs/popper/popper.js"></script>
    <script src="../assets/vendor/js/bootstrap.js"></script>
    <script src="../js/CallServlet.js"></script>
    <script src="../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

    <script src="../assets/vendor/js/menu.js"></script>
    <!-- endbuild -->

    <!-- Vendors JS -->

    <!-- Main JS -->
    <script src="../assets/js/main.js"></script>
    
	<!-- Page JS -->
    <script src="../assets/js/pages-account-settings-account.js"></script>
    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
    <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
  </body>
  <script>
	
  $(document).ready(function () {
	  callGetAllAdminsServlet()
	});

</script>
</html>

