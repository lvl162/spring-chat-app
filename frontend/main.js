'use strict';
var script = document.createElement('script');
script.src = 'https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js';
script.type = 'text/javascript';
document.getElementsByTagName('head')[0].appendChild(script);


function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('#connecting');

// var message =

var stompClient = null;
var username = null;
var id = getCookie('uid');
var uname = getCookie('uname');
var chatId = null;
var recipientId = location.search.split('to=')[1]

function connect() {
    username = document.querySelector('#username').innerText.trim();

    var socket = new SockJS('/websocket-chat');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
}

// Connect to WebSocket Server.
connect();

function onConnected() {
    // Subscribe to the Public Topic

    $.ajax({
        url: "/api/getChatId",
        type:'post',
        data: JSON.stringify({ "senderId": id, "recipientId" : recipientId }),
        contentType : 'application/json',
        success:function(data, status){
            console.log(data)
            if (status == "success") {
                chatId = data.id;
                $.ajax({
                    url: "/api/messages/chatroom/" + chatId,
                    type:'get',
                    contentType : 'application/json',
                    success:function(data, status){

                        for (let i=0; i<data.length; i++) {
                            var messageElement = document.createElement('li');
                            console.log(data[i].content,
                            data[i].senderName, data[i].createdAt)
                            messageElement.classList.add('chat-message');
                            var usernameElement = document.createElement('strong');
                            usernameElement.classList.add('nickname');
                            var usernameText = document.createTextNode( data[i].senderName);
                            usernameElement.appendChild(usernameText);
                            messageElement.appendChild(usernameElement);

                            var textElement = document.createElement('span');
                            var messageText = document.createTextNode( data[i].content + " " + data[i].createdAt);
                            textElement.appendChild(messageText);

                            messageElement.appendChild(textElement);

                            messageArea.appendChild(messageElement);
                            messageArea.scrollTop = messageArea.scrollHeight;
                        }
                        // console.log(data)
                    }
                });
                stompClient.subscribe('/topic/' + data.id + '/queue/messages', onMessageReceived);


                // Tell your username to the server
                stompClient.send("/app/chat.addUser",
                    {},
                    JSON.stringify({sender: username, type: 'JOIN'})
                )

                connectingElement.classList.add('hidden');
            }
            else alert("loi me roi dcmmm");
        }
    });



}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            recipientId: recipientId,
            recipientName: recipientName + '',
            senderId: id,
            senderName: uname,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/app/chat", {}, JSON.stringify(chatMessage));
        messageInput.value = '';

        // var messageElement = document.createElement('li');
        // messageElement.classList.add('chat-message');
        // var usernameElement = document.createElement('strong');
        // usernameElement.classList.add('nickname');
        // var usernameText = document.createTextNode(chatMessage.senderName);
        // var usernameText = document.createTextNode(chatMessage.senderName);
        // usernameElement.appendChild(usernameText);
        // messageElement.appendChild(usernameElement);
        // var textElement = document.createElement('span');
        // var messageText = document.createTextNode(chatMessage.content);
        // textElement.appendChild(messageText);
        //
        // messageElement.appendChild(textElement);
        //
        // messageArea.appendChild(messageElement);
        // messageArea.scrollTop = messageArea.scrollHeight;
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.senderName + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.senderName + ' left!';
    } else {
        messageElement.classList.add('chat-message');
        var usernameElement = document.createElement('strong');
        usernameElement.classList.add('nickname');
        var usernameText = document.createTextNode(message.senderName);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('span');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


messageForm.addEventListener('submit', sendMessage, true);