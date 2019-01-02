var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/raspi-dc-driver-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

// Move Functions
function moveForward() {
    stompClient.send("/app/move/forward", {}, "");
}

function moveBackward() {
    stompClient.send("/app/move/backward", {}, "");
}

function moveLeft() {
    stompClient.send("/app/move/left", {}, "");
}

function moveRight() {
    stompClient.send("/app/move/right", {}, "");
}

function moveStop() {
    stompClient.send("/app/move/stop", {}, "");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });

    // Move Control
    $("#forward").mousedown(function () {
        moveForward();
    });
    $("#forward").mouseup(function () {
        moveStop();
    });

    $("#backward").mousedown(function () {
        moveBackward();
    });
    $("#backward").mouseup(function () {
        moveStop();
    });

    $("#left").mousedown(function () {
        moveLeft();
    });
    $("#left").mouseup(function () {
        moveStop();
    });

    $("#right").mousedown(function () {
        moveRight();
    });
    $("#right").mouseup(function () {
        moveStop();
    });
});

