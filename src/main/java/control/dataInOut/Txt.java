package control.dataInOut;

import model.Agenda;
import model.Contact;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class Txt extends ReadWrite{
    public void writeInFile(File file, Agenda a){
        Set<Contact> list = a.getContactList();
        StringBuilder toWrite = new StringBuilder();
        FileWriter output;
        for(Contact c: list){
            toWrite.append(c.getName()).append(":").append(c.getNumber());
            toWrite.append(System.getProperty("line.separator"));
        }
        try {
            output = new FileWriter(file);
            output.write(toWrite.toString());
            output.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("ERROR F salida");
        }
        catch (IOException e) {
            System.out.println("ERROR I salida");
        }
    }
    public Agenda readFromFile(File file){
        TreeSet<Contact> list = new TreeSet<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            String[] fields;
            while((line = in.readLine())!=null){
                fields = line.split(":");
                list.add(new Contact(fields[0], Integer.parseInt(fields[1])));
            }
            in.close();
            return new Agenda(list);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR F en flujo entrada --> no existe el fichero " + file.getName());
            return null;
        } catch (IOException e) {
            System.out.println("Error I entrada");
            return null;
        }
    }
}
