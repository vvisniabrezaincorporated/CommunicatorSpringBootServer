function registerUser() {
        function getFormData($form){
            var unindexed_array = $form.serializeArray();
            var indexed_array = {};

            $.map(unindexed_array, function(n, i){
                indexed_array[n['name']] = n['value'];
            });

            return indexed_array;
        }
        var $form = $("#register_user_form");
        var data = getFormData($form);


    $.ajax({
        url: '/register/new',
        type : "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType : 'json',
        data : JSON.stringify(data),
        success : function(result) {
            alert("Registration success!");
            window.location.href = '/';
        },
        error: function(xhr, resp, text) {
            console.log(xhr, resp, text);
        }
    })

        return false;
    }
