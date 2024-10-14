package fiit.sipvs.zadanie.service;

import fiit.sipvs.zadanie.model.NotarizationForm;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

@Service
public class FormService {


    public Resource saveXml(NotarizationForm notarizationForm) throws JAXBException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JAXBContext jaxbContext = JAXBContext.newInstance(NotarizationForm.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.marshal(notarizationForm, outputStream);

        byte[] xmlBytes = outputStream.toByteArray();

        return new ByteArrayResource(xmlBytes);
    }

    public void validateXml(NotarizationForm notarizationForm) throws SAXException, IOException, JAXBException {
        // ulozenie xml do form2 a porovnanie noci xsd
        String xmlFile = "form2.xml";

        Resource xml = saveXml(notarizationForm);
        InputStream inputStream = xml.getInputStream();
        OutputStream outputStream = new FileOutputStream(xmlFile);

        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File("form.xsd"));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File(xmlFile)));
    }

    public void transformToHTML(OutputStream output) throws TransformerException {
        File xmlFile = new File("notarization.xml");
        File xsltFile = new File("form.xsl");
        Source xslt = new StreamSource(xsltFile);
        Source xml = new StreamSource(xmlFile);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(xslt);
        Result html = new StreamResult(output);

        transformer.transform(xml, html);
    }
}
