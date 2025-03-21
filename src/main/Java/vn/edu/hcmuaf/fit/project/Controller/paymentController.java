package vn.edu.hcmuaf.fit.project.Controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.hcmuaf.fit.project.DAO.dao.OrderDAO;
import vn.edu.hcmuaf.fit.project.DAO.dao.OrderDetailDAO;
import vn.edu.hcmuaf.fit.project.DAO.model.CartItem;
import vn.edu.hcmuaf.fit.project.DAO.model.Order;
import vn.edu.hcmuaf.fit.project.DAO.model.User;
import vn.edu.hcmuaf.fit.project.DAO.model.orderDetail;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "PaymentController", value = "/PaymentController")
public class paymentController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (currentUser == null || cart == null || cart.isEmpty()) {
            response.sendRedirect("CartShopping.jsp");
            return;
        }

        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));

        Order order = new Order();
        order.setUserID(currentUser);
        order.setOrderDate(new Date());
        order.setStatus("Processing");
        order.setTotalAmount(totalAmount);
        int orderId = OrderDAO.getInstance().insertOrder(order);

        for (CartItem item : cart) {
            orderDetail detail = new orderDetail();
            detail.setOrderID(order);
            detail.setProductID(item.getProduct());
            detail.setQuantity(item.getQuantity());
            detail.setPrice(item.getProduct().getPrice());
            OrderDetailDAO.getInstance().insertOrderDetail(detail);
        }

        session.removeAttribute("cart");
        response.sendRedirect("OrderSuccess.jsp?orderId=" + orderId);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}