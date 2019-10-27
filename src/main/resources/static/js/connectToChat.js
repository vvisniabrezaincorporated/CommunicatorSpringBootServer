window.onload = load;

function load() {
    function httpGet(theUrl) {
        var xmlHttp = new XMLHttpRequest();
        xmlHttp.open("GET", theUrl, false);
        xmlHttp.send(null);
        return xmlHttp.responseText;
    }
    selfID = httpGet('/username');

    function onlineList() {
        var online = httpGet('/web/online')
        online = online.replace(/\s/g, '');


        var removeFirst = online.substring(1);

        var onlineUsers = removeFirst.substring(0, removeFirst.length - 1);

        var array = onlineUsers.split(",")
        for(var i = array.length - 1; i >= 0; i--) {
            if(array[i] === selfID) {
                array.splice(i, 1);
            }
        }



        array.forEach((user) => {
            var para = document.createElement("div");

            para.setAttribute("id", user);
            para.setAttribute("class", "user")

            var node = document.createTextNode(user);
            para.appendChild(node);
            para.setAttribute("style", "cursor: pointer;")
            para.setAttribute("onclick", "chooseUser(\""+user+"\");");

            var element = document.getElementById("onlineList");
            element.appendChild(para);
        })


    }

    onlineList();
    var socket = new SockJS("/ws");


    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        stompClient.subscribe('/online', onlineFun);

        stompClient.send("/app/chat.addUser",
            {},
            JSON.stringify({sender: selfID, type: 'JOIN'})
        )

        stompClient.subscribe('/user/' + selfID + '/msg', appendMessage);


    });


}




