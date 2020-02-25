function logout() {
    $.ajax({
        type : 'POST',
        dataType: 'json',
        url : "../../loginDemo/logout.do",
        success: function(res) {
            if (res.state == 0) {
                location.href = '../login/login.html'
            } else {
                alert('失败：' + res.message);
            }
        },
        error: function(err) {
            alert('错误：' + err.message);
        }
    })
}

// 更多js操作cookie的示例，可以访问：https://www.w3school.com.cn/js/js_cookies.asp

/**
 * 查看cookie
 * 如果Cookie设置了 httponly，则无法读取
 * */
function showCookie() {
    var cookie = document.cookie;
    console.log(cookie);
}

/**
 * 清除cookie
 * 就是重新设置指定cookie值为空
 * */
function clearAllCookie() {
    setCookie("account", "", 0, "/sbs");
    // setCookie("ssid", "", -1)
}

/**
 * 获取当前账户名
 * */
function getAccount() {
    alert(getCookie("account"));
}

/**以下封装的cookie操作函数**/

function setCookie(cname, cvalue, exdays, path) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=" + path;
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name)  == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}