package fiit.sipvs.zadanie.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Contract {

    private String contractTitle;
    private String contractType;
    private int id = 0;


    // Getters and Setters

    @XmlElement(name = "contract-title")
    public String getContractTitle() {
        return contractTitle;
    }

    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }

    @XmlElement(name = "contract-type")
    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    @XmlAttribute(name = "contract-id")
    public int getId() {
        return id;
    }
    public void setId(int contractID) {
        this.id = contractID;
    }
}

