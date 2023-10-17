package control.saxhandler;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import model.Contact;

import java.util.TreeSet;
/* Para parsear xml a objetos con SAX es necesario crear nuestra propia clase maejadora:
Hereda de DefaultHandler y debe sobreescribir los métodos, startDocument, startElement, endElement y characters
startElement es llamado cada vez que sax encuentra una etiqueta xml --> instanciar contacto
endElement es llamado cuando encuentra una etiqueta de cierre --> añadir datos a instancia de contacto
characters recoge los valores que hay dentro de los elementos xml
* */
public class AgendaHandler extends DefaultHandler {
    StringBuilder data = new StringBuilder();
    TreeSet<Contact> contactList;
    Contact currentContact;

    public TreeSet<Contact> getContactList() {
        return contactList;
    }

    public void startDocument() {
        contactList = new TreeSet<>();
    }

    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes){
        data.setLength(0);
        if(qName.equalsIgnoreCase("contacto")){
            currentContact = new Contact();
        }
    }
    public void endElement(String uri,
                           String localName,
                           String qName){
        if(qName.equalsIgnoreCase("nombre")){
            currentContact.setName(data.toString());
        }
        if(qName.equalsIgnoreCase("telefono")){
            currentContact.setNumber(Integer.parseInt(data.toString()));
        }
        if(qName.equalsIgnoreCase("contacto")){
            contactList.add(currentContact);
        }
    }
    public void characters(char[] ch, int start, int length) {
        data.append(ch, start, length);
    }
}
