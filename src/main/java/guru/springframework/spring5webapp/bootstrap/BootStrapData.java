package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author john = new Author("John", "Doe");
        Book book = new Book("Rich Dad Poor Dad", "123");

        john.getBooks().add(book);
        book.getAuthors().add(john);
        authorRepository.save(john);
        bookRepository.save(book);

        Author tom = new Author("Tom", "Hanks");
        Book book2 = new Book("Make your bed", "121");

        tom.getBooks().add(book2);
        book2.getAuthors().add(tom);

        Publisher penguin = new Publisher("Penguin", "30 apt", "Galway", "Connaught", "H91");
        publisherRepository.save(penguin);

        book.setPublisher(penguin);
        penguin.getBooks().add(book);

        book2.setPublisher(penguin);
        penguin.getBooks().add(book2);

        authorRepository.save(tom);
        bookRepository.save(book2);
        publisherRepository.save(penguin);


        // test output
        System.out.println("<====== Started Bootstrap ======>");
        System.out.println("#Books: " + bookRepository.count());
        System.out.println("#Authors: " + authorRepository.count());
        System.out.println("Publishers: "+ publisherRepository.count());
        System.out.println("Publisher # of books [penguin]: "+ penguin.getBooks().size());


    }
}

