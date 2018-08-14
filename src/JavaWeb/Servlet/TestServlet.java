package JavaWeb.Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().println("<a href=\"k.jsp\">hello</a>");
        //req.getRequestDispatcher("/someone.jsp").forward(req,resp);
        resp.sendRedirect("http://www.baidu.com");
    }
}
