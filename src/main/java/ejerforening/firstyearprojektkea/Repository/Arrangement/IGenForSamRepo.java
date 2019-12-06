package ejerforening.firstyearprojektkea.Repository.Arrangement;
import ejerforening.firstyearprojektkea.Model.Arrangement.Arrangement;
import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;

import java.util.List;

public interface IGenForSamRepo {

    public List<Generalforsamling> hentAlleGeneralforsamlinger();
    public List<ArrangementOplysninger> findArranOplysninger(int id);
    public List<Generalforsamling> findGeneralforsamling(int id);
    public boolean sletGeneralforsamling(int id);
    //public boolean opdatereGeneralforsamling(Generalforsamling genfor, ArrangementOplysninger arranoply);
    public boolean opdatereGeneralforsamling(Generalforsamling genfor);
    public boolean opdatereArranOplys(ArrangementOplysninger arranOpl);
}
