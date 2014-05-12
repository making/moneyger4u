package moneyger4u.domain.service.user;

import moneyger4u.domain.model.Family;
import moneyger4u.domain.model.User;
import moneyger4u.domain.repository.user.UserRepository;
import org.joda.time.DateTime;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    PasswordEncoder passwordEncoder;

    @Override
    public void save(User user, String rawPassword) {
        String password = passwordEncoder.encodePassword(rawPassword,
                user.getEmail());
        user.setPassword(password);
        Date now = new DateTime().toDate();
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(now);
        }
        user.setUpdatedAt(now);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findOne(Integer id) {
        User user = userRepository.findOne(id);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAll(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findByNameLike(String name, Pageable pageable) {
        String query = name + "%"; // TODO escape
        // Page<User> page = userRepository.findByNameLike(query, pageable);
        Page<User> page = userRepository.findAll(pageable);
        return page;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "user")
    public List<User> findByFamilyId(Family familyId) {
        return userRepository.findByFamilyId(familyId);
    }

}
