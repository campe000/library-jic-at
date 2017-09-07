'use strict';

angular.module('library')
    .service('library', function ($http) {
        return {
        	listBooks: function (success) {
                return $http.get("/rest/library").then(success);
            },
            save: function (library, success) {
                return $http.post("/rest/library", library).then(success);
            }
        };
    });