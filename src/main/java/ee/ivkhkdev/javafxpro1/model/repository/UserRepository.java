package ee.ivkhkdev.javafxpro1.model.repository;

import ee.ivkhkdev.javafxpro1.model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
}
