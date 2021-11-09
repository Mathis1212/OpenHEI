package hei.projet.openhei.entities;

import java.sql.Date;

public class User {
    private Integer id;
    private String pseudo;
    private String login;
    private String password;
    private Date date_creation;

    public User(Integer Id, String Pseudo, String Login, String Password, Date date_creation){
        this.id=Id;

    }

}
