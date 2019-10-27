function onlineFun(payload) {

    var message = JSON.parse(payload.body);

    if (message.type === 'LEAVE') {


        var child = document.getElementById(message.sender);
        child.parentNode.removeChild(child);

    } else if (message.type === 'JOIN') {


        if (message.sender !== selfID && document.getElementById(message.sender) === null) {


            var para = document.createElement("div");

            para.setAttribute("id", message.sender);
            para.setAttribute("class", "user")


            para.setAttribute("style", "cursor: pointer;")
            para.setAttribute("onclick", "chooseUser(\""+message.sender+"\");");


            var node = document.createTextNode(message.sender);
            para.appendChild(node);


            var element = document.getElementById("onlineList");
            element.appendChild(para);

        } else {
            console.log("Nothing to add!");
        }
    }


}