package ejerforening.firstyearprojektkea.Repository.Arrangement;
import ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger.Slutbruger;
import ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger.SlutbrugerArrangement;
import ejerforening.firstyearprojektkea.Model.Arrangement.Arrangement;
import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
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
     *  id paa klasseniveuaet bruges til at gemme arrangementId mellem udfoersel af to metoder.
     *  Scope skal vaere klassen og ikke kun lokatl i metoden.
     */
    @Autowired
    JdbcTemplate jdbcTemplate;
    private int id =0;

    /**
     * Metoden henter alle kolonner fra generalforsamling og arrangement, hvor arrangementId har den samme vaerdi.
     * Jeg vil kun vise generalforsamlinger fra sidste tre aar (datediff 1095 dage).
     * @return List, som indeholder referencer til instanser af Generalforsamling.
     */
    @Override
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
    @Override
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
    @Override
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
    @Override
    public List<Generalforsamling> findGeneralforsamling(int id){
        RowMapper rowmapper = new BeanPropertyRowMapper<>(Generalforsamling.class);
        String sql = "SELECT * FROM generalforsamling g, arrangement a WHERE a.arrangementId=?";
        return jdbcTemplate.query(sql, rowmapper, id);
    }

    //løsning på 0? Stored procedure returnerer ikke noget, men crasher, hvis den ikke virker
    @Override
    public boolean opdatereGeneralforsamling(Generalforsamling genfor, ArrangementOplysninger arrOpl){
        String navn = genfor.getNavn();
        int arrangementId = genfor.getArrangementId();
        String ordstyrer = genfor.getOrdstyrer();
        boolean ordinaer = genfor.isOrdinaer();
        String agenda = arrOpl.getAgenda();
        String sted = arrOpl.getSted();
        String sql = "CALL SL_opdatereGeneralforsamling(?,?,?,?,?,?)";
        int opdateretGenFor = jdbcTemplate.update(sql,navn,ordstyrer,ordinaer,agenda,sted,arrangementId);
        return opdateretGenFor == 0;
    }

    /**
     * returnerer -1, hvis der hentes andet end 1 arrangementId
     * @param navn
     * @param oprettelsesDato
     * @return
     */
    @Override
    public int findArrangementId(String navn, LocalDate oprettelsesDato){
        RowMapper rowMapper = new BeanPropertyRowMapper<>(Arrangement.class);
        String sqlFind = "SELECT arrangementId FROM arrangement WHERE navn=? AND oprettelsesDato =?";
        List<Arrangement> arrangementList = (List<Arrangement>) jdbcTemplate.query(sqlFind,rowMapper,navn,oprettelsesDato);
        id = arrangementList.get(0).getArrangementId();
        if(arrangementList.size() !=1){
            id = -1;
        }

        return id;
    }

    /**
     * returnerer false, hvis der er flere arrangementId'er men det samme navn og oprettelsesdato
     * @param genfor
     * @return
     */
    //erstat kolonnenavne med tallene
    @Override
    public boolean opretGeneralforsamling(Generalforsamling genfor){
        String sql = "INSERT INTO arrangement(navn,oprettelsesDato) VALUES(?,?)";
        String navn = genfor.getNavn();
        boolean ordinaer = genfor.isOrdinaer();
        LocalDate oprettelsesDato = LocalDate.now();
        int arranOpdateret = jdbcTemplate.update(sql, navn, oprettelsesDato);
        int arrangementId = findArrangementId(navn,oprettelsesDato);
        if(arrangementId == -1){
            return false;
        }
        String sql2 = "INSERT INTO generalforsamling (arrangementId,ordstyrer,ordinaer)VALUES(?,?,?)";
        String ordstyrer = genfor.getOrdstyrer();
        int genforOpdateret = jdbcTemplate.update(sql2, arrangementId,ordstyrer, ordinaer);
        int resultat = arranOpdateret + genforOpdateret;
        return resultat == 2;
    }

    @Override
    public boolean opretGeneralforsamlingAfslut(ArrangementOplysninger arrOplys){
        String sql = "INSERT INTO arrangementOplysninger (arrangementId,agenda,dato, startTidspunkt, slutTidspunkt, sted, tilmeldingsfrist, sidstOpdateret) VALUES(?,?,?,?,?,?,?,?)";
        String agenda = arrOplys.getAgenda();
        LocalDate dato = arrOplys.getDato();
        LocalTime start = arrOplys.getStartTidspunkt();
        LocalTime slut = arrOplys.getSlutTidspunkt();
        String sted = arrOplys.getSted();
        LocalDate frist = arrOplys.getTilmeldingsfrist();
        LocalDate sidstOpdateret = LocalDate.now();
        int genforOprettet = jdbcTemplate.update(sql,this.id,agenda,dato,start,slut,sted,frist,sidstOpdateret);
        return genforOprettet !=0;
    }

    //find slutbruger på fornavn og efternavn
    public List<Slutbruger> findSlutbruger(String ...vaerdier){
        RowMapper rowmapper = new BeanPropertyRowMapper<>(Slutbruger.class);
        String sql = "SELECT * FROM slutbruger WHERE fornavn=? AND efternavn=?";
        return jdbcTemplate.query(sql, rowmapper, vaerdier);
    }

    public boolean knytSlutbrugerOgArrangement(int arrangementId,int slutbrugerId){
        RowMapper rowmapper = new BeanPropertyRowMapper<>(SlutbrugerArrangement.class);
        String sql1 = "SELECT * FROM slutbrugerArrangement WHERE slutbrugerId=? AND arrangementId=?";
        List<SlutbrugerArrangement> resultat1 = jdbcTemplate.query(sql1, rowmapper,arrangementId, slutbrugerId);
        if(resultat1.size() !=0){
            return false;
        }
        String sql2 = "INSERT INTO slutbrugerarrangement (slutbrugerId,arrangementId) values (?,?)";
        int resultat2 = jdbcTemplate.update(sql2, slutbrugerId,arrangementId);
        return resultat2 !=0;
    }




}
