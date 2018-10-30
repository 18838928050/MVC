/**
 * @copy right Dossp Company All rights reserved
 *
 * @Title: UserRoleServiceImpl.java 
 *
 * @Date:  2016年10月21日  下午12:31:56
 *
 * @Package com.dossp.pms.func.user.service.impl
 */


package test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dossp.pms.common.service.impl.BaseServiceImpl;
import com.dossp.pms.func.user.mapper.UserRoleMapper;
import com.dossp.pms.func.user.model.UserRoleModel;
import com.dossp.pms.func.user.service.UserRoleService;

/**
 * @author yufenghui
 * </p>
 * 日期：2016年10月21日 下午12:31:56
 * </p>
 * 描述：
 *
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleModel, Long> implements UserRoleService {
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	/** 
	 * @param userId
	 * @return 
	 */
	
	@Override
	public List<UserRoleModel> findUserRole(Long userId) {
		List<UserRoleModel> userRole = userRoleMapper.findUserRole(userId);
		return userRole;
	}

}
