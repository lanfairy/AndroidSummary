package com.lanfairy.elly.androidsummary.contact.TheContact;

public class Contact {
    private String id;
    private String name;
    ///手机
    private String mobile;
    ///住宅电话
    private String homePhone;
    ///单位电话
    private String unitPhone;
    ///个人
    private String personalEmail;
    private String companyEmail;

    private String company;
    ///个人地址
    private String homeAddress;
    ///单位地址
    private String companyAddress;
    //职位
    private String title;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public void setUnitPhone(String unitPhone) {
        this.unitPhone = unitPhone;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getMobile() {
        return mobile;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getUnitPhone() {
        return unitPhone;
    }

    public String getCompany() {
        return company;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", unitPhone='" + unitPhone + '\'' +
                ", personalEmail='" + personalEmail + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", company='" + company + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
