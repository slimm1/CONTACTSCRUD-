import control.Control;
import view.Vista;

public class Main {
    public static void main(String[] args) {
        Control c = new Control("contacts.xml","xml", "jaxb");
        Vista v = new Vista(c);
        v.execute();
    }
}