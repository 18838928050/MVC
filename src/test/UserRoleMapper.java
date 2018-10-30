/**
 * @copy right Dossp Company All rights reserved
 *
 * @Title: UserRoleMapper.java 
 *
 * @Date:  2016年10月21日  下午12:30:52
 *
 * @Package com.dossp.pms.func.user.mapper
 */


package test;

import java.util.List;

import com.dossp.pms.common.mybatis.MyBaseMapper;
import com.dossp.pms.func.user.model.UserRoleModel;

/**
 * @author yufenghui
 * </p>
 * 日期：2016年10月21日 下午12:30:52
 * </p>
 * 描述：
 *
 */

public interface UserRoleMapper extends MyBaseMapper<UserRoleModel> {
	
	public List<UserRoleModel> findUserRole(Long userId);
	
	public int deleteByUserId(Long userId);
	
	
	
	

}
