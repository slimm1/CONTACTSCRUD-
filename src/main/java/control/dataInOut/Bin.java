package control.dataInOut;

import model.Agenda;

import java.io.*;

public class Bin extends ReadWrite{
    public void writeInFile(File file, Agenda a){
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(a);
            output.close();
        } catch (IOException e) {
            System.out.println("ERROR I salida " + e.getMessage());;
        }
    }
    public Agenda readFromFile(File file){
        Agenda agenda;
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            agenda = (Agenda) input.readObject();
            input.close();
            return agenda;
        }
        catch(FileNotFoundException fEx){
            System.out.println("ERROR F en flujo entrada --> no existe el fichero " + file.getName());
            return null;
        }
        catch(IOException ioEx){
            System.out.println("ERROR  I entrada " + ioEx.getMessage());
            return null;
        }
        catch(ClassNotFoundException cEx){
            System.out.println("ERROR C " + cEx.getMessage());
            return null;
        }
    }
}

