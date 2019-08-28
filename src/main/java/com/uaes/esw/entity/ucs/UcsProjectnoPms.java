package com.uaes.esw.entity.ucs;

public class UcsProjectnoPms {
    private String projectno;

    private String projectname;

    private String bnumber;

    private String onumber;

    private String projmanager;

    private String sod;

    private String sop;

    private String sales;

    private String uniqueid;

    private String projecttype;

    private String match25;

    private String datafix;

    public String getProjectno() {
        return projectno;
    }

    public void setProjectno(String projectno) {
        this.projectno = projectno == null ? null : projectno.trim();
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    public String getBnumber() {
        return bnumber;
    }

    public void setBnumber(String bnumber) {
        this.bnumber = bnumber == null ? null : bnumber.trim();
    }

    public String getOnumber() {
        return onumber;
    }

    public void setOnumber(String onumber) {
        this.onumber = onumber == null ? null : onumber.trim();
    }

    public String getProjmanager() {
        return projmanager;
    }

    public void setProjmanager(String projmanager) {
        this.projmanager = projmanager == null ? null : projmanager.trim();
    }

    public String getSod() {
        return sod;
    }

    public void setSod(String sod) {
        this.sod = sod == null ? null : sod.trim();
    }

    public String getSop() {
        return sop;
    }

    public void setSop(String sop) {
        this.sop = sop == null ? null : sop.trim();
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales == null ? null : sales.trim();
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid == null ? null : uniqueid.trim();
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype == null ? null : projecttype.trim();
    }

    public String getMatch25() {
        return match25;
    }

    public void setMatch25(String match25) {
        this.match25 = match25 == null ? null : match25.trim();
    }

    public String getDatafix() {
        return datafix;
    }

    public void setDatafix(String datafix) {
        this.datafix = datafix == null ? null : datafix.trim();
    }
}