package control.dataInOut;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.Agenda;

import java.io.File;
/*La libreria jaxb se carga desde el framework maven a traves de las dependencias:
Para que funciones es necesario escribir las anotaciones correspondientes en las clases a serializar
se inicia una variable context y se ionstancia con la clase a serializar.
se inicia un Marshaller para escribir y un Unmarshaller para leer
la variable marshaller e unmarshaler tiene metodo marshal y unmarshal
* */
public class XmlJaxb extends ReadWrite{
    @Override
    public void writeInFile(File file, Agenda a) {
        JAXBContext jaxbContext;
        try{
            jaxbContext = JAXBContext.newInstance(Agenda.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(a, file);
        }
        catch(JAXBException e){
            System.out.println("error libreria JAXB de salida de datos " + e.getMessage());
        }
    }

    @Override
    public Agenda readFromFile(File file) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Agenda.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Agenda) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            System.out.println("error libreria JAXB de entrada de datos " + e.getMessage());
            return null;
        }
    }
}
