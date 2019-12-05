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
public class GenForSamRepo implements IGenForSamRepo {

    @Autowired
    JdbcTemplate jdbcTemplate;
    RowMapper rowmapper = new BeanPropertyRowMapper<>(Generalforsamling.class);

    public List<Generalforsamling> hentAlleGeneralforsamlinger(){
        String sql = "SELECT * FROM generalforsamling g, arrangement a WHERE g.arrangementId=a.arrangementId";
        return jdbcTemplate.query(sql,rowmapper);
    }

}
