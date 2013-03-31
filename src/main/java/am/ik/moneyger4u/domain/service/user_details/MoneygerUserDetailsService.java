package am.ik.moneyger4u.domain.service.user_details;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import am.ik.moneyger4u.domain.model.MoneygerUserDetails;
import am.ik.moneyger4u.domain.model.User;
import am.ik.moneyger4u.domain.repository.user.UserRepository;

@Service
public class MoneygerUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory
            .getLogger(MoneygerUserDetailsService.class);

    @Inject
    protected UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        logger.debug("username={},user={}", username, user);
        if (user == null) {
            throw new UsernameNotFoundException(username + " is not found.");
        }
        user.getFamilyId().getFamilyName(); // eager fetch
        return new MoneygerUserDetails(user);
    }

}
