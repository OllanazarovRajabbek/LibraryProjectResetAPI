package com.example.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookDTO {
//  id,title,author, publishYear
    private String id;
    private String title;
    private String author;
    private LocalDate publishYear;
}
