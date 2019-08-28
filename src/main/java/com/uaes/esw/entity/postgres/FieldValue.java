package com.uaes.esw.entity.postgres;

import java.io.Serializable;
import java.util.Date;

public class FieldValue implements Serializable {
    private Long id;

    private Long issue;

    private Long customfield;

    private String parentkey;

    private String stringvalue;

    private Double numbervalue;

    private String textvalue;

    private Date datevalue;

    private String valuetype;

    private Long updated;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIssue() {
        return issue;
    }

    public void setIssue(Long issue) {
        this.issue = issue;
    }

    public Long getCustomfield() {
        return customfield;
    }

    public void setCustomfield(Long customfield) {
        this.customfield = customfield;
    }

    public String getParentkey() {
        return parentkey;
    }

    public void setParentkey(String parentkey) {
        this.parentkey = parentkey == null ? null : parentkey.trim();
    }

    public String getStringvalue() {
        return stringvalue;
    }

    public void setStringvalue(String stringvalue) {
        this.stringvalue = stringvalue == null ? null : stringvalue.trim();
    }

    public Double getNumbervalue() {
        return numbervalue;
    }

    public void setNumbervalue(Double numbervalue) {
        this.numbervalue = numbervalue;
    }

    public String getTextvalue() {
        return textvalue;
    }

    public void setTextvalue(String textvalue) {
        this.textvalue = textvalue == null ? null : textvalue.trim();
    }

    public Date getDatevalue() {
        return datevalue;
    }

    public void setDatevalue(Date datevalue) {
        this.datevalue = datevalue;
    }

    public String getValuetype() {
        return valuetype;
    }

    public void setValuetype(String valuetype) {
        this.valuetype = valuetype == null ? null : valuetype.trim();
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FieldValue other = (FieldValue) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIssue() == null ? other.getIssue() == null : this.getIssue().equals(other.getIssue()))
            && (this.getCustomfield() == null ? other.getCustomfield() == null : this.getCustomfield().equals(other.getCustomfield()))
            && (this.getParentkey() == null ? other.getParentkey() == null : this.getParentkey().equals(other.getParentkey()))
            && (this.getStringvalue() == null ? other.getStringvalue() == null : this.getStringvalue().equals(other.getStringvalue()))
            && (this.getNumbervalue() == null ? other.getNumbervalue() == null : this.getNumbervalue().equals(other.getNumbervalue()))
            && (this.getTextvalue() == null ? other.getTextvalue() == null : this.getTextvalue().equals(other.getTextvalue()))
            && (this.getDatevalue() == null ? other.getDatevalue() == null : this.getDatevalue().equals(other.getDatevalue()))
            && (this.getValuetype() == null ? other.getValuetype() == null : this.getValuetype().equals(other.getValuetype()))
            && (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIssue() == null) ? 0 : getIssue().hashCode());
        result = prime * result + ((getCustomfield() == null) ? 0 : getCustomfield().hashCode());
        result = prime * result + ((getParentkey() == null) ? 0 : getParentkey().hashCode());
        result = prime * result + ((getStringvalue() == null) ? 0 : getStringvalue().hashCode());
        result = prime * result + ((getNumbervalue() == null) ? 0 : getNumbervalue().hashCode());
        result = prime * result + ((getTextvalue() == null) ? 0 : getTextvalue().hashCode());
        result = prime * result + ((getDatevalue() == null) ? 0 : getDatevalue().hashCode());
        result = prime * result + ((getValuetype() == null) ? 0 : getValuetype().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", issue=").append(issue);
        sb.append(", customfield=").append(customfield);
        sb.append(", parentkey=").append(parentkey);
        sb.append(", stringvalue=").append(stringvalue);
        sb.append(", numbervalue=").append(numbervalue);
        sb.append(", textvalue=").append(textvalue);
        sb.append(", datevalue=").append(datevalue);
        sb.append(", valuetype=").append(valuetype);
        sb.append(", updated=").append(updated);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}