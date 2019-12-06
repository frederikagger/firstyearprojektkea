package ejerforening.firstyearprojektkea.Service.Arrangement;

import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @author paivi
 * Interface til genforsamlingens service. I Spring kan interfaces autowires (laves en instans af bean),
 * saa Controller faar autowired denne interface i stedet for selve service.
 * Fordelen er, at man ville kunne genbruge interface i en anden sammenhaeng.
 * Interface indeholder siganturer paa metoder, som de implementerende klasser skal implementere.
 */
public interface IGenForSamService {

    public List<Generalforsamling> hentAlleGeneralforsamlinger();
    public List<ArrangementOplysninger> findArranOplysninger(int id);
    public List<Generalforsamling> findGeneralforsamling(int id);
    public boolean sletGeneralforsamling(int id);
    public String validereOgOpdatereGenForSam(Generalforsamling genfor, ArrangementOplysninger arroplys, BindingResult bResult, Model model);
}
