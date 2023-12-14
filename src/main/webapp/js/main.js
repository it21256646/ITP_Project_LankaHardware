 AOS.init({
 	duration: 800,
 	easing: 'slide'
 });

(function($) {

	"use strict";

	var isMobile = {
		Android: function() {
			return navigator.userAgent.match(/Android/i);
		},
			BlackBerry: function() {
			return navigator.userAgent.match(/BlackBerry/i);
		},
			iOS: function() {
			return navigator.userAgent.match(/iPhone|iPad|iPod/i);
		},
			Opera: function() {
			return navigator.userAgent.match(/Opera Mini/i);
		},
			Windows: function() {
			return navigator.userAgent.match(/IEMobile/i);
		},
			any: function() {
			return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
		}
	};


	$(window).stellar({
    responsive: true,
    parallaxBackgrounds: true,
    parallaxElements: true,
    horizontalScrolling: false,
    hideDistantElements: false,
    scrollProperty: 'scroll'
  });


	var fullHeight = function() {

		$('.js-fullheight').css('height', $(window).height());
		$(window).resize(function(){
			$('.js-fullheight').css('height', $(window).height());
		});

	};
	fullHeight();

	// loader
	var loader = function() {
		setTimeout(function() { 
			if($('#ftco-loader').length > 0) {
				$('#ftco-loader').removeClass('show');
			}
		}, 1);
	};
	loader();

	// Scrollax
   $.Scrollax();

	var carousel = function() {
		$('.home-slider').owlCarousel({
	    loop:true,
	    autoplay: true,
	    margin:0,
	    animateOut: 'fadeOut',
	    animateIn: 'fadeIn',
	    nav:false,
	    autoplayHoverPause: false,
	    items: 1,
	    navText : ["<span class='ion-md-arrow-back'></span>","<span class='ion-chevron-right'></span>"],
	    responsive:{
	      0:{
	        items:1
	      },
	      600:{
	        items:1
	      },
	      1000:{
	        items:1
	      }
	    }
		});
	
		$('.carousel-testimony').owlCarousel({
			center: true,
			loop: true,
			items:1,
			margin: 30,
			stagePadding: 0,
			nav: false,
			navText: ['<span class="ion-ios-arrow-back">', '<span class="ion-ios-arrow-forward">'],
			responsive:{
				0:{
					items: 1
				},
				600:{
					items: 1
				},
				1000:{
					items: 1
				}
			}
		});

	};
	carousel();

	$('nav .dropdown').hover(function(){
		var $this = $(this);
		// 	 timer;
		// clearTimeout(timer);
		$this.addClass('show');
		$this.find('> a').attr('aria-expanded', true);
		// $this.find('.dropdown-menu').addClass('animated-fast fadeInUp show');
		$this.find('.dropdown-menu').addClass('show');
	}, function(){
		var $this = $(this);
			// timer;
		// timer = setTimeout(function(){
			$this.removeClass('show');
			$this.find('> a').attr('aria-expanded', false);
			// $this.find('.dropdown-menu').removeClass('animated-fast fadeInUp show');
			$this.find('.dropdown-menu').removeClass('show');
		// }, 100);
	});


	$('#dropdown04').on('show.bs.dropdown', function () {
	  console.log('show');
	});

	// scroll
	var scrollWindow = function() {
		$(window).scroll(function(){
			var $w = $(this),
					st = $w.scrollTop(),
					navbar = $('.ftco_navbar'),
					sd = $('.js-scroll-wrap');

			if (st > 150) {
				if ( !navbar.hasClass('scrolled') ) {
					navbar.addClass('scrolled');	
				}
			} 
			if (st < 150) {
				if ( navbar.hasClass('scrolled') ) {
					navbar.removeClass('scrolled sleep');
				}
			} 
			if ( st > 350 ) {
				if ( !navbar.hasClass('awake') ) {
					navbar.addClass('awake');	
				}
				
				if(sd.length > 0) {
					sd.addClass('sleep');
				}
			}
			if ( st < 350 ) {
				if ( navbar.hasClass('awake') ) {
					navbar.removeClass('awake');
					navbar.addClass('sleep');
				}
				if(sd.length > 0) {
					sd.removeClass('sleep');
				}
			}
		});
	};
	scrollWindow();

	
	var counter = function() {
		
		$('#section-counter').waypoint( function( direction ) {

			if( direction === 'down' && !$(this.element).hasClass('ftco-animated') ) {

				var comma_separator_number_step = $.animateNumber.numberStepFactories.separator(',')
				$('.number').each(function(){
					var $this = $(this),
						num = $this.data('number');
						console.log(num);
					$this.animateNumber(
					  {
					    number: num,
					    numberStep: comma_separator_number_step
					  }, 7000
					);
				});
				
			}

		} , { offset: '95%' } );

	}
	counter();

	var contentWayPoint = function() {
		var i = 0;
		$('.ftco-animate').waypoint( function( direction ) {

			if( direction === 'down' && !$(this.element).hasClass('ftco-animated') ) {
				
				i++;

				$(this.element).addClass('item-animate');
				setTimeout(function(){

					$('body .ftco-animate.item-animate').each(function(k){
						var el = $(this);
						setTimeout( function () {
							var effect = el.data('animate-effect');
							if ( effect === 'fadeIn') {
								el.addClass('fadeIn ftco-animated');
							} else if ( effect === 'fadeInLeft') {
								el.addClass('fadeInLeft ftco-animated');
							} else if ( effect === 'fadeInRight') {
								el.addClass('fadeInRight ftco-animated');
							} else {
								el.addClass('fadeInUp ftco-animated');
							}
							el.removeClass('item-animate');
						},  k * 50, 'easeInOutExpo' );
					});
					
				}, 100);
				
			}

		} , { offset: '95%' } );
	};
	contentWayPoint();


	// navigation
	var OnePageNav = function() {
		$(".smoothscroll[href^='#'], #ftco-nav ul li a[href^='#']").on('click', function(e) {
		 	e.preventDefault();

		 	var hash = this.hash,
		 			navToggler = $('.navbar-toggler');
		 	$('html, body').animate({
		    scrollTop: $(hash).offset().top
		  }, 700, 'easeInOutExpo', function(){
		    window.location.hash = hash;
		  });


		  if ( navToggler.is(':visible') ) {
		  	navToggler.click();
		  }
		});
		$('body').on('activate.bs.scrollspy', function () {
		  console.log('nice');
		})
	};
	OnePageNav();


	// magnific popup
	$('.image-popup').magnificPopup({
    type: 'image',
    closeOnContentClick: true,
    closeBtnInside: false,
    fixedContentPos: true,
    mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
     gallery: {
      enabled: true,
      navigateByImgClick: true,
      preload: [0,1] // Will preload 0 - before current, and 1 after the current image
    },
    image: {
      verticalFit: true
    },
    zoom: {
      enabled: true,
      duration: 300 // don't foget to change the duration also in CSS
    }
  });

  $('.popup-youtube, .popup-vimeo, .popup-gmaps').magnificPopup({
    disableOn: 700,
    type: 'iframe',
    mainClass: 'mfp-fade',
    removalDelay: 160,
    preloader: false,

    fixedContentPos: false
  });



	var goHere = function() {

		$('.mouse-icon').on('click', function(event){
			
			event.preventDefault();

			$('html,body').animate({
				scrollTop: $('.goto-here').offset().top
			}, 500, 'easeInOutExpo');
			
			return false;
		});
	};
	goHere();

	function makeTimer() {

		var endTime = new Date("21 December 2019 9:56:00 GMT+01:00");			
		endTime = (Date.parse(endTime) / 1000);

		var now = new Date();
		now = (Date.parse(now) / 1000);

		var timeLeft = endTime - now;

		var days = Math.floor(timeLeft / 86400); 
		var hours = Math.floor((timeLeft - (days * 86400)) / 3600);
		var minutes = Math.floor((timeLeft - (days * 86400) - (hours * 3600 )) / 60);
		var seconds = Math.floor((timeLeft - (days * 86400) - (hours * 3600) - (minutes * 60)));

		if (hours < "10") { hours = "0" + hours; }
		if (minutes < "10") { minutes = "0" + minutes; }
		if (seconds < "10") { seconds = "0" + seconds; }

		$("#days").html(days + "<span>Days</span>");
		$("#hours").html(hours + "<span>Hours</span>");
		$("#minutes").html(minutes + "<span>Minutes</span>");
		$("#seconds").html(seconds + "<span>Seconds</span>");		

}

setInterval(function() { makeTimer(); }, 1000);




})(jQuery);

//Important elements
var miniCart_itemList = document.getElementById('miniCart_itemList')
var mainCart_itemList = document.getElementById('mainCart_itemList')
var newArrival_itemList = document.getElementById('newArrival_itemList')
var wishlist_itemList = document.getElementById('wishlist_itemList')
var cartQuantity = document.getElementById('cartQuantity')
var added_msg = document.getElementById('added_msg')
var cartTotals = document.getElementById('cartTotals')
var cartSubTotal = document.getElementById('cartSubTotal')
var productDetails = document.getElementById('productDetails')
var ProductSearchResult = document.getElementById('result')
var mainSearchClose = document.getElementById('mainSearchClose')
var shopItemList = document.getElementById('shopItemList')
var shopMainCategoryList = document.getElementById('accordion')
var priceMax = document.getElementById('priceMax')
var priceMin = document.getElementById('priceMin')
var priceRangeMin = document.getElementById('priceRangeMin')
var priceRangeMax = document.getElementById('priceRangeMax')
var priceRangeProgress = document.getElementById('priceRangeProgress')
var reviewContainer = document.getElementById('reviewContainer')
var currentFilters = document.getElementById('currentFilters')
var sortBy = document.getElementById('people')
var relatedProductList = document.getElementById('relatedProductList')
var ratingSubmitBtn = document.getElementById('ratingSubmitBtn')
var sendEmailBtn = document.getElementById('sendEmailBtn')
var emailModalBody = document.getElementById('emailModalBody')
var totalRatings = document.getElementById('totalRatings')
var averageProductRating = document.getElementById('averageProductRating')
var brandListElement = document.getElementById('brandListElement')
var allToggle = document.getElementById('allToggle')
var imagesToggle = document.getElementById('imagesToggle')
var fiveStarToggle = document.getElementById('fiveStarToggle')
var fourStarToggle = document.getElementById('fourStarToggle')
var threeStarToggle = document.getElementById('threeStarToggle')
var twoStarToggle = document.getElementById('twoStarToggle')
var oneStarToggle = document.getElementById('oneStarToggle')
var productQuestionsList = document.getElementById('productQuestionsList')
var totalQuestions = document.getElementById('totalQuestions')
var openQuestionModal = document.getElementById('openQuestionModal')
var questionModalHeader = document.getElementById('questionModalHeader')
var questionModalBody = document.getElementById('questionModalBody')
var questionModalFooter = document.getElementById('questionModalFooter')
var quickViewModal = document.getElementById('quickViewModal')

function stopScrollingToTop(){
	return false
}

//Mini cart
const openModalButtons = document.querySelectorAll('[data-modal-target]')
const closeModalButtons = document.querySelectorAll('[data-close-button]')
const overlay = document.getElementById('mini-cart-overlay')

openModalButtons.forEach(button => {
  button.addEventListener('click', () => {
	callCartServlet(false)
    const modal = document.querySelector(button.dataset.modalTarget)
    openModal(modal)
  })
})

overlay.addEventListener('click', () => {
  const modals = document.querySelectorAll('.mini-cart.active')
  modals.forEach(modal => {
    closeModal(modal)
  })
})

closeModalButtons.forEach(button => {
  button.addEventListener('click', () => {
    const modal = button.closest('.mini-cart')
    closeModal(modal)
  })
})

function openModal(modal) {
  if (modal == null) return
  modal.classList.add('active')
  overlay.classList.add('active')
}

function closeModal(modal) {
  if (modal == null) return
  modal.classList.remove('active')
  overlay.classList.remove('active')
  
  setTimeout(function() {
    miniCart_itemList.innerHTML="";
  }, 300);
}

//Call wishlist servlet
var wishlistItems = []
var fromWishlist = false

function callWishlistServlet(){
	$.get("http://localhost:8080/LankaHardware/GetWishlistServlet", function(response) {
				
		wishlistItems = response
		buildWishlist(wishlistItems)
		fromWishlist = true
	})
}

function buildWishlist(wishlistItems){
	quickViewsizesAndPrizes = []
	isInWishlist = []
	quickViewsizesAndStock = []
	
	wishlist_itemList.innerHTML = ''
	
	for(var i = 0; i < wishlistItems.length; i++){
		quickViewsizesAndPrizes.push(wishlistItems[i].sizesAndPrizes)
		isInWishlist.push(wishlistItems[i].isInWishlist)
		quickViewsizesAndStock.push(wishlistItems[i].sizesAndStock)
		
		var starID = `${wishlistItems[i].itemID + wishlistItems[i].size}wishlistStar`
		
		var item = `<div class="col-sm-12 col-md-6 col-lg-3 ftco-animate d-flex fadeInUp ftco-animated" id="${wishlistItems[i].itemID + wishlistItems[i].size}ProductCard">
    				<div class="product d-flex flex-column">
    					<a href="#" class="img-prod"><img class="img-fluid" src="${wishlistItems[i].mainImg}" alt="Colorlib Template">
    						<div class="overlay"></div>
    					</a>
    					<div class="text py-3 pb-4 px-3">
    					
    						<div class="d-flex">
    							<div class="cat">
		    						<span>${wishlistItems[i].brand}</span>
		    					</div>
		    					<div class="rating">
	    							<p class="text-right mb-0">
	    								<a href="#"><span class="ion-ios-star-outline" id="${starID}1"></span></a>
	    								<a href="#"><span class="ion-ios-star-outline" id="${starID}2"></span></a>
	    								<a href="#"><span class="ion-ios-star-outline" id="${starID}3"></span></a>
	    								<a href="#"><span class="ion-ios-star-outline" id="${starID}4"></span></a>
	    								<a href="#"><span class="ion-ios-star-outline" id="${starID}5"></span></a>
	    							</p>
	    						</div>
	    					</div>
    						<h3><a href="#">${wishlistItems[i].name}</a></h3>
    						<div class="pricing">
	    						<p class="price" style="display: flex; justify-content: space-between;">
		    						<span>Rs${wishlistItems[i].price}</span>
		    						<span style="color: grey;">${wishlistItems[i].size}</span>
	    						</p>
	    					</div>
    						
    						<p class="bottom-area d-flex px-3" data-bs-toggle="modal" data-bs-target="#quickViewModal" onclick="buildQuickView('${wishlistItems[i].itemID}', '${wishlistItems[i].mainImg}', '${wishlistItems[i].name}', '${wishlistItems[i].price}', '${wishlistItems[i].description}', ${wishlistItems[i].avgRating}, ${i}, '${wishlistItems[i].size}', ${wishlistItems[i].stock});">
								<a href="#" onclick="return false;" class="add-to-cart text-center py-2 mr-1"><span>Quick View <i class="fa-regular fa-eye ml-1"></i></span></a>
							</p>
							
    					</div>
    				</div>
    			</div>`
    			
    	wishlist_itemList.innerHTML += item
    	buildAverageRating(wishlistItems[i], starID)
	}
}

//Add to wishlist
function callAddToWishlistServlet(itemID){
	var size = document.getElementById('ProductSizes').value
	$.post("http://localhost:8080/LankaHardware/AddToWishlistServlet", {itemID : itemID, size: size}, function(response){
	    
	    added_msg.innerHTML = response
	    added_msg.classList.add('active')
	    setTimeout(function() {
	    	added_msg.classList.remove('active')
	  	}, 2000);
	})
}

function callAddToWishlistServletFromQuickView(itemID){
	var size = document.getElementById('quickViewProductSizes').value
	$.post("http://localhost:8080/LankaHardware/AddToWishlistServlet", {itemID : itemID, size: size}, function(response){
	    
	    if(fromWishlist == true){
			callWishlistServlet()
		}
	    
	    added_msg.innerHTML = response
	    added_msg.classList.add('active')
	    setTimeout(function() {
	    	added_msg.classList.remove('active')
	  	}, 2000);
	})
}

//Remove from wishlist
function callRemoveFromWishlistServlet(itemID){
	var size = document.getElementById('ProductSizes').value
	
	$.post("http://localhost:8080/LankaHardware/RemoveFromWishlistServlet", {itemID : itemID, size: size}, function(response){
	   
	   added_msg.innerHTML = response
	   added_msg.classList.add('active')
	   setTimeout(function() {
	   	added_msg.classList.remove('active')
	   }, 2000);
	})
}

function callRemoveFromWishlistServletFromQuickView(itemID){
	var size = document.getElementById('quickViewProductSizes').value
	
	$.post("http://localhost:8080/LankaHardware/RemoveFromWishlistServlet", {itemID : itemID, size: size}, function(response){
	   
		if(fromWishlist == true){
			deleteWishlistItemElement(itemID + size)
		}
		
		added_msg.innerHTML = response
		added_msg.classList.add('active')
		setTimeout(function() {
			added_msg.classList.remove('active')
		}, 2000);
})
}

//delete wishlist item element
function deleteWishlistItemElement(id){
	var productCard = document.getElementById(`${id}ProductCard`)
	
	productCard.classList.remove('d-flex')
	productCard.style = "display: none;"
}

//call SendBackInStockEmailServlet
function callSendBackInStockEmailServlet(){
	var itemID = 'i200'
	
	emailModalBody.innerHTML = `<span class="loader">Send&nbsp;ng</span>
                    			<span class="loader2"></span>`
	
	$.post("http://localhost:8080/LankaHardware/SendBackInStockEmailServlet", {itemID : itemID}, function(response){
	   
	   emailModalBody.innerHTML = `<div style="display: flex; justify-content: center; align-items: center; column-gap: 10px;">
									        <lottie-player src="https://assets3.lottiefiles.com/packages/lf20_q7hiluze.json"  background="transparent"  speed="1"  style="width: 50px; height: 50px;" autoplay></lottie-player>
									        <span style="font-size: x-large;">${response}</span>
									</div>`
									
		setTimeout(function() {
			$('#emailModal').modal('hide')
		}, 2500);
	})
}

//call index servlet
var newArrivals = []
var quickViewsizesAndPrizes = []
var isInWishlist = []
var quickViewsizesAndStock = []

function callIndexServlet(){
	$.get("http://localhost:8080/LankaHardware/GetIndexServlet", function(response) {
				
		newArrivals = response
		buildNewArrivalslist(newArrivals)
	})
}

function buildNewArrivalslist(newArrivals){
	quickViewsizesAndPrizes = []
	isInWishlist = []
	quickViewsizesAndStock = []
	
	for(var i = 0; i < newArrivals.length; i++){
		quickViewsizesAndPrizes.push(newArrivals[i].sizesAndPrizes)
		isInWishlist.push(newArrivals[i].isInWishlist)
		quickViewsizesAndStock.push(newArrivals[i].sizesAndStock)
		
		var starID = newArrivals[i].itemID + 'AvgStar'

		var item = `<div class="col-sm-12 col-md-6 col-lg-3 ftco-animate d-flex fadeInUp ftco-animated">
    				<div class="product d-flex flex-column">
    					<a href="#" class="img-prod" onclick="return false;"><img class="img-fluid" src="${newArrivals[i].mainImg}" alt="Colorlib Template" onclick="toProductSinglePage('${newArrivals[i].itemID}');">
    						<div class="overlay"></div>
    					</a>
    					<div class="text py-3 pb-4 px-3">
    						<div class="d-flex">
    							<div class="cat">
		    						<span>${newArrivals[i].brand}</span>
		    					</div>
		    					<div class="rating">
	    							<p class="text-right mb-0">
	    								<a href="#"><span class="ion-ios-star-outline" id="${starID}1"></span></a>
	    								<a href="#"><span class="ion-ios-star-outline" id="${starID}2"></span></a>
	    								<a href="#"><span class="ion-ios-star-outline" id="${starID}3"></span></a>
	    								<a href="#"><span class="ion-ios-star-outline" id="${starID}4"></span></a>
	    								<a href="#"><span class="ion-ios-star-outline" id="${starID}5"></span></a>
	    							</p>
	    						</div>
	    					</div>
    						<h3><a href="#" onclick="toProductSinglePage('${newArrivals[i].itemID}'); return false;">${newArrivals[i].name}</a></h3>
    						<div class="pricing">
	    						<p class="price"><span>Rs${newArrivals[i].price}</span></p>
	    					</div>
	    					<p class="bottom-area d-flex px-3" data-bs-toggle="modal" data-bs-target="#quickViewModal" onclick="buildQuickView('${newArrivals[i].itemID}', '${newArrivals[i].mainImg}', '${newArrivals[i].name}', '${newArrivals[i].price}', '${newArrivals[i].description}', ${newArrivals[i].avgRating}, ${i}, '${newArrivals[i].size}', ${newArrivals[i].stock});">
								<a href="#" onclick="return false;" class="add-to-cart text-center py-2 mr-1"><span>Quick View <i class="fa-regular fa-eye ml-1"></i></span></a>
							</p>
    					</div>
    				</div>
    			</div>`
    			
    	newArrival_itemList.innerHTML += item
    	buildAverageRating(newArrivals[i], starID)
	}
}

//build quick view
function buildQuickView(itemID, mainImg, name, price, description, avgRating, sizesAndPrizes, size, stock){
	var starID = `QuickViewStar`
	var iconID = `${itemID}${size}Icon`
	
	quickViewModal.innerHTML = `<div class="modal-dialog modal-dialog-centered quickView-modal" role="document">
								  <div class="modal-content quickView-modalContent">
									<div class="modal-header quickView-header" style="justify-content: flex-end; padding-top: 0px; padding-right: 10px; padding-bottom: 0px;">
									  <button data-bs-dismiss="modal" style="border: none; outline: none; background: none; font-size: 1.25rem; font-weight: bold; color: rgb(0, 0, 0);">&times;</button>
									</div>
									<div class="modal-body quickView-body">
											<div class="quickView-imgContainer">
												<img src="${mainImg}" class="quickView-img" alt="Colorlib Template">
											</div>
						
											<div class="product-details ftco-animate fadeInUp ftco-animated quickView-description">
												<h3 class="quickView-margin">${name}</h3>
												<div class="rating d-flex quickView-ratingAndPrice">
													<p class="text-left mr-4 quickView-margin">
														<a href="#" class="mr-2">${Math.round(avgRating * 10) / 10}</a>
														<a href="#"><span class="ion-ios-star-outline" id="${starID}1"></span></a>
														<a href="#"><span class="ion-ios-star-outline" id="${starID}2"></span></a>
														<a href="#"><span class="ion-ios-star-outline" id="${starID}3"></span></a>
														<a href="#"><span class="ion-ios-star-outline" id="${starID}4"></span></a>
														<a href="#"><span class="ion-ios-star-outline" id="${starID}5"></span></a>
													</p>
													<p class="price quickView-margin"><span id="quickViewProductPrice">Rs${price}</span></p>
												</div>
												
												<p>${description}</p>
												
												<div class="quickView-btnSet">
													<div class="row mt-4">
														<div class="col-md-6">
															<div class="form-group d-flex">
																<div class="select-wrap">
																	<select name="" id="quickViewProductSizes" class="form-control" onchange="displayQuickViewProductPrice(${sizesAndPrizes}, '${itemID}', '${iconID}');">
																	</select>
																</div>
															</div>
														</div>
														<div class="w-100"></div>
														<div style="position: relative; display: flex; justify-content: flex-start; align-items: start; width: 100%;">
															<div class="input-group col-md-6 d-flex mb-3">
																<div class="quantity buttons_added">
																	<input type="button" value="-" class="minus"><input type="number" step="1" min="1" max="" name="quantity" value="1" title="Qty" class="input-text qty text" size="4" pattern="" inputmode="" id="quickViewQuantity"><input type="button" value="+" class="plus">
																</div>
															</div>
															
															<span style="position: absolute; top: 34%; right: 3%; transform: translate(-50%, -50%);" id="${iconID}">	
															</span>

														</div>
													</div>
													
													<div class="w-100"></div>
													<div class="col-md-12" id="quickViewAvailableStock">
														
													</div>
													
													<p style="width: 100%;" id="quickViewAddToCartButton"></p>
												</div>
												
												
											</div>
										
									</div>
									<div class="modal-footer quickView-footer" style="display: none;">
									  
									</div>
								  </div>
								</div>`
	
	callGetItemStockForCartServlet(itemID, size, sizesAndPrizes, true)
	buildQuickViewSizes(sizesAndPrizes, size)
	var productSize = document.getElementById('quickViewProductSizes').value
	buildWishlistIcon(iconID, sizesAndPrizes, itemID, productSize)
	jQuery('select').niceSelect();
	var item = {"avgRating": avgRating}
    buildAverageRating(item, starID)
}

//build quick view sizes and prices
function buildQuickViewSizes(sizesAndPrizes, size){
	var ProductSizes = document.getElementById('quickViewProductSizes')
	ProductSizes.innerHTML = ''
	
	for(const [size, price] of Object.entries(quickViewsizesAndPrizes[sizesAndPrizes])){
		var productSize = `<option value="${size}">${size}</option>`
		
		ProductSizes.innerHTML += productSize
	}
	
	ProductSizes.value = size
	$('select').niceSelect('update');
}

//build wishlist icon
function buildWishlistIcon(iconID, i, itemID, productSize){
	var icon = document.getElementById(iconID)
	
	for(const [size, val] of Object.entries(isInWishlist[i])){
		if(productSize == size && val == false){
			icon.innerHTML = `<button type="button" style="display: none;" id="heartButton" value="outlineHeart"></button>
							  <i class="fa-regular fa-heart clickable" style="font-size: larger;" onclick="callAddToWishlistServletFromQuickView('${itemID}'); toggleWishlistIcon('${iconID}', '${itemID}', ${i}); return false;"></i>`
		}
		else if(productSize == size && val == true){
			icon.innerHTML = `<button type="button" style="display: none;" id="heartButton" value="filledHeart"></button>
							  <i class="fa-solid fa-heart clickable" style="font-size: larger;" onclick="callRemoveFromWishlistServletFromQuickView('${itemID}'); toggleWishlistIcon('${iconID}', '${itemID}', ${i}); return false;"></i>`
		}
	}
}

function buildWishlistIconProductSingle(iconID, itemID, productSize){
	var icon = document.getElementById(iconID)
	
	for(const [size, val] of Object.entries(isInWishlistProductSingle[0])){
		if(productSize == size && val == false){
			icon.innerHTML = `<button type="button" style="display: none;" id="heartButtonSingle" value="outlineHeart"></button>
							  <i class="fa-regular fa-heart clickable" style="font-size: larger;" onclick="callAddToWishlistServlet('${itemID}'); toggleWishlistIconProductSingle('${iconID}', '${itemID}', 0); return false;"></i>`
		}
		else if(productSize == size && val == true){
			icon.innerHTML = `<button type="button" style="display: none;" id="heartButtonSingle" value="filledHeart"></button>
							  <i class="fa-solid fa-heart clickable" style="font-size: larger;" onclick="callRemoveFromWishlistServlet('${itemID}'); toggleWishlistIconProductSingle('${iconID}', '${itemID}', 0); return false;"></i>`
		}
	}
}

//toggle wishlist icon
function toggleWishlistIcon(iconID, itemID, i){
	var icon = document.getElementById(iconID)
	var heartButtonValue = document.getElementById('heartButton').value
	var productSize = document.getElementById('quickViewProductSizes').value
	
	if(heartButtonValue == 'outlineHeart'){
		icon.innerHTML = `<button type="button" style="display: none;" id="heartButton" value="filledHeart"></button>
						  <i class="fa-solid fa-heart clickable" style="font-size: larger;" onclick="callRemoveFromWishlistServletFromQuickView('${itemID}'); toggleWishlistIcon('${iconID}', '${itemID}', ${i}); return false;"></i>`
			  
		for(const [size, val] of Object.entries(isInWishlist[i])){
			if(size == productSize) {
				isInWishlist[i][size] = true
			}
		}
		
	}else if(heartButtonValue == 'filledHeart'){
		icon.innerHTML = `<button type="button" style="display: none;" id="heartButton" value="outlineHeart"></button>
						  <i class="fa-regular fa-heart clickable" style="font-size: larger;" onclick="callAddToWishlistServletFromQuickView('${itemID}'); toggleWishlistIcon('${iconID}', '${itemID}', ${i}); return false;"></i>`
	
		for(const [size, val] of Object.entries(isInWishlist[i])){
			if(size == productSize) {
				isInWishlist[i][size] = false
			}
		}
	}
}

function toggleWishlistIconProductSingle(iconID, itemID, i){
	var icon = document.getElementById(iconID)
	var heartButtonValue = document.getElementById('heartButtonSingle').value
	var productSize = document.getElementById('ProductSizes').value
	
	if(heartButtonValue == 'outlineHeart'){
		icon.innerHTML = `<button type="button" style="display: none;" id="heartButtonSingle" value="filledHeart"></button>
						  <i class="fa-solid fa-heart clickable" style="font-size: larger;" onclick="callRemoveFromWishlistServlet('${itemID}'); toggleWishlistIconProductSingle('${iconID}', '${itemID}', 0); return false;"></i>`
	
		for(const [size, val] of Object.entries(isInWishlistProductSingle[i])){
			if(size == productSize) {
				isInWishlistProductSingle[i][size] = true
			}
		}
	
	}else if(heartButtonValue == 'filledHeart'){
		icon.innerHTML = `<button type="button" style="display: none;" id="heartButtonSingle" value="outlineHeart"></button>
						  <i class="fa-regular fa-heart clickable" style="font-size: larger;" onclick="callAddToWishlistServlet('${itemID}'); toggleWishlistIconProductSingle('${iconID}', '${itemID}', 0); return false;"></i>`
	
		for(const [size, val] of Object.entries(isInWishlistProductSingle[i])){
			if(size == productSize) {
				isInWishlistProductSingle[i][size] = false
			}
		}
	}
}

//display relevant product price
function displayQuickViewProductPrice(sizesAndPrizes, itemID, iconID){
	var productPrice = document.getElementById('quickViewProductPrice')
	var productSize = document.getElementById('quickViewProductSizes').value
	var displayPrice = quickViewsizesAndPrizes[sizesAndPrizes][productSize]
	
	productPrice.innerHTML = `Rs${displayPrice}`
	
	buildWishlistIcon(iconID, sizesAndPrizes, itemID, productSize)
	callGetItemStockForCartServlet(itemID, productSize, sizesAndPrizes, true)
}

//build average rating
function buildAverageRating(item, starID){
	if(item.avgRating == 5){
		for(var i = 1; i < 6; i++){
			var id = `${starID}${i}`
			var star = document.getElementById(id)
			
			star.classList.remove('ion-ios-star-outline')
			star.classList.add('ion-ios-star')
		}
		
	} else if(item.avgRating > 4){
		for(var i = 1; i < 5; i++){
			var id = `${starID}${i}`
			var star = document.getElementById(id)
			
			star.classList.remove('ion-ios-star-outline')
			star.classList.add('ion-ios-star')

		}
		var id = `${starID}${i}`
		var star = document.getElementById(id)
		star.classList.remove('ion-ios-star-outline')
		star.classList.add('ion-ios-star-half')
		
	} else if(item.avgRating == 4){
		for(var i = 1; i < 5; i++){
			var id = `${starID}${i}`
			var star = document.getElementById(id)
			
			star.classList.remove('ion-ios-star-outline')
			star.classList.add('ion-ios-star')
		}
		
	} else if(item.avgRating > 3){
		for(var i = 1; i < 4; i++){
			var id = `${starID}${i}`
			var star = document.getElementById(id)
			
			star.classList.remove('ion-ios-star-outline')
			star.classList.add('ion-ios-star')

		}
		var id = `${starID}${i}`
		var star = document.getElementById(id)
		star.classList.remove('ion-ios-star-outline')
		star.classList.add('ion-ios-star-half')
		
	} else if(item.avgRating == 3){
		for(var i = 1; i < 4; i++){
			var id = `${starID}${i}`
			var star = document.getElementById(id)
			
			star.classList.remove('ion-ios-star-outline')
			star.classList.add('ion-ios-star')
		}
		
	} else if(item.avgRating > 2){
		for(var i = 1; i < 3; i++){
			var id = `${starID}${i}`
			var star = document.getElementById(id)
			
			star.classList.remove('ion-ios-star-outline')
			star.classList.add('ion-ios-star')

		}
		var id = `${starID}${i}`
		var star = document.getElementById(id)
		star.classList.remove('ion-ios-star-outline')
		star.classList.add('ion-ios-star-half')
		
	} else if(item.avgRating == 2){
		for(var i = 1; i < 3; i++){
			var id = `${starID}${i}`
			var star = document.getElementById(id)
			
			star.classList.remove('ion-ios-star-outline')
			star.classList.add('ion-ios-star')
		}
		
	} else if(item.avgRating > 1){
		for(var i = 1; i < 2; i++){
			var id = `${starID}${i}`
			var star = document.getElementById(id)
			
			star.classList.remove('ion-ios-star-outline')
			star.classList.add('ion-ios-star')

		}
		var id = `${starID}${i}`
		var star = document.getElementById(id)
		star.classList.remove('ion-ios-star-outline')
		star.classList.add('ion-ios-star-half')
		
		
	} else if(item.avgRating == 1){
			var id = `${starID}${1}`
			var star = document.getElementById(id)
			
			star.classList.remove('ion-ios-star-outline')
			star.classList.add('ion-ios-star')
		
	} else if(item.avgRating > 0){
		var id = `${starID}1`
		var star = document.getElementById(id)
		star.classList.remove('ion-ios-star-outline')
		star.classList.add('ion-ios-star-half')

	}

}

//call cart servlet
var cartItems = []
var itemRemoved = false
var quantityChanged = false

function callCartServlet(fromCart){
	$.get("http://localhost:8080/LankaHardware/GetCartServlet", function(response) {
		
		cartItems = response[0]
		getCartQuantity()
		
		var Total = response[1]

		buildMiniCart(cartItems)
		
		if(cartItems.length == 0 && fromCart == true) emptyMainCart()
		
		if(fromCart == true){
			calculateSubTotal(cartItems)
			cartTotal(Total)
		}
	})
}

function buildMiniCart(cartItems){
	miniCart_itemList.innerHTML = ''
	if(cartItems.length == 0) emptyMiniCart()
	
	for(var i = 0; i < cartItems.length; i++){
		var item = `<tr class="text-center" style="display: flex; column-gap: 5px; align-items: center; border: 1px solid transparent !important; border-bottom: 1px solid rgba(0, 0, 0, 0.05) !important;">
						<td class="image-prod clickable" style="border: none; padding: 0px;" onclick="toProductSinglePage('${cartItems[i].itemID}');">
							<div class="img"
								style="background-image:url(${cartItems[i].mainImg}); margin: 0px;">
							</div>
						</td>
						<td class="product-name clickable" style="width: auto; border: none;  padding: 0px;" onclick="toProductSinglePage('${cartItems[i].itemID}');">
							<h3>${cartItems[i].name}</h3>
							<p>${cartItems[i].description}</p>
							<p class="d-flex" style="justify-content: space-between; padding-left: 5px; font-size: small;">
								<span>Size ${cartItems[i].size}</span>
								<span>Qty ${cartItems[i].quantity}</span>
							</p>
						</td>
					</tr>
					<!-- END TR-->`
    			
    	miniCart_itemList.innerHTML += item
	}
}

function getCartQuantity(){
	var qtyTotal = 0
	for(var i = 0; i < cartItems.length; i++){
		qtyTotal += cartItems[i].quantity
	}
	
	var no_of_Items = qtyTotal
	
	cartQuantity.innerHTML = no_of_Items
}

function emptyMainCart(){
	console.log('main here')
	mainCart_itemList.innerHTML = `<p style="position: absolute; right: 0; left: 0; font-size: large; color: gray; margin-top: 20px;">Your cart is empty. Add some items to the cart.</p>`
	var cartSummery = document.getElementById('cartSummery')
	cartSummery.style = "transform: scale(0);"
}

function emptyMiniCart(){
	console.log('mini here')
	miniCart_itemList.innerHTML = '<p style="text-align: center; font-size: large; color: gray;">Your cart is empty. Add some items to the cart.</p>'
}

function buildMainCart(cartItems, Total){
	mainCart_itemList.innerHTML = ''
	
	calculateSubTotal(cartItems)
	cartTotal(Total)
	
	for(var i = 0; i < cartItems.length; i++){
		var plusID = `${cartItems[i].itemID + cartItems[i].size}Plus`
		var qtyElementID = `${cartItems[i].itemID + cartItems[i].size}Qty`
		
		var item = `<tr class="text-center" id="${cartItems[i].itemID}TableRow">
						<td class="image-prod clickable" onclick="toProductSinglePage('${cartItems[i].itemID}');">
							<div class="img" style="background-image:url(${cartItems[i].mainImg});"></div>
						</td>

						<td class="product-name clickable" onclick="toProductSinglePage('${cartItems[i].itemID}');">
							<h3>${cartItems[i].name}</h3>
							<p>${cartItems[i].description}</p>
							<span style="color: grey; font-size: small;">Size ${cartItems[i].size}</span>
						</td>

						<td class="price">Rs${cartItems[i].price}</td>

						<td>
						    <div class="quantity buttons_added" style="display: inline-flex;">
								<input type="button" value="-" class="minus">
								<input type="number" step="1" min="1" max="" name="quantity" value="${cartItems[i].quantity}" title="Qty" class="input-text qty text" size="4" pattern="" inputmode="" onchange="callChangeQuantityServlet('${cartItems[i].itemID}', this, ${cartItems[i].price}, '${cartItems[i].size}', '${plusID}')" id="${qtyElementID}">
								<input type="button" value="+" class="plus" id="${plusID}">
							</div>
					    </td>

						<td class="total" id="${cartItems[i].itemID + cartItems[i].size}">Rs${cartItems[i].price * cartItems[i].quantity}</td>

						<td class="product-remove"><a href="#" onclick="callRemoveFromCartServlet('${cartItems[i].itemID}', 'one', '${cartItems[i].size}'); deleteTableRow('${cartItems[i].itemID}'); return false;"><span class="ion-ios-close"></span></a></td>
					</tr><!-- END TR-->`
    			
    	mainCart_itemList.innerHTML += item
    	
    	var qtyElement = document.getElementById(qtyElementID)
    	callGetItemStockForCartServletForMax(cartItems[i].itemID, cartItems[i].size, qtyElement, plusID)
	}
}

//Cart total
function cartTotal(Total){
	cartTotals.innerHTML = 'Rs' + Total
}

//build add to cart button
function buildQuickViewAddToCartButton(itemID, index, productSize){
	var quickViewAddToCartButton = document.getElementById('quickViewAddToCartButton')
	var quickViewAvailableStock = document.getElementById('quickViewAvailableStock')
	quickViewAvailableStock.style = "padding-left: 0;"
	quickViewAvailableStock.innerHTML = ''
	
	for(const [size, stock] of Object.entries(quickViewsizesAndStock[index])){
		if(productSize == size && stock <= 0){
			var button = `<a href="#" onclick="return false;" class="btn btn-black py-3 px-5 mr-2" style="width: 100%;">Add to Cart</a>`
			quickViewAddToCartButton.style = "opacity: 0.5;"
			quickViewAddToCartButton.innerHTML = button
			
			quickViewAvailableStock.innerHTML = `<p style="color: #000;">This product is currently out of stock.</p>`
			
			return
		}else if(productSize == size && stock > 0){
			var button = `<a href="#" onclick="addToCartFromQuickView('${itemID}', ${index}); return false;" class="btn btn-black py-3 px-5 mr-2" style="width: 100%;">Add to Cart</a>`
			quickViewAddToCartButton.style = "opacity: 1;"
			quickViewAddToCartButton.innerHTML = button
			
			if(stock <= 10) quickViewAvailableStock.innerHTML = `<p style="color: #000;">Only ${stock} available. Buy this soon before it's gone!</p>`
			
			return
		}
	}
}

function buildProductSingleAddToCartButton(itemID, index, productSize){
	var quickViewAddToCartButton = document.getElementById('productSingleAddToCartButton')
	var availableStock = document.getElementById('availableStock')
	availableStock.innerHTML = ''
	
	for(const [size, stock] of Object.entries(sizesAndStockProductSingle[index])){
		if(productSize == size && stock <= 0){
			var button = `<a href="#" onclick="return false;" class="btn btn-black py-3 px-5 mr-2" style="width: 100%;">Add to Cart</a>`
			quickViewAddToCartButton.style = "opacity: 0.5;"
			quickViewAddToCartButton.innerHTML = button
			
			availableStock.innerHTML = `<p style="color: #000;">This product is currently out of stock.</p>`
			
			return
		}else if(productSize == size && stock > 0){
			var button = `<a href="#" onclick="addToCartFromSingleProductPage('${itemID}', 0, 0); return false;" class="btn btn-black py-3 px-5 mr-2" style="width: 100%;">Add to Cart</a>`
			quickViewAddToCartButton.style = "opacity: 1;"
			quickViewAddToCartButton.innerHTML = button
			
			if(stock <= 10) availableStock.innerHTML = `<p style="color: #000;">Only ${stock} available. Buy this soon before it's gone!</p>`
			
			return
		}
	}
}

//call add to cart from single product page
function addToCartFromSingleProductPage(itemID, quantity, index){
	var size = document.getElementById('ProductSizes').value
	callAddToCartServlet(itemID, quantity, size, index, false)
}

function addToCartFromQuickView(itemID, index){
	var size = document.getElementById('quickViewProductSizes').value
	var quantity = document.getElementById('quickViewQuantity').value
	callAddToCartServlet(itemID, quantity, size, index, true)
}

//Add to cart
function callAddToCartServlet(itemID, quantity, size, index, fromQuick){
	if(quantity == 0){
		var productSingleQuantity = document.getElementById('productSingleQuantity').value
		quantity = productSingleQuantity
	}
	
	$.post("http://localhost:8080/LankaHardware/AddToCartServlet", {itemID : itemID, quantity : quantity, size : size}, function(response){
	    
	    if(response == "Login message"){
			$('#loginMsgModal').modal('show');
			
			if(fromQuick){
				$('#quickViewModal').modal('hide');
			}
		}
		else{
			callCartServlet(false)
	    
		    added_msg.innerHTML = response
		    added_msg.classList.add('active')
		    setTimeout(function() {
		    	added_msg.classList.remove('active')
		  	}, 2000);
		  	
		  	callGetItemStockForCartServlet(itemID, size, index, fromQuick)
		}
	    
	})
}

//update stock
function callGetItemStockForCartServlet(itemID, size, index, fromQuick){
	var newStock
	$.get("http://localhost:8080/LankaHardware/GetItemStockForCartServlet", {itemID : itemID, size : size}, function(response) {
		
		newStock = response
		updateStock(index, size, newStock, itemID, fromQuick)
	})
	
}

function updateStock(index, size, newStock, itemID, fromQuick){
	if(fromQuick == true) {
		quickViewsizesAndStock[index][size] = newStock
		buildQuickViewAddToCartButton(itemID, index, size)
	}
	else {
		sizesAndStockProductSingle[index][size] = newStock
		buildProductSingleAddToCartButton(itemID, index, size)
	}
	
	
}


//Clear cart
function callRemoveFromCartServlet(itemID, operation, size){
	$.post("http://localhost:8080/LankaHardware/RemoveFromCartServlet", {itemID : itemID, operation : operation, size : size}, function(response){
	   
	   itemRemoved = true
	   quantityChanged = true
	   //mainCart_itemList.innerHTML="";
	   callCartServlet(true)
	   
	   added_msg.innerHTML = response
	   added_msg.classList.add('active')
	   setTimeout(function() {
	   	added_msg.classList.remove('active')
	   }, 2000);
	})
}

//delete table row
function deleteTableRow(id){
	var row = document.getElementById(`${id}TableRow`)
	
	row.style = "display: none;"
}

//call change quantity servlet
function callChangeQuantityServlet(itemID, element, price, size, plusID){
	var quantity = element.value
	
	$.post("http://localhost:8080/LankaHardware/ChangeQuantityServlet", {itemID : itemID, quantity : quantity, size : size}, function(response) {

	   quantityChanged = true
	   callCartServlet(true)
	   calculateItemtotal(quantity, price, itemID, size)
	   callGetItemStockForCartServletForMax(itemID, size, element, plusID)
	})
}

function callGetItemStockForCartServletForMax(itemID, size, element, plusID){
	var newStock
	$.get("http://localhost:8080/LankaHardware/GetItemStockForCartServlet", {itemID : itemID, size : size}, function(response) {
		
		newStock = response
		setMaxQuantity(element, newStock, plusID)
	})
	
}

function setMaxQuantity(element, newStock, plusID){
	var newMax = parseInt(element.value) + parseInt(newStock)
	var plusBtn = document.getElementById(plusID)
	
	element.max = newMax
	
	if(newStock <= 0) {
		plusBtn.disabled = true
	}else {
		plusBtn.disabled = false
	}
}


//calculate item total
function calculateItemtotal(quantity, price, itemID, size){
	var itemTotal = document.getElementById(itemID + size)
	itemTotal.innerHTML = `Rs${price * quantity}`
}

//calculate cart subtotal
function calculateSubTotal(cartItems){
	var subTotal = 0
	for(var i = 0; i < cartItems.length; i++){
		subTotal += cartItems[i].price * cartItems[i].quantity
	}
	cartSubTotal.innerHTML = `Rs${subTotal}`
}

//Redirect to product-single page
function toProductSinglePage(itemID){
	window.location = 'http://localhost:8080/LankaHardware/product-single.jsp?product=' + itemID
}

//call GetProductSingleServlet
var product = []
var productSizeAndPriceList = []
var allReviews = []
var relatedProducts = []
var oneStarReviews = []
var twoStarReviews = []
var threeStarReviews = []
var fourStarReviews = []
var fiveStarReviews = []
var reviewsWithImages = []
var productQuestions = []
var itemIDForQuestion
var isInWishlistProductSingle = []
var sizesAndStockProductSingle = []

function callGetProductSingleServlet(itemID){
	$.get("http://localhost:8080/LankaHardware/GetProductSingleServlet", {itemID : itemID}, function(response) {
		
		product = response[0]
		productSizeAndPriceList = response[1]
		allReviews = response[2]
		relatedProducts = response[3]
		productQuestions = response[4]
		itemIDForQuestion = product.itemID
		
		buildProductSingle(product)
		buildProductSizes()
		buildReviewPercentagesAndCounts(product, product.ratingCount)
		buildAllReviews(allReviews, product.ratingCount)
		buildRelatedProducts()
		filterReviews()
		bulidProductQuestions()
	})
}

function buildProductSingle(product){
	var starID = 'productStar'
	var iconID = `${product.itemID}${product.size}ProductSingleIcon`
	
	isInWishlistProductSingle.push(product.isInWishlist)
	sizesAndStockProductSingle.push(product.sizesAndStock)
	
	productDetails.innerHTML = ''
	
	var details = `<div class="col-lg-5">
                    <img src="${product.mainImg}" class="img-fluid pb-1" alt="Colorlib Template" id="mainImg">

					<div class="small-img-group" id="allImages">
						<div class="small-img-col">
							<img src="${product.mainImg}" alt="" width="100%" class="small-img" onclick="changeMainImage(this)">
						</div>
						<div class="small-img-col">
							<img src="images/product-2.jpg" alt="" width="100%" class="small-img" onclick="changeMainImage(this)">
						</div>
						<div class="small-img-col">
							<img src="images/product-3.png" alt="" width="100%" class="small-img" onclick="changeMainImage(this)">
						</div>
						<div class="small-img-col">
							<img src="images/product-4.jpg" alt="" width="100%" class="small-img" onclick="changeMainImage(this)">
						</div>
					</div>
                    
                </div>
				<div class="col-lg-6 product-details pl-md-5 ftco-animate fadeInUp ftco-animated">
					<h3>${product.name}</h3>
					<div class="rating d-flex">
						<p class="text-left mr-4">
							<a href="#" class="mr-2">${Math.round(product.avgRating * 10) / 10}</a>
							<a href="#"><span class="ion-ios-star-outline" id="${starID}1"></span></a>
							<a href="#"><span class="ion-ios-star-outline" id="${starID}2"></span></a>
							<a href="#"><span class="ion-ios-star-outline" id="${starID}3"></span></a>
							<a href="#"><span class="ion-ios-star-outline" id="${starID}4"></span></a>
							<a href="#"><span class="ion-ios-star-outline" id="${starID}5"></span></a>
						</p>
						<p class="text-left mr-4">
							<a href="#" class="mr-2" style="color: #000;">${product.ratingCount} <span style="color: #bbb;"
									id="v-pills-3-tab" data-toggle="pill" href="#v-pills-3" role="tab"
									aria-controls="v-pills-3" aria-selected="false">Rating</span></a>
						</p>
						<p class="text-left">
							<a href="#" class="mr-2" style="color: #000;">500 <span style="color: #bbb;">Sold</span></a>
						</p>
					</div>
					<p class="price"><span id="productPrice">Rs${product.price}</span></p>
					<p>${product.description}</p>
					<p>On her way she met a copy. The copy warned the Little Blind Text, that where it came from it
						would have been rewritten a thousand times and everything that was left from its origin would be
						the word "and" and the Little Blind Text should turn around and return to its own, safe country.
						But nothing the copy said could convince her and so it didn’t take long until a few insidious
						Copy Writers ambushed her, made her drunk with Longe and Parole and dragged her into their
						agency, where they abused her for their.
					</p>
					<div class="row mt-4">
						<div class="col-md-6">
							<div class="form-group d-flex">
								<div class="select-wrap">
									<select name="" id="ProductSizes" class="form-control" onchange="displayProductPrice('${iconID}', '${product.itemID}');">
									</select>
								</div>
							</div>
						</div>
						<div class="w-100"></div>
						
						<div style="position: relative; display: flex; justify-content: flex-start; align-items: start; width: 100%;">
							<div class="input-group col-md-6 d-flex mb-3">
								<div class="quantity buttons_added">
									<input type="button" value="-" class="minus"><input type="number" step="1" min="1" max="" name="quantity" value="1" title="Qty" class="input-text qty text" size="4" pattern="" inputmode="" id="productSingleQuantity"><input type="button" value="+" class="plus">
								</div>
							</div>
							
							<span style="position: absolute; top: 34%; right: 3%; transform: translate(-50%, -50%);" id="${iconID}">	
							</span>
						</div>
						
						<div class="w-100"></div>
						<div class="col-md-12" id="availableStock">
							
						</div>
					</div>
					<p id="productSingleAddToCartButton"></p>
				</div>`
    			
    	productDetails.innerHTML += details
    	callGetItemStockForCartServlet(product.itemID, product.size, 0, false)
    	buildAverageRating(product, starID)
    	mainImg = document.getElementById('mainImg')
    	buildWishlistIconProductSingle(iconID, product.itemID, product.size)
    	buildAllImages()
}

//building product images
function buildAllImages(){
	var allImagesElement = document.getElementById('allImages')
	allImagesElement.innerHTML = ''
	
	for(var i = 0; i < product.allImages.length; i++){
		allImagesElement.innerHTML += `<div class="small-img-col">
											<img src="${product.allImages[i]}" alt="" width="100%" class="small-img" onclick="changeMainImage(this)">
										</div>`
	}
}

function changeMainImage(element){
	console.log(mainImg)
	mainImg.src = element.src
}

//Building product sizes
function buildProductSizes(){
	var ProductSizes = document.getElementById('ProductSizes')
	
	for(const [size, price] of Object.entries(productSizeAndPriceList)){
		var productSize = `<option value="${size}">${size}</option>`
		
		ProductSizes.innerHTML += productSize
	}
	
	jQuery('select').niceSelect();
}

//Building review percentages and counts
function buildReviewPercentagesAndCounts(product, ratingCount){
	totalRatings.innerHTML = ratingCount
	averageProductRating.innerHTML = product.avgRating.toFixed(1)
	
	for(var i = 5; i >= 1; i--){
		var percentageElement = document.getElementById(`${numberToWord(i)}StarPercentage`)
		var countElement = document.getElementById(`${numberToWord(i)}StarCount`)
		updateProgressBar(percentageElement, product.ratingPercentageList[i - 1][0]);
		//percentageElement.innerHTML = `(${parseInt(product.ratingPercentageList[i - 1][0])}%)`
		countElement.innerHTML = `${parseInt(product.ratingPercentageList[i - 1][1])} Reviews`
	}
	
	var starID = `overviewStar`
	buildAverageRating(product, starID)
}

function updateProgressBar(progressBar, value) {
	value = Math.round(value);
	progressBar.querySelector(".ratingProgress__fill").style.width = `${value}%`;
	//progressBar.querySelector(".progress__text").textContent = `${value}%`;
}

//Build product questions
function bulidProductQuestions(){
	totalQuestions.innerHTML = `${productQuestions.length} Questions`
	productQuestionsList.innerHTML = ''
	
	for(var i = 0; i < productQuestions.length; i++){
		var answerID = `${productQuestions[i].questionID}Answer`
		var question = `<ul class="comment-list" style="overflow: auto; border-bottom: 1px solid rgba(0, 0, 0, 0.05); margin-top: 10px;">
						<li class="comment">
							<div class="vcard bio">
								<img src="images/person_1.jpg" alt="Image placeholder">
							</div>
							<div class="comment-body">
								<h3>${productQuestions[i].customer.email}</h3>
								<div class="meta">${productQuestions[i].questionDate}</div>
								<p><span>Question: </span> ${productQuestions[i].question}</p>
								
							</div>
	
							<ul class="children" id="${answerID}" style="padding: 0px;">
								
							</ul>
						</li>
					</ul>`
		
		productQuestionsList.innerHTML += question
		if(productQuestions[i].admin.email != null && productQuestions[i].answer != null) buildProductAnswers(answerID, productQuestions[i].admin.email, productQuestions[i].answer, productQuestions[i].answerDate)
	}
}

//Build product answers
function buildProductAnswers(answerID, email, answer, answerDate){
	var answerElement = document.getElementById(answerID)
	answerElement.style = "padding: 50px 0 0 40px;"
	
	answerElement.innerHTML = `<li class="comment">
									<div class="vcard bio">
										<img src="images/person_1.jpg" alt="Image placeholder">
									</div>
									<div class="comment-body">
										<h3>${email}</h3>
										<div class="meta">${answerDate}</div>
										<p><span>Answer: </span> ${answer}</p>
									</div>
								</li>`
}

//Build all reviews
function buildAllReviews(allReviews, productRatingCount){
	reviewContainer.innerHTML = `<h3 class="mb-4">${productRatingCount} Reviews</h3>`
	for(var i = 0; i < allReviews.length; i++){
		var starID = `${allReviews[i].reviewID}reviewStar`
		var review = `<div class="review">
						<div class="user-img" style="background-image: url(images/person_2.jpg)"></div>
						<div class="desc">
							<h4>
								<span class="text-left">${allReviews[i].customer.email}</span>
								<span class="text-right">${allReviews[i].reviewDate}</span>
							</h4>
							<p class="star">
								<span>
									<i class="ion-ios-star-outline" id="${starID}1"></i>
									<i class="ion-ios-star-outline" id="${starID}2"></i>
									<i class="ion-ios-star-outline" id="${starID}3"></i>
									<i class="ion-ios-star-outline" id="${starID}4"></i>
									<i class="ion-ios-star-outline" id="${starID}5"></i>
								</span>
								<span class="text-right"><a href="#" class="reply"><i class="icon-reply"></i></a></span>
							</p>
							<p>${allReviews[i].reviewDescription}</p>
							<div class="reviewImgContainer" style="display: flex; flex-direction: row; flex-wrap: wrap;" id="${allReviews[i].reviewID}ImageContainer">
								
							</div>
						</div>
					</div>`
    			
    	reviewContainer.innerHTML += review
    	
    	var item = {"avgRating": allReviews[i].stars}
    	buildAverageRating(item, starID)
    	if(allReviews[i].reviewImages.length != 0) buildAllReviewImages(allReviews[i].reviewImages, allReviews[i].reviewID)
	}
}

//Build all review images
function buildAllReviewImages(images, containerID){
	var reviewImageContainer = document.getElementById(`${containerID}ImageContainer`)
	
	for(var i = 0; i < images.length; i++){
		var image = `<div class="col-lg-6 mb-5 ftco-animate fadeInUp ftco-animated" style="max-width: 20%;">
						<a href="${images[i]}" class="image-popup">
							<img src="${images[i]}" class="img-fluid-ratings" alt="Colorlib Template">
						</a>
					</div>`
		
		
		reviewImageContainer.innerHTML += image
	}
    	
    	$('#reviewContainer').magnificPopup({

		          delegate:`.image-popup`,
		          type: 'image',
		          gallery: {
		            enabled: false
		          }

		});
}

//build review filtered list
function filterReviews(){
	fiveStarReviews = []
	fourStarReviews = []
	threeStarReviews = []
	twoStarReviews = []
	oneStarReviews = []
	reviewsWithImages = []
	
	for(var i = 0; i < allReviews.length; i++){
		if(allReviews[i].stars == 5){
			fiveStarReviews.push(allReviews[i])
		}else if(allReviews[i].stars == 4){
			fourStarReviews.push(allReviews[i])
		}else if(allReviews[i].stars == 3){
			threeStarReviews.push(allReviews[i])
		}else if(allReviews[i].stars == 2){
			twoStarReviews.push(allReviews[i])
		}else if(allReviews[i].stars == 1){
			oneStarReviews.push(allReviews[i])
		}
		if(allReviews[i].reviewImages.length > 0){
			reviewsWithImages.push(allReviews[i])
		}
	}
}

//get filtered reviews
function getFilteredReviews(type){
	if(type == 'all'){
		allToggle.checked = true
		toggleReviewFilterCheck()
		buildAllReviews(allReviews, allReviews.length)
	}else if(type == 'with images'){
		imagesToggle.checked = true
		toggleReviewFilterCheck()
		if(reviewsWithImages.length == 0) emptyFilteredReviews('images.')
		else buildAllReviews(reviewsWithImages, reviewsWithImages.length)
	}else if(type == 'fiveStar'){
		fiveStarToggle.checked = true
		toggleReviewFilterCheck()
		if(fiveStarReviews.length == 0) emptyFilteredReviews('five stars.')
		else buildAllReviews(fiveStarReviews, fiveStarReviews.length)
	}else if(type == 'fourStar'){
		fourStarToggle.checked = true
		toggleReviewFilterCheck()
		if(fourStarReviews.length == 0) emptyFilteredReviews('four stars.')
		else buildAllReviews(fourStarReviews, fourStarReviews.length)
	}else if(type == 'threeStar'){
		threeStarToggle.checked = true
		toggleReviewFilterCheck()
		if(threeStarReviews.length == 0) emptyFilteredReviews('three stars.')
		else buildAllReviews(threeStarReviews, threeStarReviews.length)
	}else if(type == 'twoStar'){
		twoStarToggle.checked = true
		toggleReviewFilterCheck()
		if(twoStarReviews.length == 0) emptyFilteredReviews('two stars.')
		else buildAllReviews(twoStarReviews, twoStarReviews.length)
	}else if(type == 'oneStar'){
		oneStarToggle.checked = true
		toggleReviewFilterCheck()
		if(oneStarReviews.length == 0) emptyFilteredReviews('one stars.')
		else buildAllReviews(oneStarReviews, oneStarReviews.length)
	}
	
}

//toggle the check mark
function toggleReviewFilterCheck() {
	var allCheck = document.getElementById('allCheck')
	var imagesCheck = document.getElementById('imagesCheck')
	var fiveCheck = document.getElementById('fiveCheck')
	var fourCheck = document.getElementById('fourCheck')
	var threeCheck = document.getElementById('threeCheck')
	var twoCheck = document.getElementById('twoCheck')
	var oneCheck = document.getElementById('oneCheck')

	allCheck.style = "display: none;"
	imagesCheck.style = "display: none;"
	fiveCheck.style = "display: none;"
	fourCheck.style = "display: none;"
	threeCheck.style = "display: none;"
	twoCheck.style = "display: none;"
	oneCheck.style = "display: none;"

	if (allToggle.checked == true) {
		allCheck.style = "display: block; color: green;"
	} else if (imagesToggle.checked == true) {
		imagesCheck.style = "display: block; color: green;"
	} else if (fiveStarToggle.checked == true) {
		fiveCheck.style = "display: block; color: green;"
	} else if (fourStarToggle.checked == true) {
		fourCheck.style = "display: block; color: green;"
	} else if (threeStarToggle.checked == true) {
		threeCheck.style = "display: block; color: green;"
	} else if (twoStarToggle.checked == true) {
		twoCheck.style = "display: block; color: green;"
	} else if (oneStarToggle.checked == true) {
		oneCheck.style = "display: block; color: green;"
	}
}

//empty filtered reviews
function emptyFilteredReviews(text){
	reviewContainer.innerHTML = `<h4 style="color: grey; text-align: center;">No reviews with ${text}</h4>`
}

//build related products
function buildRelatedProducts(){
	relatedProductList.innerHTML = ''
	quickViewsizesAndPrizes = []
	quickViewsizesAndStock = []
	
	for(var i = 0; i < relatedProducts.length; i++){
		quickViewsizesAndPrizes.push(relatedProducts[i].sizesAndPrizes)
		isInWishlist.push(relatedProducts[i].isInWishlist)
		quickViewsizesAndStock.push(relatedProducts[i].sizesAndStock)
		
		var starID = relatedProducts[i].itemID + 'RelatedProductAvgStar'
		
		var item = `<div class="item">
						<div class="col-sm-12 col-md-6 col-lg-3 ftco-animate d-flex fadeInUp ftco-animated" style="min-width: fit-content;" id="related${relatedProducts[i].itemID}">
							<div class="product d-flex flex-column">
								<a href="#" class="img-prod" onclick="return flase;"><img class="img-fluid" src="${relatedProducts[i].mainImg}" alt="Colorlib Template" onclick="toProductSinglePage('${relatedProducts[i].itemID}');">
									<div class="overlay"></div>
								</a>
								<div class="text py-3 pb-4 px-3">
									<div class="d-flex">
										<div class="cat">
											<span>${relatedProducts[i].brand}</span>
										</div>
										<div class="rating">
											<p class="text-right mb-0">
												<a href="#"><span class="ion-ios-star-outline" id="${starID}1"></span></a>
			    								<a href="#"><span class="ion-ios-star-outline" id="${starID}2"></span></a>
			    								<a href="#"><span class="ion-ios-star-outline" id="${starID}3"></span></a>
			    								<a href="#"><span class="ion-ios-star-outline" id="${starID}4"></span></a>
			    								<a href="#"><span class="ion-ios-star-outline" id="${starID}5"></span></a>
											</p>
										</div>
									</div>
									<h3><a href="#" onclick="toProductSinglePage('${relatedProducts[i].itemID}'); return false;">${relatedProducts[i].name}</a></h3>
									<div class="pricing">
										<p class="price"><span>${relatedProducts[i].price}</span></p>
									</div>
									<p class="bottom-area d-flex px-3" data-bs-toggle="modal" data-bs-target="#quickViewModal" onclick="buildQuickView('${relatedProducts[i].itemID}', '${relatedProducts[i].mainImg}', '${relatedProducts[i].name}', '${relatedProducts[i].price}', '${relatedProducts[i].description}', ${relatedProducts[i].avgRating}, ${i}, '${relatedProducts[i].size}', ${relatedProducts[i].stock});">
										<a href="#" onclick="return false;" class="add-to-cart text-center py-2 mr-1"><span>Quick View <i class="fa-regular fa-eye ml-1"></i></span></a>
									</p>
								</div>
							</div>
						</div>
					</div>`
		
		
		relatedProductList.innerHTML += item
		
		if(afterQuestion == true) {
			var relatedCard = document.getElementById(`related${relatedProducts[i].itemID}`)
			relatedCard.style = "min-width: initial;"
		}
		
		buildAverageRating(relatedProducts[i], starID)
	}
	
	$('.owl-carousel').owlCarousel({
			loop:false,
			margin:10,
			nav:false,
			dots:false,
			autoplay:true,
			autoplayTimeout: 3000,
			responsive:{
				0:{
					items:1
				},
				600:{
					items:2
				},
				1000:{
					items:4
				}
			}
		})
}

//display relevant product price
function displayProductPrice(iconID, itemID){
	var productPrice = document.getElementById('productPrice')
	var productSize = document.getElementById('ProductSizes').value
	var displayPrice = productSizeAndPriceList[productSize]
	
	productPrice.innerHTML = `Rs${displayPrice}`
	
	buildWishlistIconProductSingle(iconID, itemID, productSize)
	callGetItemStockForCartServlet(itemID, productSize, 0, false)
}


//call main product search servlet
var mainProductSearchResults = []
var itemNameForShop
var isFromSearch

function mainSearch(){
	var itemName = document.getElementById('search-input').value
	itemName = itemName.trim()
	itemNameForShop = itemName
	
	if(itemName == ''){
		ProductSearchResult.innerHTML = ""
		return
	} 
	
	callMainProductSearch(itemName)
}

function callMainProductSearch(itemName){
	var forNoResults = itemName
	itemName = '%' + itemName + '%'
	
	$.get("http://localhost:8080/LankaHardware/GetMainProductSearch", {itemName : itemName}, function(response) {
		
		mainProductSearchResults = response
		builtResults(mainProductSearchResults, forNoResults)
	})
}

function builtResults(mainProductSearchResults, forNoResults){
	ProductSearchResult.innerHTML = ""
	
	if(mainProductSearchResults.length == 0){
		ProductSearchResult.innerHTML = `<div style='display: flex; flex-direction: column; justify-content: center; font-size: medium; margin-top: 50px;'>
											<span style='font-size: x-large'><i class="fa-solid fa-circle-exclamation"></i> We're Sorry</span>
											<span>We didn't find any results for '${forNoResults}'</span>
										</div>`
		return
	}
	
	for(var i = 0; i < mainProductSearchResults.length; i++){
		var item = `<tr class="text-center clickable" style="display: flex; align-items: center; border: 1px solid transparent !important; border-bottom: 1px solid rgba(0, 0, 0, 0.05) !important;" onclick="toProductSinglePage('${mainProductSearchResults[i].itemID}');">
						<td class="image-prod" style="border: none; padding: 0px;">
							<div class="img"
								style="background-image:url(${mainProductSearchResults[i].mainImg}); margin: 0px;">
							</div>
						</td>
						<td class="product-name" style="width: auto; border: none;  padding: 0px;">
							<h3>${mainProductSearchResults[i].name}</h3>
							<p>${mainProductSearchResults[i].description}</p>
						</td>
					</tr><!-- END TR-->`
    			
    	ProductSearchResult.innerHTML += item
	}
}

mainSearchClose.addEventListener('click', () => {
  ProductSearchResult.innerHTML = ""
})

//from search to shop page
function searchToShop(){
	var itemName = document.getElementById('search-input').value
	
	if(itemName == '') return
	else window.location = 'http://localhost:8080/LankaHardware/shop.jsp?search=' + itemNameForShop
}

//customized search
function customizedSearch(itemName){
	itemNameForShop = itemName
	callGetShopServlet()
}

//Call get shop servlet
var shopItems = []
var mainCategories = []
var subCategories = []
var highestPrice
var lowestPrice
var customizedShopItems = []
var currentMainCategory
var currentSubCategory
var priceRangeChanged = false
var clickedCategory = false
var sortByValue
var sortByFilterOpen = false
var brandList = []
var currentBrand = ''
var includeOutOfStock = false

function callGetShopServlet(){
	var itemName
	if(itemNameForShop != undefined || itemNameForShop != null){
		itemName = itemNameForShop
		itemName = `%${itemName}%`
	} else{
		itemName = `%%`
	}
	
	$.get("http://localhost:8080/LankaHardware/GetShopServlet", {itemName : itemName}, function(response) {
		
		shopItems = response[0]
		mainCategories = response[1]
		highestPrice = response[2]
		lowestPrice = response[3]
		subCategories = response[4]
		
		buildShopItems(shopItems)
		buildShopCategories()
		buildPriceRange()
		buildCurrentFilters()
		buildDefaultBrandList()
	})
}

//Build items in shop page
function buildShopItems(shopItems){
	shopItemList.innerHTML = ''
	quickViewsizesAndPrizes = []
	quickViewsizesAndStock = []
	
	if(shopItems.length == 0) shopEmpty()
	else shopItemList.style = "justify-content: inherit;"
	
	for(var i = 0; i < shopItems.length; i++){
		quickViewsizesAndPrizes.push(shopItems[i].sizesAndPrizes)
		isInWishlist.push(shopItems[i].isInWishlist)
		quickViewsizesAndStock.push(shopItems[i].sizesAndStock)
		
		var starID = shopItems[i].itemID + 'shopStar'
		
		var item = `<div class="col-sm-12 col-md-12 col-lg-4 ftco-animate d-flex fadeInUp ftco-animated">
							<div class="product">
								<a href="#" class="img-prod" onclick="toProductSinglePage('${shopItems[i].itemID}'); return false;"><img class="img-fluid"
									src="${shopItems[i].mainImg}" alt="Colorlib Template">
									<div class="overlay"></div> </a>
								<div class="text py-3 pb-4 px-3">
									<div class="d-flex">
										<div class="cat">
											<span>${shopItems[i].brand}</span>
										</div>
										<div class="rating">
											<p class="text-right mb-0">
												<a href="#"><span class="ion-ios-star-outline" id="${starID}1"></span></a> <a
													href="#"><span class="ion-ios-star-outline" id="${starID}2"></span></a> <a
													href="#"><span class="ion-ios-star-outline" id="${starID}3"></span></a> <a
													href="#"><span class="ion-ios-star-outline" id="${starID}4"></span></a> <a
													href="#"><span class="ion-ios-star-outline" id="${starID}5"></span></a>
											</p>
										</div>
									</div>
									<h3>
										<a href="#" onclick="toProductSinglePage('${shopItems[i].itemID}'); return false;">${shopItems[i].name}</a>
									</h3>
									<div class="pricing">
										<p class="price">
											<span>Rs${shopItems[i].price}</span>
										</p>
									</div>
									<p class="bottom-area d-flex px-3" data-bs-toggle="modal" data-bs-target="#quickViewModal" onclick="buildQuickView('${shopItems[i].itemID}', '${shopItems[i].mainImg}', '${shopItems[i].name}', '${shopItems[i].price}', '${shopItems[i].description}', ${shopItems[i].avgRating}, ${i}, '${shopItems[i].size}', ${shopItems[i].stock});">
										<a href="#" onclick="return false;" class="add-to-cart text-center py-2 mr-1"><span>Quick View <i class="fa-regular fa-eye ml-1"></i></span></a>
									</p>
								</div>
							</div>
						</div>`
    			
    	shopItemList.innerHTML += item
    	buildAverageRating(shopItems[i], starID)
	}
}

//Buils main categories in the shop page
function buildShopCategories(){
	shopMainCategoryList.innerHTML = ''
	for(var i = 0; i < mainCategories.length; i++){
		var subCategoryID = `${mainCategories[i]}SubType`
		
		var headingID = `heading${numberToWord(i + 1)}`
		var collapseID = `collapse${numberToWord(i + 1)}`
		
		var category = `<div class="panel panel-default">
                         <div class="panel-heading" role="tab" id="${headingID}">
                             <h4 class="panel-title">
                                 <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#${collapseID}" aria-expanded="false" aria-controls="${collapseID}" onclick="setCurrentMainCategory('${mainCategories[i]}'); buildCurrentFilters();">${mainCategories[i]}
                                 </a>
                             </h4>
                         </div>
                         <div id="${collapseID}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="${headingID}">
                             <div class="panel-body">
                                <ul id="${subCategoryID}">
                                </ul>
                             </div>
                         </div>
                     </div>`
    			
    	shopMainCategoryList.innerHTML += category
	}
	
	buildShopSubCategories()
}

function buildShopSubCategories(){
	
//	console.log(subCategories[mainCategories[0]])
	
	for(var i = 0; i < mainCategories.length; i++){
		var subCategoryID = `${mainCategories[i]}SubType`
		var subCategoryElement = document.getElementById(subCategoryID)
		subCategoryElement.innerHTML = ''
		
		for(var j = 0; j < subCategories[mainCategories[i]].length; j++){
			var subType = `<li onclick="setCurrentSubCategory('${subCategories[mainCategories[i]][j]}'); buildCurrentFilters();"><a href="javascript: stopScrollingToTop();">${subCategories[mainCategories[i]][j]}</a></li>`
	    	//subCategoryElement.innerHTML += `<li>${subCategories[mainCategories[i]][j]}</li>`
	    	
	    	subCategoryElement.innerHTML += subType
		}
		
		//console.log(subCategoryElement)
	}

}

//Set current main category
function setCurrentMainCategory(mainCategory){
	currentMainCategory = mainCategory
	currentBrand = ''
	currentSubCategory = undefined
}

//Set current sub category
function setCurrentSubCategory(subCategory){
	currentSubCategory = subCategory
	currentBrand = ''
}

//set current brand
function setCurrentBrand(){
	currentBrand = document.querySelector('input[name="brand"]:checked').value
	callGetCustomizedShopServlet(currentMainCategory, currentSubCategory)
}

//set availability
function setAvailability(){
	includeOutOfStock = document.querySelector('input[name="availability"]:checked').value
	callGetCustomizedShopServlet(currentMainCategory, currentSubCategory)
}

//Build price range
function buildPriceRange(){	
	priceMax.value = highestPrice
	priceMin.value = lowestPrice
	priceRangeMin.value = lowestPrice
	priceRangeMax.value = highestPrice

	priceRangeProgress.style = `left: ${lowestPrice / 100}%; right: ${100 - (highestPrice/100)}%`
	
	priceRangeMax.addEventListener('change', () => {
		priceRangeChanged = true
	 	buildCurrentFilters()
	})	
	
	priceRangeMin.addEventListener('change', () => {
		priceRangeChanged = true
	 	buildCurrentFilters()
	})
}

//call get customized shop servlet
function callGetCustomizedShopServlet(mainCategory, subCategory){
	if(mainCategory == undefined){
		buildShopCategories()
		mainCategory = `%%`
	}else mainCategory = `%${mainCategory}%`
	
	if(subCategory == undefined){
		buildShopCategories()
		subCategory = `%%`
	}else subCategory = `%${subCategory}%`
	
	var itemName
	if(itemNameForShop != undefined){
		itemName = itemNameForShop
		itemName = `%${itemName}%`
	}else{
		itemName = `%%`
	}
	
	var brand
	if(currentBrand == "" || currentBrand == null){
		brand = `%%`
	}else{
		brand = `%${currentBrand}%`
	}
	
	var lowerPrice = priceMin.value
	var higherPrice = priceMax.value
	
	$.get("http://localhost:8080/LankaHardware/GetCustomizedShopServlet", {mainCategory : mainCategory, lowerPrice : lowerPrice, higherPrice : higherPrice, sortByValue: sortByValue, itemName: itemName, brand: brand, subType: subCategory, includeOutOfStock: includeOutOfStock}, function(response) {
		
		customizedShopItems = response[0]
		brandList = response[1]
		
		buildShopItems(customizedShopItems)
		buildDefaultBrandList()
		if(brandList != null) buildBrandList()
	})
}

//build default brand list
function buildDefaultBrandList(){
	if(currentBrand == '' || currentBrand == null){
		brandListElement.innerHTML = `<label style="display: flex; color: gray; margin-bottom: 0px; justify-content: space-between;">
										<span style="width: fit-content;">All</span>
										<input class="brand-check-input" type="radio" value='' name="brand" checked onclick="setCurrentBrand()" id="allBrandRadioButton">
									  </label>`
	}else {
		brandListElement.innerHTML = `<label style="display: flex; color: gray; margin-bottom: 0px; justify-content: space-between;">
										<span style="width: fit-content;">All</span>
										<input class="brand-check-input" type="radio" value='' name="brand" onclick="setCurrentBrand()" id="allBrandRadio">
									  </label>`
	}
	
	
}

//build brand list
function buildBrandList(){
	for(var i = 0; i < brandList.length; i++){
		var brand = `<label style="display: flex; color: gray; margin-bottom: 0px; justify-content: space-between;">
						<span style="width: fit-content;">${brandList[i]}</span>
						<input class="brand-check-input" type="radio" value="${brandList[i]}" name="brand" onclick="setCurrentBrand()">
					 </label>`
					 
		brandListElement.innerHTML += brand
	}
	
	var selectedRadioButton
	
	selectedRadioButton = document.querySelector(`input[name="brand"][value='${currentBrand}']`)
	selectedRadioButton.checked = true
}

//build current filters
function buildCurrentFilters(){
	sortByValue = sortBy.value
	
	currentFilters.innerHTML = ''
	
	if(currentMainCategory != null){
		currentFilters.innerHTML = `<div class="cat" style="padding: 10px; text-transform: capitalize;">
										<a href="" onclick="removeMainCategory(); return false;" class="btn btn-outline-secondary filter" style="display: flex; align-items: center; height: 33px;">${currentMainCategory}<span style="font-size: x-large; margin-left: 5px;">&times;</i></span></a>
									</div>`
	}
	
	if(currentSubCategory != null){
		currentFilters.innerHTML += `<div class="cat" style="padding: 10px; text-transform: capitalize;">
										<a href="" onclick="removeSubCategory(); return false;" class="btn btn-outline-secondary filter" style="display: flex; align-items: center; height: 33px;">${currentSubCategory}<span style="font-size: x-large; margin-left: 5px;">&times;</i></span></a>
									</div>`
	}
	
	if(itemNameForShop != null){
		currentFilters.innerHTML += `<div class="cat" style="padding: 10px; text-transform: capitalize;">
										<a href="" onclick="removeSearchedName(); return false;" class="btn btn-outline-secondary filter" style="display: flex; align-items: center; height: 33px;">"${itemNameForShop}"<span style="font-size: x-large; margin-left: 5px;">&times;</i></span></a>
									</div>`
	}
	
	if(sortByFilterOpen == true){
		currentFilters.innerHTML += `<div class="cat" style="padding: 10px; text-transform: capitalize;">
									<a href="" onclick="removeSortBy(); return false;" class="btn btn-outline-secondary filter" style="display: flex; align-items: center; height: 33px;">${sortByValue}<span style="font-size: x-large; margin-left: 5px;">&times;</i></span></a>
								</div>`
	}
	
	if(currentMainCategory != null || itemNameForShop != null || sortByFilterOpen != false || currentSubCategory != null){
		currentFilters.innerHTML += `<div class="cat" style="padding: 10px;">
									<button type="reset" class="btn btn-outline-secondary filterReset" onclick="resetCurrentFilters();">Reset Filters	</button>
								</div>`
	}
				
	callGetCustomizedShopServlet(currentMainCategory, currentSubCategory)
}

//remove main category
function removeMainCategory(){
	currentMainCategory = null
	currentBrand = ''
	buildCurrentFilters()
}

//remove sub category
function removeSubCategory(){
	currentSubCategory = null
	buildCurrentFilters()
}

//remove searched name
function removeSearchedName(){
	itemNameForShop = null
	buildCurrentFilters()
}

//set sort by
function setSotrBy(){
	sortByFilterOpen = true
}

//remove sort by
function removeSortBy(){
	sortByFilterOpen = false
	sortBy.value = 'Price: Low To High'
	$('select').niceSelect('update');
	buildCurrentFilters()
}

//reset filters
function resetCurrentFilters(){
	currentFilters.innerHTML = ``
	currentMainCategory = null
	currentSubCategory = null
	currentBrand = ''
	brandList = null
	itemNameForShop = null
	sortByFilterOpen = false
	sortBy.value = 'Price: Low To High'
	$('select').niceSelect('update');
	callGetShopServlet()
}

//shop empty message
function shopEmpty(){
	shopItemList.style = "justify-content: center;"
	shopItemList.innerHTML = `<div style='display: flex; flex-direction: column; justify-content: center; align-items: center; font-size: medium; margin-top: 50px;'>
									<span style='font-size: x-large'><i class="fa-solid fa-circle-exclamation"></i> We're Sorry</span>
									<span>We couldn't find any items</span>
							</div>`
							
	var pagination = document.getElementById('pagination')
	pagination.style = "display: none;"
}

//call AddReviewServlet

function test(){
	console.log(ratingSubmitBtn)
}

function callAddReviewServlet(){
	var inputFile = document.getElementById('inputFile')
	var reviewDescription = document.getElementById('reviewDescription').value
	var stars = document.querySelector('input[name="rate"]:checked').value
	var endpoint = "http://localhost:8080/LankaHardware/AddReviewServlet"
	var formData = new FormData();
	
	for(const file of inputFile.files){
		formData.append('inputFile', file)
	}
	
	formData.append('reviewDescription', reviewDescription)
	formData.append('stars', stars)
	
	fetch(endpoint, {
		method: "post",
		body: formData
	}).catch(console.error)
}
	

//call AskQuestionServlet
var afterQuestion = false

openQuestionModal.addEventListener('click', () => {
	questionModalHeader.innerHTML = `<i class="fa-solid fa-arrow-left" data-bs-dismiss="modal" style="font-size: large;"></i>
									<h5 style="color: gray;"> Ask a Question </h5>`
	questionModalBody.innerHTML = `<textarea name="desc" id="questionTextArea" cols="30" rows="7" class="form-control reviewTextArea" style="height: 130px; margin: 20px 0px 20px 0px;"></textarea>`
    questionModalFooter.innerHTML = `<button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">Close</button>
									 <button type="button" class="btn btn-primary" id="questionSubmitBtn" onclick="toQuestionServlet();">Submit</button>`

	questionModalHeader.style = "display: flex; justify-content: flex-start; border-bottom: none; padding-bottom: 0px; align-items: baseline; column-gap: 10px;"
	questionModalBody.style = "display: flex;"
	questionModalFooter.style = "display: flex; border-top: none;"
})

function toQuestionServlet(){
	var question = document.getElementById('questionTextArea').value
	question = question.trim()
	if(question.length > 0) callAskQuestionServlet(question)
}

function callAskQuestionServlet(question){
	var itemID = itemIDForQuestion
	
	$.post("http://localhost:8080/LankaHardware/AskQuestionServlet", {question : question, itemID : itemID}, function(response){
	    
	    questionModalHeader.style = "display: none;"
	    questionModalBody.style = "padding: 1rem;"
	    questionModalBody.innerHTML = `<div style="display: flex; justify-content: center; align-items: center; column-gap: 10px;">
									        <lottie-player src="https://assets3.lottiefiles.com/packages/lf20_q7hiluze.json"  background="transparent"  speed="1"  style="width: 50px; height: 50px;" autoplay></lottie-player>
									        <span style="font-size: x-large;">${response}</span>
									    </div>`
	    questionModalFooter.style = "display: none;"
	    
	    afterQuestion = true
	    
	    callGetProductSingleServlet(itemID)
	    
	    setTimeout(function() {
	    	$('#modalCenter').modal('hide')
	  	}, 2500);
	})
}

//Get the number in words
function numberToWord(number){
	var ones = {
		0: "Zero",
		1: "One",
		2: "Two",
		3: "Three",
		4: "Four",
		5: "Five",
		6: "Six",
		7: "Seven",
		8: "Eight",
		9: "Nine"
	}
	
	for(const [n, w] of Object.entries(ones)){
		if(number == n){
			var word = w
			break
		}
	}
	
	return word
}