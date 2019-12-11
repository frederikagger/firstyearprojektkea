package ejerforening.firstyearprojektkea.Repository.Arrangement;
import ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger.Slutbruger;
import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import java.time.LocalDate;
import java.util.List;

/**
 * @author paivi
 * @since 08-12-2019
 *  * Interface til genforsamlingens repository. I Spring kan interfaces autowires (laves en instans af bean),
 *  * saa Controller faar autowired denne interface i stedet for selve repository.
 *  * Fordelen er, at man ville kunne genbruge interface i en anden sammenhaeng.
 *  * Interface indeholder siganturer paa metoder, som de implementerende klasser skal implementere.
 */

public interface IGenForSamRepo {

    List<Generalforsamling> hentAlleGeneralforsamlinger();
    List<ArrangementOplysninger> findArranOplysninger(int id);
    List<Generalforsamling> findGeneralforsamling(int id);
    boolean sletGeneralforsamling(int id);
    boolean opdatereGeneralforsamling(Generalforsamling genfor, ArrangementOplysninger arranoply);
    boolean opretGeneralforsamling(Generalforsamling genfor);
    boolean opretGeneralforsamlingAfslut(ArrangementOplysninger arrOplys);
    int findArrangementId(String navn, LocalDate oprettelsesDato);
    List<Slutbruger> findSlutbruger(String ...vaerdier);
    boolean knytSlutbrugerOgArrangement(int arrangementId, int slutbrugerId);
    List<Slutbruger> findTilmeldte(int id);
}
