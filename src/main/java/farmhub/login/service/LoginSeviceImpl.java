package farmhub.login.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import farmhub.login.dto.LoginDTO;
import farmhub.login.entity.Login;
import farmhub.login.exception.FarmHubException;
import farmhub.login.repository.LoginRepository;

@Service(value = "login-service")
@Transactional
public class LoginSeviceImpl implements LoginService {
	@Autowired(required = true)
	private LoginRepository loginRepository;

	private Login setEntity(LoginDTO loginDTO) {

		Login login1 = new Login();
		login1.setUserName(loginDTO.getUserName());
		login1.setPassword(loginDTO.getPassword());
		login1.setPhoneNumber(loginDTO.getPhoneNumber());
		login1.setEmail(loginDTO.getEmail());
		// Login login2 = loginRepository.save(login1);

		// return login2.getPhoneNumber();
		return login1;
	}

	@Override
	public String validLogin(LoginDTO loginDTO) throws FarmHubException {
		Optional<Login> optional = loginRepository.findById(loginDTO.getPhoneNumber());
		if (optional.isEmpty())
			throw new FarmHubException("Service.NOT_REGISTERED");
		Login login = optional.get();
		if (loginDTO.getPhoneNumber().equals(login.getPhoneNumber())
				&& loginDTO.getPassword().equals(login.getPassword()))
			return "Login Successful";
		else
			throw new FarmHubException("Service.INVALID_CREDENTILAS");

	}
//	@Override
//	public String validLogin2(LoginDTO loginDTO) throws FarmHubException {
//		if(loginDTO.getPhoneNumber()!=null) {
//			Optional<Login> optional = loginRepository.findById(loginDTO.getPhoneNumber());
//			if (optional.isEmpty())
//				throw new FarmHubException("Service.NOT_REGISTERED");
//			Login login = optional.get();
//			if (loginDTO.getPhoneNumber().equals(login.getPhoneNumber())
//					&& loginDTO.getPassword().equals(login.getPassword()))
//				return "Login Successful";
//			else
//				throw new FarmHubException("Service.INVALID_CREDENTILAS");
//
//		
//		}
//		if(loginDTO.getEmail()!=null)
//		return null;
//	}

	@Override
	public String registerUser(LoginDTO loginDTO) throws FarmHubException {
		Iterable<Login> loginList = loginRepository.findAll();
		for (Login login : loginList) {

			if (login.getPhoneNumber() != null && loginDTO.getPhoneNumber() != null)
				if (loginDTO.getPhoneNumber().equals(login.getPhoneNumber()))
					throw new FarmHubException("Service.REGISTERED_PHONENUMBER");

			if (login.getEmail() != null && loginDTO.getEmail() != null)
				if (loginDTO.getEmail().equals(login.getEmail()))
					throw new FarmHubException("Service.REGISTERED_EMAIL");

			if (login.getUserName() != null && loginDTO.getUserName() != null)
				if (loginDTO.getUserName().equals(login.getUserName()))
					throw new FarmHubException("Service.REGISTERED_USER_NAME");

		}

		Login login1 = setEntity(loginDTO);
		loginRepository.save(login1);

		return "Login Successful";

	}

	@Override
	public String validLogin2(LoginDTO loginDTO) throws FarmHubException {
		List<Login> login2 = loginRepository.findByPhoneNumberOrUserNameOrEmail(loginDTO.getPhoneNumber(),
				loginDTO.getUserName(), loginDTO.getEmail());
		
		if (login2.isEmpty()) {
		
			throw new FarmHubException("Service.USER_NOT_REGISTERED");
		} else {

			for (Login login : login2) {
				// System.out.println("check");
				if (loginDTO.getUserName() != null) {
					if (!(login.getUserName().equalsIgnoreCase(loginDTO.getUserName())

							&& loginDTO.getPassword().equals(login.getPassword())))
						throw new FarmHubException("Sevice.CRDENTILAS_MISMATCH_USERNAME");
				} else if (loginDTO.getEmail() != null) {
					if (!(loginDTO.getEmail().equalsIgnoreCase(login.getEmail())

							&& loginDTO.getPassword().equals(login.getPassword())))
						throw new FarmHubException("Sevice.CRDENTILAS_MISMATCH_EMAIL");
				} else if (loginDTO.getPhoneNumber() != null) {
					if (!(loginDTO.getPhoneNumber().equals(login.getPhoneNumber())

							&& loginDTO.getPassword().equals(login.getPassword())))
						throw new FarmHubException("Sevice.CRDENTILAS_MISMATCH_PHONE");
				}
//
			}
			// Login login = optional.get();

			if (loginDTO.getEmail() != null)

				return "Login Successful with your email :" + loginDTO.getEmail();
			else if (loginDTO.getPhoneNumber() != null)
				return "Login Successful with your phone Number :" + loginDTO.getPhoneNumber();
			else
				return "login successfull with your user name :" + loginDTO.getUserName();

		}

	}

}

//	@Override
//	public Long registerUser(LoginDTO loginDTO) throws FarmHubException {
//		// try with else if like for each if matches throw exception
//		List<Login> login2 = loginRepository.findByPhoneNumberOrUserNameOrEmail(loginDTO.getPhoneNumber(),
//				loginDTO.getUserName(), loginDTO.getEmail());
//		
//		if (login2 == null) {
//			setEntity(loginDTO);
//		}
//		else {
//			for (Login login : login2) {
////			System.out.println("check for");
//			// Login login2 = login.get();
//			if (login.getPhoneNumber() != null && loginDTO.getPhoneNumber() != null) {
//				if (loginDTO.getPhoneNumber().equals(login.getPhoneNumber()))
//					throw new FarmHubException("Service.REGISTERED_PHONENUMBER");
//				else 
//					return null;
//			} else if (login.getEmail() != null && loginDTO.getEmail() != null) {
//				if (loginDTO.getEmail().equals(login.getEmail()))
//					throw new FarmHubException("Service.REGISTERED_EMAIL");
//				return null;
//			}
//
//			else if (login.getUserName() != null && loginDTO.getUserName() != null) {
//				if (loginDTO.getUserName().equals(login.getUserName()))
//					throw new FarmHubException("Service.REGISTERED_USER_NAME");
//			 else
//				// System.out.println("check1");
//				return null;}
//			else
//				throw new FarmHubException("Service.REGISTERED");
//		}
//		// System.out.println("check");
//		
//
//	}}
//			
//			
//
//
//	}
//	@Override
//	public Long registerUser(LoginDTO loginDTO) throws FarmHubException {
//
//		if (loginDTO.getPhoneNumber() != null) {
//			Optional<Login> optional = loginRepository.findById(loginDTO.getPhoneNumber());
//			if (optional.isPresent()) {
//				throw new FarmHubException("Service.REGISTERED_PHONENUMBER");
//			}
//		} else
//			throw new FarmHubException("Service.NUMBER_NOT_FOUND");
//		if (loginDTO.getUserName() != null) {
//			List<Login> login = loginRepository.findByUserName(loginDTO.getUserName());
//			if (login != null)
//				throw new FarmHubException("Service.REGISTERED_USERNAME");
//		} else
//			throw new FarmHubException("Service.USER_NAME_NOT_FOUND");
//		if (loginDTO.getEmail() != null) {
//			List<Login> login = loginRepository.findByEmail(loginDTO.getEmail());
//			if (login != null)
//				throw new FarmHubException("Service.REGISTERED_EMAIL");
//		} else
//			throw new FarmHubException("Service.EMAIL_NOT_FOUND");
//	}else
//
//	{
//			if (loginDTO.getPhoneNumber() == null)
//				
//
//		}return
//
//	setEntity(loginDTO);
//
//	}
