package com.uaes.esw.entity.ebon;

public class UpmsToUcs {
    private String prono;

    private String proname;

    private String systype;

    private String bcode;

    private String ocode;

    private String createtime;

    public String getProno() {
        return prono;
    }

    public void setProno(String prono) {
        this.prono = prono == null ? null : prono.trim();
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname == null ? null : proname.trim();
    }

    public String getSystype() {
        return systype;
    }

    public void setSystype(String systype) {
        this.systype = systype == null ? null : systype.trim();
    }

    public String getBcode() {
        return bcode;
    }

    public void setBcode(String bcode) {
        this.bcode = bcode == null ? null : bcode.trim();
    }

    public String getOcode() {
        return ocode;
    }

    public void setOcode(String ocode) {
        this.ocode = ocode == null ? null : ocode.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime == null ? null : createtime.trim();
    }
}