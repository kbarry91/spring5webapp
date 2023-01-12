package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author john = new Author("John","Doe");
        Book book = new Book("Rich Dad Poor Dad","123");

        john.getBooks().add(book);
        book.getAuthors().add(john);
        authorRepository.save(john);
        bookRepository.save(book);

        Author tom = new Author("Tom","Hanks");
        Book book2 = new Book("Make your bed","121");

        tom.getBooks().add(book2);
        book2.getAuthors().add(tom);

        authorRepository.save(tom);
        bookRepository.save(book2);

        System.out.println("<====== Started Bootstrap ======>");
        System.out.println("#Books: "+ bookRepository.count());
        System.out.println("#Authors: "+ authorRepository.count());

    }
}

