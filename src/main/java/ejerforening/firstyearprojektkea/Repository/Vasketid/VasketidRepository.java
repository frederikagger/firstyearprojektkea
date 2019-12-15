package ejerforening.firstyearprojektkea.Repository.Vasketid;

import ejerforening.firstyearprojektkea.Model.Vasketider.Vasketid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Frede
 */

@Repository
public class VasketidRepository implements IVasketidRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**Denne metode returnere alle vasketider som ikke er booket
     *
     * @return
     */

    @Override
    public List<Vasketid> hentLedigeVasketider() {
        String sql = "SELECT * FROM database_first_year_projekt.vasketid WHERE erBooket=FALSE";
        RowMapper<Vasketid> rowMapper = new BeanPropertyRowMapper<>(Vasketid.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    /** Denne metode returnere en vasketid med et givent vaskeid
     * @param vaskeid
     * @return
     */
    @Override
    public Vasketid findbyVaskeId(int vaskeid) {
        String sql = "SELECT * FROM database_first_year_projekt.vasketid WHERE vaskeId = ? LIMIT 1";
        RowMapper<Vasketid> rowMapper = new BeanPropertyRowMapper<>(Vasketid.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,vaskeid);
    }

    /** Denne metoder opdatere en vasketid med parametre fra en given vasketid
     *
     * @param vasketid
     */
    @Override
    public void book(Vasketid vasketid){
        String sql = "update vasketid set erBooket=true, lejlighedsid=? where vaskeId=?";
        jdbcTemplate.update(sql,vasketid.getLejlighedsId(), vasketid.getVaskeId());
    }

    @Override
    public boolean sletBooking() {
        return false;
    }

    @Override
    public List<Vasketid> hentBookedeVasketider() {
        String sql = "SELECT * FROM database_first_year_projekt.vasketid WHERE erBooket=true";
        RowMapper<Vasketid> rowMapper = new BeanPropertyRowMapper<>(Vasketid.class);
        return jdbcTemplate.query(sql, rowMapper);
    }
}