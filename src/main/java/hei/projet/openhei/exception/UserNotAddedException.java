package hei.projet.openhei.exception;

public class UserNotAddedException extends Exception{
  public UserNotAddedException(){
    super("Erreur, l'utilisateur n'a pas pu être ajouté");
  }
}