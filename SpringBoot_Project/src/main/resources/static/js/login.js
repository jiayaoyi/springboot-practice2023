$(document).ready(function() {
    $('#login').click(function (event) {
        event.preventDefault(); // 阻止按钮默认行为
        const user_name = $('input[name="user_name"]').val();
        const user_password = $('input[name="user_pass"]').val();

        // 定义并初始化 data 对象
        const data = {
            "user_name": user_name,
            "user_password": user_password,
        };

        // 将 data 对象序列化为 JSON 字符串并发送 POST 请求
        $.ajax({
            type: 'POST',
            url: '/user/login',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (data) {
                if (data === 1) {
                    window.location.href = "/shop/index";
                } else {
                    alert("账号或密码错误，请重新输入");
                }
            },
            error: function () {
                alert("网络错误");
            }
        });
    });
});
