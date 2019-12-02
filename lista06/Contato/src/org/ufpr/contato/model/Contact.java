package org.ufpr.contato.model;

import java.time.LocalDate;
public class Contact {

    private Long id;
    private String name;
    private String email;
    private String address;
    private LocalDate birthdate;
    //private Calendar dataNascimento;

    public Contact(Long id, String name, String email, String address, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.birthdate = birthdate;
    }

    public void clone(Contact contact){
        this.name = contact.name;
        this.email = contact.email;
        this.address = contact.address;
        this.birthdate = contact.birthdate;        
    }

    public LocalDate getDataNascimento() {
        return birthdate;
    }

    public void setDataNascimento(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }
}
