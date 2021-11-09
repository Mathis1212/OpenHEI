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
        return new Matiere( resulSelect.getInt("id_matiere"), resulSelect.getString("nom_matiere"),resulSelect.getInt("num_notions"));


    }

    @Override
    public List<Matiere> ListMatiere() {
        List<Matiere> result = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 Statement statement = cnx.createStatement();
                 ResultSet resultSelect = statement.executeQuery("SELECT * FROM matiere ")) {
                while(resultSelect.next()) {
                    result.add(createMatiereFromResultSet(resultSelect));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public String getNom(Integer id) {
        String nom ="";
        String sql = "SELECT nom_matiere  FROM matiere  WHERE id_matiere=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try(ResultSet result = preparedStatement.executeQuery()) {
                    nom=createMatiereFromResultSet(result).getNomMatiere();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nom;
    }


    @Override
    public Integer getNotions(Integer id) {
        Integer nb =null;
        String sql = "SELECT num_notions  FROM matiere  WHERE id_matiere=?";
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
}
