'use strict';

angular.module('library')
    .controller('LibraryCtrl', function ($scope, library) {

        $scope.load = function() {
        	library.list($scope.form,function (list) {
                $scope.list = list.result;
            });
        }

        $scope.save = function() {
        	library.save($scope.form, function() {
                $scope.load();
            });
        }

        $scope.form = {};

        $scope.load();
    });
