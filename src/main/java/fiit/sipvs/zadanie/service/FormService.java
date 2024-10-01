package fiit.sipvs.zadanie.service;

import fiit.sipvs.zadanie.model.NotarizationForm;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;

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

}
