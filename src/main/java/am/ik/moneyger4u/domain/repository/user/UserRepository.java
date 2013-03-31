package am.ik.moneyger4u.domain.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import am.ik.moneyger4u.domain.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
