package com.perfmath.spring.soba.webflow.domain;

import java.io.Serializable;

public class CustomerCriteria implements Serializable {

    private String zipcode;
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
