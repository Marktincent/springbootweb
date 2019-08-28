package com.uaes.esw.entity.postgres;

import java.io.Serializable;
import java.util.Date;

public class WorkLog implements Serializable {
    private Long id;

    private Long issueid;

    private String author;
    private String project;

    private String grouplevel;

    private Long rolelevel;

    private String worklogbody;

    private Date created;

    private String updateauthor;

    private Date updated;

    private Date startdate;

    private Long timeworked;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIssueid() {
        return issueid;
    }

    public void setIssueid(Long issueid) {
        this.issueid = issueid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getGrouplevel() {
        return grouplevel;
    }

    public void setGrouplevel(String grouplevel) {
        this.grouplevel = grouplevel == null ? null : grouplevel.trim();
    }

    public Long getRolelevel() {
        return rolelevel;
    }

    public void setRolelevel(Long rolelevel) {
        this.rolelevel = rolelevel;
    }

    public String getWorklogbody() {
        return worklogbody;
    }

    public void setWorklogbody(String worklogbody) {
        this.worklogbody = worklogbody == null ? null : worklogbody.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUpdateauthor() {
        return updateauthor;
    }

    public void setUpdateauthor(String updateauthor) {
        this.updateauthor = updateauthor == null ? null : updateauthor.trim();
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Long getTimeworked() {
        return timeworked;
    }

    public void setTimeworked(Long timeworked) {
        this.timeworked = timeworked;
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
        WorkLog other = (WorkLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getIssueid() == null ? other.getIssueid() == null : this.getIssueid().equals(other.getIssueid()))
                && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
                && (this.getGrouplevel() == null ? other.getGrouplevel() == null : this.getGrouplevel().equals(other.getGrouplevel()))
                && (this.getRolelevel() == null ? other.getRolelevel() == null : this.getRolelevel().equals(other.getRolelevel()))
                && (this.getWorklogbody() == null ? other.getWorklogbody() == null : this.getWorklogbody().equals(other.getWorklogbody()))
                && (this.getCreated() == null ? other.getCreated() == null : this.getCreated().equals(other.getCreated()))
                && (this.getUpdateauthor() == null ? other.getUpdateauthor() == null : this.getUpdateauthor().equals(other.getUpdateauthor()))
                && (this.getUpdated() == null ? other.getUpdated() == null : this.getUpdated().equals(other.getUpdated()))
                && (this.getStartdate() == null ? other.getStartdate() == null : this.getStartdate().equals(other.getStartdate()))
                && (this.getTimeworked() == null ? other.getTimeworked() == null : this.getTimeworked().equals(other.getTimeworked()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIssueid() == null) ? 0 : getIssueid().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getGrouplevel() == null) ? 0 : getGrouplevel().hashCode());
        result = prime * result + ((getRolelevel() == null) ? 0 : getRolelevel().hashCode());
        result = prime * result + ((getWorklogbody() == null) ? 0 : getWorklogbody().hashCode());
        result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
        result = prime * result + ((getUpdateauthor() == null) ? 0 : getUpdateauthor().hashCode());
        result = prime * result + ((getUpdated() == null) ? 0 : getUpdated().hashCode());
        result = prime * result + ((getStartdate() == null) ? 0 : getStartdate().hashCode());
        result = prime * result + ((getTimeworked() == null) ? 0 : getTimeworked().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", issueid=").append(issueid);
        sb.append(", author=").append(author);
        sb.append(", grouplevel=").append(grouplevel);
        sb.append(", rolelevel=").append(rolelevel);
        sb.append(", worklogbody=").append(worklogbody);
        sb.append(", created=").append(created);
        sb.append(", updateauthor=").append(updateauthor);
        sb.append(", updated=").append(updated);
        sb.append(", startdate=").append(startdate);
        sb.append(", timeworked=").append(timeworked);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}