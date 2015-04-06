'use strict';

var exampleApp = angular.module('exampleApp', ['ui.bootstrap', 'ngWebSocket'])

exampleApp.factory('MyData', function($websocket) {
    // Open a WebSocket connection
    var ws = $websocket('${remoteWebsocketAddress}');

    var collection = [];

    ws.onMessage(function(event) {
        var msg = JSON.parse(event.data);
        var t = new Date();
        t.setSeconds(t.getSeconds() + msg.timeToShow);
        msg.showTill = t;

        console.log(msg);
        if (msg.type == "RandomsGenerated") {
            collection.push(msg);
        } else if (msg.type == "tts") {
            // collection.push(event.data);
        } else if (msg.type == "gr") {
            // collection.push(event.data);
        }
    });

    ws.onOpen(function() {

    });

    return {
        collection: collection,
        status: function() {
            return ws.readyState;
        },
        send: function(message) {
            if (angular.isString(message)) {
                ws.send(message);
            } else if (angular.isObject(message)) {
                ws.send(JSON.stringify(message));
            }
        }
    };
}).controller('ButtonsCtrl', function($scope, MyData) {

    $scope.radioModel = 'A';


    $scope.$watch('radioModel', function() {
        //  MyData.send('{}');
    });
    // $scope.MyData = MyData;
}).controller('ConfigInputController', function($scope, $timeout, MyData) {

    $scope.timeToShow = 3;
    $scope.generateRate = 4;
    $scope.MyData = MyData;

    $scope.closeAlert = function(index) {
        $scope.MyData.collection.splice(index, 1);
    };

    $scope.$watch('timeToShow', function(v) {
        if (v) {
            MyData.send({
                type: "tts",
                value: v
            });
        }
    });

    $scope.$watch('generateRate', function(v) {
        if (v) {
            MyData.send({
                type: "gr",
                value: v
            });
        }
    });


    $scope.$watchCollection('MyData.collection', function(c) {
        var now = new Date();
        for (var i = c.length; i-- > 0;) {
            if (c[i].showTill < now) {
                $scope.closeAlert(i);
            }
        }

    });


});