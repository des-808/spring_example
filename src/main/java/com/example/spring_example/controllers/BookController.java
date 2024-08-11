package com.example.spring_example.controllers;

import com.example.spring_example.models.Book;
import com.example.spring_example.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "pages/index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "pages/create-book";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book != null) {
            model.addAttribute("book", book);
            return "pages/edit-book";
        }
        return "pages/index";
    }

    @PostMapping
    public String createBook(Book book) {
        bookRepository.save(book);
        return "redirect:/api/books";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, Book bookDetails) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book != null) {
            book.setIsbn(bookDetails.getIsbn());
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setYear(bookDetails.getYear());
            return "redirect:/api/books";
        }
        return "pages/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/api/books";
    }
}
