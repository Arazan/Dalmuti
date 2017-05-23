/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * game module
 */
define(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojbutton', 'ojs/ojdialog', 'ojs/ojinputtext', 'ojs/ojselectcombobox'
], function (oj, ko, $) {
    /**
     * The view model for the main content view template
     */
    function gameContentViewModel() {
        var self = this;
        var wsUri = "ws://localhost:4444";

        self.username = ko.observable();
        self.room = ko.observable();
        self.serverOnline = ko.observable(false);
        self.roomList = ko.observableArray([
            {gameID: '', gameOwner: ''}
        ]);



        self.joinClick = function (data, event) {
            console.log("Join Room");
            return true;
        };

        self.createClick = function (data, event) {
            websocket.send('{"userId":"","gameID":"","action":"CREATE_ROOM","data":{"username":"Mike","roomSize":"10"}}');

            self.createRoom(self.username());
            return true;
        };


        self.connectToServer = function () {
            console.log("Connect to Server");
            websocket = new WebSocket(wsUri);
            websocket.onopen = function (evt) {
                self.onOpen(evt)
            };
            websocket.onclose = function (evt) {
                self.onClose(evt)
            };
            websocket.onmessage = function (evt) {
                self.onMessage(evt)
            };
            websocket.onerror = function (evt) {
                self.onError(evt)
            };
        };

        self.onOpen = function (evt) {
            console.log("Connected");
            self.serverOnline(true);
            self.getRooms();

        };

        self.onClose = function (evt) {
            console.log("Disconnected");
            self.serverOnline(false);
        };

        self.onMessage = function (evt) {
            var payload;
            console.log("Message: " + evt.data);
            payload = JSON.parse(evt.data);


            console.log(payload.gameRooms);
            self.roomList(payload.gameRooms);



        };

        self.onError = function (evt) {
            console.log("ERROR: " + evt.data);
        };


        self.getRooms = function () {
            var request = {};
            var payload;

            request.action = 'GET_ROOMS';
            payload = JSON.stringify(request);
            websocket.send(payload);
        }

        self.createRoom = function (roomName) {
            console.log("Create Room: " + roomName);
            self.room(self.username());
        }



        self.connectToServer();
    }

    return gameContentViewModel;
});
