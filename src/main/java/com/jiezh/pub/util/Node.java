package com.jiezh.pub.util;

public class Node {

	private String id;
	private String pId;
	private String name;
	private String organLevel; // 机构等级 added by yipan 2016年5月5日 16:19:54
	private String organTId; // 机构真实ID added by yipan 2016年5月6日 15:08:29
	private Boolean isParent;
	private Boolean open;
	private Boolean checked;
	private Boolean nocheck;
	private String organCode;
	private String organPcode;

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getOrganPcode() {
		return organPcode;
	}

	public void setOrganPcode(String organPcode) {
		this.organPcode = organPcode;
	}

    public Boolean getNocheck() {
        return nocheck;
    }

    public void setNocheck(Boolean nocheck) {
        this.nocheck = nocheck;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getOrganLevel() {
        return organLevel;
    }

    public void setOrganLevel(String organLevel) {
        this.organLevel = organLevel;
    }

    public String getOrganTId() {
        return organTId;
    }

    public void setOrganTId(String organTId) {
        this.organTId = organTId;
    }
}
