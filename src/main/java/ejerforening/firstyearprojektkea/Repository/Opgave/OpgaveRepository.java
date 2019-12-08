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
import java.time.LocalTime;
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
        String sql = "SELECT opgaveregister_view.opgaveid, opgaveregister_view.navn, opgaveregister_view.lejlighedsid FROM opgaveregister_view";

        List<OpgaveOversigt> resultHentAlle = jdbcTemplate.query(sql, rowmapper);
        return resultHentAlle;
    }

    @Override
    public List<Opgave> findOpgave(int opgaveId)
    {
        RowMapper<Opgave> rowmapper = new BeanPropertyRowMapper<>(Opgave.class);
        String sql = "SELECT opgaveregister_view.opgaveid, opgaveregister_view.navn, opgaveregister_view.lejlighedsid FROM opgaveregister_view WHERE opgaveId=?";
        List<Opgave> resultFindOpgave = jdbcTemplate.query(sql, rowmapper, opgaveId);
        return resultFindOpgave;
    }

    @Override
    public List<OpgaveOplysninger> findValgteOpgave(int opgaveId)
    {
        RowMapper<OpgaveOplysninger> rowmapper = new BeanPropertyRowMapper<>(OpgaveOplysninger.class);
        String sql = "SELECT * FROM opgaveregister_view WHERE opgaveId=?";
        List<OpgaveOplysninger> resultfindValgteOpgave = jdbcTemplate.query(sql, rowmapper, opgaveId);
        return resultfindValgteOpgave;
    }

    @Override
    public int erOpgaveOprettetFoer(String navn, LocalDate oprettelsesDato)
    {
        RowMapper rowmapper = new BeanPropertyRowMapper<>(Opgave.class);
        String sql = "SELECT opgaveid FROM opgave WHERE navn=? AND oprettelsesDato=?";
        List<Opgave> opgaveList = (List<Opgave>) jdbcTemplate.query(sql, rowmapper, navn, oprettelsesDato);

        int opgaveId = opgaveList.get(0).getOpgaveId();
        if(opgaveList.size() !=1){
            opgaveId = -1;
        }
        return opgaveId;
    }



    @Override
    public boolean opretOpgave(Opgave opgave)
    {
        String navn = opgave.getNavn();
        LocalDate oprettelsesDato = LocalDate.now();
        String sql = "INSERT INTO opgave(navn, oprettelsesDato) VALUES(?,?)";
        int opgaveOpdateret = jdbcTemplate.update(sql, navn, oprettelsesDato);

        int opgaveId = erOpgaveOprettetFoer(navn,oprettelsesDato);
        if(opgaveId == -1)
        {
            return false;
        }
        else
            {
                return true;
            }
    }

    @Override
    public boolean opretOplysninger(OpgaveOplysninger opgaveOplysninger, Opgave opgave)
    {
        String navn = opgave.getNavn();
        LocalDate oprettelsesDato = LocalDate.now();
        int opgaveId = erOpgaveOprettetFoer(navn,oprettelsesDato);
        String beskrivelse = opgaveOplysninger.getBeskrivelse();
        int varighed = opgaveOplysninger.getVarighed();
        int svaerhedsgrad = opgaveOplysninger.getSvaerhedsgrad();
        LocalDate startDato = opgaveOplysninger.getStartDato();
        LocalDate slutDato = opgaveOplysninger.getSlutDato();
        LocalDate sidstOpdateret = LocalDate.now();
        String sql = "INSERT INTO opgaveOplysninger(opgaveId, beskrivelse, svaerhedsgrad, startDato, slutDato, sidstOpdateret) VALUES(?,?,?,?,?,?)";
        int opgaveOplysningerOprettet = jdbcTemplate.update(sql, opgaveId, beskrivelse, svaerhedsgrad, startDato, slutDato, sidstOpdateret);

        return opgaveOplysningerOprettet !=0;
    }


    @Override
    public boolean opdaterOpgave(Opgave opgave, OpgaveOplysninger opgaveOplysninger)
    {
        int opgaveId = opgave.getOpgaveId();
        String navn = opgave.getNavn();
        int lejlighedsId = opgave.getLejlighedsId();
        int opgaveOplysningerId = opgaveOplysninger.getOpgaveOplysningerId();
        String beskrivelse = opgaveOplysninger.getBeskrivelse();
        int varighed = opgaveOplysninger.getVarighed();
        int svaerhedsgrad = opgaveOplysninger.getSvaerhedsgrad();

        LocalDate startDato = opgaveOplysninger.getStartDato();
        LocalDate slutDato = opgaveOplysninger.getSlutDato();
        //LocalDate opdateringsDato = opgaveOplysninger.getOpdateringsDato();

        String sql = "CALL SP_opdaterOpgave(?,?,?,?,?,?,?,?,?)";
        int erOpgaveOpdateret = jdbcTemplate.update(sql, opgaveId, navn, lejlighedsId, opgaveOplysningerId, beskrivelse, varighed, svaerhedsgrad, startDato, slutDato);
        return erOpgaveOpdateret == 0;
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
