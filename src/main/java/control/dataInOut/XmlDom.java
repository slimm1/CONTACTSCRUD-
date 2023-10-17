package control.dataInOut;

import model.Agenda;
import model.Contact;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.TreeSet;
/*Dom lee y escribe los documentos más facilmente si conocemos la estructura xml.
Para lectura: instanciar DocumentBuilderFactory y DocumentBuilder. Crear variable Document usando el builder
NodeList para crear un bucle for y recorrer los nodos del documento xml. getElementsByTagName("") --> Javascript
dentro del for instanciamos Node con un elemento contenido en NodeList
se hace un condicional para comprobar.....
y ya por fin obtenemos los datos de dentro de los elementos con getElement...item()...getTextContent();
* */
public class XmlDom extends ReadWrite{
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
            //clases e instancias para transformar a xml:
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
    public Agenda readFromFile(File file){
        TreeSet<Contact> contactList = new TreeSet<>();
        String name;
        int number;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            //huecos vacíos, errores:
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("contacto");
            for(int i = 0; i < nList.getLength(); i++){
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    name = element.getElementsByTagName("nombre").item(0).getTextContent();
                    number = Integer.parseInt(element.getElementsByTagName("telefono").item(0).getTextContent());
                    contactList.add(new Contact(name,number));
                }
            }
            return new Agenda(contactList);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
            return null;
        } catch (SAXException e) {
            System.out.println("SAXException " + e.getMessage());
            return null;
        }
    }
}
