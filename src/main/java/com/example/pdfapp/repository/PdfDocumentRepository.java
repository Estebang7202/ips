package com.example.pdfapp.repository;

import com.example.pdfapp.model.PdfDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PdfDocumentRepository extends JpaRepository<PdfDocument, Long> {
}
