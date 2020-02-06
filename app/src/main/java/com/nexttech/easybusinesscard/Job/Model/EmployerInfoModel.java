package com.nexttech.easybusinesscard.Job.Model;

public class EmployerInfoModel {
    private String firstName;
    private String lastName;
    private String userName;
    private String companyName;
    private String password;
    private String email;
    private String mobileNumber;
    private String country;

    public EmployerInfoModel() {
    }

    public EmployerInfoModel(String firstName, String lastName, String userName, String companyName, String password, String email, String mobileNumber, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.companyName = companyName;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
