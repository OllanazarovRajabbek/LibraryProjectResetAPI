package com.example.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentDTO {
//    id, name, surname,phone, createdDate
    private String id;
    private String name;
    private String surname;
    private String phone;
    private LocalDate createdDate;

}
