

package com.github.surpassm.im.server.service.impl;

import com.github.surpassm.im.server.service.IAuthService;
import org.springframework.stereotype.Service;


/**
 * 用户名和密码认证服务
 * @author Administrator
 */
@Service
public class AuthService implements IAuthService {

	@Override
	public boolean checkValid(String username, String password) {
		return true;
	}


}
