package control.dataInOut;

import control.saxhandler.AgendaHandler;
import model.Agenda;
import model.Contact;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XmlSax extends ReadWrite{
    @Override
    public void writeInFile(File file, Agenda a) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            Element root = doc.createElement("agenda");
            doc.appendChild(root);
            for(Contact c:a.getContactList()){
                Element contact = doc.createElement("contacto");
                root.appendChild(contact);
                Element name = doc.createElement("nombre");
                name.appendChild(doc.createTextNode(c.getName()));
                contact.appendChild(name);
                Element number = doc.createElement("telefono");
                number.appendChild(doc.createTextNode(String.valueOf(c.getNumber())));
                contact.appendChild(number);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException " + e.getMessage());
        } catch (TransformerConfigurationException e) {
            System.out.println("TransformerConfigurationException " + e.getMessage());
        } catch (TransformerException e) {
            System.out.println("TransformerException " + e.getMessage());
        }
    }
    @Override
    public Agenda readFromFile(File file) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            AgendaHandler handler = new AgendaHandler();
            saxParser.parse(file, handler);
            return new Agenda(handler.getContactList());
        } catch (IOException e) {
            System.out.println("IOException, el sistema no pudo encontrar el archivo --> " + file.getName());
            return null;
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException " + e.getMessage());
            return null;
        } catch (SAXException e) {
            System.out.println("SAXException " + e.getMessage());
            return null;
        }
    }
}
