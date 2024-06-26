package ru.croc.javaschool2024.soloveva.task13.kitchener;

import java.util.Objects;

/**
 * Повар.
 */
public class Kitchener {
    private final long id;
    private final String name;

    public Kitchener(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kitchener kitchener = (Kitchener) o;
        return Objects.equals(name, kitchener.name);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

