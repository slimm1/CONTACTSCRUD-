package model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.TreeSet;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement()
public class Agenda implements Serializable {
    @XmlElement(name = "contacto", type = Contact.class)
    private TreeSet<Contact> contactList;
    public Agenda(TreeSet<Contact> contactList){
        this.contactList = contactList;
    }
    public Agenda(){}
    public TreeSet<Contact> getContactList(){
        return contactList;
    }
    public boolean addContact(String name, int number) {
        if (getContactByName(name) == null) {
            contactList.add(new Contact(name, number));
            return true;
        }
        return false;
    }
    public boolean removeContact(String name){
        Contact c = getContactByName(name);
        if(c != null){
            contactList.remove(c);
            return true;
        }
        return false;
    }
    public String listContacts(){
        StringBuilder out = new StringBuilder();
        for(Contact c: contactList){
            out.append(c.toString());
            out.append(System.getProperty("line.separator"));
        }
        return out.toString();
    }
    public boolean modifyName(String name, String newName){
        Contact c = getContactByName(name);
        if(c != null){
            if(c.getName().equalsIgnoreCase(newName)){
                return false;
            }
            else{
                c.setName(newName);
                return true;
            }
        }
        return false;
    }
    public boolean modifyNumber(String name, int newNumber){
        Contact c = getContactByName(name);
        if(c != null){
            if(c.getNumber()==newNumber){
                return false;
            }
            else{
                c.setNumber(newNumber);
                return true;
            }
        }
        return false;
    }
    public boolean equals(Object o){
        if(o == null){return false;}
        if(!(o instanceof Agenda)){return false;}
        Agenda a = (Agenda)o;
        if(contactList.equals(a.getContactList())){return true;}
        return false;
    }
    private Contact getContactByName(String name){
        for(Contact c:contactList){
            if(c.getName().equalsIgnoreCase(name)){
                return c;
            }
        }
        return null;
    }
}
