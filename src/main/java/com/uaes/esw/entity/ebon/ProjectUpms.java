package com.uaes.esw.entity.ebon;

public class ProjectUpms {
    private String projectcode;

    private String customercode;

    private String createyear;

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode == null ? null : projectcode.trim();
    }

    public String getCustomercode() {
        return customercode;
    }

    public void setCustomercode(String customercode) {
        this.customercode = customercode == null ? null : customercode.trim();
    }

    public String getCreateyear() {
        return createyear;
    }

    public void setCreateyear(String createyear) {
        this.createyear = createyear == null ? null : createyear.trim();
    }
}