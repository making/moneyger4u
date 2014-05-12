package moneyger4u.app.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
