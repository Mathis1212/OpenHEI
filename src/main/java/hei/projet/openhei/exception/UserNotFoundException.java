package hei.projet.openhei.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("L'utilisateur n'éxiste pas !");
    }
}
