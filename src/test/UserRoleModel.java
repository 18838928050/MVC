/**
 * @copy right Dossp Company All rights reserved
 *
 * @Title: UserRoleModel.java 
 *
 * @Date:  2016年10月21日  下午12:29:46
 *
 * @Package com.dossp.pms.func.user.model
 */


package test;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.dossp.pms.common.model.BaseModel;

/**
 * @author yufenghui
 * </p>
 * 日期：2016年10月21日 下午12:29:46
 * </p>
 * 描述：
 *
 */
@Table(name = "func_user_role")
public class UserRoleModel extends BaseModel {

	/** 
	 * 
	 * @Fields serialVersionUID
	 */ 
	private static final long serialVersionUID = 6088515547501075644L;

	/**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    private Long id;

    /**
     * 人员主键
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色主键
     */
    @Column(name = "role_id")
    private Long roleId;
    
    @Transient
    private String roleName;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取人员主键
     *
     * @return userId - 人员主键
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置人员主键
     *
     * @param userId 人员主键
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取角色主键
     *
     * @return roleId - 角色主键
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色主键
     *
     * @param roleId 角色主键
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
    
    
}
