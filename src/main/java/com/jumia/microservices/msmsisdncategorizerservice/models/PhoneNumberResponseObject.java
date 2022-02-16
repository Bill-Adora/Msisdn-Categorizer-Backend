package com.jumia.microservices.msmsisdncategorizerservice.models;

public class PhoneNumberResponseObject {
    private String country;
    private State state;
    private String countryCode;
    private String phoneNumber;

    public PhoneNumberResponseObject() {
    }

    public PhoneNumberResponseObject(String country, State state, String countryCode, String phoneNumber) {
        this.country = country;
        this.state = state;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
