package ejerforening.firstyearprojektkea.Service.Opgave;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.Opgave;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOplysninger;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;

import java.util.List;

/**
 * Interface for OpgaveService, som Spring goer muligt at autowire, saa man ikke behoeves at autowire direkte til klassen,
 * saa interfacet ville havde vaeret muligt at genbruge et andet sted.
 * Indeholder hvilke metoder der skal implementeres, hvor interfacet bliver implementeret
 * @author Signe
 * @since 5-12-2019
 */

public interface IOpgaveService
{
    List<OpgaveOversigt> hentAlle();

    OpgaveOplysninger findValgteOpgaveOplysninger(int opgaveId);

    boolean erOpgaveOpdateret(OpgaveOplysninger opgaveOplysninger);

    void slet(int opgaveId);

    boolean OpretOpgave (Opgave opgave);

    boolean OpgaveOplysningerOprettet(OpgaveOplysninger opgaveOplysninger);
}
