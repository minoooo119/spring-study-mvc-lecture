package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "responseJsonServet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private final ObjectMapper objectMapper= new ObjectMapper();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloData helloData = new HelloData();
        helloData.setAge(20);
        helloData.setUsername("mino");

        //{"username" : "mino" , "age":20}
        String s = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(s);
    }
}
