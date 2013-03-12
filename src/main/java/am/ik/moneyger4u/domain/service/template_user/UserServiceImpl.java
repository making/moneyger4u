package am.ik.moneyger4u.domain.service.template_user;

import java.util.Date;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import am.ik.moneyger4u.domain.model.TemplateUser;
import am.ik.moneyger4u.domain.repository.template_user.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    protected UserRepository userRepository;

    @Inject
    protected PasswordEncoder passwordEncoder;

    @Override
    public void save(TemplateUser user, String rawPassword) {
        String password = passwordEncoder.encodePassword(rawPassword, user
                .getName());
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
    public TemplateUser findOne(Integer id) {
        TemplateUser user = userRepository.findOne(id);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TemplateUser> findAll(Pageable pageable) {
        Page<TemplateUser> page = userRepository.findAll(pageable);
        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TemplateUser> findByNameLike(String name, Pageable pageable) {
        String query = name + "%"; // TODO escape
        Page<TemplateUser> page = userRepository.findByNameLike(query, pageable);
        return page;
    }

    @Override
    public void delete(TemplateUser user) {
        userRepository.delete(user);
    }

}
