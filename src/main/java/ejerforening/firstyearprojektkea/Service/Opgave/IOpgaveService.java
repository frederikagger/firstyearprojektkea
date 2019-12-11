package ejerforening.firstyearprojektkea.Service.Opgave;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.Opgave;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOplysninger;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;

import java.util.List;


public interface IOpgaveService
{
    List<OpgaveOversigt> hentAlle();
    OpgaveOplysninger findValgteOpgaveOplysninger(int opgaveId);
    boolean OpretOpgave (Opgave opgave);
    boolean OpgaveOplysningerOprettet(OpgaveOplysninger opgaveOplysninger);
    boolean erOpgaveOpdateret(OpgaveOplysninger opgaveOplysninger);

    void slet(int opgaveId);

}
