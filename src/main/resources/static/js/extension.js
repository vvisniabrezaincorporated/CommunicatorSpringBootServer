function httpGet(theUrl) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", theUrl, false);
    xmlHttp.send(null);
    return xmlHttp.responseText;
}

function messageContentScript() {
    appendMyMessage(document.getElementById('messageText').value);
    window.postMessage({
        type: "encrypt".toString(),
        key: httpGet("/web/user/" + sessionStorage.getItem("current".toString())),
        message: document.getElementById('messageText').value
    }, "*");

    let messageStorage = sessionStorage.getItem(sessionStorage.getItem('current'));

    if (!messageStorage) {
        messageStorage = [];
    } else {
        messageStorage = JSON.parse(messageStorage);
    }
    messageStorage.push({
        message: document.getElementById('messageText').value,
        messageType: "send".toString()
    });



    sessionStorage.setItem(sessionStorage.getItem('current'), JSON.stringify(messageStorage));

}

function encryptedMessageToContentScript(msg, id) {
    window.postMessage({
        type: "decrypt".toString(),
        key: httpGet("/web/user/" + id.toString()),
        message: msg.toString(),
        sender: id.toString()
    }, "*");
}
function sendKey() {
    window.postMessage({
        type: "currentSignIn".toString()
    }, "*");

}

