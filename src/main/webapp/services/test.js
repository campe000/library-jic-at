'use strict';

angular.module('library')
    .service('library', function ($http) {
        return {
            list: function (success) {
                return $http.get("/rest/library").then(success);
            },
            save: function (test, success) {
                return $http.post("/rest/library", test).then(success);
            }
        };
    });
