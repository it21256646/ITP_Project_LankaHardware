<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<!-- =========================================================
* Sneat - Bootstrap 5 HTML Admin Template - Pro | v1.0.0
==============================================================

* Product Page: https://themeselection.com/products/sneat-bootstrap-html-admin-template/
* Created by: ThemeSelection
* License: You must have a valid license purchased in order to legally use the theme for your project.
* Copyright ThemeSelection (https://themeselection.com)

=========================================================
 -->
<!-- beautify ignore:start -->
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

    <title>Tables - Basic Tables | Sneat - Bootstrap 5 HTML Admin Template - Pro</title>

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
              <a href="productManagerCharts.jsp" class="menu-link">
                <i class="menu-icon tf-icons bx bx-table"></i>
                <div data-i18n="Tables">Charts </div>
              </a>
            </li>
          
            <!-- Forms & Tables -->
            <li class="menu-header small text-uppercase"><span class="menu-header-text">Tables</span></li>
            <!-- Forms -->
            <!-- Tables -->
            <li class="menu-item active" id="new">
              <a href="#" onclick="return false;" class="menu-link">
                <i class="menu-icon tf-icons bx bx-table"></i>
                <div data-i18n="Tables">New Questions <span class="badge rounded-pill badge-center h-px-20 w-px-20 bg-label-danger" id="newMessagesCount">0</span></div>
              </a>
            </li>
            
            <li class="menu-item" id="answered">
              <a href="#" onclick="return false;" class="menu-link">
                <i class="menu-icon tf-icons bx bx-table"></i>
                <div data-i18n="Tables">Answered Questions</div>
              </a>
            </li>
            
            <li class="menu-item">
              <a href="ViewFeedbacks.jsp" class="menu-link">
                <i class="menu-icon tf-icons bx bx-table"></i>
                <div data-i18n="Tables">Feedback</div>
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
              <div class="navbar-nav align-items-center" id="dynamicSearch">
                
              </div>
              <!-- /Search -->

              <ul class="navbar-nav flex-row align-items-center ms-auto">

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
            <!-- Content -->

            <div class="container-xxl flex-grow-1 container-p-y">
              <h4 class="fw-bold py-3 mb-4" id="breadCrumbHeading"><span class="text-muted fw-light">Tables /</span> New Questions</h4>

              <!-- Basic Bootstrap Table -->
              <div class="card" id="dynamicTable">
              </div>
              <!--/ Basic Bootstrap Table -->
            </div>
            <!-- / Content -->

            <!-- Footer -->
            <footer class="content-footer footer bg-footer-theme">
              <div class="container-xxl d-flex flex-wrap justify-content-between py-2 flex-md-row flex-column">
                <div class="mb-2 mb-md-0">
                  ©
                  <script>
                    document.write(new Date().getFullYear());
                </script>
                  , made with ❤️ by
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

      <!-- Modal -->
      <div class="modal fade" id="modalCenter" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header" id="answerModalHeader">
              <h5 class="modal-title" id="modalCenterTitle">Type the Answer</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body" id="answerModalBody">
              <span>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Cupiditate maiores quia pariatur accusantium! Sit nemo ab magnam perspiciatis, porro hic, vitae nam ratione, ducimus eaque nihil aliquid quos. Maiores, veritatis!</span>
              <div class="row mt-3">
                <div class="mb-3">
                    <label class="form-label" for="answerTextArea">Answer</label>
                    <textarea id="answerTextArea" class="form-control" placeholder=""></textarea>
                </div>
              </div>
            </div>
            <div class="modal-footer" id="answerModalFooter">
              <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                Close
              </button>
              <button type="button" class="btn btn-primary">Submit</button>
            </div>
          </div>
        </div>
      </div>

	  <!-- Modal -->
	  <div class="modal fade" id="modalCenter2" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
          <div class="modal-content">
            <div class="modal-header" id="viewAnswerModalHeader">
              <h5 class="modal-title" id="modalCenterTitle">View the Answer</h5>
              <button
                type="button"
                class="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              ></button>
            </div>
            <div class="modal-body" id="viewAnswerModalBody">
              <span>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Cupiditate maiores quia pariatur accusantium! Sit nemo ab magnam perspiciatis, porro hic, vitae nam ratione, ducimus eaque nihil aliquid quos. Maiores, veritatis!</span>
              <div class="row mt-3">
                <div class="mb-3">
                    <label class="form-label" for="answerTextArea">Answer</label>
                    <textarea id="answerTextArea" class="form-control" placeholder=""></textarea>
                </div>
              </div>
            </div>
            <div class="modal-footer" id="viewAnswerModalFooter">
              <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                Close
              </button>
              <button type="button" class="btn btn-primary">Submit</button>
            </div>
          </div>
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

      <!-- Overlay -->
      <div class="layout-overlay layout-menu-toggle"></div>
    </div>
    <!-- / Layout wrapper -->

    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->
    <script src="../assets/vendor/libs/jquery/jquery.js"></script>
    <script src="../assets/vendor/libs/popper/popper.js"></script>
    <script src="../assets/vendor/js/bootstrap.js"></script>
    <script src="../assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="../assets/vendor/js/menu.js"></script>
    <!-- endbuild -->
    <!-- Main JS -->
    <script src="../assets/js/main.js"></script>
    <!-- Page JS -->
    <script src="../assets/js/ui-modals.js"></script>
    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
    <!-- Servlet JS -->
    <script src="../js/main.js"></script>
    <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
    
    <script>
    $(document).ready(function () {
        var newElement = document.getElementById('new')
        var answered = document.getElementById('answered')
        var dynamicTable = document.getElementById('dynamicTable')
		var breadCrumbHeading = document.getElementById('breadCrumbHeading')
		
        newElement.addEventListener('click', () => {
          newElement.classList.add('active')
          answered.classList.remove('active')
		  breadCrumbHeading.innerHTML = `<span class="text-muted fw-light">Tables /</span> New Questions`
          
          dynamicTable.innerHTML = `<h5 class="card-header">New Questions</h5>
                                  <div class="text-nowrap">
                                    <table class="table">
                                      <thead>
                                        <tr>
	                                        <th>Question ID</th>
	                                        <th>Customer</th>
	                                        <th>Product</th>
	                                        <th>Date</th>
	                                        <th>Question</th>
	                                        <th>Actions</th>
                                        </tr>
                                      </thead>
                                      <tbody class="table-border-bottom-0" id="newQuestionsList">
                                      </tbody>
                                    </table>
                                  </div>`
                                  
            var newQuestionsList = document.getElementById('newQuestionsList')
        	callGetNewQuestionsServlet(newQuestionsList)
        })

        answered.addEventListener('click', () => {
          answered.classList.add('active')
          newElement.classList.remove('active')
          breadCrumbHeading.innerHTML = `<span class="text-muted fw-light">Tables /</span> Answered Questions`

          dynamicTable.innerHTML = `<h5 class="card-header">Answered Questions</h5>
                                  <div class="text-nowrap">
                                    <table class="table">
                                      <thead>
                                        <tr>
	                                        <th>Question ID</th>
	                                        <th>Customer</th>
	                                        <th>Product</th>
	                                        <th>Answered Date</th>
	                                        <th>Question</th>
	                                        <th>Answer</th>
	                                        <th>Actions</th>
                                        </tr>
                                      </thead>
                                      <tbody class="table-border-bottom-0" id="answeredQuestionsList">
                                      </tbody>
                                    </table>
                                  </div>`
                                  
            var answeredQuestionsList = document.getElementById('answeredQuestionsList')
          	callGetAnsweredQuestionsServlet(answeredQuestionsList)
        })

        $('#new').click()
      });
    </script>
  </body>
</html>