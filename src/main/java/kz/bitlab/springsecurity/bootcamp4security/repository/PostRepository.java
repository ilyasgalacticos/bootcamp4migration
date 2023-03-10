package kz.bitlab.springsecurity.bootcamp4security.repository;

import kz.bitlab.springsecurity.bootcamp4security.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByIdDesc();
}
