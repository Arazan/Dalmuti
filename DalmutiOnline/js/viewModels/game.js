/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * game module
 */
define(['ojs/ojcore', 'knockout', 'jquery', 'ojs/ojknockout', 'ojs/ojbutton', 'ojs/ojdialog', 'ojs/ojinputtext', 'ojs/ojselectcombobox',
    'ojs/ojinputnumber'
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
        self.roomSize = ko.observable();
        self.errorDialogTittle = ko.observable();
        self.errorDialogMessage = ko.observable();
        self.roomList = ko.observableArray([
            {gameID: '', gameOwner: ''}
        ]);




        self.joinClick = function (data, event) {
            //Set Mandatory Fields            
            $("#roomSize").ojInputNumber({required: false});
            $("#roomSelect").ojSelect({required: true});


            //Validate Fields
            if (!self.validateInputs())
                return true;

            console.log("Join Room");

            return true;
        };

        self.createClick = function (data, event) {
            //Set Mandatory Fields            
            $("#roomSize").ojInputNumber({required: true});
            $("#roomSelect").ojSelect({required: false});

            //Validate Fields
            if (!self.validateInputs())
                return true;


            console.log("Create Room");

            //Create Room
            self.createRoom(self.username());
            return true;
        };
        
        self.okDialogClick = function(data,event){
            $("#errorDialog").ojDialog("close");
            return true;
        }


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

            //Enable Buttons
            $("#joinButton").ojButton("option", "disabled", false);
            $("#createRoomButton").ojButton("option", "disabled", false);

            //get Available rooms
            self.getRooms();

        };

        self.onClose = function (evt) {
            console.log("Disconnected");
            self.serverOnline(false);
        };

        self.onMessage = function (evt) {
            console.log("Message: " + evt.data);
            self.processMessage(evt.data);
        };

        self.onError = function (evt) {
            console.log("ERROR: " + evt.data);
        };


        self.validateInputs = function () {
            var isUserNameValid = $("#userName").ojInputText('validate');
            var isRoomSizeValid = $("#roomSize").ojInputNumber('validate');
            var isRoomSelected = $("#roomSelect").ojSelect('validate');
            if (isUserNameValid
                    && isRoomSizeValid
                    && isRoomSelected) {
                return true;
            }
            return false;
        }


        self.getRooms = function () {
            var request = {};
            var payload;

            request.action = 'GET_ROOMS';
            payload = JSON.stringify(request);
            websocket.send(payload);
        }

        self.createRoom = function (roomName) {
            console.log("Create Room: " + roomName);
            websocket.send('{"userId":"","gameID":"","action":"CREATE_ROOM","data":{"username":"' + self.username() + '","roomSize":"' + self.roomSize() + '"}}');
            self.room(self.username());
        }


        self.processMessage = function (message) {
            var payload;
            payload = JSON.parse(message);

            //If any error
            if (payload.status == 'ERROR') {
                console.log(payload.status + ":" + payload.message);
                self.errorDialogTittle(payload.status);
                self.errorDialogMessage(payload.message);
                $("#errorDialog").ojDialog("open");
                return;
            }

            //If get rooms
            if (payload.action == 'GET_ROOMS') {
                self.roomList(payload.data.gameRooms);
            }



        }



        self.connectToServer();
    }

    return gameContentViewModel;
});
