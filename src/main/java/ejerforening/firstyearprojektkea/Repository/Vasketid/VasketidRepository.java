package ejerforening.firstyearprojektkea.Repository.Vasketid;

import ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger.Slutbruger;
import ejerforening.firstyearprojektkea.Model.Lejlighed.Lejlighed;
import ejerforening.firstyearprojektkea.Model.Vasketider.Vasketid;
import org.apache.catalina.User;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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
    public Vasketid findbyVaskeId(int vaskeid) {
        String sql = "SELECT * FROM database_first_year_projekt.vasketid WHERE vaskeId = ? LIMIT 1";
        RowMapper<Vasketid> rowMapper = new BeanPropertyRowMapper<>(Vasketid.class);
        return jdbcTemplate.queryForObject(sql,rowMapper,vaskeid);
    }

    @Override
    public void book(Vasketid vasketid){
        //String sql = "UPDATE vasketid SET erBooketEllerEj=TRUE, lejlighedsId=? WHERE vaskeId=?";
       // jdbcTemplate.update(sql, vasketid.getLejlighedsId(), vasketid.getVaskeId());
        String sql = "update vasketid set erBooketEllerEj=true, lejlighedsid=? where vaskeId=?";
        System.out.println(vasketid.getLejlighedsId()+" "+ vasketid.getVaskeId());
        jdbcTemplate.update(sql,vasketid.getLejlighedsId(),vasketid.getVaskeId());
    }

    @Override
    public boolean sletBooking() {
        return false;
    }
}
