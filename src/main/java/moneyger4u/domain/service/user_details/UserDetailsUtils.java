package moneyger4u.domain.service.user_details;

import moneyger4u.domain.model.MoneygerUserDetails;
import org.springframework.security.core.Authentication;

import java.security.Principal;

public class UserDetailsUtils {
    public static MoneygerUserDetails getUserDetails(Principal principal) {
        MoneygerUserDetails details = (MoneygerUserDetails) ((Authentication) principal)
                .getPrincipal();
        return details;
    }
}
