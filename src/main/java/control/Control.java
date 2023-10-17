package control;

import control.dataInOut.*;
import model.Agenda;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.TreeSet;

public class Control {
    private final String CONFIGPATH = "../files/config.properties";
    private final ReadWrite inOut;
    private final String[] programProps;
    private Agenda a;
    /*Constructor Control:
    1.- Inicia propiedades del fichero config
    2.- inicia la clase de lectura/escritura según el ficherod e propiedades
    3.- Instancia objeto Agenda según el fichero esté vacío o no (es posible que salten excepciones pero el programa continúa)
    * */
    public Control(String fileName, String fileType, String fileParser){
        setProperties(fileName,fileType,fileParser);
        programProps = loadProperties();
        inOut = readWriteMethod();
        if(fileFromPropsPath().length()==0){
            a = new Agenda(new TreeSet<>());
        }
        else {
            a = inOut.readFromFile(fileFromPropsPath());
        }
    }
    //public methods:
    public Agenda getAgenda(){
        return a;
    }
    //método público que guarda los cambios según ciertas condiciones.
    public void saveChanges(){
        File toWrite = fileFromPropsPath();
        if (a.getContactList().isEmpty()){
            System.out.println("No hay cambios que guardar! ADIOS!");
        }
        else if(fileFromPropsPath().length()>0){
            if(a.equals(inOut.readFromFile(toWrite))){
                System.out.println("No hay cambios que guardar! ADIOS!");
            }
        }
        else{
            File backUp = new File(programProps[3]);
            if(backUp.mkdirs()){
                System.out.println("Creado nuevo directorio de versiones");
            }
            backUp = new File(programProps[3]+"/"+getDateTime()+"."+programProps[1]);
            inOut.writeInFile(toWrite,a);
            inOut.writeInFile(backUp,a);
            System.out.println("Los cambios se han guardado! ADIOS!");
        }
    }
    //private methods:
    //método privado para escribir cambios en el fichero de props desde constructor, si no existe el directorio lo crea
    private void setProperties(String fileName, String fileType, String fileParser){
        if(new File("../files").mkdir()){
            System.out.println("Creado nuevo directorio --> files");
        }
        try (OutputStream output = new FileOutputStream(CONFIGPATH)) {
            Properties prop = new Properties();
            // set the properties value
            prop.setProperty("file.name", "../files/"+fileType.toUpperCase()+fileParser+"/"+fileName);
            prop.setProperty("file.type", fileType);
            prop.setProperty("file.parser", fileParser);
            prop.setProperty("backup.path","../files/"+fileType.toUpperCase()+fileParser+"/versionControl");
            // save properties to project root folder
            prop.store(output, null);
        } catch (IOException io) {
            System.out.println("error IO");
        }
    }
    private File fileFromPropsPath(){
        File newFile = new File(programProps[0]);
        String folderPath = newFile.getAbsolutePath().substring(0,newFile.getAbsolutePath().lastIndexOf('\\'));
        if(!newFile.exists()){
            try {
                System.out.println("No existe un fichero con el nombre --> " + newFile.getName());
                System.out.println(new File(folderPath).mkdir()?"creado nuevo directorio":"no se pudo crear el directorio");
                System.out.println(newFile.createNewFile()?"Creado nuevo fichero --> " + newFile.getName():"No se ha podido crear nuevo directorio ");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return newFile;
    }
    //var inout se instancia diferente según el tipo de fichero:
    private ReadWrite readWriteMethod(){
        return switch (programProps[1]) {
            case "bin" -> new Bin();
            case "txt" -> new Txt();
            case "xml" -> xmlParser(programProps[2]);
            default -> null;
        };
    }
    //si el tipo de fichero es xml entra aquí:
    private ReadWrite xmlParser(String parser){
        return switch (parser) {
            case "dom" -> new XmlDom();
            case "sax" -> new XmlSax();
            case "jaxb" -> new XmlJaxb();
            case "xstream" -> new XmlXstream();
            default -> null;
        };
    }
    //método de carga de propiedades del fichero config
    private String[] loadProperties(){
        String[] props = new String[4];
        try (InputStream input = new FileInputStream(CONFIGPATH)) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value
            props[0]=prop.getProperty("file.name");
            props[1]=prop.getProperty("file.type");
            props[2]=prop.getProperty("file.parser");
            props[3]=prop.getProperty("backup.path");
            return props;
        } catch (IOException ex) {
            System.out.println("Error IO");
            return null;
        }
    }
    //método para convertir la fecha/hora actual a string según el formato establecido en el patron
    private String getDateTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddLLLuuuu-HH.mm.ss"));
    }
}
