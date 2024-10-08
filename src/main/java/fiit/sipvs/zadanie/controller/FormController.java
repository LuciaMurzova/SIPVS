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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.List;


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
}
