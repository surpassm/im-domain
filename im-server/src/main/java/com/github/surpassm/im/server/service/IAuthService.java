/**
 * Copyright (c) 2018, Mr.Wang (recallcode@aliyun.com) All rights reserved.
 */

package com.github.surpassm.im.server.service;

/**
 * 用户和密码认证服务接口
 * @author Administrator
 */
public interface IAuthService {

	/**
	 * 验证用户名和密码是否正确
	 */
	boolean checkValid(String username, String password);

}