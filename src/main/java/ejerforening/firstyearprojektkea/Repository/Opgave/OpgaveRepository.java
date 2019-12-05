package ejerforening.firstyearprojektkea.Repository.Opgave;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository implementerer metoderne fra interfacet IOpgaveRepository autowirer jdbctemplate, som bruges til at udfoere sql-statements med.
 *
 * @author Signe
 */
@Repository
public class OpgaveRepository implements IOpgaveRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

     /**
      * BeanPropertyRowMapper er interfacet af RowMapper, som kun kan hente en raekke af gangen fra databasen.
      * Raekken bliver som et object af den type, den har faaet som parameter.
      * Da RowMapper bliver benyttet i flere af metoderne i klassen, er den blevet skubbet ud til at daekke klassescopet.
      */
    RowMapper<OpgaveOversigt> rowmapper = new BeanPropertyRowMapper<>(OpgaveOversigt.class);


    /**
     * Metoden henter alle kolonner fra opgavelejlighed_view.
     * JDBC Template bruges som en query, der bliver sent til databasen og retunere liste med oversigten over Opgaver
     *
     * Med hjaelp af view kan de to tabeller, som opgave og lejlighed er i databasen,
     * retuneres som en samlet klasse af parameter
     * @return result - indeholder listen med OpgaveOversigt
     */
    @Override
    public List<OpgaveOversigt> hentAlle()
    {
        String sql = "SELECT opgavelejlighed_view.opgaveid, opgavelejlighed_view.navn, opgavelejlighed_view.lejlighedsid " +
                "FROM opgavelejlighed_view;";

        List<OpgaveOversigt> result  = jdbcTemplate.query(sql, rowmapper);
        return result;
    }

    /**
     * Metoden sletter opgaver, hvor opgaveidet der var givet med som parameter matcher i tabellen opgave.
     * Der ønskes ingen returvaerdi, da opgaven forsvinder fra hjemmesiden
     * @param opgaveId
     */
    @Override
    public void slet(int opgaveId)
    {
        String sql = "DELETE FROM opgave WHERE opgave.opgaveid = ?;";
        jdbcTemplate.update(sql, opgaveId);
    }
}
