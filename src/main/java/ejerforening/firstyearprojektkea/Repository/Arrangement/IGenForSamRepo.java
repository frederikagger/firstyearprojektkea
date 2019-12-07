package ejerforening.firstyearprojektkea.Repository.Arrangement;
import ejerforening.firstyearprojektkea.Model.Arrangement.Arrangement;
import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;

import java.util.List;

public interface IGenForSamRepo {

    List<Generalforsamling> hentAlleGeneralforsamlinger();
    List<ArrangementOplysninger> findArranOplysninger(int id);
    List<Generalforsamling> findGeneralforsamling(int id);
    boolean sletGeneralforsamling(int id);
    boolean opdatereGeneralforsamling(Generalforsamling genfor, ArrangementOplysninger arranoply);
    boolean opretGeneralforsamling(Generalforsamling genfor);

    boolean opretGeneralforsamlingAfslut(ArrangementOplysninger arrOplys);
    //public boolean opdatereArranOplys(ArrangementOplysninger arranOpl);
}
