package control.dataInOut;

import com.thoughtworks.xstream.XStream;
import model.Agenda;
import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;

//Problemas en la carga de TreeSet al implementar Comparable.
//guardo y cargo arraylist
public class XmlXstream extends ReadWrite{
    public void writeInFile(File file, Agenda a) {
        try {
            XStream x = new XStream();
            FileOutputStream f = new FileOutputStream(file);
            x.toXML(new ArrayList<>(a.getContactList()),f);
            f.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("ERROR F salida");
        } catch (IOException e) {
            System.out.println("ERROR I salida");
        }
    }
    @SuppressWarnings({})
    public Agenda readFromFile(File file) {
        XStream x = new XStream();
        x.allowTypesByWildcard(new String[] { "model.*" }); // Permitir todas las clases en el paquete model
        try {
            ArrayList<Contact> list = (ArrayList<Contact>) x.fromXML(new FileInputStream(file));
            return new Agenda(new TreeSet<>(list));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR F en flujo entrada --> no existe el fichero " + file.getName());
            return null;
        }
    }
}
