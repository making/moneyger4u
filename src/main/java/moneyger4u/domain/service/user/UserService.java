package moneyger4u.domain.service.user;

import moneyger4u.domain.model.Family;
import moneyger4u.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.List;

public interface UserService {

    void save(User user, String password);

    Page<User> findAll(Pageable pageable);

    User findOne(Integer id);

    void delete(User user);

    Page<User> findByNameLike(String query, Pageable pageable);

    List<User> findByFamilyId(Family familyId);

    User getLoginUser(Principal principal);
}
