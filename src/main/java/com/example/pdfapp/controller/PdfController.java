package com.example.pdfapp.controller;

import com.example.pdfapp.model.PdfDocument;
import com.example.pdfapp.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @PostMapping("/create")
    public PdfDocument createPdf(@RequestParam String title, @RequestParam String content) throws Exception {
        return pdfService.createPdf(title, content);
    }
}
