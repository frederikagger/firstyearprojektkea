package ejerforening.firstyearprojektkea.Service.Arrangement;

import ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger.Slutbruger;
import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

/**
 * @author paivi
 * Interface til genforsamlingens service. I Spring kan interfaces autowires (laves en instans af bean),
 * saa Controller faar autowired denne interface i stedet for selve service.
 * Fordelen er, at man ville kunne genbruge interface i en anden sammenhaeng.
 * Interface indeholder siganturer paa metoder, som de implementerende klasser skal implementere.
 */
public interface IGenForSamService {

    List<Generalforsamling> hentAlleGeneralforsamlinger();
    List<ArrangementOplysninger> findArranOplysninger(int id);
    List<Generalforsamling> findGeneralforsamling(int id);
    boolean sletGeneralforsamling(int id);
    String validereOgOpretGenForSam(Generalforsamling genfor, BindingResult bResult, Model model);
    String validereOgOpdatereGenForSam(Generalforsamling genfor, BindingResult binding, ArrangementOplysninger arroplys, BindingResult bResult, Model model);

    String validereOgOpretAfslut(ArrangementOplysninger arrOplys, BindingResult binding, Model model);

    String findDeltager(WebRequest webr, int id, Model model);
    List<Slutbruger> findTilmeldte(int id);

}
