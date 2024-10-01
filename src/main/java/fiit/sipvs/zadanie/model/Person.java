package fiit.sipvs.zadanie.model;

import javax.xml.bind.annotation.XmlElement;

public class Person {

    private String name;
    private String email;

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
