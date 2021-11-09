package hei.projet.openhei.entities;

import java.util.Date;

public class User {
    private String pseudo;
    private String login;
    private String password;
    private java.util.Date date_creation;

    public User(String Pseudo, String Login, String Password){
        this.date_creation=new Date(System.currentTimeMillis());


    }

}
