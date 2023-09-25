package com.bookstore.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private String description;
    private int publicationYear;
    private String publisher;
    private double price;
    private int quantityAvailable;
    private String language;
    private double averageRating;
}
