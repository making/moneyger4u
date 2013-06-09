package am.ik.moneyger4u.app.home;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "home/index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "home/login";
    }

    @RequestMapping(value = "/env")
    @ResponseBody
    public Map<String, String> env() {
        return System.getenv();
    }
}
