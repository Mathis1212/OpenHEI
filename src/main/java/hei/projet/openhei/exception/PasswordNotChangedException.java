package hei.projet.openhei.exception;

public class PasswordNotChangedException extends Exception{
    public PasswordNotChangedException(){
        super("Le mot de passe n'a pas pu être changer");
    }
}
