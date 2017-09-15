'use strict';

angular.module('library')
    .service('library', function ($http) {
        return {
        	listBooks: function (success) {
                return $http.get("/rest/library").then(success);
            },
        	detailBook: function (bookId,success) {
                return $http.get("/rest/library/"+ bookId).then(success);
            },
            createBook: function (library, success) {
                return $http.post("/rest/library", library).then(success);
            },
            searchBook: function (library, success) {
            	return $http({
            	    method: 'GET',
            	    url: '/rest/library/listado/' + library,
            	    data: {
            	        id: library
            	    },
            	    headers: {
            	        'Content-type': 'application/json;charset=utf-8'
            	    }
            	})
            	.then(function(response) {
            	    console.log(response.data);
            	}, function(rejection) {
            	    console.log(rejection.data);
            	});
            },
            deleteBook: function (bookId) {
            	return $http({
            	    method: 'DELETE',
            	    url: '/rest/library/delete/' + bookId,
            	    data: {
            	        id: bookId
            	    },
            	    headers: {
            	        'Content-type': 'application/json;charset=utf-8'
            	    }
            	})
            	.then(function(response) {
            	    console.log(response.data);
            	}, function(rejection) {
            	    console.log(rejection.data);
            	});
            }
        };
    });
