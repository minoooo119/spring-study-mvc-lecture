package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

    public FrontControllerServletV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
        //이렇게 프론트 컨트롤러로 와서 어떤 컨트롤러를 불러줄지 요청을 넘겨준다.
    }



    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        String requestURI = request.getRequestURI();

        //해당 URI 에 맞는 컨트롤러를 뽑아낸다.
        ControllerV3 controllerV3 = controllerV3Map.get(requestURI);
        if (controllerV3 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("text/plain");
            response.getWriter().write("no controller");
            return;
        }
        System.out.println("controllerV3 = " + controllerV3);
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controllerV3.process(paramMap);//controller 가 ModelView 객체를 반환
        //여기서 요청 param 에 대해서도 req. set attribute 해야함

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);// model 도 view 에 담아줘야함.

        view.render(mv.getModel(),request, response); // 해당 view 객체가 render 를 통해 뷰를 보여주게 된다.
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName+".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator().forEachRemaining(paramName ->{
            System.out.println("paramName = " + paramName);
            paramMap.put(paramName, request.getParameter(paramName));
            System.out.println("request.getParameter(paramName) = " + request.getParameter(paramName));
        });
        return paramMap;
    }
}
