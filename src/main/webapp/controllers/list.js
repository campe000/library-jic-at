'use strict';

angular.module('library')
    .controller('LibraryCtrl', function ($scope, library) {

        $scope.load = function() {
        	library.listBooks(function (list) {
                $scope.list = list;
            });
        }

        $scope.createBook = function() {
        	library.createBook($scope.form, function() {
                $scope.load();
            });
        }

        $scope.form = {};

        $scope.load();
    });
