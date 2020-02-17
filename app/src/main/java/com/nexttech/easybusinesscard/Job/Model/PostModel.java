package com.nexttech.easybusinesscard.Job.Model;

public class PostModel {

    long postId;
    String jobPostion;
    String companyName;
    String companyAddress;
    String jobSalary;
    String jobPublisher;

    public PostModel() {
    }

    public PostModel(long postId, String jobPostion, String companyName, String companyAddress, String jobSalary, String jobPublisher) {
        this.postId = postId;
        this.jobPostion = jobPostion;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.jobSalary = jobSalary;
        this.jobPublisher = jobPublisher;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getJobPostion() {
        return jobPostion;
    }

    public void setJobPostion(String jobPostion) {
        this.jobPostion = jobPostion;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getJobPublisher() {
        return jobPublisher;
    }

    public void setJobPublisher(String jobPublisher) {
        this.jobPublisher = jobPublisher;
    }
}
