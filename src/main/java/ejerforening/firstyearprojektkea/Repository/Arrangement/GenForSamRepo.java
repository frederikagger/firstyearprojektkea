package ejerforening.firstyearprojektkea.Repository.Arrangement;
import ejerforening.firstyearprojektkea.Model.Arrangement.Arrangement;
import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * @author paivi
 * Repository for generalforsamlinger implementerer metoderne fra interface for generalforsamlinger.
 */
@Repository
public class GenForSamRepo implements IGenForSamRepo {

    /**
     *  Klassen autowirer jdbctemplate, som den bruger i metoderne til at udfoere sql-statements.
     *  BeanPropertyRowMapper (interface er RowMapper) henter en raekke fra databasen som et object af den type,
     *  som den faar som parameter. Der er flere metoder, hvor rowmapperen til Generalforsamling bruges, saa der
     *  laves en faelles instans paa klasseniveuaet.
     *  Klassen faar ogsaa Arraylist af Integer som felt, fordi to metoder skal have adgang til den, saa dens scope
     *  skal vaere op klasseniveauet og ikke lokalt i en metode.
     */
    @Autowired
    JdbcTemplate jdbcTemplate;
    RowMapper rowmapper = new BeanPropertyRowMapper<>(Generalforsamling.class);
    ArrayList<String> arrangementIdeer = new ArrayList<>();

    /**
     * Metoden henter alle kolonner fra generalforsamling og arrangement, hvor arrangementId har den samme vaerdi.
     * Jeg vil kun vise generalforsamlinger fra sidste tre aar (datediff 1095 dage).
     * RowMapper har den begraensning, at man kun kan give den én klasse som parameter. Service har brug for ogsaa
     * at hente ArrangementOplysninger,som er knyttet til Arrangement, men jdbcTemplate kan ikke hente oplysninger
     * fra tre tabeller ad gangen.
     * Saa der skal laves en ny metode til det: hentAlleArranOplysninger()- se laengere nede i klassen.
     * Den skal bruge vaerdierne i kolonnen arrangementId fra dette soegeresultat, saa de gemmes i arraylisten arrangementIdeer
     *
     * @return List, som indeholder referencer til instanser af Generalforsamling.
     */

     public List<Generalforsamling> hentAlleGeneralforsamlinger() {
        String sql = "SELECT * FROM generalforsamling g, arrangement a WHERE g.arrangementId=a.arrangementId AND DATEDIFF(now(),a.oprettelsesDato) < 1095";
        List<Generalforsamling> genList = jdbcTemplate.query(sql, rowmapper);
         for (Generalforsamling g : genList) {
             arrangementIdeer.add(String.valueOf(g.getArrangementId()));
         }
        return genList;
    }

    /**
     * Vaerdier fra den tidligere metode hentes foerst, saadan at formatet passer til WHERE ..IN -clause.
     * Metoden laver beanPropertyMapper til ArrangementOplysninger (tabel- og klassenavnet).
     * Der hentes alle de raekker, hvor arrangementId har den samme vaerdi som i det foerste soegresultat.
     * @return List, som indeholder referencer til instanserne af ArranegementOplysninger.
     */
    public List<ArrangementOplysninger> hentAlleArranOplysninger() {
        String vaerdier = "";
        if (arrangementIdeer.size() != 0) {
            //pga. stakitproblem
            vaerdier = arrangementIdeer.get(0);
            if (arrangementIdeer.size() > 1) {
                for (int i = 1; i < arrangementIdeer.size(); i++) {
                    vaerdier = vaerdier + "," + arrangementIdeer.get(i);
                }
            }
        }
        vaerdier = "(" + vaerdier + ")";
        RowMapper rowmapper = new BeanPropertyRowMapper<>(ArrangementOplysninger.class);
        String sql = "SELECT * FROM arrangementOplysninger WHERE arrangementId IN" + vaerdier;
        return jdbcTemplate.query(sql, rowmapper);
    }


    /**
     * Det er Arrangement, som holder arranegementId som PK for alle subklasser,
     * saa generalforsamling skal slettes fra den. Samtidigt slettes ArrangementOplysninger (composition)
     * Update returnerer antallet af raekker, som er blevet opdateret.
     * @param id arrangementId paa det arrangement,som skal slettes
     * @return true, hvis antallet af raekker, som er blevet slettet, ikke er 0
     */

    public boolean sletGeneralforsamling(int id){
        String sql = "DELETE FROM arrangement WHERE arrangementId=?";
        System.out.println(sql);
        int slettetRaekke = jdbcTemplate.update(sql,id);
        return (slettetRaekke != 0);
    }




}
