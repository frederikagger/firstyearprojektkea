package ejerforening.firstyearprojektkea.Repository.Vasketid;

import ejerforening.firstyearprojektkea.Model.Vasketider.Vasketid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VasketidRepository implements IVasketidRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Vasketid> hentVasketider() {
        String sql = "SELECT * FROM database_first_year_projekt.vasketid WHERE erBooketEllerEj=FALSE";
        RowMapper<Vasketid> rowmapper = new BeanPropertyRowMapper<>(Vasketid.class);
        return jdbcTemplate.query(sql, rowmapper);
    }

    @Override
    public boolean book() {
        return false;
    }

    @Override
    public boolean sletBooking() {
        return false;
    }
}
