'use strict';
angular
    .module('library', ['ngRoute'])
    .config(function ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/list.html',
                controller: 'LibraryCtrl',
                controllerAs: 'list'
            })
            .when('/rest/library/:id', {
                templateUrl: 'views/detail.html',
                controller: 'LibraryCtrl',
                controllerAs: 'detail'
            })
            .otherwise({
                redirectTo: '/'
            });
    });
