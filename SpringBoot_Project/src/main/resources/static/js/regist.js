$(document).ready(function() {
    $('#regist').click(function (event) {
        event.preventDefault(); // 阻止按钮默认行为
        const userRegistName = $('input[name="userRegistName"]').val();
        const userRegistPass = $('input[name="userRegistPass"]').val();
        const userRegistCharge = $('input[name="userRegistCharge"]').val();

        // 定义并初始化 data 对象
        const data = {
            "user_name": userRegistName,
            "user_password": userRegistPass,
            "user_balance": userRegistCharge
        };
        // 将 data 对象序列化为 JSON 字符串并发送 POST 请求
        $.ajax({
            type: 'POST',
            url: '/user/regist',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (result) {
                if (result === 1) {
                    window.location.href = "login.html";
                } else {
                    alert("用户名已存在！");
                }
            },
            error: function () {
                alert("注册失败，请重新注册！");
            }
        });
    });
});
