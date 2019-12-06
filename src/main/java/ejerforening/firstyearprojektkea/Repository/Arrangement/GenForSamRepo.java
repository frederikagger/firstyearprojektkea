package ejerforening.firstyearprojektkea.Repository.Arrangement;
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

    /**
     * Metoden henter generalforsamling knyttet til en bestemt arrangementId
     * @param id arrangementId
     * @return List, som indeholder referencen til instansen af Generalforsamling.
     */
    public List<Generalforsamling> findGeneralforsamling(int id){
        RowMapper rowmapper = new BeanPropertyRowMapper<>(Generalforsamling.class);
        String sql = "SELECT * FROM generalforsamling g, arrangement a WHERE a.arrangementId=?";
        return jdbcTemplate.query(sql, rowmapper, id);
    }

    /*public boolean opdatereGeneralforsamling(Generalforsamling genfor, ArrangementOplysninger arranoply){
        String sql = "SP_call opdatere(true, genfor.getNavn(), arranoply.getAgenda(), arranoply.getDato(),arranoply.getSted(), genfor.getEmne())";
        int opdateretGenFor = jdbcTemplate.update(sql);
        return opdateretGenFor != 0;
    }*/

    public boolean opdatereGeneralforsamling(Generalforsamling genfor){
        String sql = "UPDATE generalforsamling g,arrangement a SET a.navn=?, a.oprettelsesDato=?, a.opdateringsDato=?, g.referatId=?, g.emne=? WHERE a.arrangementId=?";
        int opdateretGenFor = jdbcTemplate.update(sql, genfor.getNavn(), genfor.getOprettelsesDato(), genfor.getOpdateringsDato(), genfor.getReferatId(),genfor.getEmne(), genfor.getArrangementId());
        return opdateretGenFor !=0;
    }

    public boolean opdatereArranOplys(ArrangementOplysninger arranOpl){
        String sql = "UPDATE arrangementOplysninger SET agenda=?, dato=?, startTidspunkt=?, slutTidspunkt=?, sted=?, tilmeldingsfrist=?, opdateringsDato=? WHERE arrangementId=?";
        int opdateretArranOpl = jdbcTemplate.update(sql, arranOpl.getAgenda(), arranOpl.getDato(),arranOpl.getStartTidspunkt(), arranOpl.getSlutTidspunkt(), arranOpl.getSted(), arranOpl.getTilmeldingsfrist(), arranOpl.getTilmeldingsfrist(), arranOpl.getArrangementId());
        return opdateretArranOpl !=0;
    }

}
