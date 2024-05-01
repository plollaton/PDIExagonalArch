package com.pdi.hexago.domains.customers;

import java.util.UUID;

public class Customer {

    private UUID id;

    private String name;

    private String email;

    private String documentNumber;

    public Customer(String name, String email, String documentNumber) {
        this.name = name;
        this.email = email;
        this.documentNumber = documentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof Customer)) {
            return false;
        }

        return this.documentNumber.equals(((Customer) obj).getDocumentNumber());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
