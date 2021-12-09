package hei.projet.openhei.dao.impl;
import hei.projet.openhei.dao.CoursDao;
import hei.projet.openhei.dao.MatiereDao;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;
import hei.projet.openhei.service.DataSourceProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatiereDaoImpl implements MatiereDao {
    //Appel de l'instance Log4j2
    static final Logger LOGGER = LogManager.getLogger();

    //Creation de l'instance de MatiereDaoimpl
    private static class ServiceHolder {
        private final static MatiereDaoImpl instance = new MatiereDaoImpl();
    }

    //Creation de la methode pour recuperer l'instance
    public static MatiereDaoImpl getInstance() {
        return ServiceHolder.instance;
    }

    //Recupération de l'instance de CoursDaoImpl
    private CoursDao coursDao = CoursDaoImpl.getInstance();

    //Méthode de création d'un objet matière à la récupération des informations de la BDD
    private Matiere createMatiereFromResultSet(ResultSet resultSelect) throws SQLException {
        LOGGER.info("Création d'un objet matière avec les informations récupérées de la BDD");
        Matiere mat=new Matiere(resultSelect.getInt("id_matiere"),resultSelect.getString("nom_matiere"));
        return mat;
    }

    @Override
    //Méthode qui récupère l'ensemble des matière de la BDD
    public List<Matiere> ListMatiere() {
        List<Matiere> list = new ArrayList<>();

        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 Statement statement = cnx.createStatement();
                 ResultSet resultSelect = statement.executeQuery("SELECT*FROM matiere ")) {
                while (resultSelect.next()) {
                    list.add(createMatiereFromResultSet(resultSelect));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Erreur dans la transmission avec la BDD lors de la récupération de la liste des matières :", e);
        }
        return list;
    }

    //Méthode qui récupère le nom de la matiere dans la BDD à partir de son id
    @Override
    public String getNom(Integer id) {
        String nom = "";
        String sql = "SELECT nom_matiere FROM matiere  WHERE id_matiere=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try (ResultSet result = preparedStatement.executeQuery()) {
                    nom = result.getString("nom_matiere");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Erreur dans la transmission avec la BDD lors de la récupération du nom de la matière :", e);
        }
        return nom;
    }

    //Méthode qui compte le nombre notions dans une matière en BDD à partir de son id
    @Override
    public int getnbCour(Integer id) {
        int nb = 0;
        String sql = "SELECT count(*)  FROM cours join matiere on cours.id_matiere_cours=matiere.id_matiere where matiere.id_matiere=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try (ResultSet result = preparedStatement.executeQuery()) {
                    nb = result.getInt("count(*)");
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Erreur dans la transmission avec la BDD lors de la récupération du nombre de cours dans la matière :", e);
        }
        return nb;
    }

    //Méthode qui récupère la liste des cours en BDD par l'id commun de la matière à laquel ils appartiennent
    @Override
    public List<Cours> getListCour(Integer matiereId) {
        List<Cours> list = new ArrayList<>();
        String sql = "SELECT cours.nom_cours,cours.url_cours FROM cours join matiere on cours.id_matiere_cours=matiere.id_matiere where matiere.id_matiere=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, matiereId);
                try (ResultSet resultSelect = preparedStatement.executeQuery()) {
                    while(resultSelect.next()){
                        list.add(coursDao.createCoursFromResultSet(resultSelect));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Erreur dans la transmission avec la BDD lors de la récupération de la liste des cours de la matière d'id :"+matiereId+ " :", e);
        }
        return list;
    }

    //Méthode qui permet de récupérer l'id d'une matière en BDD à partir de son nom
    @Override
    public Integer getID(String nom) {
        Integer id=0;
        String sql = "SELECT id_matiere FROM matiere  WHERE nom_matiere=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setString(1, nom);
                try (ResultSet result = preparedStatement.executeQuery()) {
                    while(result.next()) {
                        id = result.getInt("id_matiere");
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Erreur dans la transmission avec la BDD lors de la récupération de l'id de la matière de nom :"+nom+ " :", e);
        }
        return id;
    }
}


