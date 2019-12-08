package ejerforening.firstyearprojektkea.Repository.Arrangement;
import ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger.Slutbruger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Arrangement;
import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;

import java.time.LocalDate;
import java.util.List;

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
}
