package hei.projet.openhei.dao.impl;

import hei.projet.openhei.dao.CoursDao;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CoursDaoImpl implements CoursDao {
    public List<Cours> listCours() {
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

    private Cours createCoursFromResultSet(ResultSet resultSelect) throws SQLException {
        return new Cours(
                resultSelect.getInt("id_cours"),
                resultSelect.getString("nom_cours"),
                new Matiere(resultSelect.getInt("id_matiere_cours"),resultSelect.getString("nom_matiere")));


    }
}
