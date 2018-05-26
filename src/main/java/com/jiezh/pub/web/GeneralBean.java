package com.jiezh.pub.web;

import java.util.List;

public class GeneralBean {
    private String sysOrganCode;
    private String sysUserRole;
    
    // 用户数据权限 addby btdong 2017年3月20日17:29:26
    private List<String> orgCompany;

    public String getSysOrganCode() {
        return sysOrganCode;
    }

    public void setSysOrganCode(String sysOrganCode) {
        this.sysOrganCode = sysOrganCode;
    }

    public String getSysUserRole() {
        return sysUserRole;
    }

    public void setSysUserRole(String sysUserRole) {
        this.sysUserRole = sysUserRole;
    }

	public List<String> getOrgCompany() {
		return orgCompany;
	}

	public void setOrgCompany(List<String> orgCompany) {
		this.orgCompany = orgCompany;
	}

    
}
