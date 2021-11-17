package hei.projet.openhei.exception;

public class UserFoundException extends Exception{
    public UserFoundException(){
        super("L'utilisateur existe déjà !");
    }
}

