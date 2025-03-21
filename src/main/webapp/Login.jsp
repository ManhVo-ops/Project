<%--
  Created by IntelliJ IDEA.
  User: zenry
  Date: 23/12/2024
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
            integrity="sha384-rOA1PnstxnOBLzCLMcre8ybwbTmemjzdNlILg8O7z1lUkLXozs4DHonlDtnE7fpc"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
          integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- icon -->
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/4.5.0/remixicon.css" rel="stylesheet">
    <!--Link CSS  -->

    <link rel="stylesheet" href="./JSPWeb/CSS/CSS_Login.css">
    <title>Login_Register_Page</title>
</head>

<body>
<div class="contain_login_Register" id="LoginAccess">
    <div class="wrapper_Login">
        <form action="login" >
            <h1>Đăng nhập tài khoản</h1><br>

<%--            <p class="text-danger" style="text-align: center; color: #9b2805">${mess}</p>--%>

            <%
                String message = (String) request.getAttribute("mess");
            %>
            <p class="text-danger"
               style="text-align: center; color: #9b2805; display: <%= (message != null && !message.trim().isEmpty()) ? "block" : "none" %>;">
                <%= (message != null) ? message : "" %>
            </p>

            <div class="inputBox">
                <input name="username" type="text" placeholder="Tên Đăng Nhập" required>
                <i class="ri-mail-fill"></i>
            </div>
            <div class="inputBox">
                <input name="password" type="password" id="Pass_Login" placeholder="Mật Khẩu" required>
                <i class="ri-eye-off-fill" id="Icon_Login" style="right: 5%; cursor: pointer;"></i>
            </div>
            <!-- Google reCAPTCHA -->
            <div class="g-recaptcha" data-sitekey="6LclbPsqAAAAAFBNZP1-Gv6O4DmJXxRrWLrtInpX"></div>

            <div class="remember_forgot">
                <label><input type="checkbox">Nhớ tôi</label>
                <a href="./ResetPass.jsp">Bạn Quên Mật Khẩu?</a>
            </div>
            <button type="submit" class="btn">Đăng Nhập</button>
            <div class="mxh_register">
                <i onclick="loginFacebook()"  class="fa-brands fa-facebook" style="color: #005eff; font-size: 30px;"></i>
                <i class="fa-brands fa-google" style="color: #ff0000; font-size: 30px;"></i>
            </div>

            <div class="register">
                <p>
                    Bạn Chưa Có Tài Khoản?
                    <a href="#" id="switchToRegister"> Đăng Ký </a>
                    <a><img src=""></a>
                </p>
            </div>
        </form>
    </div>
    <!-- Thêm script reCAPTCHA -->
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>

    <div class="wrapper_Register">
        <form action="login">
            <h1>Đăng ký tài khoảng</h1>

<%--            <div class="Name">--%>
<%--                <div class="first_name">--%>
<%--                    <input type="text" placeholder="Tên" required>--%>
<%--                </div>--%>
<%--                <div class="last_name">--%>
<%--                    <input type="text" placeholder="Tên Đệm" required>--%>
<%--                </div>--%>
<%--            </div>--%>

            <div class="inputBox_register">
                <input name="Signusername" type="text" placeholder="Tên Đăng Nhập" required>
                <i class="ri-mail-fill"></i>
            </div>
            <div class="inputBox_register">
                <input name="Signpassword" type="password" id="Pass_Register" placeholder="Mật Khẩu" required>
                <i class="ri-eye-off-fill" id="Icon_Register"></i>
            </div>
            <div class="inputBox_register">
                <input name="repass" type="password" id="RePass_Register" placeholder="Nhập lại mật Khẩu" required>
                <i class="ri-eye-off-fill" id="ReIcon_Register"></i>
            </div>
            <button type="submit" class="btn">Tạo Tài Khoản</button>

            <div class="login">
                <p>
                    Bạn Đã Có Tài Khoản?
                    <a href="#" id="switchToLogin" > Đăng nhập </a>

                </p>


            </div>
        </form>
    </div>

</div>
<script src="./JSPWeb/js/Hide_Password.js"></script>
<script>
    window.fbAsyncInit = function() {
        FB.init({
            appId      : 'YOUR_APP_ID', // Thay YOUR_APP_ID bằng App ID của bạn
            cookie     : true,
            xfbml      : true,
            version    : 'v18.0' // Phiên bản API mới nhất
        });

        FB.AppEvents.logPageView();
    };

    (function(d, s, id){
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) {return;}
        js = d.createElement(s); js.id = id;
        js.src = "https://connect.facebook.net/en_US/sdk.js";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
</script>

</body>
</html>
