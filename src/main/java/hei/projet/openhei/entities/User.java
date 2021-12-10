package hei.projet.openhei.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private Integer id;
    private String pseudo;
    private String login;
    private String password;
    private java.util.Date date_creation;
    private boolean administrateur;
    private List<String> matsuivie;

    //Premier constructeur de la class User
    public User(String pseudo, String login, String password){
        this.id=0;
        this.pseudo=pseudo;
        this.login=login;
        this.password=password;
        this.date_creation=new Date(System.currentTimeMillis());
        this.matsuivie=new ArrayList<>();
    }

    //Deuxième constructeur de la class User
    public User(String pseudo, String login, String password,boolean admin){
        this.id=0;
        this.pseudo=pseudo;
        this.login=login;
        this.password=password;
        this.date_creation=new Date(System.currentTimeMillis());
        this.administrateur=admin;
        this.matsuivie=new ArrayList<>();
    }
    public User(Integer id, String pseudo, String login, String password,boolean admin){
        this.id=id;
        this.pseudo=pseudo;
        this.login=login;
        this.password=password;
        this.date_creation=new Date(System.currentTimeMillis());
        this.administrateur=admin;
        this.matsuivie=new ArrayList<>();
    }


    //Getters
    public String getUserlogin(){return this.login;}
    public boolean getstatus(){return this.administrateur;}
    public Integer getId(){return this.id;}
    public String getPseudo(){return this.pseudo;}
    public String getUserpassword(){return this.password;}

    //Setters
    public void setUserpassword(String password){this.password=password;}
    public void setUserId(Integer id){this.id=id;}
    public void setPseudo(String pseudo){this.pseudo=pseudo;}
    public void setUserlogin(String login){this.login=login;}
    public void setUserAdmin(boolean admin){this.administrateur=admin;}

}
