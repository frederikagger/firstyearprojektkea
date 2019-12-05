package ejerforening.firstyearprojektkea.Service.Arrangement;

import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import java.util.List;

/**
 * @author paivi
 * Interface til genforsamlingens service. I Spring kan interfaces autowires (laves en instans af bean),
 * saa Controller faar autowired denne interface i stedet for selve service.
 * Fordelen er, at man ville kunne genbruge interface i en anden sammenhaeng.
 * Interface indeholder siganturer på metoder, som de implementerende klasser skal implementere.
 */
public interface IGenForSamService {

    public List<Generalforsamling> hentAlleGeneralforsamlinger();
    public List<ArrangementOplysninger> hentAlleArranOplysninger();
}
