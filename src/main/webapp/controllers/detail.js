'use strict';

angular.module('library')
    .controller('DetailCtrl', function ($scope, $routeParams, library) {
    	$scope.load = function() {
    		console.log($routeParams.id);
    		
    		library.detailBook($routeParams.id, function(book) {
    			$scope.book = book;
            });
        }
        
        $scope.form = {};

        $scope.load();
    });
