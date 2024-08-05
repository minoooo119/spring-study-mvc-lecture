package hello.servlet.web.frontcontroller.v5.adpater;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        //어떤 헨들러를 지원하는지 알려준다.
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();//이게 view 를 위한 data 라고 생각하면 된다.

        String viewName = controller.process(paramMap, model);

        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }
//    이렇게 할필요가 없었네.
//    private static ModelView createModelView(String viewName, Map<String, Object> model) {
//        ModelView modelView = new ModelView(viewName);
//        model.forEach((key, value) -> modelView.getModel().put(key, value));
//        return modelView;
//    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> pramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> pramMap.put(paramName, request.getParameter(paramName)));
        return pramMap;
    }
}
