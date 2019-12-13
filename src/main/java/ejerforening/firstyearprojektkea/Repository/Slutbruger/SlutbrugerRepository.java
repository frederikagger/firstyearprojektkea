package ejerforening.firstyearprojektkea.Repository.Slutbruger;

import ejerforening.firstyearprojektkea.Model.Slutbruger.SlutbrugerOversigt;
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

    /**
     * BeanPropertyRowMapper er interfacet af RowMapper, som kun kan hente en raekke af gangen fra databasen.
     * Raekken bliver som et object af den type, den har faaet som parameter.
     * Da RowMapper bliver benyttet i flere af metoderne i klassen, er den blevet skubbet ud til at daekke klassescopet.
     */
    RowMapper<SlutbrugerOversigt> rowmapper = new BeanPropertyRowMapper<>(SlutbrugerOversigt.class);

    /**
     * Metoden henter alle kolonner fra opgavelejlighed_view.
     * JDBC Template bruges som en query, der bliver sent til databasen og retunere liste med oversigten over Opgaver
     *
     * Med hjaelp af view kan de to tabeller, som opgave og lejlighed er i databasen,
     * retuneres som en samlet klasse af parameter
     * @return result - indeholder listen med OpgaveOversigt
     */

    @Override
    public List<SlutbrugerOversigt> hentAlle()
    {
        String sql = "SELECT CONCAT(slutbruger.fornavn, ' ', slutbruger.mellemnavn, ' ', slutbruger.efternavn) AS navn, slutbruger.slutbrugerid, slutbrugeroplysninger.lejlighedsid " +
                "FROM slutbruger INNER JOIN slutbrugeroplysninger ON slutbruger.slutbrugerid = slutbrugeroplysninger.slutbrugeroplysid;";

        List<SlutbrugerOversigt> result  = jdbcTemplate.query(sql, rowmapper);
        return result;
    }

    /**
     * Metoden sletter slutbruger, hvor slutbrugeridet der var givet med som parameter matcher i tabellen slutbruger.
     * Der ønskes ingen returvaerdi, da opgaven forsvinder fra hjemmesiden
     * @param slutbrugerId
     */
    @Override
    public void slet(int slutbrugerId)
    {
        String sql = "DELETE FROM slutbruger WHERE slutbruger.slutbrugerid = ?;";
        jdbcTemplate.update(sql, slutbrugerId);
    }
}
