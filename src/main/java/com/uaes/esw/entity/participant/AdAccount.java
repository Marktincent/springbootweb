package com.uaes.esw.entity.participant;

import java.io.Serializable;

/**
 * 
 * AD账户信息 
 * @author Administrator
 * 在与AD进行同步时有用到此类
 */
public class AdAccount implements Serializable {
	private static final long serialVersionUID = -2222246174738712004L;
	private String FacsimileTelephoneNumber = "";//传真
	private String url = "";
	private String whenChanged = "";
	private String employeeID = "";
	private String name = "";//用户名称
	private String userPrincipalName = "";//登录名
	private String physicalDeliveryOfficeName = "";
	private String departmentNumber = "";//部门编号
	private String telephoneNumber = "";//电话号码
	private String homePhone = "";//家庭电话
	private String mobille = "";//手机
	private String department = "";//部门
	private String sAMAccountName = ""; //名.姓
	private String whenchanged = "";
	private String mail = ""; //电子邮箱
	private String displayname = "";//显示名称
	private String pager = "";//工号
	private String sn = "";//姓
	private String givenname = "";//名
	private String ipPhone = "";//主管
	private String distinguishedName = "";
	private String msExchUserCulture = "";

	/**
	 * AD中的IPPhone，代表主管
	 * @return
	 */
	public String getIpPhone() {
		return ipPhone;
	}

	public void setIpPhone(String ipPhone) {
		this.ipPhone = ipPhone;
	}

	/**
	 * AD中的寻呼机，代表工号字段
	 * @return
	 */
	public String getPager() {
		return pager;
	}

	public void setPager(String pager) {
		this.pager = pager;
	}

	/**
	 * 显示名称
	 * @return
	 */
	public String getDisplayname() {
		return displayname;
	}

	/**
	 * 显示名称
	 * @return
	 */
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWhenChanged() {
		return whenChanged;
	}

	public void setWhenChanged(String whenChanged) {
		this.whenChanged = whenChanged;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 登录名
	 * @return
	 */
	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}

	public String getPhysicalDeliveryOfficeName() {
		return physicalDeliveryOfficeName;
	}

	public void setPhysicalDeliveryOfficeName(String physicalDeliveryOfficeName) {
		this.physicalDeliveryOfficeName = physicalDeliveryOfficeName;
	}

	/**
	 * 部门编号
	 * @return
	 */
	public String getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}

	/**
	 * 电话号码
	 * @return
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * 家庭电话
	 * @return
	 */
	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	/**
	 * 手机
	 * @return
	 */
	public String getMobille() {
		return mobille;
	}

	public void setMobille(String mobille) {
		this.mobille = mobille;
	}

	/**
	 * 部门
	 * @return
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * 部门
	 * @param department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * 名.姓
	 * @return
	 */
	public String getSAMAccountName() {
		return sAMAccountName;
	}

	/**
	 * 名.姓
	 * @return
	 */
	public void setSAMAccountName(String accountName) {
		sAMAccountName = accountName;
	}

	public String getWhenchanged() {
		return whenchanged;
	}

	public void setWhenchanged(String whenchanged) {
		this.whenchanged = whenchanged;
	}

	/**
	 * 电子邮箱
	 * @return
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * 电子邮箱
	 * @return
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * 姓
	 * @return
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * 姓
	 * @param sn
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 名
	 * @return
	 */
	public String getGivenname() {
		return givenname;
	}

	/**
	 * 名
	 * @param givenname
	 */
	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}

	/**
	 * 传真
	 * @return
	 */
	public String getFacsimileTelephoneNumber() {
		return FacsimileTelephoneNumber;
	}

	public void setFacsimileTelephoneNumber(String facsimileTelephoneNumber) {
		FacsimileTelephoneNumber = facsimileTelephoneNumber;
	}

	/**
	 * DN的全称
	 * @param distinguishedName
	 */
	public void setDistinguishedName(String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}

	/**
	 * DN的全称
	 * @param distinguishedName
	 */
	public String getDistinguishedName() {
		return distinguishedName;
	}

	public String getMsExchUserCulture() {
		return msExchUserCulture;
	}

	public void setMsExchUserCulture(String msExchUserCulture) {
		this.msExchUserCulture = msExchUserCulture;
	}

}
