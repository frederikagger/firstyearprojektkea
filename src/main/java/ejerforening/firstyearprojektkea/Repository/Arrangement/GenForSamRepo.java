package ejerforening.firstyearprojektkea.Repository.Arrangement;

import ejerforening.firstyearprojektkea.Model.Arrangement.Arrangement;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenForSamRepo implements IArrangementRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    RowMapper rowmapper = new BeanPropertyRowMapper<>(Arrangement.class);

    public List<Generalforsamling> hentAlleArrangementer(){
        String sql = "SELECT * FROM generalforsamling";
        return jdbcTemplate.query(sql,rowmapper);
    }

}
