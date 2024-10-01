package fiit.sipvs.zadanie.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "notarization-form")
public class NotarizationForm {

    private String applicantName;
    private String applicantEmail;
    private List<Contract> contracts;
    private String notaryName;
    private String notaryEmail;
    private String notarizationDate;
    private String contractDocument;
    private Boolean receiveConfirmationEmail;

    // Getters and Setters

    @XmlElement(name = "applicant-name")
    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    @XmlElement(name = "applicant-email")
    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    @XmlElement(name = "contracts")
    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    @XmlElement(name = "notary-name")
    public String getNotaryName() {
        return notaryName;
    }

    public void setNotaryName(String notaryName) {
        this.notaryName = notaryName;
    }

    @XmlElement(name = "notary-email")
    public String getNotaryEmail() {
        return notaryEmail;
    }

    public void setNotaryEmail(String notaryEmail) {
        this.notaryEmail = notaryEmail;
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
