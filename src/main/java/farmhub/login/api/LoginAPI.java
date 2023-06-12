package farmhub.login.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import farmhub.login.dto.LoginDTO;
import farmhub.login.exception.FarmHubException;
import farmhub.login.service.LoginSeviceImpl;

@RestController
@Validated
@RequestMapping(value="login-api")
public class LoginAPI {
@Autowired
private LoginSeviceImpl loginService;
@Autowired
Environment environment;
@PostMapping(value="login")
public ResponseEntity<String> validLogin(@Valid @RequestBody LoginDTO loginDTO) throws FarmHubException{
	//Iterable<T>

	return new ResponseEntity<String>(loginService.validLogin(loginDTO), HttpStatus.ACCEPTED);
}
@PostMapping(value="loginChck")
public ResponseEntity<String> validLogin2(@Valid @RequestBody LoginDTO loginDTO) throws FarmHubException{
	//Iterable<T>
	
	return new ResponseEntity<String>(loginService.validLogin2(loginDTO), HttpStatus.ACCEPTED);
}
@PostMapping(value="loginregister")
public ResponseEntity<String> registerUser(@Valid @RequestBody LoginDTO loginDTO) throws FarmHubException{
	//Iterable<T>

	return new ResponseEntity<>(environment.getProperty("USER_REGISTERED")+loginService.registerUser(loginDTO), HttpStatus.CREATED);
}
}
