package iuh.fit.se.Baitaptuan1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(
        name = "register",
        value = "/register"
)
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp, "GET");
        String htmlPath = getServletContext().getRealPath("/register.html");

        try (PrintWriter out = resp.getWriter()) {
            if (htmlPath != null) {
                String htmlContent = Files.readString(Paths.get(htmlPath));
                out.println(htmlContent);
            } else
                out.println("<h1>Page not found!</h1>");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Html file not found!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp, "POST");
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp, String method) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password"); // sẽ không hiển thị
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        String[] hobbies = req.getParameterValues("hobby");
        String description = req.getParameter("description");

        var out = resp.getWriter();
        out.println("<h2>Kết quả đăng ký (" + method + ")</h2>");

        if (username == null || email == null || gender == null || username.isEmpty() || email.isEmpty() || gender.isEmpty()) {
            out.println("<p style='color:red;'>Vui lòng nhập đầy đủ thông tin!</p>");
        } else {
            out.println("<p>Username: " + username + "</p>");
            out.println("<p>Email: " + email + "</p>");
            out.println("<p>Giới tính: " + gender + "</p>");

            if (hobbies != null) {
                out.print("<p>Sở thích: ");
                for (String h : hobbies) {
                    out.print(h + " ");
                }
                out.println("</p>");
            }

            out.println("<p>Mô tả bản thân: " + description + "</p>");
            out.println("<p><i>(Password không hiển thị để bảo mật)</i></p>");
        }
    }
}
