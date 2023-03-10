package kz.bitlab.springsecurity.bootcamp4security.repository;

import kz.bitlab.springsecurity.bootcamp4security.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Permission findByRole(String role);
}
