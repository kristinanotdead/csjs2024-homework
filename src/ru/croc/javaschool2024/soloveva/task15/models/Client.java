package ru.croc.javaschool2024.soloveva.task15.models;

import java.util.Objects;

public class Client {
    private int id;
    private String name;
    private String surname;
    private String phoneNumber;

    public Client(int id, String surname, String name, String phoneNumber){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && name.equals(client.name) && surname.equals(client.surname) && phoneNumber.equals(client.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phoneNumber);
    }
}
