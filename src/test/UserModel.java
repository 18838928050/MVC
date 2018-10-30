/**
 * @copy right Dossp Company All rights reserved
 *
 * @Title: UserModel.java 
 *
 * @Date:  2016年10月21日  下午12:27:27
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
 * 日期：2016年10月21日 下午12:27:27
 * </p>
 * 描述：
 *
 */
@Table(name = "func_user")
public class UserModel extends BaseModel {

	/** 
	 * 
	 * @Fields serialVersionUID
	 */ 
	private static final long serialVersionUID = 3118873627026715859L;

	 /**
     * 人员主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id")
    private Long id;
    
    @Transient
    private String corpName;
    
    @Transient
    private String deptName;
    
    /**
     * 中心主键
     */
    @Column(name = "corp_id")
    private Long corpId;
    
    /**
     * 业务单元主键
     */
    @Column(name = "unit_id")
    private Long unitId;

    /**
     * 部门主键
     */
    @Column(name = "dept_id")
    private Long deptId;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 账号状态 0 正常  1禁用
     */
    private String state;

    /**
     * 0负责人,1联络人
     */
    private String type;

    /**
     * 姓名
     */
    private String name;

    /**
     * 职位
     */
    private String post;

    /**
     * 邮箱
     */
    @Column(name = "e_mail")
    private String eMail;

    /**
     * 座机
     */
    private String landline;

    /**
     * 手机
     */
    private String telephone;

    /**
     * 备注
     */
    private String remark;
    
    /**
     * 获取人员主键
     *
     * @return id - 人员主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置人员主键
     *
     * @param id 人员主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    
    
    public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public Long getCorpId() {
		return corpId;
	}

	public void setCorpId(Long corpId) {
		this.corpId = corpId;
	}

	/**
     * 获取业务单元主键
     *
     * @return pk_func_corp - 业务单元主键
     */
    

    public Long getDeptId() {
    	return deptId;
    }
    
    public void setDeptId(Long deptId) {
    	this.deptId = deptId;
    }
    /**
     * 获取登录账号
     *
     * @return account - 登录账号
     */
    public String getAccount() {
        return account;
    }


	/**
     * 设置登录账号
     *
     * @param account 登录账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取登录密码
     *
     * @return password - 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置登录密码
     *
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取账号状态 0 正常  1禁用
     *
     * @return state - 账号状态 0 正常  1禁用
     */
    public String getState() {
        return state;
    }

    /**
     * 设置账号状态 0 正常  1禁用
     *
     * @param state 账号状态 0 正常  1禁用
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取0负责人,1联络人
     *
     * @return type - 0负责人,1联络人
     */
    public String getType() {
        return type;
    }

    /**
     * 设置0负责人,1联络人
     *
     * @param type 0负责人,1联络人
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取职位
     *
     * @return post - 职位
     */
    public String getPost() {
        return post;
    }

    /**
     * 设置职位
     *
     * @param post 职位
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * 获取邮箱
     *
     * @return e_mail - 邮箱
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * 设置邮箱
     *
     * @param eMail 邮箱
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * 获取座机
     *
     * @return landline - 座机
     */
    public String getLandline() {
        return landline;
    }

    /**
     * 设置座机
     *
     * @param landline 座机
     */
    public void setLandline(String landline) {
        this.landline = landline;
    }

    /**
     * 获取手机
     *
     * @return telephone - 手机
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置手机
     *
     * @param telephone 手机
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

}
