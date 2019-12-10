package ejerforening.firstyearprojektkea.Repository.Opgave;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.Opgave;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOplysninger;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;

import java.time.LocalDate;
import java.util.List;

public interface IOpgaveRepository
{
    List<OpgaveOversigt> hentAlle();

    OpgaveOplysninger findValgteOpgaveOplysninger(int opgaveId);

    boolean opretOpgave(Opgave opgave);

    boolean opretOplysninger(OpgaveOplysninger opgaveOplysninger);

    boolean opdaterOpgave(OpgaveOplysninger opgaveOplysninger);

    void slet(int opgaveId);
}
