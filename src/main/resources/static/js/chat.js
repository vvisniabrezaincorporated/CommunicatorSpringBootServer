function appendMessage(payload) {
    let message = JSON.parse(payload.body);
    var d = new Date();
    var time = document.createTextNode(d.getHours() + ":" + d.getMinutes());
    sessionStorage.setItem(message.answer, message.from, time);
    encryptedMessageToContentScript(message.answer, message.from);


}

function appendMyMessage(message) {


    var para = document.createElement("div");

    para.className = "container lighter"

    var p = document.createElement("p");
    para.appendChild(p);

    var node = document.createTextNode(message);
    p.appendChild(node);

    var element = document.getElementById("chat");
    element.appendChild(para);


}


window.addEventListener("message", function (event) {


    if (event.data.type === "encrypted") {
        sendMessage(event.data.encyptedMessage);
    } else if (event.data.type === "currentSignedIn") {


        setKey(event.data.publicKey);
        setKeyEmail(event.data.email);

    } else if (event.data.type === "decrypted") {

        if (event.data.sender === sessionStorage.getItem('current')) {
            let para = document.createElement("div");
            para.className = "container darker"


            let p = document.createElement("p");
            para.appendChild(p);

            let node = document.createTextNode(event.data.message);
            p.appendChild(node);


            let element = document.getElementById("chat");
            element.appendChild(para);
        } else {
            alert("Tou got new message from "+ event.data.sender);
        }


        let messageStorage = sessionStorage.getItem(event.data.sender);

        if (!messageStorage) {
            messageStorage = [];
        } else {
            messageStorage = JSON.parse(messageStorage);
        }
        messageStorage.push({
            message: event.data.message,
            messageType: "recieved".toString()
        });


        sessionStorage.setItem(event.data.sender, JSON.stringify(messageStorage));


    } else if (event.data.type === "decryptedStorage") {

        if (event.data.messageType === 'owner') {
            var para = document.createElement("div");
            para.className = "container lighter"


            var p = document.createElement("p");
            para.appendChild(p);

            var node = document.createTextNode(event.data.message);
            p.appendChild(node);

            var span = document.createElement('span');
            span.className = "time-right"
            var d = new Date();
            var time = document.createTextNode(d.getHours() + ":" + d.getMinutes());
            span.appendChild(time);
            para.appendChild(span);

            var element = document.getElementById("chat");
            element.appendChild(para);
        } else if (event.data.messageType === 'reciever') {
            var para = document.createElement("div");
            para.className = "container darker"


            var p = document.createElement("p");
            para.appendChild(p);

            var node = document.createTextNode(event.data.message);
            p.appendChild(node);

            var span = document.createElement('span');
            span.className = "time-right"
            var d = new Date();
            var time = document.createTextNode(d.getHours() + ":" + d.getMinutes());
            span.appendChild(time);
            para.appendChild(span);

            var element = document.getElementById("chat");
            element.appendChild(para);
        }
    }


});

function sendMessage(message) {

    stompClient.send('/chat', {}, JSON.stringify({
        'userID': sessionStorage.getItem('current'),
        'fromUserID': selfID,
        'message': message
    }));


}

