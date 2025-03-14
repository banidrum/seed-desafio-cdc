package com.dev.eficiente.controller;

import com.dev.eficiente.dto.AuthorDto;
import com.dev.eficiente.entity.Author;
import com.dev.eficiente.repository.AuthorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/author")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping()
    void createAuthor(@Valid @RequestBody AuthorDto authorDto) {
        // In this case, I don't think that a service layer is needed since the validation is already being done through the DTO
        // Also, there is not required business rules at this moment.
        Author author = toEntity(authorDto);

        authorRepository.save(author);
    }

    private Author toEntity(AuthorDto authorDto) {
        Author author = new Author();

        author.setName(authorDto.getName());
        author.setEmail(authorDto.getEmail());
        author.setDescription(authorDto.getDescription());

        return author;
    }
}
