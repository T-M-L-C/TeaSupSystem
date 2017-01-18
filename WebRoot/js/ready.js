function exit() {
    if (getCookieValue("username") != "") {
        $.ajax({
            type: "post",
            url: "Login",
            data: {
                action: 'logout',
                username: getCookieValue("username")
            },
            success: function(data) {
                var json = jQuery.parseJSON(data);
                if (json.id == 0) {
                    deleteCookie("username", "/");
                    deleteCookie("password", "/");
                    deleteCookie("teacher", "/");
                    window.location.href = "HomeNews";
                    return true;
                }
            }
        });
    } else {
        deleteCookie("username", "/");
        deleteCookie("password", "/");
        deleteCookie("teacher", "/");
        window.location.href = "HomeNews";
        return true;
    }
}

function islogin(teacher) {
    if (getCookieValue("username") != "" && getCookieValue("password") != "") {
        var tv = getCookieValue("teacher");
        if ((tv != "" && tv == teacher) || ((teacher == 1 || teacher == 2) && (tv == 1 || tv == 2))) {
            $.ajax({
                type: "post",
                url: "Login",
                async: false,
                data: {
                    "action": "vlogin",
                    "teacher": getCookieValue("teacher"),
                    "uname": getCookieValue("username"),
                    "passwd": getCookieValue("password")
                },
                dataType: "json",
                success: function(data) {
                    if (data == true) {
                        return data;
                    } else {
                        window.location.href = "HomeNews";
                        return false;
                    }
                }
            });
        } else {
            alert("你不具备访问本页面的权限");
            window.location.href = "HomeNews";
            return false;
        }
    } else {
        window.location.href = "HomeNews";
        return false;
    }
}