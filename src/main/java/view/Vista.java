package view;

import control.Control;

import java.util.Scanner;
public class Vista {
    private final Control controller;
    private final Scanner scanner;
    public Vista(Control controller){
        this.controller=controller;
        scanner = new Scanner(System.in);
    }
    public void menu(){
        System.out.println("1. Agregar contacto");
        System.out.println("2. Actualizar contacto");
        System.out.println("3. Eliminar contacto");
        System.out.println("4. Listar contactos");
        System.out.println("5. Salir y guardar cambios");
        System.out.println("6. Salir sin guardar cambios");
    }
    public void execute(){
        int opcion;
        do{
            menu();
            System.out.println("Elija una opcion");
            opcion = checkAnswer(scanner.nextLine());
            while(opcion>6){
                System.out.println("Por favor, Introduce una opcion válida:");
                opcion = checkAnswer(scanner.nextLine());
            }
            String name;
            switch (opcion){
                case 1:
                    System.out.println("Introduce el nombre del nuevo contacto: ");
                    name = scanner.nextLine();
                    System.out.println("Introduce el numero de telefono del nuevo contacto: ");
                    int number = checkPhoneNumber(scanner.nextLine());
                    if(controller.getAgenda().addContact(name,number)){
                        System.out.println("Contacto añadido exitosamente --> " + name);
                    }
                    else{
                        System.out.println("Ya existe un contacto con el nombre --> " + name);
                    }
                    break;
                case 2:
                    int answer;
                    System.out.println("Introduce el nombre del contacto!!:");
                    name = scanner.nextLine();
                    System.out.println("Que quieres modificar?:");
                    System.out.println("1.- nombre");
                    System.out.println("2.- telefono");
                    System.out.println("-- cualquier otro para volver");
                    answer = checkAnswer(scanner.nextLine());
                    if(answer==1){
                        System.out.println("Introduce el nuevo nombre:");
                        String newName = scanner.nextLine();
                        if(controller.getAgenda().modifyName(name,newName)){
                            System.out.println("Nombre modificado con exito!");
                        }
                        else{
                            System.out.println("Nombre de contacto --> " + name + " no figura en la lista");
                        }
                    }
                    else if(answer == 2){
                        System.out.println("Introduce el nuevo telefono:");
                        int newNumber = checkPhoneNumber(scanner.nextLine());
                        if(controller.getAgenda().modifyNumber(name,newNumber)){
                            System.out.println("Telefono modificado con exito!");
                        }
                        else{
                            System.out.println("Nombre de contacto --> " + name + " no figura en la lista");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Introduce el nombre del contacto para borrarlo !");
                    name = scanner.nextLine();
                    if(controller.getAgenda().removeContact(name)){
                        System.out.println("Contacto eliminado exitosamente --> " + name);
                    }
                    else{
                        System.out.println("No existe un contacto con el nombre --> " + name);
                    }
                    break;
                case 4:
                    System.out.println(controller.getAgenda().listContacts());
                    break;
                case 5:
                    controller.saveChanges();
                    break;
                case 6:
                    System.out.println("ADIOS");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while (opcion<5);
    }
    public int checkPhoneNumber(String number){
        //nueve digitos positivos
        while(!number.matches("\\d{9}")){
            System.out.println("El telefono proporcionado es incorrecto");
            System.out.println("Introduce un telefono válido:");
            number = scanner.nextLine();
        }
        return Integer.parseInt(number);
    }
    public int checkAnswer(String answer){
        //digitos positivos
        while(!answer.matches("\\d+$")){
            System.out.println("Por favor, Introduce una opcion valida:");
            answer = scanner.nextLine();
        }
        return Integer.parseInt(answer);
    }
}