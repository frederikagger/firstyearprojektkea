package ejerforening.firstyearprojektkea.Repository.Opgave;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.Opgave;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOplysninger;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository implementerer metoderne fra interfacet IOpgaveRepository autowirer jdbctemplate, som bruges til at udfoere sql-statements med.
 *
 * @author Signe
 */
@Repository
public class OpgaveRepository implements IOpgaveRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;


    /**
     * Metoden henter alle kolonner fra opgavelejlighed_view.
     * JDBC Template bruges som en query, der bliver sent til databasen og retunere liste med oversigten over Opgaver.
     *
     *       * BeanPropertyRowMapper er interfacet af RowMapper, som kun kan hente en raekke af gangen fra databasen.
     *       * Raekken bliver som et object af den type, den har faaet som parameter.
     *
     * Med hjaelp af view kan de to tabeller, som opgave og lejlighed er i databasen,
     * retuneres som en samlet klasse af parameter
     * @return result - indeholder listen med OpgaveOversigt
     */
    @Override
    public List<OpgaveOversigt> hentAlle()
    {
        RowMapper<OpgaveOversigt> rowmapper = new BeanPropertyRowMapper<>(OpgaveOversigt.class);
        String sql = "SELECT opgaveId, navn, lejlighedsId FROM opgaveregister_view";

        List<OpgaveOversigt> resultHentAlle =  jdbcTemplate.query(sql, rowmapper);
        return resultHentAlle;
    }

    @Override
    public OpgaveOplysninger findValgteOpgaveOplysninger(int opgaveId)
    {
        RowMapper<OpgaveOplysninger> rowmapper = new BeanPropertyRowMapper<>(OpgaveOplysninger.class);
        String sql = "SELECT * FROM opgaveregister_view WHERE opgaveId=? LIMIT 1";
        List<OpgaveOplysninger> resultfindValgteOpgaveOplysninger = jdbcTemplate.query(sql, rowmapper, opgaveId);
        return resultfindValgteOpgaveOplysninger.get(0);
    }

    @Override
    public boolean opretOpgave(Opgave opgave)
    {
        String navn = opgave.getNavn();
        LocalDate oprettelsesDato = LocalDate.now();
        String sql = "INSERT INTO opgave(navn, oprettelsesDato) VALUES(?,?)";
        int opgaveOpdateret = jdbcTemplate.update(sql, navn, oprettelsesDato);
        return true;
    }

    @Override
    public boolean opretOplysninger(OpgaveOplysninger opgaveOplysninger)
    {
        int opgaveId = opgaveOplysninger.getOpgaveId();
        String beskrivelse = opgaveOplysninger.getBeskrivelse();
        int varighed = opgaveOplysninger.getVarighed();
        int svaerhedsgrad = opgaveOplysninger.getSvaerhedsgrad();
        LocalDate startDato = opgaveOplysninger.getStartDato();
        LocalDate slutDato = opgaveOplysninger.getSlutDato();
        LocalDate sidstOpdateret = LocalDate.now();
        String sql = "INSERT INTO opgaveOplysninger(opgaveId, beskrivelse, varighed, svaerhedsgrad, startDato, slutDato, sidstOpdateret) VALUES(?,?,?,?,?,?,?)";
        int opgaveOplysningerOprettet = jdbcTemplate.update(sql, opgaveId, beskrivelse, varighed, svaerhedsgrad, startDato, slutDato, sidstOpdateret);

        return true;
    }

    /**
     * Mangler forbindelsen til lejlighedsid
     * @param opgave
     * @param opgaveOplysninger
     * @return
     */

    @Override
    public boolean opdaterOpgave(OpgaveOplysninger opgaveOplysninger)
    {
        int opgaveId = opgaveOplysninger.getOpgaveId();
        int opgaveOplysningerId = opgaveOplysninger.getOpgaveOplysningerId();
        String navn = opgaveOplysninger.getNavn();
        int lejlighedsId = opgaveOplysninger.getLejlighedsId();
        String beskrivelse = opgaveOplysninger.getBeskrivelse();
        int varighed = opgaveOplysninger.getVarighed();
        int svaerhedsgrad = opgaveOplysninger.getSvaerhedsgrad();
        LocalDate startDato = opgaveOplysninger.getStartDato();
        LocalDate slutDato = opgaveOplysninger.getSlutDato();

        String sql = "CALL SP_opdaterOpgave(?,?,?,?,?,?,?,?,?)";
        int erOpgaveOpdateret = jdbcTemplate.update(sql, opgaveId, opgaveOplysningerId, navn, lejlighedsId, beskrivelse, varighed, svaerhedsgrad, startDato, slutDato);
        return true;
    }


    /**
     * Metoden sletter opgaver, hvor opgaveidet der var givet med som parameter matcher i tabellen opgave.
     * Der ønskes ingen returvaerdi, da opgaven forsvinder fra hjemmesiden
     * @param opgaveId
     */
    @Override
    public void slet(int opgaveId)
    {
        String sql = "DELETE FROM opgave WHERE opgave.opgaveId = ?;";
        jdbcTemplate.update(sql, opgaveId);
    }
}
