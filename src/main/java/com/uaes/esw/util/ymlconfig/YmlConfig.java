package com.uaes.esw.util.ymlconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jiraconfig")
public class YmlConfig {
    String issueUrl;
    String username;
    String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getIssueUrl() {
        return issueUrl;
    }

    public void setIssueUrl(String issueUrl) {
        this.issueUrl = issueUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
