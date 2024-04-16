package ru.croc.javaschool2024.soloveva.task15.models;

import java.util.Objects;

public class Pet {
    private int id;
    private String name;
    private int age;

    public Pet(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return id == pet.id && age == pet.age && Objects.equals(name, pet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }
}
