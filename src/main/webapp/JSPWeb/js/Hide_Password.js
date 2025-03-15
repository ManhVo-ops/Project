
const passwordLogin = (inputPass, InputIcon) => {
    const input = document.getElementById(inputPass),
        iconEye = document.getElementById(InputIcon)
    iconEye.addEventListener('click', () => {
        if (input.type === 'password') {
            input.type = 'text'; // Chuyển thành dạng hiển thị
            iconEye.classList.remove('ri-eye-off-fill');
            iconEye.classList.add('ri-eye-fill'); // Đổi biểu tượng
        } else {
            input.type = 'password'; // Chuyển thành dạng ẩn
            iconEye.classList.remove('ri-eye-fill');
            iconEye.classList.add('ri-eye-off-fill'); // Trở về biểu tượng ban đầu
        }
    });
}
// hide_Show Password login form
passwordLogin('Pass_Login', "Icon_Login")

// const passwordRegister = (inputPass, InputIcon) => {
//     const input = document.getElementById(inputPass),
//         iconEye = document.getElementById(InputIcon)
//     iconEye.addEventListener('click', () => {
//         if (input.type === 'password') {
//             input.type = 'text'; // Chuyển thành dạng hiển thị
//             iconEye.classList.remove('ri-eye-off-fill');
//             iconEye.classList.add('ri-eye-fill'); // Đổi biểu tượng
//         } else {
//             input.type = 'password'; // Chuyển thành dạng ẩn
//             iconEye.classList.remove('ri-eye-fill');
//             iconEye.classList.add('ri-eye-off-fill'); // Trở về biểu tượng ban đầu
//         }
//     });
// }
// hide_Show Password Register form
passwordLogin('Pass_Register', "Icon_Register")
passwordLogin('RePass_Register', "ReIcon_Register")

// Giao Thoa
const LoginAccess = document.getElementById('LoginAccess'),
      switchToRegister = document.getElementById('switchToRegister'),
      switchToLogin = document.getElementById('switchToLogin');

// Chuyển sang biểu mẫu đăng ký
switchToRegister.addEventListener('click', (e) => {
    e.preventDefault(); // Ngăn chặn việc tải lại trang
    LoginAccess.classList.add('active');
});

// Chuyển sang biểu mẫu đăng nhập
switchToLogin.addEventListener('click', (e) => {
    e.preventDefault(); // Ngăn chặn việc tải lại trang
    LoginAccess.classList.remove('active');
});

function loginFacebook() {
    FB.login(function(response) {
        if (response.authResponse) {
            console.log('Đăng nhập thành công!', response);
            getUserInfo();
        } else {
            console.log('Người dùng từ chối đăng nhập.');
        }
    }, {scope: 'email,public_profile'}); // Yêu cầu quyền truy cập email và thông tin công khai
}

// Lấy thông tin người dùng sau khi đăng nhập
function getUserInfo() {
    FB.api('/me', {fields: 'id, name, email, picture'}, function (response) {
        console.log('Thông tin người dùng:', response);

        // Chuẩn bị dữ liệu để gửi
        const userData = {
            id: response.id,
            name: response.name,
            email: response.email,
            picture: response.picture.data.url
        };

        // Gửi dữ liệu qua Servlet bằng Fetch API
        fetch("loginFacebook", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userData)
        })
            .then(response => response.text())
            .then(data => {
                console.log("Phản hồi từ server:", data);
            })
            .catch(error => console.error("Lỗi khi gửi dữ liệu:", error));

        // Hiển thị thông tin lên giao diện
        document.getElementById('userInfo').innerHTML =
            `<p>Chào, ${response.name}!</p>
             <p>Email: ${response.email}</p>
         
}    <img src="${response.picture.data.url}" alt="Avatar">`;
    });
}


