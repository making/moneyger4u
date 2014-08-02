package moneyger4u.app.common;

import moneyger4u.domain.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.inject.Inject;

@ControllerAdvice
public class UserDetailsControllerAdvice {
    @Inject
    UserService userService;

    @ModelAttribute("principal")
    UserDetails userDetails(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }
}
