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
 * @since 09-12-2019
 * Repository for generalforsamlinger implementerer metoderne fra interface for generalforsamlinger.
 * Danner forbindelsen til databasen.
 */
@Repository
public class GenForSamRepo implements IGenForSamRepo {

    /**
     *  Klassen autowirer jdbctemplate, som den bruger i metoderne til at udfoere sql-statements.
     *  BeanPropertyRowMapper (interface hedder RowMapper) returnerer en raekke fra databasen
     *  som et object af den type, som den faar som parameter.
     *  Integer-felt bruges til at gemme arrangementId mellem udfoersel af to metoder (opretGeneralforsamling() og
     *  opretGeneralforsamlingAfslut(). Scope skal dermed vaere klassen og ikke kun lokalt i metoden.
     *  Feltet opdateres i metoden findArrangementId().
     *  Feltet skal vaere Integer i stedet for int, fordi man i java ikke kan manipulere vaerdien af primitive
     *  vaerdier (call by value) i en metode, men objektets vaerdier (call by reference) kan man
     *  (java kan dog ofte finde ud af at laese int som Integer, men man kan ikke stole paa det).
     */
    @Autowired
    JdbcTemplate jdbcTemplate;
    private Integer id = 0;

    /**
     * Metoden henter alle kolonner fra generalforsamling og arrangement, hvor arrangementId har den samme vaerdi.
     * Jeg vil kun vise generalforsamlinger fra sidste tre aar (datediff 1095 dage).
     * Vises paa html-siden "generalforsamlinger"
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
     * Metoden henter ArrangementOplysninger knyttet til en bestemt arrangementId.
     * Bruges til at vise flere oplysninger om generalforsamling paa html-siden "flereOplysningerGeneralforsamling"
     * og til at vise en opdateret generalforsamling paa html-siden "opdateringGeneralforsamling"
     * @param id arrangementId paa de arrangement, hvis oplysninger skal vises
     * @return List, som indeholder referencen til den soegte instans af ArranegementOplysninger.
     */
    @Override
    public List<ArrangementOplysninger> findArranOplysninger(int id){
        RowMapper rowmapper = new BeanPropertyRowMapper<>(ArrangementOplysninger.class);
        String sql = "SELECT * FROM arrangementOplysninger WHERE arrangementId=?";
        return jdbcTemplate.query(sql, rowmapper, id);
    }

    /**
     * Metoden henter tilmeldte (Slutbrugere) til html-siden "flereOplysningerGeneralforsamling"
     * Forbindelsen mellem Slutbruger og Arrangement er mange-til-mange, saa vi har en linkende tabel/model-klasse
     * SlutbrugerArrangement. Fra den skal vi foerst hente slutbrugerId'er, som er knyttet til arrangementet.
     * (den foerste sql-statament). De bliver gemt i List.
     * Saa skal vi hente slutbrugernes oplysninger med disse slutbrugerId'er (den anden sql-statement).
     * Metoden returnerer slutbrugerne i List.
     * @param id arrangementId paa det arrangement, som slutbrugerne er tilmeldt til
     * @return List, som indeholder referencen til Slutbrugerne.
     */
    @Override
    public List<Slutbruger> findTilmeldte(int id){
        RowMapper rowmapper1 = new BeanPropertyRowMapper<>(SlutbrugerArrangement.class);
        String sql1 = "SELECT slutbrugerId FROM slutbrugerArrangement WHERE arrangementId=?";
        List<SlutbrugerArrangement> findId= jdbcTemplate.query(sql1,rowmapper1,id);

        RowMapper rowmapper2 = new BeanPropertyRowMapper<>(Slutbruger.class);
        String sql2 = "SELECT * FROM slutbruger WHERE slutbrugerId < ?";
        return jdbcTemplate.query(sql2, rowmapper2, findId.size()+1);
    }


    /**
     * Metoden sletter generalforsamling fra html-siden "generalforsamlinger"
     * Arrangement har arranegementId som PK for alle subklasser,saa generalforsamling skal slettes fra den.
     * Samtidigt slettes automatisk ArrangementOplysninger (composition)
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
     * Metoden henter generalforsamling knyttet til en bestemt arrangementId.
     * Superklassen Arrangement indeholder stamoplysninger, mens subklassen Generalforsamling indeholder
     * felter, som kun tilhoerer den. Oplysninger skal hentes fra begge tabeller.
     * Bruges til at knytte flere oplysninger om generalforsamling til den paagaeldende generalforsamling paa
     * html-siden "flereOplysningerGeneralforsamling" og til at knytte en slutbruger, som tilmeldes
     * til generalforsamling, til denne generalforsamling paa html-siden "tilfoejSlutbrugerTilGenforsam"
     * @param id arrangementId paa generalforsamlingen
     * @return List, som indeholder referencen til instansen af Generalforsamling.
     */
    @Override
    public List<Generalforsamling> findGeneralforsamling(int id){
        RowMapper rowmapper = new BeanPropertyRowMapper<>(Generalforsamling.class);
        String sql = "SELECT * FROM generalforsamling g, arrangement a WHERE a.arrangementId=?";
        return jdbcTemplate.query(sql, rowmapper, id);
    }

    /**
     * Metoden opdaterer en generalforsamling paa html-siden "opdateringGeneralforsamling".
     * Oplysningerne er fordelt paa tre tabeller: Generalforsamling (subklassen i Spring),
     * Arrangement(superklassen i Spring) og ArrangementOplysninger(composition til Arrangement).
     * Vi henter oplysninger fra tabellerne Generalforsamling og ArrangementOplysninger og opdateringen udfoeres som
     * stored procedure. Naar jdbcTemplate udfoerer stored procedure, returnerer update() 0, hvis den gaar igennem og ellers false.
     * @param genfor generalforsamling
     * @param arrOpl arrangementOplysninger
     * @return true, hvis jdbctemplate returnerer 0. Hvis stored procedure ikke gaar igennem, returneres ikke 0.
     */
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
     * Metoden opretter en generalforsamling med stamdata paa html-siden "opretGeneralforsamling".
     * Stamdata findes i superklassen, som der laves foerst en ny raekke til tabellen Arrangement.
     *
     * Saa skal der hentes arrangementId paa denne nye raekke (databasen har genereret) med metoden findArrangementid().
     * (se den naeste metode). Metoden returnerer -1, hvis der ikke er blevet oprettet én raekke i Arrangement
     * og hele oprettelsen returnerer false til service.
     * Hvis metoden returnerer en arrangementId(), indsaetter vi de naeste oplysninger i tabellen Generalsamling.
     *
     * Metoden returnerer true, hvis der er blevet oprettet en ny raekke i generalforsamling.
     * @param genfor generalforsamling, som skal oprettes
     * @return true, hvis der er blevet oprettet én ny raekke i tabellen Generalforsamling
     */
    @Override
    public boolean opretGeneralforsamling(Generalforsamling genfor){
        String sql = "INSERT INTO arrangement(navn,oprettelsesDato) VALUES(?,?)";
        String navn = genfor.getNavn();
        boolean ordinaer = genfor.isOrdinaer();
        LocalDate oprettelsesDato = LocalDate.now();
        jdbcTemplate.update(sql, navn, oprettelsesDato);

        int arrangementId = findArrangementId(navn,oprettelsesDato);
        if(arrangementId == -1){
            return false;
        }
        String sql2 = "INSERT INTO generalforsamling (arrangementId,ordstyrer,ordinaer)VALUES(?,?,?)";
        String ordstyrer = genfor.getOrdstyrer();
        int genforOpdateret = jdbcTemplate.update(sql2, arrangementId,ordstyrer, ordinaer);
        return genforOpdateret == 1;
    }


    /**
     * Metoden henter arrangementId til det arrangement, som man lige har oprettet i metoden opretGeneralforsamling().
     * ArrangementId hentes med arrangementets navn og oprettelsesDato.
     * Metoden returnerer -1, hvis resultatet er andet end én arrangementId.
     * Samtidigt opdageters Integer-feltet, som nu faar arrangementid som sin vaerdi.
     * Den skal vi bruge som parameter i den naeste metode, hvor vi henter arrangementOplysninger til det samme arrangement.
     * @param navn navn paa arrangement som et soegekriterium
     * @param oprettelsesDato oprettelsesDato som et andet soegekriterium
     * @return arrangementId
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
     * Metoden opretter arrangementOplysninger til den generalsamling, som lige er blevet oprettet. Den faar
     * Integer-feltet arrangementId, som blev opdateret i metoden findArrangementId() som sin parameter.
     * Dermed bliver oplysninger knyttet til det rigtige arrangement.
     * Foerst skal man tjekke, at arrangementOplysninger ikke findes i forvejen. Vi faar
     * arrangementid fra Integer-felt i klassen,som blev opdateret i metoden findArrangementId().
     * Findes arrangementOplysninger findes i forvejen, returneres false.
     * Ellers tilfoejes raekken til arrangementOplysninger.
     * @param arrOplys arrangementOplysninger
     * @return true, hvis jdbcTemplate ikke returnerer 0 opdaterede raekker.
     */
    @Override
    public boolean opretGeneralforsamlingAfslut(ArrangementOplysninger arrOplys){
        RowMapper rowMapper = new BeanPropertyRowMapper<>(ArrangementOplysninger.class);
        String sqlFind = "SELECT arranOplysId FROM ArrangementOplysninger WHERE arrangementId=?";
        List<ArrangementOplysninger> arrOplysListe = jdbcTemplate.query(sqlFind,rowMapper,this.id);
        if(arrOplysListe.size() > 0){
            return false;
        }
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

    /**
     * Metoden bruges til at hente slutbruger med et fornavn op et efternavn, inden slutbrugeren
     * kan tilfoejes til generalforsamling paa html-siden "tilfoejSlutbrugerTilGenforsam".
     * Der er kun 10 lejligheder, saa det er ikke sandsynligt, at der er to personer med det samme navn
     * i tabellen Slutbruger, men man kan komme til at tilmelde den samme person to gange.
     * @param vaerdier varargs, dvs. array af String med fornavn og efternavn, som brugeren indtaster paa html-siden
     * @return List med reference til Slutbruger
     */
    @Override
    public List<Slutbruger> findSlutbruger(String ...vaerdier){
        RowMapper rowmapper = new BeanPropertyRowMapper<>(Slutbruger.class);
        String sql = "SELECT * FROM slutbruger WHERE fornavn=? AND efternavn=?";
        return jdbcTemplate.query(sql, rowmapper, vaerdier);
    }

    /**
     * Metoden bruges til at knytte slutbruger til generalforsamling paa html-siden "tilfoejSlutbrugerTilGenforsam".
     * SlutbrugerArrangement er en table, som knytter Slutbruger og Arrangement sammen i mange-til-mange -relationen.
     * Foerst skal man tjekke, at slutbrugeren ikke i forvejen er knyttet til generalforsamlingen (unique index i databasen
     * soeger for, at den samme kombination ikke kan findes to gange i samme tabel). Hvis resultatet af soegningen er
     * noget andet end 0, returneres false til service.
     * Ellers tilfoejes slutbruger til generalforsamling og hvis resultatet er én raekke, returneres true.
     * @param slutbrugerId
     * @param arrangementId
     * @return
     */
     @Override
    public boolean knytSlutbrugerOgArrangement(int slutbrugerId, int arrangementId){
        RowMapper rowmapper = new BeanPropertyRowMapper<>(SlutbrugerArrangement.class);
        String sql1 = "SELECT * FROM slutbrugerArrangement WHERE slutbrugerId=? AND arrangementId=?";
        List<SlutbrugerArrangement> resultat1 = jdbcTemplate.query(sql1, rowmapper,slutbrugerId, arrangementId);
        if(resultat1.size() !=0){
            return false;
        }
        String sql2 = "INSERT INTO slutbrugerArrangement (slutbrugerId,arrangementId) values (?,?)";
        int resultat2 = jdbcTemplate.update(sql2, slutbrugerId,arrangementId);
        return resultat2 ==1;
    }

}
