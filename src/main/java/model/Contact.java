package model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "contacto")
public class Contact implements Comparable<Contact>, Serializable {
    private String name;
    private int number;
    public Contact(String name, int number){
        this.name = name;
        this.number = number;
    }
    public Contact(){}
    public int getNumber() {
        return number;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean equals(Object obj) {
        if(obj==null){return false;}
        if(!(obj instanceof Contact)){return false;}
        Contact c = (Contact)obj;
        return c.getName().equalsIgnoreCase(this.name);
    }
    public int hashCode() {
        return Objects.hash(name);
    }
    public int compareTo(Contact c) {
        return name.compareTo(c.getName());
    }
    public String toString() {
        return this.name + " --> " + number;
    }
}