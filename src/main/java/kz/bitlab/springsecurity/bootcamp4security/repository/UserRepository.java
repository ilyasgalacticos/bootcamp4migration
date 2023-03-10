package kz.bitlab.springsecurity.bootcamp4security.repository;

import kz.bitlab.springsecurity.bootcamp4security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email); // SELECT * FROM t_users WHERE email = :email
}
