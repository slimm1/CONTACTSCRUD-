package control.dataInOut;

import model.Agenda;

import java.io.File;
import java.util.TreeSet;

public abstract class ReadWrite {
    public abstract void writeInFile(File file, Agenda a);
    public abstract Agenda readFromFile(File file);
}
