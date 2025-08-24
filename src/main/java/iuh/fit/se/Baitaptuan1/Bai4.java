package iuh.fit.se.Baitaptuan1;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Bai4 extends HttpServlet {
    private String author;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        author = config.getInitParameter("author"); // lấy init-param từ web.xml
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        // Lấy context-param (global)
        ServletContext context = getServletContext();
        String appName = context.getInitParameter("appName");

        resp.getWriter().println("<h2>Thông tin ứng dụng</h2>");
        resp.getWriter().println("<p>App Name (context-param): " + appName + "</p>");
        resp.getWriter().println("<p>Tác giả (init-param): " + author + "</p>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=UTF-8");
        resp.getWriter().println("Dữ liệu POST đã nhận!");
    }
}
