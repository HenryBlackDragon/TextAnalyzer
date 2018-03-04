var app = angular.module("AppSpring", []);

app.controller("AppController", function ($scope, $http) {
    $scope.websites = [];

    $http.get('http://localhost:8080/analyzer/list?path=e:/a.txt').then(function (response) {
        $scope.websites = response.data;
    });
});

