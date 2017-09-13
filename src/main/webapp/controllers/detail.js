'use strict';

angular.module('library')
    .controller('DetailCtrl', function ($scope, library) {

        $scope.detailBook = function(bookId) {
        	library.detailBook(bookId, function(book) {
        		$scope.book = book;
            });
        }
        
        $scope.form = {};

        $scope.load();
    });
