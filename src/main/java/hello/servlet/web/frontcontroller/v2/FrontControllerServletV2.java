package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerV2Map = new HashMap<>();

    public FrontControllerServletV2() {
        controllerV2Map.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerV2Map.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerV2Map.put("/front-controller/v2/members", new MemberListControllerV2());
        //이렇게 프론트 컨트롤러로 와서 어떤 컨트롤러를 불러줄지 요청을 넘겨준다.
    }



    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        String requestURI = request.getRequestURI();

        //해당 URI 에 맞는 컨트롤러를 뽑아낸다.
        ControllerV2 controllerV2 = controllerV2Map.get(requestURI);
        if (controllerV2 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        System.out.println("controllerV2 = " + controllerV2);
        MyView view = controllerV2.process(request, response);
        view.render(request, response);
    }
}
