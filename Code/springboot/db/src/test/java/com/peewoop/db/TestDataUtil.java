package com.peewoop.db;


import com.peewoop.db.domain.Author;
import com.peewoop.db.domain.Book;

public final class TestDataUtil {

    private TestDataUtil(){}

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("John Carl Atillo")
                .age(21)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Ken Andre Villafuerte")
                .age(22)
                .build();
    }

    public static Book createTestBookDataA() {
        return Book.builder()
                .isbn("123-456-789")
                .title("Percy Jackson - The Lightning Thief")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookDataB() {
        return Book.builder()
                .isbn("987-654-321")
                .title("Percy Jackson - Sea of Monster")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookDataC() {
        return Book.builder()
                .isbn("321-654-987")
                .title("Percy Jackson - Titan's Curse")
                .authorId(1L)
                .build();
    }
}
