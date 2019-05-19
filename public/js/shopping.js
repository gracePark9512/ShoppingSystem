"use strict";

// create application, and load used modules //
var app = angular.module('ShoppingApp', ['ngResource', 'ngStorage']);

app.factory('productDAO', function ($resource) {
	return $resource('/api/products/:id');
});

app.factory('categoryDAO', function ($resource) {
	return $resource('/api/categories/:cat');
});

app.factory('registerDAO', function ($resource) {
	return $resource('/api/register');
});

app.factory('signInDAO', function ($resource) {
	return $resource('/api/customers/:username');
});

app.controller('ProductController', function (productDAO, categoryDAO) {
	// load the products
	this.products = productDAO.query();

	// load the categories
	this.categories = categoryDAO.query();

	// filter by category click handler
	this.selectCategory = function (selectedCat) {
		this.products = categoryDAO.query({"cat": selectedCat});
	};

	// get All products click handler
	this.getAll = function () {
		this.products = productDAO.query();
	};
});

app.controller('CustomerController', function (registerDAO, $sessionStorage, $window, signInDAO) {
	// registering a customer
	this.registerCustomer = function (customer) {
		registerDAO.save(null, customer);
		//redirect to sign in
		$window.window.location.href = '/signin.html';
	};

	// sign in message
	this.signInMessage = "Please sign in to continue.";
	
	// sign in function
	// alias 'this' so that we can access it inside callback functions
	let ctrl = this;
	this.signIn = function (username, password) {
		// get customer from web service
		signInDAO.get({'username': username},
				// success
				  function (customer) {
					// also store the retrieved customer
					  $sessionStorage.customer = customer;
					// redirect to home
					  $window.window.location.href = '.';
				  },
				// fail
				  function () {
					  ctrl.signInMessage = 'Sign in failed. Please try again.';
				  }
		);
	};
	
	//tells menu that no one is signed in
	this.signedIn = false;
	
	//sets the default welcome message
	this.welcome = "";
	
	//clears account in session storage
	this.signOut = function(){
		//resset session storage
		$sessionStorage.$reset();
		//redirect to home
		$window.window.location.href = '.';
	};
	
	this.checkSignIn = function (){
		if($sessionStorage.customer){
			//sets signedIn to true to tell menu an account is logged in
			this.signedIn = true;
			//sets the welcome message to Welcome + customer's name
			this.welcome = "Welcome " + $sessionStorage.customer.name;
		}
	};
});
