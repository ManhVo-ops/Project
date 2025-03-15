package vn.edu.hcmuaf.fit.project.Controller.Login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import vn.edu.hcmuaf.fit.project.DAO.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loginFacebook" , urlPatterns = "/loginFacebook")
public class LoginFacebook extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Đọc dữ liệu JSON từ request
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        // Chuyển đổi JSON thành đối tượng
        JSONObject json = new JSONObject(sb.toString());
        String id = json.getString("id");
        String name = json.getString("name");
        String email = json.getString("email");
        String picture = json.getString("picture");
        User user = new User();
        user.setEmail(email);
        user.setUsername(name);
        user.setPassword("null");
        // Lưu vào session
        request.getSession().setAttribute("acc", user);


        // Trả về phản hồi cho client
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject resJson = new JSONObject();
        resJson.put("status", "success");
        resJson.put("message", "Đăng nhập Facebook thành công!");
        out.print(resJson.toString());
        out.flush();
    }

}
