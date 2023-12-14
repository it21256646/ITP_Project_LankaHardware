<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Minishop - Free Bootstrap 4 Template by Colorlib</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!--Search css Styles -->
<link rel="stylesheet" href="ashion-master/css/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet" href="ashion-master/css/elegant-icons.css"
	type="text/css">
<link rel="stylesheet" href="css/search.css" type="text/css">

<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">

<link rel="stylesheet" href="nice select/css/nice-select.css">

<link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
<link rel="stylesheet" href="css/animate.css">

<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">
<link rel="stylesheet" href="css/magnific-popup.css">

<link rel="stylesheet" href="css/aos.css">

<link rel="stylesheet" href="css/ionicons.min.css">

<link rel="stylesheet" href="css/bootstrap-datepicker.css">
<link rel="stylesheet" href="css/jquery.timepicker.css">

<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;200;300;400;500;700;900&display=swap">

<link rel="stylesheet" href="css/flaticon.css">
<link rel="stylesheet" href="css/icomoon.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
<link rel="stylesheet" href="quantity/style.css">

<style>
		.small-img-group {
			display: flex;
			column-gap: 10px;
		}

		.small-img-col {
			flex-basis: 24%;
			cursor: pointer;
		}

		.row {
			transition: 300ms all;
		}
		
		.small-img.active {
			border: 1px solid #8080803b;
		}
	</style>
</head>

<body class="goto-here">
	<div class="py-1 bg-black">
		<div class="container">
			<div
				class="row no-gutters d-flex align-items-start align-items-center px-md-0">
				<div class="col-lg-12 d-block">
					<div class="row d-flex">
						<div class="col-md pr-4 d-flex topper align-items-center">
							<div
								class="icon mr-2 d-flex justify-content-center align-items-center">
								<span class="icon-phone2"></span>
							</div>
							<span class="text">+ 1235 2355 98</span>
						</div>
						<div class="col-md pr-4 d-flex topper align-items-center">
							<div
								class="icon mr-2 d-flex justify-content-center align-items-center">
								<span class="icon-paper-plane"></span>
							</div>
							<span class="text">youremail@email.com</span>
						</div>
						<div
							class="col-md-5 pr-4 d-flex topper align-items-center text-lg-right">
							<span class="text">3-5 Business days delivery &amp; Free
								Returns</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
		<div class="container">
			<a class="navbar-brand" href="index.jsp">Minishop</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#ftco-nav" aria-controls="ftco-nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>

			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a href="index.html" class="nav-link">Home</a></li>
					<li class="nav-item dropdown active"><a
						class="nav-link dropdown-toggle" href="#" id="dropdown04"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Catalog</a>
						<div class="dropdown-menu" aria-labelledby="dropdown04">
							<a class="dropdown-item" href="shop.html">Shop</a> <a
								class="dropdown-item" href="product-single.html">Single
								Product</a> <a class="dropdown-item" href="cart.html">Cart</a> <a
								class="dropdown-item" href="checkout.html">Checkout</a>
						</div></li>
					<li class="nav-item"><a href="about.html" class="nav-link">About</a></li>
					<li class="nav-item"><a href="blog.html" class="nav-link">Blog</a></li>
					<li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li>
					<li class="nav-item"><a href="javascript: stopScrollingToTop();" class="nav-link"><i
							class="icon_search search-switch"></i></a></li>
					<li class="nav-item cta cta-colored" data-modal-target="#mini-cart"><a
						href="javascript: stopScrollingToTop();" class="nav-link"><span class="icon-shopping_cart"></span><span
							id="cartQuantity"></span></a></li>
					<li class="nav-item"><a href="javascript: stopScrollingToTop();" class="nav-link"><i
							class="fa-solid fa-user"></i></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<!-- END nav -->

	<div class="hero-wrap hero-bread"
		style="background-image: url('images/bg_6.jpg');">
		<div class="container">
			<div
				class="row no-gutters slider-text align-items-center justify-content-center">
				<div class="col-md-9 ftco-animate text-center">
					<p class="breadcrumbs">
						<span class="mr-2"><a href="index.html">Home</a></span> <span>Shop</span>
					</p>
					<h1 class="mb-0 bread">Shop</h1>
				</div>
			</div>
		</div>
	</div>

	<section class="ftco-section">
		<div class="container">
			<div class="row" id="productDetails"></div>




			<div class="row mt-5">
				<div class="col-md-12 nav-link-wrap"></div>
				<div class="col-md-12 tab-wrap">

					<div class="tab-content bg-light" id="v-pills-tabContent">




						<div class="tab-pane fade" id="v-pills-3" role="tabpanel"
							aria-labelledby="v-pills-day-3-tab">

							<div class="nav nav-pills d-flex text-center" id="v-pills-tab"
								role="tablist" aria-orientation="vertical">


								<a class="nav-link ftco-animate active" id="v-pills-3-tab"
									data-toggle="pill" href="#v-pills-3" role="tab"
									aria-controls="v-pills-3" aria-selected="false"
									style="width: 100%; padding: 15px 10px;">Reviews</a>

							</div>

							<div class="col-md-4" style="max-width: 100%;">
									<div class="rating-wrap">
									<div style="display: none;">
											<input type="radio" name="toggleTick" id="allToggle">
											<input type="radio" name="toggleTick" id="imagesToggle">
											<input type="radio" name="toggleTick" id="fiveStarToggle">
											<input type="radio" name="toggleTick" id="fourStarToggle">
											<input type="radio" name="toggleTick" id="threeStarToggle">
											<input type="radio" name="toggleTick" id="twoStarToggle">
											<input type="radio" name="toggleTick" id="oneStarToggle">
										</div>
										<h3 class="mb-4">Overview</h3>
										<div style="display: flex; justify-content: center; align-items: center; column-gap: 100px;">
                                    <div>
                                        <span style="font-size: xxx-large;" id="averageProductRating">4.1</span>
                                        <p class="star" style="display: flex; flex-direction: column;">
                                            <span style="display: flex;">
                                                <i class="ion-ios-star-outline" id="overviewStar1"></i>
                                                <i class="ion-ios-star-outline" id="overviewStar2"></i>
                                                <i class="ion-ios-star-outline" id="overviewStar3"></i>
                                                <i class="ion-ios-star-outline" id="overviewStar4"></i>
                                                <i class="ion-ios-star-outline" id="overviewStar5"></i>
                                            </span>
                                            <span id="totalRatings">1654</span>
                                        </p>
                                    </div>
                            
                                    <div style="display: flex; flex-direction: column;">
                                    <div style="display: flex; align-items: baseline; justify-content: space-between; column-gap: 10px;">
                                        <span>5</span>
                                        <div class="ratingProgress" id="FiveStarPercentage">
                                            <div class="ratingProgress__fill"></div>
                                        </div>
                                        <span id="FiveStarCount">20 Reviews</span>
                                    </div>
                            
                                    <div style="display: flex; align-items: baseline; justify-content: space-between; column-gap: 10px;">
                                        <span>4</span>
                                        <div class="ratingProgress" id="FourStarPercentage">
                                            <div class="ratingProgress__fill"></div>
                                        </div>
                                        <span id="FourStarCount">20 Reviews</span>
                                    </div>
                                    <div style="display: flex; align-items: baseline; justify-content: space-between; column-gap: 10px;">
                                        <span>3</span>
                                        <div class="ratingProgress" id="ThreeStarPercentage">
                                            <div class="ratingProgress__fill"></div>
                                        </div>
                                        <span id="ThreeStarCount">20 Reviews</span>
                                    </div>
                                    <div style="display: flex; align-items: baseline; justify-content: space-between; column-gap: 10px;">
                                        <span>2</span>
                                        <div class="ratingProgress" id="TwoStarPercentage">
                                            <div class="ratingProgress__fill"></div>
                                        </div>
                                        <span id="TwoStarCount">20 Reviews</span>
                                    </div>
                                    <div style="display: flex; align-items: baseline; justify-content: space-between; column-gap: 10px;">
                                        <span>1</span>
                                        <div class="ratingProgress" id="OneStarPercentage">
                                            <div class="ratingProgress__fill"></div>
                                        </div>
                                        <span id="OneStarCount">20 Reviews</span>
                                    </div>
                                </div>
                                </div>
									</div>
								</div>

							<div style="display: flex; justify-content: center; flex-wrap: wrap; margin-top: 20px;">
									<div class="cat" style="padding: 10px;" onclick="getFilteredReviews('all');">
										<a href="" onclick="return false;" class="btn reviewFilterButton" style="display: flex; align-items: center;  height: 33px; border: 1px solid #6c757d; color: #6c757d; width: fit-content; column-gap: 5px;">All<i class="fa-solid fa-check" style="color: green; display: block;" id="allCheck"></i></a>
									</div>
									<div class="cat" style="padding: 10px;" onclick="getFilteredReviews('with images');">
										<a href="" onclick="return false;" class="btn reviewFilterButton" style="display: flex; align-items: center;  height: 33px; border: 1px solid #6c757d; color: #6c757d; width: fit-content; column-gap: 5px;">With images<i class="fa-solid fa-check" style="color: green;" id="imagesCheck"></i></a>
									</div>
									<div class="cat" style="padding: 10px;" onclick="getFilteredReviews('fiveStar');">
										<a href="" onclick="return false;" class="btn reviewFilterButton" style="display: flex; align-items: center;  height: 33px; border: 1px solid #6c757d; color: #6c757d; width: fit-content; column-gap: 5px;">
											<i class="ion-ios-star"></i>
											<i class="ion-ios-star"></i>
											<i class="ion-ios-star"></i>
											<i class="ion-ios-star"></i>
											<i class="ion-ios-star"></i>
											<i class="fa-solid fa-check" style="color: green;" id="fiveCheck"></i></a>
									</div>
									<div class="cat" style="padding: 10px;" onclick="getFilteredReviews('fourStar');">
										<a href="" onclick="return false;" class="btn reviewFilterButton" style="display: flex; align-items: center;  height: 33px; border: 1px solid #6c757d; color: #6c757d; width: fit-content; column-gap: 5px;"><i class="ion-ios-star"></i>
											<i class="ion-ios-star"></i>
											<i class="ion-ios-star"></i>
											<i class="ion-ios-star"></i><i class="fa-solid fa-check" style="color: green;" id="fourCheck"></i></a>
									</div>
									<div class="cat" style="padding: 10px;" onclick="getFilteredReviews('threeStar');">
										<a href="" onclick="return false;" class="btn reviewFilterButton" style="display: flex; align-items: center;  height: 33px; border: 1px solid #6c757d; color: #6c757d; width: fit-content; column-gap: 5px;"><i class="ion-ios-star"></i>
											<i class="ion-ios-star"></i>
											<i class="ion-ios-star"></i><i class="fa-solid fa-check" style="color: green;" id="threeCheck"></i></a>
									</div>
									<div class="cat" style="padding: 10px;" onclick="getFilteredReviews('twoStar');">
										<a href="" onclick="return false;" class="btn reviewFilterButton" style="display: flex; align-items: center;  height: 33px; border: 1px solid #6c757d; color: #6c757d; width: fit-content; column-gap: 5px;"><i class="ion-ios-star"></i>
											<i class="ion-ios-star"></i><i class="fa-solid fa-check" style="color: green;" id="twoCheck"></i></a>
									</div>
									<div class="cat" style="padding: 10px;" onclick="getFilteredReviews('oneStar');">
										<a href="" onclick="return false;" class="btn reviewFilterButton" style="display: flex; align-items: center;  height: 33px; border: 1px solid #6c757d; color: #6c757d; width: fit-content; column-gap: 5px;"><i class="ion-ios-star"></i><i class="fa-solid fa-check" style="color: green;" id="oneCheck"></i></a>
									</div>
								</div>

							<div class="row p-4">
								<div class="col-md-7 reviewOverflow"
									style="max-height: 715px; overflow-y: scroll;"  id="reviewContainer">
									
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="ftco-section">
			<div class="container bg-light" style="padding: 15px;">
				<h3>Questions about this Product</h3>
				<h5 class="mb-4" id="totalQuestions">23 Questions</h5>
				<div class="mt-5 reviewOverflow" id="productQuestionsList">
					<ul class="comment-list" style="overflow: auto; border-bottom: 1px solid rgba(0, 0, 0, 0.05); margin-top: 10px;">
						<li class="comment">
							<div class="vcard bio">
								<img src="images/person_1.jpg" alt="Image placeholder">
							</div>
							<div class="comment-body">
								<h3>John Doe</h3>
								<div class="meta">June 27, 2018 at 2:21pm</div>
								<p><span>Question: </span> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum
									necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim sapiente iste
									iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
								
							</div>
	
							<ul class="children">
								<li class="comment">
									<div class="vcard bio">
										<img src="images/person_1.jpg" alt="Image placeholder">
									</div>
									<div class="comment-body">
										<h3>John Doe</h3>
										<div class="meta">June 27, 2018 at 2:21pm</div>
										<p><span>Answer: </span> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur quidem laborum
											necessitatibus, ipsam impedit vitae autem, eum officia, fugiat saepe enim
											sapiente iste iure! Quam voluptas earum impedit necessitatibus, nihil?</p>
										
									</div>
								</li>
							</ul>
						</li>
					</ul>
					<!-- END comment-list -->
	
					
				</div>
				<div style="display: flex; justify-content: flex-end;">
					<a href="#" class="btn btn-primary py-3 px-4" data-bs-toggle="modal" data-bs-target="#modalCenter" id="openQuestionModal">Ask a
						Question</a>
				</div>
			</div>
		</section>

	<section class="ftco-section bg-light">
    	<div class="container">
				<div class="row justify-content-center mb-3 pb-3">
          <div class="col-md-12 heading-section text-center ftco-animate fadeInUp ftco-animated">
            <h2 class="mb-4">Related Products</h2>
          </div>
        </div>   		
    	</div>
    	<div class="container">
    		<div class="row">
				<div class="owl-carousel owl-theme" id="relatedProductList">
					
				</div>

    		</div>
    	</div>
    </section>

	<footer class="ftco-footer ftco-section">
		<div class="container">
			<div class="row">
				<div class="mouse">
					<a href="#" class="mouse-icon">
						<div class="mouse-wheel">
							<span class="ion-ios-arrow-up"></span>
						</div>
					</a>
				</div>
			</div>
			<div class="row mb-5">
				<div class="col-md">
					<div class="ftco-footer-widget mb-4">
						<h2 class="ftco-heading-2">Minishop</h2>
						<p>Far far away, behind the word mountains, far from the
							countries Vokalia and Consonantia.</p>
						<ul
							class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
							<li class="ftco-animate"><a href="#"><span
									class="icon-twitter"></span></a></li>
							<li class="ftco-animate"><a href="#"><span
									class="icon-facebook"></span></a></li>
							<li class="ftco-animate"><a href="#"><span
									class="icon-instagram"></span></a></li>
						</ul>
					</div>
				</div>
				<div class="col-md">
					<div class="ftco-footer-widget mb-4 ml-md-5">
						<h2 class="ftco-heading-2">Menu</h2>
						<ul class="list-unstyled">
							<li><a href="#" class="py-2 d-block">Shop</a></li>
							<li><a href="#" class="py-2 d-block">About</a></li>
							<li><a href="#" class="py-2 d-block">Journal</a></li>
							<li><a href="#" class="py-2 d-block">Contact Us</a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-4">
					<div class="ftco-footer-widget mb-4">
						<h2 class="ftco-heading-2">Help</h2>
						<div class="d-flex">
							<ul class="list-unstyled mr-l-5 pr-l-3 mr-4">
								<li><a href="#" class="py-2 d-block">Shipping
										Information</a></li>
								<li><a href="#" class="py-2 d-block">Returns &amp;
										Exchange</a></li>
								<li><a href="#" class="py-2 d-block">Terms &amp;
										Conditions</a></li>
								<li><a href="#" class="py-2 d-block">Privacy Policy</a></li>
							</ul>
							<ul class="list-unstyled">
								<li><a href="#" class="py-2 d-block">FAQs</a></li>
								<li><a href="#" class="py-2 d-block">Contact</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md">
					<div class="ftco-footer-widget mb-4">
						<h2 class="ftco-heading-2">Have a Questions?</h2>
						<div class="block-23 mb-3">
							<ul>
								<li><span class="icon icon-map-marker"></span><span
									class="text">203 Fake St. Mountain View, San Francisco,
										California, USA</span></li>
								<li><a href="#"><span class="icon icon-phone"></span><span
										class="text">+2 392 3929 210</span></a></li>
								<li><a href="#"><span class="icon icon-envelope"></span><span
										class="text">info@yourdomain.com</span></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12 text-center">

					<p>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						Copyright &copy;
						<script>
							document.write(new Date().getFullYear());
						</script>
						All rights reserved | This template is made with <i
							class="icon-heart color-danger" aria-hidden="true"></i> by <a
							href="https://colorlib.com" target="_blank">Colorlib</a>
						<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
					</p>
				</div>
			</div>
		</div>
	</footer>



	<!-- loader -->
	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" />
		</svg>
	</div>


	<!-- Mini cart -->
	<div class="mini-cart" id="mini-cart">
		<div class="mini-cart-header">
			<div class="mini-cart-header-title">Mini cart</div>
			<button data-close-button class="mini-cart-close">&times;</button>
		</div>
		<div class="mini-cart-body mini-cart-no-scroll-bar">
			<section class="ftco-section ftco-cart mini-cart-no-scroll-bar"
				style="padding: 0px; height: 100%; overflow-y: scroll;">
				<div class="container">
					<div class="row">
						<div class="col-md-12 ftco-animate"
							style="padding-left: 0px; padding-right: 0px;">
							<div class="cart-list">
								<table class="table">
									<tbody id="miniCart_itemList">
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</section>
			<p class="text-center">
				<a href="cart.jsp" class="btn btn-primary py-3 px-4"
					style="width: 100%; font-size: 1.25rem;">Proceed to Cart</a>
			</p>
		</div>
	</div>

	<div id="mini-cart-overlay"></div>

	<div class="alert alert-success added_msg" role="alert" id="added_msg"></div>

	<!-- Search Begin -->
	<div class="search-model">
		<div class="h-100 d-flex align-items-center justify-content-center">
			<div class="search-close-switch" id="mainSearchClose">+</div>
			<form class="search-model-form">
				<div>
	                <input type="text" id="search-input" placeholder="Search here....." oninput="mainSearch();">
	                <i class="fa-solid fa-magnifying-glass searchFormBtn clickable" onclick="searchToShop();"></i>
	            </div>

				<section class="mini-cart-no-scroll-bar"
					style="max-height: 500px; overflow-y: scroll;">
					<div class="container">
						<div class="row">
							<div class="col-md-12 ftco-animate"
								style="padding-left: 0px; padding-right: 0px;">
								<div class="cart-list">
									<table class="table">
										<tbody id="result">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</section>
			</form>
		</div>
	</div>
	<!-- Search End -->

	<!-- Question Modal -->
	<div class="modal fade" id="modalCenter" tabindex="-1" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content" style="border: none;">
				<div class="modal-header"
					style="justify-content: flex-start; border-bottom: none; padding-bottom: 0px; align-items: baseline; column-gap: 10px;" id="questionModalHeader">
					<i class="fa-solid fa-arrow-left" data-bs-dismiss="modal" style="font-size: large;"></i>
					<h5 style="color: gray;"> Ask a Question </h5>
				</div>
				<div class="modal-body" style="padding-top: 0px;" id="questionModalBody">



					<textarea name="desc" id="questionTextArea" cols="30" rows="7" class="form-control reviewTextArea" style="height: 130px; margin: 20px 0px 20px 0px;"></textarea>




				</div>
				<div class="modal-footer" style="border-top: none;" id="questionModalFooter">
					<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="questionSubmitBtn">Submit</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="quickViewModal" tabindex="-1" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered quickView-modal" role="document">
		</div>
	</div>
	
	<!-- Login msg Modal -->
		<div class="modal fade" id="loginMsgModal" tabindex="-1" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
			  <div class="modal-content quickView-modalContent">
				<div class="modal-header" style="justify-content: flex-end; padding-top: 0px; padding-right: 10px; padding-bottom: 0px;">
				  <button data-bs-dismiss="modal" style="border: none; outline: none; background: none; font-size: 1.25rem; font-weight: bold; color: rgb(0, 0, 0);">&times;</button>
				</div>
				<div class="modal-body" style="text-align: center;">
					<h1>You need to login first</h1>
				</div>
				<div class="modal-footer">
	                <a href="#" class="btn btn-primary px-4" data-bs-dismiss="modal">Cancel</a>
	                <a href="#" class="btn btn-primary px-4">Login</a>
				</div>
			  </div>
			</div>
		</div>
		
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

	<!--Search Js Plugins -->
	<script src="ashion-master/js/jquery-3.3.1.min.js"></script>
	<script src="ashion-master/js/main.js"></script>

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
	<script src="js/main.js"></script>
	<script src="https://kit.fontawesome.com/339febfaad.js" crossorigin="anonymous"></script>
	<script src="quantity/script.js"></script>
	<script src="nice select/js/jquery.js"></script> 
	<script src="nice select/js/jquery.nice-select.js"></script>
	<!-- magnific popup js cdn link  -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
	
	<script>
		$(document).ready(function() {
			jQuery('select').niceSelect();
			
			callCartServlet(false)

			const keyValue = window.location.search
			const urlParam = new URLSearchParams(keyValue)
			var itemID = urlParam.get('product')

			callGetProductSingleServlet(itemID)
		});
	</script>

</body>

</html>