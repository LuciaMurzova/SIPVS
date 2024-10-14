package fiit.sipvs.zadanie.controller;

import fiit.sipvs.zadanie.model.NotarizationForm;
import fiit.sipvs.zadanie.model.Contract;
import fiit.sipvs.zadanie.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;

@Controller
public class FormController {

    @Autowired
    private FormService formService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("notarizationForm", new NotarizationForm());
        return "index";
    }

    @PostMapping("/saveXml")
    public ResponseEntity<Resource> saveXml(@ModelAttribute NotarizationForm notarizationForm, Model model) {
        model.addAttribute("notarizationForm", notarizationForm);
        //pridanie contract ID
        List<Contract> contracts = notarizationForm.getContracts();
        for (int i = 0; i < contracts.size(); i++) {
            contracts.get(i).setId(i + 1);
        }
        try {
            Resource resource = formService.saveXml(notarizationForm);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=notarization.xml");
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE);

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(resource.contentLength())
                    .body(resource);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/validateXml")
    public String validateXml(@ModelAttribute NotarizationForm notarizationForm, Model model) {
        model.addAttribute("notarizationForm", notarizationForm);

        try {
            formService.validateXml(notarizationForm);
            model.addAttribute("validationResult", "Validácia prebehla úspešne");
        } catch (SAXException e) {
            model.addAttribute("validationResult", "Validácia nebola úspešná: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException | JAXBException e) {
            model.addAttribute("validationResult", "Validácia nebola úspešná: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return "index";
    }

    @PostMapping("/transformToHtml")
    public ResponseEntity<InputStreamResource> transformToHtml() throws Exception {
        File htmlFile = File.createTempFile("output", ".html");

        try (FileOutputStream fos = new FileOutputStream(htmlFile)) {
            formService.transformToHTML(fos);
        }

        InputStreamResource resource = new InputStreamResource(new FileInputStream(htmlFile));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.html");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(htmlFile.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/notarization.xml")
    public ResponseEntity<Resource> getNotarizationFile() throws MalformedURLException {
        Path filePath = Paths.get("notarization.xml").toAbsolutePath().normalize();
        Resource resource = new UrlResource(filePath.toUri());
        return ResponseEntity.ok().body(resource);
    }

    @GetMapping("/form.xsd")
    public ResponseEntity<Resource> getFormXsd() throws MalformedURLException {
        Path filePath = Paths.get("form.xsd").toAbsolutePath().normalize();
        Resource resource = new UrlResource(filePath.toUri());
        return ResponseEntity.ok().body(resource);
    }

    @GetMapping("/form.xsl")
    public ResponseEntity<Resource> getFormXsl() throws MalformedURLException {
        Path filePath = Paths.get("form.xsl").toAbsolutePath().normalize();
        Resource resource = new UrlResource(filePath.toUri());
        return ResponseEntity.ok().body(resource);
    }

    @GetMapping("/form.pdf")
    public ResponseEntity<Resource> getFormPDF() throws MalformedURLException {
        Path filePath = Paths.get("form.pdf").toAbsolutePath().normalize();
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() || resource.isReadable()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF) // Set the content type to PDF
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"form.pdf\"") // Inline for display, or use "attachment" for download
                    .body(resource);
        } else {
            throw new RuntimeException("File not found or not readable");
        }
    }


    @PostMapping("/sign")
    public ResponseEntity<String> saveSignedXml(@RequestBody String signedXml) {
        // Define where you want to save the signed XML file
        File file = new File("signed-notarization.xml");

        try (FileWriter writer = new FileWriter(file)) {
            // Write the signed XML content to the file
            writer.write(signedXml);
            return ResponseEntity.ok("Signed XML saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving signed XML.");
        }
    }
}
