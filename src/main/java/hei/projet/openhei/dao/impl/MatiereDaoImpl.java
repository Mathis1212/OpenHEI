package hei.projet.openhei.dao.impl;

import hei.projet.openhei.dao.MatiereDao;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatiereDaoImpl implements MatiereDao {

    private Matiere createMatiereFromResultSet(ResultSet resulSelect) throws SQLException {
        return new Matiere( resulSelect.getInt("id_matiere"), resulSelect.getString("nom_matiere"));
    }

    @Override
    public List<Matiere> ListMatiere() {
        List<Matiere> list = new ArrayList<Matiere>();
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 Statement statement = cnx.createStatement();
                 ResultSet resultSelect = statement.executeQuery("SELECT * FROM matiere ")) {
                while(resultSelect.next()) {
                    list.add(createMatiereFromResultSet(resultSelect));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String getNom(Integer id) {
        String nom ="";
        String sql = "SELECT nom_matiere FROM matiere  WHERE id_matiere=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try(ResultSet result = preparedStatement.executeQuery()) {
                    nom= result.getString("nom_matiere");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nom;
    }

    @Override
    public int getnbCour(Integer id) {
        int nb =0;
        String sql = "SELECT*FROM matiere  WHERE id_matiere=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try(ResultSet result = preparedStatement.executeQuery()) {

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nb;
    }

    @Override
    public List<Cours> getListCour(Integer id) {
        List<Cours> list = new ArrayList<Cours>();
        String sql = "SELECT*FROM matiere  WHERE id_matiere=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try(ResultSet resultSelect = preparedStatement.executeQuery()) {
                    list.add(CoursDaoImpl.createCoursFromResultSet(resultSelect));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // compte nb notions dans matiere
}
