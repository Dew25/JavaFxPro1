package ee.ivkhkdev.javafxpro1.model.repository;

import ee.ivkhkdev.javafxpro1.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
