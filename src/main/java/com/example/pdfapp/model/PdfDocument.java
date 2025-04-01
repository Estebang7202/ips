package com.example.pdfapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class PdfDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String filePath; // URL de Cloudinary
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int version;
}
