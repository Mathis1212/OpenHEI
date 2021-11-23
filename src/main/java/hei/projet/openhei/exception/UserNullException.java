package hei.projet.openhei.exception;

public class UserNullException extends Exception{
    public UserNullException(){
        super("L'utilisateur ne peut être null !");
    }
}
