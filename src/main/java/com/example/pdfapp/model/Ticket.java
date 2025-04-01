package com.example.pdfapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String status; // "OPEN", "CLOSED"
    
    @ManyToOne
    private PdfDocument pdfDocument;
}
