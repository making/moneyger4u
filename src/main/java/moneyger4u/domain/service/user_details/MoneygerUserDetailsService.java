package moneyger4u.domain.service.user_details;

import moneyger4u.domain.model.MoneygerUserDetails;
import moneyger4u.domain.model.Role;
import moneyger4u.domain.model.User;
import moneyger4u.domain.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class MoneygerUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory
            .getLogger(MoneygerUserDetailsService.class);

    @Inject
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findDetailByEmail(username);
        logger.debug("username={},user={}", username, user);
        if (user == null) {
            throw new UsernameNotFoundException(username + " is not found.");
        }
        user.getFamilyId().getFamilyName(); // eager fetch
        List<Role> roles = user.getRoleList();
        if (roles != null) {
            for (Role role : roles) {
                role.getRoleName(); // eager fetch
            }
        }
        return new MoneygerUserDetails(user);
    }

}
