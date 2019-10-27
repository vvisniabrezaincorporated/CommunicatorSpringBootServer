package pl.wnb.communicator.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import pl.wnb.communicator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "update Users set public_key = :public_key where user_id = :id", nativeQuery = true)
    void setKey(@Param("public_key") byte[] public_key, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update Users set key_email = :public_key_email where user_id = :id", nativeQuery = true)
    void setKeyEmail(@Param("public_key_email") String public_key_email, @Param("id") Long id);


}
