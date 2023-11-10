package com.binarfud.backend.example;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
public class PdfController {

    @GetMapping(value = "pdf",produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPdfFile() throws IOException {
        return generatePdf();
    }

    private byte[] generatePdf() throws IOException {
        String htmlStr = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Invoice</title>\n" +
                "    <style>\n" +
                "        /* CSS styling seperti sebelumnya */\n" +
                "\n" +
                "        .sender-recipient {\n" +
                "            display: flex;\n" +
                "            justify-content: space-between;\n" +
                "        }\n" +
                "\n" +
                "        .sender, .recipient {\n" +
                "            width: 45%;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"invoice\">\n" +
                "        <div class=\"invoice-header\">\n" +
                "            <h1>Invoice</h1>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"sender-recipient\">\n" +
                "            <div class=\"sender\">\n" +
                "                <h3>Sender</h3>\n" +
                "                <p>John Doe</p>\n" +
                "                <p>123 Main Street</p>\n" +
                "                <p>Cityville, USA</p>\n" +
                "                <p>Email: john.doe@example.com</p>\n" +
                "            </div>\n" +
                "            <div class=\"recipient\">\n" +
                "                <h3>Recipient</h3>\n" +
                "                <p>Jane Smith</p>\n" +
                "                <p>456 Oak Avenue</p>\n" +
                "                <p>Townsville, USA</p>\n" +
                "                <p>Email: jane.smith@example.com</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <!-- Sisa konten seperti sebelumnya -->\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();

        HtmlConverter.convertToPdf(new ByteArrayInputStream(htmlStr.getBytes()), outputStream, converterProperties);
        
        return outputStream.toByteArray();
    }

}
