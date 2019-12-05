package ejerforening.firstyearprojektkea.Repository.Opgave;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OpgaveRepository implements IOpgaveRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    RowMapper<OpgaveOversigt> rowmapper = new BeanPropertyRowMapper<>(OpgaveOversigt.class);



    @Override
    public List<OpgaveOversigt> hentAlle()
    {
        String sql = "SELECT opgave.opgaveid, opgave.navn, opgaveLejlighed.lejlighedsid " +
                "FROM opgave INNER JOIN opgaveLejlighed ON opgave.opgaveid = opgaveLejlighed.opgaveid;";

        List<OpgaveOversigt> result  = jdbcTemplate.query(sql, rowmapper);
        return result;
    }


    @Override
    public void slet(int opgaveId)
    {
        String sql = "";
        jdbcTemplate.update(sql, opgaveId);
    }
}
