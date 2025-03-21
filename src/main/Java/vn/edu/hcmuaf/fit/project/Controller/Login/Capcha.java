package vn.edu.hcmuaf.fit.project.Controller.Login;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/login")
public class Capcha extends HttpServlet {
    private static final String SECRET_KEY = "6LclbPsqAAAAAOTYoeYy-f1CTrRCIu1Id3MX18Sk"; // Thay bằng Secret Key của bạn

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

        // Kiểm tra CAPTCHA trước khi xử lý đăng nhập
        if (!verifyRecaptcha(gRecaptchaResponse)) {
            request.setAttribute("mess", "Captcha không hợp lệ!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Xử lý đăng nhập nếu CAPTCHA hợp lệ
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "123456".equals(password)) {
            request.getSession().setAttribute("user", username);
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("mess", "Sai tài khoản hoặc mật khẩu!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private boolean verifyRecaptcha(String gRecaptchaResponse) throws IOException {
        String url = "https://www.google.com/recaptcha/api/siteverify?secret=" + SECRET_KEY + "&response=" + gRecaptchaResponse;
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("POST");

        Scanner scanner = new Scanner(conn.getInputStream());
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();

        JSONObject jsonObject = new JSONObject(response);
        return jsonObject.getBoolean("success");
    }
}
