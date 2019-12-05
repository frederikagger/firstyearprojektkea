package ejerforening.firstyearprojektkea.Repository;

import ejerforening.firstyearprojektkea.Model.Lejlighed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**Oprettet af Frederik Agger og Signe Nørløv Eskildsen
 * d. 28/11/2019
 **/

@Repository
public class LejlighedRepo implements IlejlighedRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Lejlighed> hentAlle() {
        String sql = "SELECT * FROM lejlighed";
        RowMapper<Lejlighed> rowmapper = new BeanPropertyRowMapper<>(Lejlighed.class);
        return jdbcTemplate.query(sql, rowmapper);
    }

    @Override
    public Lejlighed findMedId(int id){
        String sql = "SELECT * FROM lejlighed WHERE id = ?";
        RowMapper<Lejlighed> rowMapper = new BeanPropertyRowMapper<>(Lejlighed.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,id);
    }

    @Override
    public void opret(Lejlighed lejlighed) {
        String sql = "INSERT INTO lejlighed (etage,lejlighedsside) VALUES(?,?)";
        jdbcTemplate.update(sql,lejlighed.getEtage(),lejlighed.isLejlighedsside());
    }

    @Override
    public void sletLejlighed(int id){
        String sql = "DELETE FROM lejlighed WHERE lejlighedsid = ?";
        jdbcTemplate.update(sql,id);
    }
}
