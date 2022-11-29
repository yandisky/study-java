package org.example.tomcat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/signin")
public class SignInServlet extends HttpServlet {
    //模拟一个数据库，由于Map.of在Java 9中才使用，所以JDK要大于Java 8
    private Map<String, String> users = Map.of("bob", "bob123", "alice", "alice123", "tom", "tomcat");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("<h1>Sign In</h1>");
        printWriter.write("<form action=\"/signin\" method=\"post\">");
        printWriter.write("<p>Username: <input name=\"username\"></p>");
        printWriter.write("<p>Password: <input name=\"password\" type=\"password\"></p>");
        printWriter.write("<p><button type=\"submit\">Sign In</button> <a href=\"/\">Cancel</a></p>");
        printWriter.write("</form>");
        printWriter.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String expectedPassword = users.get(username.toLowerCase());
        if (expectedPassword != null && expectedPassword.equals(password)) {
            req.getSession().setAttribute("user", username);
            resp.sendRedirect("/");
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
