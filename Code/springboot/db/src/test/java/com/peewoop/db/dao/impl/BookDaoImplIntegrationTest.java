package com.peewoop.db.dao.impl;

import com.peewoop.db.TestDataUtil;
import com.peewoop.db.dao.AuthorDao;
import com.peewoop.db.domain.Author;
import com.peewoop.db.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTest {

    private BookDaoImpl undertest;

    private AuthorDao authorDao;

    @Autowired
    public BookDaoImplIntegrationTest(BookDaoImpl undertest, AuthorDao authorDao){
        this.undertest = undertest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Book book = TestDataUtil.createTestBookDataA();
        book.setAuthorId(author.getId());
        undertest.create(book);

        Optional<Book> result = undertest.findOne(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Book bookA = TestDataUtil.createTestBookDataA();
        bookA.setAuthorId(author.getId());
        undertest.create(bookA);

        Book bookB = TestDataUtil.createTestBookDataB();
        bookB.setAuthorId(author.getId());
        undertest.create(bookB);

        Book bookC = TestDataUtil.createTestBookDataC();
        bookC.setAuthorId(author.getId());
        undertest.create(bookC);

        List<Book> result = undertest.find();

        assertThat(result).hasSize(3).containsExactly(bookA, bookB, bookC);
    }

    @Test
    public void testThatBookCanBeCreatedAndUpdated(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Book book = TestDataUtil.createTestBookDataA();
        book.setAuthorId(author.getId());
        undertest.create(book);

        book.setTitle("UPDATED");
        undertest.update(book.getIsbn(), book);

        Optional<Book> result = undertest.findOne(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatBookCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthorA();
        authorDao.create(author);

        Book book = TestDataUtil.createTestBookDataA();
        book.setAuthorId(author.getId());
        undertest.create(book);

        undertest.delete(book.getIsbn());

        Optional<Book> result = undertest.findOne(book.getIsbn());

        assertThat(result).isNotPresent();

    }
}
