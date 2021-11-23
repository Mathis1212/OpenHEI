package hei.projet.openhei.dao.impl;
import hei.projet.openhei.dao.CoursDao;
import hei.projet.openhei.dao.MatiereDao;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatiereDaoImpl implements MatiereDao {

    //Creation de l'instance de MatiereDaoimpl
    private static class ServiceHolder {
        private final static MatiereDaoImpl instance = new MatiereDaoImpl();
    }

    //Creation de la methode pour recuperer l'instance
    public static MatiereDaoImpl getInstance() {
        return ServiceHolder.instance;
    }

    // recuperation de l'instance de CoursDaoImpl
    private CoursDao coursDao = CoursDaoImpl.getInstance();

    private Matiere createMatiereFromResultSet(ResultSet resultSelect) throws SQLException {
        Matiere mat=new Matiere(resultSelect.getInt("id_matiere"),resultSelect.getString("nom_matiere"));
        return mat;

    }

    @Override
    // liste l'ensemble des matiere de la BDD
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
            e.printStackTrace();
        }
        return list;
    }

    // recupere le nom de la matiere a partir d'un id
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
            e.printStackTrace();
        }
        return nom;
    }

    // compte nb notions dans une matiere a partir de son id
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
            e.printStackTrace();
        }
        return nb;
    }

    @Override
    public List<Cours> getListCour(Integer matiereId) {
        List<Cours> list = new ArrayList<>();
        String sql = "SELECT cours.nom_cours FROM cours join matiere on cours.id_matiere_cours=matiere.id_matiere where matiere.id_matiere=?";
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
            e.printStackTrace();
        }
        return list;
    }
}

