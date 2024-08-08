package hello.servlet.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//spring bean 의 이름을 그냥 url 패턴으로 맞춘다.
@Component("/springmvc/old-controller")
public class OldController implements Controller {
    //핸들러 매핑에서 핸들러(컨트롤러)를 찾을 수 있어야 한다.
    // 스프링 빈의 이름으로 핸들러를 찾을 수 있는 핸들러 매핑이 필요하다.


    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        // prefix, suffix 추가해서 view 찾게 한다.
        return new ModelAndView("new-form");
    }

}
