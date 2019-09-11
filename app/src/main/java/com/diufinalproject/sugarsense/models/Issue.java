package com.diufinalproject.sugarsense.models;

public class Issue {

    private String issueName;
    private String issueDescription;
    private String doctorName;
    private String doctorExperties;
    private String issueStatus;

    public Issue() {
    }

    public Issue(String issueName, String issueDescription, String doctorName, String doctorExperties, String issueStatus) {
        this.issueName = issueName;
        this.issueDescription = issueDescription;
        this.doctorName = doctorName;
        this.doctorExperties = doctorExperties;
        this.issueStatus = issueStatus;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getIssueDescription() {
        return issueDescription;
    }

    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorExperties() {
        return doctorExperties;
    }

    public void setDoctorExperties(String doctorExperties) {
        this.doctorExperties = doctorExperties;
    }

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }
}
