package am.ik.moneyger4u.domain.service.user_details;

import org.springframework.security.core.context.SecurityContextHolder;

import am.ik.moneyger4u.domain.model.MoneygerUserDetails;

public class UserDetailsUtils {
    public static MoneygerUserDetails getUserDetails() {
        MoneygerUserDetails userDetails = (MoneygerUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return userDetails;
    }
}
