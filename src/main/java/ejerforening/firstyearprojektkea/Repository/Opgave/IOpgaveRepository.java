package ejerforening.firstyearprojektkea.Repository.Opgave;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.Opgave;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOplysninger;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;

import java.time.LocalDate;
import java.util.List;

public interface IOpgaveRepository {
    List<OpgaveOversigt> hentAlle();

    List<Opgave> findOpgave(int opgaveId);

    List<OpgaveOplysninger> findValgteOpgave(int opgaveId);

    int erOpgaveOprettetFoer(String navn, LocalDate oprettelsesDato);

    boolean opretOpgave(Opgave opgave);

    boolean opretOplysninger(OpgaveOplysninger opgaveOplysninger, Opgave opgave);

    boolean opdaterOpgave(Opgave opgave, OpgaveOplysninger opgaveOplysninger);

    void slet(int opgaveId);
}
