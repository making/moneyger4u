package am.ik.moneyger4u.domain.service.user_details;

import java.security.Principal;

import org.springframework.security.core.Authentication;

import am.ik.moneyger4u.domain.model.MoneygerUserDetails;

public class UserDetailsUtils {
    public static MoneygerUserDetails getUserDetails(Principal principal) {
        MoneygerUserDetails details = (MoneygerUserDetails) ((Authentication) principal)
                .getPrincipal();
        return details;
    }
}
