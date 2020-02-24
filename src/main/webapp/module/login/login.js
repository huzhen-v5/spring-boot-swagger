// 页面初始化方法
$(document).ready(function(){
    login(false);
});

function doLogin() {
    login(true);
}

/**
 * 登陆方法
 * @param showAlter 失败是否报错
 * */
function login(showAlter) {
    var account = $('#accountInput').val();
    var ps = $('#psInput').val();
    var timeout = $('#timeRadios > input:checked').val();
    // 请求
    $.ajax({
        type : 'POST',
        data: {
            account: account,
            password: ps,
            timeout: timeout
        },
        dataType: 'json',
        url : "../../loginDemo/login.do",
        success: function(res) {
            if (res.state == 0) {
                console.log(res.message);
                location.href = '../main/main.html'
            } else {
                showAlter && alert('失败：' + res.message);
            }
        },
        error: function(err) {
            showAlter && alert('错误：' + err.message);
        }
    })
}