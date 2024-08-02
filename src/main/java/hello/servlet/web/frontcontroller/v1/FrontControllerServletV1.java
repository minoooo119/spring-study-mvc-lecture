package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.tags.shaded.org.apache.regexp.RE;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//v1 하위는 다 이 서블릿이 호출되게 해준다.
@WebServlet(name = "frontControllerServlet", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerV1Map = new HashMap<>();

    public FrontControllerServletV1() {
        controllerV1Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerV1Map.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerV1Map.put("/front-controller/v1/members", new MemberListControllerV1());
        //이렇게 프론트 컨트롤러로 와서 어떤 컨트롤러를 불러줄지 요청을 넘겨준다.
    }



    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");//실제로는 log 로 찍는 것이 좋음

        String requestURI = request.getRequestURI();

        //해당 URI 에 맞는 컨트롤러를 뽑아낸다.
        ControllerV1 controllerV1 = controllerV1Map.get(requestURI);
        if (controllerV1 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        System.out.println("controllerV1 = " + controllerV1);
        controllerV1.process(request, response);
    }
}
