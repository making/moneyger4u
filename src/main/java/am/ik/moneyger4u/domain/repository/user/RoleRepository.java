package am.ik.moneyger4u.domain.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import am.ik.moneyger4u.domain.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
