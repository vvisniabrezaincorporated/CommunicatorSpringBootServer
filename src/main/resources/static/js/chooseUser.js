function chooseUser(user) {

    function check(selector) {
        var elements = document.getElementsByClassName(selector);
        for (var i = 0; i < elements.length; i++) {
            elements[i].style.backgroundColor = "red";
        }
    }

    check('user');

    function destroy(selector) {
        var elements = document.getElementsByClassName(selector);
        for (var i = 0; i < elements.length; i++) {
            elements[i].style.backgroundColor = "red";
        }
    }

    document.getElementById(String(user)).style.backgroundColor = "green";

    var chat = document.getElementById("chat");
    while (chat.firstChild) {
        chat.removeChild(chat.firstChild);
    }
    sessionStorage.setItem('current', String(user));


    let messageStorage = sessionStorage.getItem(String(user));

    if (messageStorage) {
        messageStorage = JSON.parse(messageStorage);
        messageStorage.forEach((element) => {

                if(element.messageType === "send"){
                    appendMyMessage(element.message);
                } else if (element.messageType === "recieved") {
                    var para = document.createElement("div");
                    para.className = "container darker"



                    var p = document.createElement("p");
                    para.appendChild(p);

                    var node = document.createTextNode(element.message);
                    p.appendChild(node);



                    var element = document.getElementById("chat");
                    element.appendChild(para);

                }

        });
    }


}