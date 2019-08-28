package com.uaes.esw.entity.ucs;

public class GsdjFenTan {
    private String id;

    private String projectno;

    private String workpackage;

    private String employeeid;

    private String dpet1;

    private String dpet2;

    private String dpet3;

    private String centercost;

    private String bgDate;

    private String recodedate;

    private float hours;

    private String bjid;

    private String sysplat;

    private String custom;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProjectno() {
        return projectno;
    }

    public void setProjectno(String projectno) {
        this.projectno = projectno == null ? null : projectno.trim();
    }

    public String getWorkpackage() {
        return workpackage;
    }

    public void setWorkpackage(String workpackage) {
        this.workpackage = workpackage == null ? null : workpackage.trim();
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid == null ? null : employeeid.trim();
    }

    public String getDpet1() {
        return dpet1;
    }

    public void setDpet1(String dpet1) {
        this.dpet1 = dpet1 == null ? null : dpet1.trim();
    }

    public String getDpet2() {
        return dpet2;
    }

    public void setDpet2(String dpet2) {
        this.dpet2 = dpet2 == null ? null : dpet2.trim();
    }

    public String getDpet3() {
        return dpet3;
    }

    public void setDpet3(String dpet3) {
        this.dpet3 = dpet3 == null ? null : dpet3.trim();
    }

    public String getCentercost() {
        return centercost;
    }

    public void setCentercost(String centercost) {
        this.centercost = centercost == null ? null : centercost.trim();
    }

    public String getBgDate() {
        return bgDate;
    }

    public void setBgDate(String bgDate) {
        this.bgDate = bgDate == null ? null : bgDate.trim();
    }

    public String getRecodedate() {
        return recodedate;
    }

    public void setRecodedate(String recodedate) {
        this.recodedate = recodedate == null ? null : recodedate.trim();
    }

    public float getHours() {
        return hours;
    }

    public void setHours(float hours) {
        this.hours = hours;
    }

    public String getBjid() {
        return bjid;
    }

    public void setBjid(String bjid) {
        this.bjid = bjid == null ? null : bjid.trim();
    }

    public String getSysplat() {
        return sysplat;
    }

    public void setSysplat(String sysplat) {
        this.sysplat = sysplat == null ? null : sysplat.trim();
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom == null ? null : custom.trim();
    }
}