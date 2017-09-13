'use strict';

angular.module('library')
    .controller('LibraryCtrl', function ($scope, library) {

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

        $scope.deleteBook = function(bookId) {
        	library.deleteBook(bookId, function() {
        		alert('pruebas');
                $scope.load();
            });
        }

        $scope.form = {};

        $scope.load();
    });
