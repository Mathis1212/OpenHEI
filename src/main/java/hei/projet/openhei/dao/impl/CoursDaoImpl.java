package hei.projet.openhei.dao.impl;

import hei.projet.openhei.dao.CoursDao;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;
import hei.projet.openhei.service.DataSourceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDaoImpl implements CoursDao {
    //Appel de l'instance Log4j2
    static final Logger LOGGER = LogManager.getLogger();

    //Création de l'instance CoursDao
    private static class ServiceHolder {
        private final static CoursDao instance = new CoursDaoImpl();
    }

    //Création de la methode getInstance pour récuperer les méthodes de CoursDaoImpl
    public static CoursDao getInstance() {
        CoursDao instance = ServiceHolder.instance;
        return instance;
    }

    //Méthode de création d'un objet cours à la récupération des informations de la BDD
    @Override
    public Cours createCoursFromResultSet(ResultSet resultSelect) throws SQLException {
        LOGGER.info("Création d'un objet cours avec les informations récupérées de la BDD");
        return new Cours(resultSelect.getString("nom_cours"), resultSelect.getString("url_cours"));
    }

    //Méthode qui récupère l'ensemble de la liste des cours de la BDD
    @Override
    public List<Cours> ListCour() {
        List<Cours> list = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 Statement statement = cnx.createStatement();
                 ResultSet resultSelect = statement.executeQuery("SELECT * FROM cours ")) {
                while (resultSelect.next()) {
                    list.add(createCoursFromResultSet(resultSelect));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Erreur dans la transmission avec la BDD lors de la récupération de la liste des cours :", e);
        }
        return list;
    }

    //Méthode qui récupère le nom du cours depuis la BDD en fonction de son id
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
            LOGGER.error("Erreur dans la transmission avec la BDD lors de la récupération du nom du cours :", e);
        }
        return nom;
    }

    //Méthode qui récupère le nom de la matière auxquelle le cours appartient
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
            LOGGER.error("Erreur dans la transmission avec la BDD lors de la récupération de la matière :", e);
        }
        return mat;
    }

    //Méthode qui permet à l'admin d'ajouter un cours à la BDD
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
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error("Erreur dans la transmission avec la BDD lors de l'ajout du cours :", e);
        }
    }

    //Méthode qui permet à l'admin de supprimer un cours de la BDD
    @Override
    public Integer deleteCoursFromDB(String url_cours) {
        String sqlQuery = "DELETE FROM cours WHERE cours.url_cours=?";
        int row = 0;
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, url_cours);
                row = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error("Erreur dans la transmission avec la BDD lors de la suppression du cours :", e);
        }
        return row;
    }

    //Méthode qui permet à l'admin de mettre un jours un cours
    @Override
    public Integer updateCoursFromDB(String urlcoursToUpdate, String nom_cours, String url_cours) {
        String sqlQuery = "UPDATE projet_OpenHEI.cours SET nom_cours=?, url_cours=? WHERE cours.url_cours=?";
        int row = 0;
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, nom_cours);
                preparedStatement.setString(2, url_cours);
                preparedStatement.setString(3, urlcoursToUpdate);
                row = preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error("Erreur dans la transmission avec la BDD lors de la mise à jour du cours :", e);
        }
        return row;
    }

    //Méthode qui permet de vérifier si un cours éxiste dans la BDD grâce à son url (contrainte unique en BDD)
    @Override
    public boolean ExistCours(String url_cours) throws SQLException {
        boolean resultat = false;
        String sql = "SELECT * FROM cours WHERE cours.url_cours=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setString(1, url_cours);
                try (ResultSet result = preparedStatement.executeQuery()) {
                    if (result.next()) {
                        resultat = true;
                    }
                }
            }
        }catch (SQLException e) {
            LOGGER.error("Erreur dans la transmission avec la BDD lors de la recherche du cours :", e);
        }
        return resultat;
    }
}