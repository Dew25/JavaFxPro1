package ee.ivkhkdev.javafxpro1.repository;

import ee.ivkhkdev.javafxpro1.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
}
