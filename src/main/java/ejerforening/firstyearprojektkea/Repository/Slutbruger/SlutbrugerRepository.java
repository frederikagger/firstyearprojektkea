package ejerforening.firstyearprojektkea.Repository;

import ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger.SlutbrugerOversigt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SlutbrugerRepository implements ISlutbrugerRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    RowMapper<SlutbrugerOversigt> rowmapper = new BeanPropertyRowMapper<>(SlutbrugerOversigt.class);

    @Override
    public List<SlutbrugerOversigt> hentAlle()
    {
        String sql = "SELECT CONCAT(slutbruger.fornavn, ' ', slutbruger.mellemnavn, ' ', slutbruger.efternavn) AS navn, slutbruger.slutbrugerid, slutbrugeroplysninger.lejlighedsid " +
                "FROM slutbruger INNER JOIN slutbrugeroplysninger ON slutbruger.slutbrugerid = slutbrugeroplysninger.slutbrugeroplysid;";

        List<SlutbrugerOversigt> result  = jdbcTemplate.query(sql, rowmapper);
        return result;
    }

    @Override
    public void slet(int slutbrugerId)
    {
        String sql = "DELETE FROM slutbruger WHERE slutbruger.slutbrugerid = ?;";
        jdbcTemplate.update(sql, slutbrugerId);
    }
}
