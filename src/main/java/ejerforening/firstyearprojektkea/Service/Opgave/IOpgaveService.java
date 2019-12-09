package ejerforening.firstyearprojektkea.Service.Opgave;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.Opgave;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOplysninger;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;

import java.util.List;


public interface IOpgaveService
{
    List<OpgaveOversigt> hentAlle();
    List<Opgave> findOpgave(int opgaveId);
    List<OpgaveOplysninger> findValgteOpgave(int opgaveId);
    boolean erOpgaveOprettet(Opgave opgave);
    boolean erOpgaveOplysningerOprettet(OpgaveOplysninger opgaveOplysninger, Opgave opgave);
    boolean erOpgaveOpdateret(Opgave opgave, OpgaveOplysninger opgaveOplysninger);

    void slet(int opgaveId);

}
