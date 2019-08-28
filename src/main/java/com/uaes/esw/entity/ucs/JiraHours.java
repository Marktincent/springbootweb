package com.uaes.esw.entity.ucs;

public class JiraHours {
    private Long id;

    private String taskhour;

    private String wpname;

    private String projectnumber;

    private String registertime;

    private String people;

    private String actualstarttime;

    private String actualendtime;

    private String systemname;

    private String custom;

    private String jiracreatetime;

    private String dept;

    private String sec;

    private String grp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskhour() {
        return taskhour;
    }

    public void setTaskhour(String taskhour) {
        this.taskhour = taskhour == null ? null : taskhour.trim();
    }

    public String getWpname() {
        return wpname;
    }

    public void setWpname(String wpname) {
        this.wpname = wpname == null ? null : wpname.trim();
    }

    public String getProjectnumber() {
        return projectnumber;
    }

    public void setProjectnumber(String projectnumber) {
        this.projectnumber = projectnumber == null ? null : projectnumber.trim();
    }

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime == null ? null : registertime.trim();
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    public String getActualstarttime() {
        return actualstarttime;
    }

    public void setActualstarttime(String actualstarttime) {
        this.actualstarttime = actualstarttime == null ? null : actualstarttime.trim();
    }

    public String getActualendtime() {
        return actualendtime;
    }

    public void setActualendtime(String actualendtime) {
        this.actualendtime = actualendtime == null ? null : actualendtime.trim();
    }

    public String getSystemname() {
        return systemname;
    }

    public void setSystemname(String systemname) {
        this.systemname = systemname == null ? null : systemname.trim();
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom == null ? null : custom.trim();
    }

    public String getJiracreatetime() {
        return jiracreatetime;
    }

    public void setJiracreatetime(String jiracreatetime) {
        this.jiracreatetime = jiracreatetime == null ? null : jiracreatetime.trim();
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getGrp() {
        return grp;
    }

    public void setGrp(String grp) {
        this.grp = grp;
    }
}