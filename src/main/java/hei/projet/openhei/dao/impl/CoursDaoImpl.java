package hei.projet.openhei.dao.impl;

import hei.projet.openhei.dao.CoursDao;
import hei.projet.openhei.entities.Cours;
import hei.projet.openhei.entities.Matiere;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursDaoImpl implements CoursDao {
    public Cours createCoursFromResultSet(ResultSet resultSelect) throws SQLException {
        return new Cours(resultSelect.getString("nom_cours"));
    }

    @Override
    public List<Cours> ListCour() {
        return null;
    }

    @Override
    public String getNom(Integer id) {
        return null;
    }

    @Override
    public Matiere getMatiere(Integer id) {
        return null;
    }
}
