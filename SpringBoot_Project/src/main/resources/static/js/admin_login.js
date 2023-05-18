$(document).ready(function() {
    // 在页面加载时获取验证码
    function getCaptcha() {
        $.ajax({
            type: 'POST',
            url: '/admin/captcha',
            success: function() {
                // 在AJAX请求成功后更新验证码图片
                $('#captcha-image').attr('src', '/admin/captcha?'+ new Date().getTime());
            }
        });
    }

    // 在页面加载时获取验证码
    getCaptcha();

    $('#admin-login').click(function (event) {
        event.preventDefault(); // 阻止按钮默认行为
        const admin_name = $('input[name="admin_name"]').val().trim();
        const admin_password = $('input[name="admin_pass"]').val().trim();
        const admin_captcha = $('input[name="admin_captcha"]').val().trim();

        // 检查管理员名字、密码和验证码是否为空
        if (admin_name === "" || admin_password === "" || admin_captcha === "") {
            alert("Admin name, password, and captcha cannot be empty");
            return;
        }

        // 定义并初始化 data 对象
        const data = {
            "admin_name": admin_name,
            "admin_password": admin_password,
            "admin_captcha": admin_captcha
        };

        // 将 data 对象序列化为 JSON 字符串并发送 POST 请求
        $.ajax({
            type: 'POST',
            url: '/admin/login',
            data: JSON.stringify(data),
            contentType: 'application/json',
            success: function (response) {
                if (response === "success") {
                    window.location.href = "/admin/init";
                } else if (response === "noadminname") {
                    alert("管理员名不存在");
                } else if (response === "wrongpassword") {
                    alert("密码不正确，请重新输入");
                } else if (response === "wrongcaptcha") {
                    alert("验证码错误，请重新输入");
                    // 如果验证码错误，刷新验证码
                    $('#captcha-image').attr('src', '/admin/captcha?'+ new Date().getTime());
                } else {
                    alert("网络错误，请重试");
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 400) {
                    alert("请求错误: " + errorThrown);
                } else {
                    alert("网络错误 " + errorThrown);
                }
            }
        });
    });

    $('#refresh-captcha').click(function (event) {
        event.preventDefault(); // 阻止按钮默认行为
        $('#captcha-image').attr('src', '/admin/captcha?'+ new Date().getTime());
    });


});
