// 页面初始化方法
$(document).ready(function(){
    // login(false);
});

function doLogin() {
    login(true);
}

var sessionId = '';

/**
 * 登陆方法
 * @param showAlter 失败是否报错
 * */
function login(showAlter) {
    var account = $('#accountInput').val();
    var ps = $('#psInput').val();
    // 请求
    $.ajax({
        type : 'POST',
        data: {
            account: account,
            password: ps
        },
        dataType: 'json',
        url : getCookieUrl_2("../../sLoginDemo/login.do"),
        success: function(res) {
            if (res.state == 0) {
                console.log(res.message);
                // location.href = '../sMain/sMain.html'

                // 通过localstorage保存sessionId
                supports_html5_storage() && localStorage.setItem('sessionId', res.sessionId);

                // sessionId = res.sessionId;

            } else {
                showAlter && alert('失败：' + res.message);
            }
        },
        error: function(err) {
            showAlter && alert('错误：' + err.message);
        }
    })
}

/**
 * 判断浏览器是否支持localstorage
 * */
function supports_html5_storage() {
    try {
        return 'localStorage' in window && window['localStorage'] !== null;
    } catch (e) {
        return false;
    }
}

/**
 * 通过判断客户端浏览器是否支持cookie来判断是否将jsessionid加入到url后
 * */
function getCookieUrl_2(url) {
    // window.navigator.cookieEnabled用于判断浏览器是否支持cookie
    if (!window.navigator.cookieEnabled && supports_html5_storage() && localStorage.getItem('sessionId')) {
        return url + ';jsessionid=' + localStorage.getItem('sessionId');
    }
    return url;
}

/**
 * 通过判断客户端浏览器是否支持cookie来判断是否将jsessionid加入到url后
 * */
function getCookieUrl(url) {
    if (!window.navigator.cookieEnabled && sessionId) {
        return url + ';jsessionid=' + sessionId;
    }
    return url;
}