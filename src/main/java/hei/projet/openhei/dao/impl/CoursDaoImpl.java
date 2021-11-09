package hei.projet.openhei.dao.impl;

import hei.projet.openhei.dao.CoursDao;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDaoImpl implements CoursDao {

    private Cours createCoursFromResultSet(ResultSet resultSelect) throws SQLException {
        return new Cours(
                resultSelect.getInt("id_cours"),
                resultSelect.getString("nom_cours"),
                new Matiere(resultSelect.getInt("id_matiere_cours"),resultSelect.getString("nom_matiere")));


    }

    @Override
    public List<Cours> ListCour() {
        List<Cours> result = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 Statement statement = cnx.createStatement();
                 ResultSet resultSelect = statement.executeQuery("SELECT * FROM cours JOIN matiere ON matiere.id_matiere = cours.id_matiere_cours ORDER BY nom_cours")) {
                while(resultSelect.next()) {
                    result.add(createCoursFromResultSet(resultSelect));
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
        String sql = "SELECT nom_cours  FROM cour  WHERE id_cour=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try(ResultSet result = preparedStatement.executeQuery()) {
                    nom=createCoursFromResultSet(result).getnomCours();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nom;
    }

    @Override
    public Matiere getMatiere(Integer id) {
        Matiere mat = new Matiere();
        String sql = "SELECT matiere.nom_matiere JOIN cour on matiere.id_matiere= cour.id_matiere_cour WHERE id_cour=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try(ResultSet result = preparedStatement.executeQuery()) {
                    mat=createCoursFromResultSet(result).getMatiere();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mat;
    }
}
