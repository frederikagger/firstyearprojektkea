package ejerforening.firstyearprojektkea.Repository.Arrangement;
import ejerforening.firstyearprojektkea.Model.Arrangement.Arrangement;
import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author paivi
 * Repository for generalforsamlinger implementerer metoderne fra interface for generalforsamlinger.
 */
@Repository
public class GenForSamRepo implements IGenForSamRepo {

    /**
     *  Klassen autowirer jdbctemplate, som den bruger i metoderne til at udfoere sql-statements.
     *  BeanPropertyRowMapper (interface er RowMapper), som bruges i metoderne sammen med jdbcTemplate,
     *  henter en raekke fra databasen som et object af den type, som den faar som parameter.
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Metoden henter alle kolonner fra generalforsamling og arrangement, hvor arrangementId har den samme vaerdi.
     * Jeg vil kun vise generalforsamlinger fra sidste tre aar (datediff 1095 dage).
     * @return List, som indeholder referencer til instanser af Generalforsamling.
     */

     public List<Generalforsamling> hentAlleGeneralforsamlinger() {
         RowMapper rowmapper = new BeanPropertyRowMapper<>(Generalforsamling.class);
         String sql = "SELECT * FROM generalforsamling g, arrangement a WHERE g.arrangementId=a.arrangementId AND DATEDIFF(now(),a.oprettelsesDato) < 1095";
         List<Generalforsamling> genList = jdbcTemplate.query(sql, rowmapper);
        return genList;
    }

    /**
     * Metoden henter arrangementOplysninger knyttet til en bestemt arrangementId
     * @param id arrangementId
     * @return List, som indeholder referencen til instansen af ArranegementOplysninger.
     */
    public List<ArrangementOplysninger> findArranOplysninger(int id){
        RowMapper rowmapper = new BeanPropertyRowMapper<>(ArrangementOplysninger.class);
        String sql = "SELECT * FROM arrangementOplysninger WHERE arrangementId=?";
        return jdbcTemplate.query(sql, rowmapper, id);
    }

    /**
     * Det er Arrangement, som holder arranegementId som PK for alle subklasser,
     * saa generalforsamling skal slettes fra den. Samtidigt slettes automatisk ArrangementOplysninger (composition)
     * update() returnerer antallet af raekker, som er blevet opdateret.
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
