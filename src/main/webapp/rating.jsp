<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <!-- Core CSS -->
    <link rel="stylesheet" href="Admin/assets/vendor/css/core.css" class="template-customizer-core-css" />

    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">

    <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">

    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">

    <link rel="stylesheet" href="css/aos.css">

    <link rel="stylesheet" href="css/ionicons.min.css">

    <link rel="stylesheet" href="css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="css/jquery.timepicker.css">


    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/icomoon.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/imageSelect.css">
    <!-- Page CSS -->

    <!-- Helpers -->
    <script src="Admin/assets/vendor/js/helpers.js"></script>

    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="Admin/assets/js/config.js"></script>


</head>

<body>
    <div class="mt-3">
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary reviewBtn" data-bs-toggle="modal"
            data-bs-target="#modalCenter">Rate</button>

        <!-- Modal -->
        <div class="modal fade" id="modalCenter" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content" style="border: none;">
                    <div class="modal-header"
                        style="justify-content: flex-start; border-bottom: none; padding-bottom: 0px; align-items: baseline; column-gap: 10px;">
                        <i class="fa-solid fa-arrow-left" data-bs-dismiss="modal" style="font-size: large;"></i>
                        <h5 style="color: gray;"> Write a review </h5>
                        <!-- 							<i class="fa-solid fa-xmark" data-bs-dismiss="modal" style="margin: 0px; position: absolute; margin: 0px; top: 10px; right: 10px; font-size: x-large;"></i> -->
                    </div>
                    <div class="modal-body" style="padding-top: 0px;">
                        <section class="mini-cart-no-scroll-bar" style="max-height: 500px; overflow-y: scroll;">
                            <div class="container">
                                <div class="row">
                                    <div class="col-md-12" style="padding-left: 0px; padding-right: 0px;">
                                        <div class="cart-list">
                                            <table class="table">
                                                <tbody>
                                                    <tr class="text-center"
                                                        style="display: flex; align-items: center; justify-content: center; column-gap: 25px; border: 1px solid transparent !important; border-bottom: 1px solid rgba(0, 0, 0, 0.05) !important;">
                                                        <td class="image-prod" style="border: none; padding: 0px;">
                                                            <div class="img"
                                                                style="background-image: url(images/product-3.jpg); margin: 0px;">
                                                            </div>
                                                        </td>
                                                        <td class="product-name"
                                                            style="width: auto; border: none; padding: 0px;">
                                                            <h3>Nike Free RN 2019 iD</h3>
                                                        </td>
                                                        <td class="product-name"
                                                            style="width: auto; border: none; padding: 0px;"><span>Size
                                                                Small</span>
                                                            <div class="w-100"></div> <span>Price RS114</span>
                                                        </td>
                                                    </tr>
                                                    <!-- END TR-->
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <div class="star-widget">
                            <input type="radio" name="rate" id="rate-5" value="5" checked> <label for="rate-5" class="fas fa-star"></label>
                            <input type="radio" name="rate" id="rate-4" value="4"> <label for="rate-4" class="fas fa-star"></label>
                            <input type="radio" name="rate" id="rate-3" value="3"> <label for="rate-3" class="fas fa-star"></label>
                            <input type="radio" name="rate" id="rate-2" value="2"> <label for="rate-2" class="fas fa-star"></label>
                            <input type="radio" name="rate" id="rate-1" value="1"> <label for="rate-1" class="fas fa-star"></label>
                        </div>
                        <div class="reviewCard">
                            <div class="drag-area">
                                <span class="visible">
                                    Drag & drop image here or
                                    <span class="select" role="button">Browse</span>
                                </span>
                                <span class="on-drop">Drop images here</span>
                                <input name="file" id="inputFile" type="file" class="file" multiple />
                            </div>

                            <!-- IMAGE PREVIEW CONTAINER -->
                            <div class="imageContainer"></div>
                        </div>
                        <textarea name="desc" id="reviewDescription" cols="30" rows="7" class="form-control reviewTextArea"
                            placeholder="Review" style="height: 130px; margin: 20px 0px 20px 0px;"></textarea>

                    </div>
                    <div class="modal-footer" style="border-top: none;">
                        <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary" id="ratingSubmitBtn">Submit</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <template>
        <div>
            <b-form-rating v-model="value" variant="warning" class="mb-2"></b-form-rating>
            <b-form-rating v-model="value" variant="success" class="mb-2"></b-form-rating>
            <b-form-rating v-model="value" variant="danger" class="mb-2"></b-form-rating>
            <b-form-rating v-model="value" variant="primary" class="mb-2"></b-form-rating>
            <b-form-rating v-model="value" variant="info" class="mb-2"></b-form-rating>
            <p class="mt-2">Value: {{ value }}</p>
        </div>
    </template>

    <script>
        export default {
            data() {
                return {
                    value: 3
                }
            }
        }
    </script>
    <!-- Core JS -->
    <!-- build:js assets/vendor/js/core.js -->
    <script src="Admin/assets/vendor/libs/jquery/jquery.js"></script>
    <script src="Admin/assets/vendor/libs/popper/popper.js"></script>
    <script src="Admin/assets/vendor/js/bootstrap.js"></script>
    <script src="Admin/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

    <script src="Admin/assets/vendor/js/menu.js"></script>
    <!-- endbuild -->

    <!-- Vendors JS -->

    <!-- Main JS -->
    <script src="Admin/assets/js/main.js"></script>

    <!-- Page JS -->
    <script src="Admin/assets/js/ui-modals.js"></script>

    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>

    <script src="js/jquery.min.js"></script>
    <script src="js/jquery-migrate-3.0.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.easing.1.3.js"></script>
    <script src="js/jquery.waypoints.min.js"></script>
    <script src="js/jquery.stellar.min.js"></script>
    <script src="js/owl.carousel.min.js"></script>
    <script src="js/jquery.magnific-popup.min.js"></script>
    <script src="js/aos.js"></script>
    <script src="js/jquery.animateNumber.min.js"></script>
    <script src="js/bootstrap-datepicker.js"></script>
    <script src="js/scrollax.min.js"></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
    <script src="js/google-map.js"></script>
    <script src="js/rating.js"></script>
    <script src="js/main.js"></script>
    <script src="https://kit.fontawesome.com/339febfaad.js" crossorigin="anonymous"></script>
    
    <script>
    ratingSubmitBtn.addEventListener('click', () => {
    	callAddReviewServlet()
    })
    
    </script>
</body>

</html>