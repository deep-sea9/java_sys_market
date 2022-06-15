package com.gdupt.hai.domain;

public class Proffer {

    private String ProfferId;   //供应商编号
    private String fullName;    //供应商名称
    private String contacts;    //联系人
    private String phone;       //联系电话
    private String address;     //联系地址
    private String account;     //银行账号

    public String getProfferId() {
        return ProfferId;
    }

    public void setProfferId(String profferId) {
        ProfferId = profferId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Proffer{" +
                "ProfferId='" + ProfferId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contacts='" + contacts + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
