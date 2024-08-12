package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * `RequestMappingHandlerMapping` 은 스프링 빈 중에서 `@RequestMapping` 또는 `@Controller` 가 클래스
 * 레벨에 붙어 있는 경우에 매핑 정보로 인식한다.
 * 두 어노테이션이 있어야 핸들러로 인식을 한다.
 */


//이건 자동으로 controller 를 등록이 된다. component scan 에 의해서 빈으로 등록이 된다.
//이 어노테이션이 있다면 -> RequestMappingHandlerMapping 에서 해당 핸들러를 조회할 수 있다.
@Controller
//@Component
//@RequestMapping
public class SpringMemberFormControllerV1 {// 등록된 빈의 이름은 이거랑 같지.



    //얘는 요청 정보를 매핑한다. -> 이 url 이 들어오면 메서드가 호출된다.
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
