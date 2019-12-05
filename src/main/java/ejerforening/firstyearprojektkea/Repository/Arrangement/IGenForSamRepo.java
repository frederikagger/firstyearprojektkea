package ejerforening.firstyearprojektkea.Repository.Arrangement;
import ejerforening.firstyearprojektkea.Model.Arrangement.Arrangement;
import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;

import java.util.List;

public interface IGenForSamRepo {

    public List<Generalforsamling> hentAlleGeneralforsamlinger();
    public List<ArrangementOplysninger> hentAlleArranOplysninger();
}
