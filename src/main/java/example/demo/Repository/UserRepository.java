package example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import example.demo.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	public UserEntity findByUserName(String username);
	@Query(value = "select * from users as u where u.email = :email", nativeQuery = true)
	public UserEntity findByEmail(String email) ;
}
