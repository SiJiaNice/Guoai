package web;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import org.springframework.web.servlet.DispatcherServlet;

@WebServlet(value="/",
initParams=@WebInitParam(name="contextConfigLocation",
value="classpath:spring-mvc.xml"),
loadOnStartup=1)
public class SpringMVCServlet extends DispatcherServlet {

}
