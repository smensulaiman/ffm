package com.diufinalproject.sugarsense.activities.expense;

class ModelUserVisit {

    private String companyName;
    private String purpose;
    private String contactPerson;
    private String amount;
    private String dateTime;

    public ModelUserVisit() {
    }

    public ModelUserVisit(String companyName, String purpose, String contactPerson, String amount, String dateTime) {
        this.companyName = companyName;
        this.purpose = purpose;
        this.contactPerson = contactPerson;
        this.amount = amount;
        this.dateTime = dateTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
