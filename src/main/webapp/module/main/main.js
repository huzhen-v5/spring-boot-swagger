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