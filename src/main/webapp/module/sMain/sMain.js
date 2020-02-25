function logout() {
    $.ajax({
        type : 'POST',
        dataType: 'json',
        url : "../../sLoginDemo/logout.do",
        success: function(res) {
            if (res.state == 0) {
                location.href = '../sLogin/sLogin.html'
            } else {
                alert('失败：' + res.message);
            }
        },
        error: function(err) {
            alert('错误：' + err.message);
        }
    })
}