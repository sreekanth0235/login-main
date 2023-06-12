package farmhub.login.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import farmhub.login.entity.Login;

public interface LoginRepository extends CrudRepository<Login, Long> {
	List<Login> findByEmail(String email);

	List<Login> findByUserName(String userName);

	List<Login> findByPhoneNumberOrUserNameOrEmail(Long PhoneNumber, String UserName, String Email);

	List<Login> findByPhoneNumberAndUserNameAndEmail(Long PhoneNumber, String UserName, String Email);

}
