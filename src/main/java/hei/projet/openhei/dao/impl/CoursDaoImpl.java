package hei.projet.openhei.dao.impl;

import hei.projet.openhei.dao.CoursDao;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDaoImpl implements CoursDao {


    //creattion de l'instance
    private static class ServiceHolder {
        private final static CoursDao instance = new CoursDaoImpl();
    }

    //creation de la methode getInstance pour recuperer les methodes de CoursDaoImpl
    public static CoursDao getInstance() {
        CoursDao instance = ServiceHolder.instance;
        return instance;
    }

    // sert a creer un cour depuis un ResultSet
    @Override
    public Cours createCoursFromResultSet(ResultSet resultSelect) throws SQLException {
        return new Cours(resultSelect.getString("nom_cours"), resultSelect.getString("url_cours"));
    }

    // recupere l'ensemble de la liste des cours de la BDD
    @Override
    public List<Cours> ListCour() {

        List<Cours> list = new ArrayList<>();
        final Logger LOGGER = LogManager.getLogger();


        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 Statement statement = cnx.createStatement();
                 ResultSet resultSelect = statement.executeQuery("SELECT*FROM cours ")) {
                while (resultSelect.next()) {
                    list.add(createCoursFromResultSet(resultSelect));
                }
            }
        } catch (SQLException e) {
            LOGGER.info("Exception : {}", e);
        }
        return list;
    }

    // recupere le nom du cour
    @Override
    public String getNom(Integer id) {
        String nom = "";
        String sql = "SELECT nom_cours FROM cours  WHERE id_cours=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try (ResultSet result = preparedStatement.executeQuery()) {
                    nom = result.getString("nom_cours");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nom;
    }

    //recupere le nom de la matiere auxquelle le cour apartient
    @Override
    public Matiere getMatiere(Integer id) {
        String nom = "";
        Integer id_mat;
        Matiere mat = new Matiere();

        String sql = "SELECT matiere.nom_matiere, matiere.id_matiere FROM matiere join cours on matiere.id_matiere=cours.id_matiere_cours  WHERE cours.id_cours=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try (ResultSet result = preparedStatement.executeQuery()) {
                    while (result.next()) {
                        nom = result.getString("nom_matiere");
                        id_mat = result.getInt("id_matiere");

                        mat.setNomMatiere(nom);
                        mat.setId(id_mat);
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mat;
    }

    @Override
    public void addCour(Cours cours) throws SQLException {
        String nom = cours.getnomCours();
        String url = cours.getUrl();
        Integer id_mat = cours.getId_mat();
        String sql = "Insert into cours (nom_cours, id_matiere_cours,url_cours) values (?,?,?)";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setString(1, nom);
                preparedStatement.setString(3, url);
                preparedStatement.setInt(2, id_mat);
                preparedStatement.executeUpdate();            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}