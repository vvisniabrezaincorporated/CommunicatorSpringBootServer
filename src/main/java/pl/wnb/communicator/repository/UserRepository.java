package pl.wnb.communicator.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.wnb.communicator.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}