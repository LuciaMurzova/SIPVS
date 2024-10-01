package fiit.sipvs.zadanie.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "notarization-form")
@XmlType(propOrder = { "applicant", "contracts", "notaryPublic", "notarizationDate", "contractDocument", "receiveConfirmationEmail" })
public class NotarizationForm {

    private Person applicant;
    private List<Contract> contracts;
    private Person notaryPublic;
    private String notarizationDate;
    private String contractDocument;
    private Boolean receiveConfirmationEmail;

    // Getters and Setters

    @XmlElement(name = "applicant")
    public Person getApplicant() {
        return applicant;
    }

    public void setApplicant(Person applicant) {
        this.applicant = applicant;
    }

    @XmlElementWrapper(name = "contracts")
    @XmlElement(name = "contract")
    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    @XmlElement(name = "notary-public")
    public Person getNotaryPublic() {
        return notaryPublic;
    }

    public void setNotaryPublic(Person notaryPublic) {
        this.notaryPublic = notaryPublic;
    }

    @XmlElement(name = "notarization-date")
    public String getNotarizationDate() {
        return notarizationDate;
    }

    public void setNotarizationDate(String notarizationDate) {
        this.notarizationDate = notarizationDate;
    }

    @XmlElement(name = "contract-agreement-document")
    public String getContractDocument() {
        return contractDocument;
    }

    public void setContractDocument(String contractDocument) {
        this.contractDocument = contractDocument;
    }

    @XmlElement(name = "receive-confirmation-email")
    public Boolean getReceiveConfirmationEmail() {
        return receiveConfirmationEmail;
    }

    public void setReceiveConfirmationEmail(Boolean receiveConfirmationEmail) {
        this.receiveConfirmationEmail = receiveConfirmationEmail;
    }
}
