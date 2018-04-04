




    $.ajax({
        url: "http://127.0.0.1:8080/rest/checkuser/check",
        dataType: "String",
        data: "kostya",
        type: "GET",
        success: function(){
            console.log("запрос ушел");
        }

    });


