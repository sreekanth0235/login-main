package farmhub.login.service;

import farmhub.login.dto.LoginDTO;
import farmhub.login.exception.FarmHubException;

public interface LoginService {
String validLogin(LoginDTO loginDTO) throws FarmHubException;
public String validLogin2(LoginDTO loginDTO) throws FarmHubException;
public String registerUser(LoginDTO loginDTO) throws FarmHubException ;
}
