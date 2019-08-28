package com.uaes.esw.entity.obpmdir;

import java.util.Date;

public class Participant {
    private Object fuegoId;

    private Object fuegoModifier;

    private String fuegoTimezone;

    private Integer fuegoPermissions;

    private Object fuegoLastname;

    private Long fuegoIn;

    private Object fuegoMail;

    private Object fuegoFirstname;

    private Object fuegoFax;

    private String fuegoStatus;

    private Object fuegoDisplayname;

    private Object fuegoTelephone;

    private Date fuegoCrtime;

    private Object fuegoOu;

    private Object fuegoManager;

    private Object fuegoRid;

    private Object fuegoCreator;

    private String fuegoLocale;

    private Date fuegoMotime;

    private byte[] fuegoPhoto;

    private byte[] fuegoPassword;

    public Object getFuegoId() {
        return fuegoId;
    }

    public void setFuegoId(Object fuegoId) {
        this.fuegoId = fuegoId;
    }

    public Object getFuegoModifier() {
        return fuegoModifier;
    }

    public void setFuegoModifier(Object fuegoModifier) {
        this.fuegoModifier = fuegoModifier;
    }

    public String getFuegoTimezone() {
        return fuegoTimezone;
    }

    public void setFuegoTimezone(String fuegoTimezone) {
        this.fuegoTimezone = fuegoTimezone == null ? null : fuegoTimezone.trim();
    }

    public Integer getFuegoPermissions() {
        return fuegoPermissions;
    }

    public void setFuegoPermissions(Integer fuegoPermissions) {
        this.fuegoPermissions = fuegoPermissions;
    }

    public Object getFuegoLastname() {
        return fuegoLastname;
    }

    public void setFuegoLastname(Object fuegoLastname) {
        this.fuegoLastname = fuegoLastname;
    }

    public Long getFuegoIn() {
        return fuegoIn;
    }

    public void setFuegoIn(Long fuegoIn) {
        this.fuegoIn = fuegoIn;
    }

    public Object getFuegoMail() {
        return fuegoMail;
    }

    public void setFuegoMail(Object fuegoMail) {
        this.fuegoMail = fuegoMail;
    }

    public Object getFuegoFirstname() {
        return fuegoFirstname;
    }

    public void setFuegoFirstname(Object fuegoFirstname) {
        this.fuegoFirstname = fuegoFirstname;
    }

    public Object getFuegoFax() {
        return fuegoFax;
    }

    public void setFuegoFax(Object fuegoFax) {
        this.fuegoFax = fuegoFax;
    }

    public String getFuegoStatus() {
        return fuegoStatus;
    }

    public void setFuegoStatus(String fuegoStatus) {
        this.fuegoStatus = fuegoStatus == null ? null : fuegoStatus.trim();
    }

    public Object getFuegoDisplayname() {
        return fuegoDisplayname;
    }

    public void setFuegoDisplayname(Object fuegoDisplayname) {
        this.fuegoDisplayname = fuegoDisplayname;
    }

    public Object getFuegoTelephone() {
        return fuegoTelephone;
    }

    public void setFuegoTelephone(Object fuegoTelephone) {
        this.fuegoTelephone = fuegoTelephone;
    }

    public Date getFuegoCrtime() {
        return fuegoCrtime;
    }

    public void setFuegoCrtime(Date fuegoCrtime) {
        this.fuegoCrtime = fuegoCrtime;
    }

    public Object getFuegoOu() {
        return fuegoOu;
    }

    public void setFuegoOu(Object fuegoOu) {
        this.fuegoOu = fuegoOu;
    }

    public Object getFuegoManager() {
        return fuegoManager;
    }

    public void setFuegoManager(Object fuegoManager) {
        this.fuegoManager = fuegoManager;
    }

    public Object getFuegoRid() {
        return fuegoRid;
    }

    public void setFuegoRid(Object fuegoRid) {
        this.fuegoRid = fuegoRid;
    }

    public Object getFuegoCreator() {
        return fuegoCreator;
    }

    public void setFuegoCreator(Object fuegoCreator) {
        this.fuegoCreator = fuegoCreator;
    }

    public String getFuegoLocale() {
        return fuegoLocale;
    }

    public void setFuegoLocale(String fuegoLocale) {
        this.fuegoLocale = fuegoLocale == null ? null : fuegoLocale.trim();
    }

    public Date getFuegoMotime() {
        return fuegoMotime;
    }

    public void setFuegoMotime(Date fuegoMotime) {
        this.fuegoMotime = fuegoMotime;
    }

    public byte[] getFuegoPhoto() {
        return fuegoPhoto;
    }

    public void setFuegoPhoto(byte[] fuegoPhoto) {
        this.fuegoPhoto = fuegoPhoto;
    }

    public byte[] getFuegoPassword() {
        return fuegoPassword;
    }

    public void setFuegoPassword(byte[] fuegoPassword) {
        this.fuegoPassword = fuegoPassword;
    }
}