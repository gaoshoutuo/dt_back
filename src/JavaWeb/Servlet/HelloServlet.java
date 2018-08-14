package JavaWeb.Servlet;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class HelloServlet extends HttpServlet {// 到时候在开放socket的时候 也要开放这个servlet 的web
    private final Logger logger = Logger.getLogger(this.getClass());
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        //ContextLoaderListener contextLoaderListener
        try {
            String str1="C:\\Users\\Administrator\\IdeaProjects\\DTSJ\\src\\JavaWeb\\Html\\jiegou.html";
            String str2="C:\\Users\\Administrator\\IdeaProjects\\DTSJ\\src\\JavaWeb\\Html\\someone.jsp";
            FileInputStream fileInputStream=new FileInputStream(new File(str2));
            int len=0;byte []html=new byte[1024];StringBuilder sb=new StringBuilder();

            while ((len=fileInputStream.read(html))!=-1){
                sb.append(new String(html,0,len));
            }
           // response.getWriter().println(request.getRealPath("/")+"WEB-INF\\classes\\JavaWeb\\Servlet\\someone.jsp");//很奇怪呀 我之前怎么就显示不出来呢
            //response.getWriter().println(sb.toString());
            request.getRequestDispatcher("WEB-INF/classes/JavaWeb/Servlet/someone.jsp").forward(request,response);//终于ok 了 打通servlet 与jsp struct而不是hibernate 互联网公司都抛弃了
            //response.sendRedirect("someone.jsp");
            //既然部署在tomcat 容器里面就是在tomcat 里找
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
