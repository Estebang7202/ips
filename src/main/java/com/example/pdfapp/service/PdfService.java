package com.example.pdfapp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.pdfapp.model.PdfDocument;
import com.example.pdfapp.repository.PdfDocumentRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class PdfService {

    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
        "cloud_name", "dul26l4sd",
        "api_key", "644893912335333",
        "api_secret", "B9vTDgb_vZx-9UxXp9Pm6inpEHc"
    ));

    @Autowired
    private PdfDocumentRepository pdfRepository;

    public PdfDocument createPdf(String title, String content) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph(content));
        document.close();

        Map uploadResult = cloudinary.uploader().upload(baos.toByteArray(), 
            ObjectUtils.asMap("resource_type", "raw", "public_id", title + "_" + System.currentTimeMillis()));
        String fileUrl = (String) uploadResult.get("url");

        PdfDocument pdfDoc = new PdfDocument();
        pdfDoc.setTitle(title);
        pdfDoc.setFilePath(fileUrl);
        pdfDoc.setCreatedAt(LocalDateTime.now());
        pdfDoc.setUpdatedAt(LocalDateTime.now());
        pdfDoc.setVersion(1);

        return pdfRepository.save(pdfDoc);
    }
}
