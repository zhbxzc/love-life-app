package com.taikang.healthcare.cis.dig.bean;

import java.util.Date;

public class DidicVo {
    
	private Integer id;
    private String uuid;
    private String icdCode;
    private String icdCodeAdd;
    private String name;
    private String des;
    private Boolean symptomFlag;
    private Boolean infectiousFlag;
    private String spellNo;
    private String wubiNo;
    private Integer createdOrgId;
    private Integer createdDeptId;
    private Integer versionNo;
    private Long createdById;
    private Date createdTime;
    private Long lastUpdatedById;
    private Date lastUpdatedTime;
    private Boolean deletedFlag;
    private Integer PageNo;
    private Integer PageSize;
    private Integer total;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIcdCode() {
        return icdCode;
    }

    public void setIcdCode(String icdCode) {
        this.icdCode = icdCode;
    }

    public String getIcdCodeAdd() {
        return icdCodeAdd;
    }

    public void setIcdCodeAdd(String icdCodeAdd) {
        this.icdCodeAdd = icdCodeAdd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    public Boolean getSymptomFlag() {
        return symptomFlag;
    }

    public void setSymptomFlag(Boolean symptomFlag) {
        this.symptomFlag = symptomFlag;
    }

    public Boolean getInfectiousFlag() {
        return infectiousFlag;
    }
    public void setInfectiousFlag(Boolean infectiousFlag) {
        this.infectiousFlag = infectiousFlag;
    }

    public String getSpellNo() {
        return spellNo;
    }

    public void setSpellNo(String spellNo) {
        this.spellNo = spellNo;
    }

    public String getWubiNo() {
        return wubiNo;
    }

    public void setWubiNo(String wubiNo) {
        this.wubiNo = wubiNo;
    }

    public Integer getCreatedOrgId() {
        return createdOrgId;
    }

    public void setCreatedOrgId(Integer createdOrgId) {
        this.createdOrgId = createdOrgId;
    }

    public Integer getCreatedDeptId() {
        return createdDeptId;
    }

    public void setCreatedDeptId(Integer createdDeptId) {
        this.createdDeptId = createdDeptId;
    }

    public Integer getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getLastUpdatedById() {
        return lastUpdatedById;
    }
    public void setLastUpdatedById(Long lastUpdatedById) {
        this.lastUpdatedById = lastUpdatedById;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Boolean getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(Boolean deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

	public Integer getPageNo() {
		return PageNo;
	}

	public void setPageNo(Integer pageNo) {
		PageNo = pageNo;
	}

	public Integer getPageSize() {
		return PageSize;
	}

	public void setPageSize(Integer pageSize) {
		PageSize = pageSize;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	public static String toURL(DidicVo didic){
    	String url="?";
    	if(didic.getId()!=null){
    		url+="&id="+didic.getId();
    	}
    	if(didic.getName()!=null){
    		url+="&name="+didic.getName();
    	}
    	if(didic.getIcdCode()!=null){
    		url+="&icdCode="+didic.getIcdCode();
    	}
    	if(didic.getIcdCodeAdd()!=null){
    		url+="&icdCodeAdd="+didic.getIcdCodeAdd();
    	}
    	if(didic.getInfectiousFlag()!=null){
    		url+="&infectiousFlag="+didic.getInfectiousFlag();
    	}
    	if(didic.getSymptomFlag()!=null){
    		url+="&symptomFlag="+didic.getSymptomFlag();
    	}
    	if(didic.getPageNo()!=null){
    		url+="&pageIndex="+didic.getPageNo();
    	}
    	if(didic.getPageSize()!=null){
    		url+="&pageSize="+didic.getPageSize();
    	}
    	if(url.equals("?")){
    		url="";
    	}
    	return url;
    }
}