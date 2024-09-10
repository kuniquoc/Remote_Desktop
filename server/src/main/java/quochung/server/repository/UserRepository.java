package quochung.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quochung.server.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
