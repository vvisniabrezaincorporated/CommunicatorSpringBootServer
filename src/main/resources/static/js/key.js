function setKey(data) {

    var arr=[];
    for(var i=0; i<data.length; i++) {
        arr.push(data.charCodeAt(i))
    }

    $.ajax({
        url: '/web/user/'+selfID,
        type : "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType : 'json',
        data : JSON.stringify(arr),

        //
        success : function(result) {
            console.log(result);
        },
        error: function(xhr, resp, text) {
            console.log(xhr, resp, text);
        }
    })

    return false;
}
function setKeyEmail(data) {
    $.ajax({
        url: '/web/email/'+selfID,
        type : "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType : 'json',
        data : JSON.stringify(data),
        success : function(result) {
            console.log(result);
        },
        error: function(xhr, resp, text) {
            console.log(xhr, resp, text);
        }
    })

    return false;
}