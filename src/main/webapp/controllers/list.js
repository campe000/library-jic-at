'use strict';

angular.module('library')
    .controller('LibraryCtrl', function ($scope,$location, library) {
    	
        $scope.load = function() {
        	library.listBooks(function (list) {
        		$scope.list = list.data;
            });
        }

        $scope.createBook = function() {
        	library.createBook($scope.form, function() {
                $scope.load();
            });
        }

        $scope.searchBook = function() {
        	library.searchBook($scope.bookToSearch,function (list) {
        		$scope.list = list.data;
            });
        }
        
        $scope.deleteBook = function(bookId) {
        	library.deleteBook(bookId, function() {
                $scope.load();
            });
        }
        
        $scope.form = {};

        $scope.load();
    });
