package hei.projet.openhei.entities;

import java.util.Date;

public class User {
    private Integer id;
    private String pseudo;
    private String login;
    private String password;
    private java.util.Date date_creation;

    public User(String pseudo, String login, String password){
        super();
        this.id=0;
        this.pseudo=pseudo;
        this.login=login;
        this.password=password;
        this.date_creation=new Date(System.currentTimeMillis());
    }
    public String getUserlogin(){
        return this.login;
    }
    public void setUserlogin(String login){
        this.login=login;
    }
    public String getUsername(){
        return this.pseudo;
    }
    public void setUsername(String pseudo){
        this.pseudo=pseudo;
    }
    public String getUserpassword(){
        return this.password;
    }
    public void setUserpassword(String password){
        this.password=password;
    }
    public void setUserId(Integer id){
        this.id=id;
    }

}
