/**
 * @copy right Dossp Company All rights reserved
 *
 * @Title: UserServiceImpl.java 
 *
 * @Date:  2016年10月21日  下午12:28:50
 *
 * @Package com.dossp.pms.func.user.service.impl
 */

package test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dossp.pms.common.service.impl.BaseServiceImpl;
import com.dossp.pms.func.menu.model.MenuModel;
import com.dossp.pms.func.user.mapper.UserMapper;
import com.dossp.pms.func.user.mapper.UserRoleMapper;
import com.dossp.pms.func.user.model.UserModel;
import com.dossp.pms.func.user.model.UserRoleModel;
import com.dossp.pms.func.user.service.UserService;
import com.dossp.pms.func.user.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @author yufenghui
 *         </p>
 *         日期：2016年10月21日 下午12:28:50
 *         </p>
 *         描述：
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserModel, Long> implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRoleMapper userRoleMapper;


	/**
	 * @param userVo
	 * @return
	 */
	@Override
	public PageInfo<UserModel> findPageInfo(UserVo userVo) {
		PageHelper.startPage(userVo.getPageNum(), userVo.getPageSize());
		List<UserModel> userList = userMapper.findUserList(userVo);
		return new PageInfo<>(userList);
	}

	/**
	 * @param userId
	 * @return
	 */

	@Override
	public UserModel findUser(Long userId) {
		UserModel model = userMapper.findUser(userId);
		return model;
	}

	/**
	 * @param account
	 * @return
	 */
	@Override
	public UserModel findUserByAccount(String account) {
		return userMapper.findUserByAccount(account);
	}

	/** 
	 * @param userId
	 * @return 
	 */
	@Override
	public List<MenuModel> findUserMenuList(Long userId) {
		return userMapper.findUserMenuList(userId);
	}

	/** 
	 * @param userModel
	 * @param roleList
	 * @return 
	 */
	
	@Transactional
	@Override
	public int saveUser(UserModel userModel, String[] roleList) {
		int result = 0;
		int flag = userMapper.insert(userModel);
		result = flag;
		if(flag > 0){
			for (String role : roleList) {
				UserRoleModel userRoleModel = new UserRoleModel();
				userRoleModel.setUserId(userModel.getId());
				userRoleModel.setRoleId(Long.parseLong(role));
				result = userRoleMapper.insert(userRoleModel);
			}
		}
		return result;
	}

	/** 
	 * @param userModel
	 * @param roleList
	 * @return 
	 */
	
	@Transactional
	@Override
	public int updateUser(Long id, String[] roleList) {
		
		Example example = new Example(UserRoleModel.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", id);
		
		int result = userRoleMapper.deleteByExample(example);
		
		for (String roleId : roleList) {
			UserRoleModel userRole = new UserRoleModel();
			userRole.setUserId(id);
			userRole.setRoleId(Long.parseLong(roleId));
			
			result = userRoleMapper.insert(userRole);
		}
		
		return result;
	}

	/** 
	 * @param userModel
	 * @return 
	 */
	
	
	@Override
	public int resetPwd(UserModel userModel) {
		int update = this.update(userModel);
		return update;
	}

}
