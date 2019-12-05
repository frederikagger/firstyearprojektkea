package ejerforening.firstyearprojektkea.Repository.Opgave;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;


import java.util.List;
import java.util.Set;

public interface IOpgaveRepository
{
    List<OpgaveOversigt> hentAlle();

    void slet(int opgaveId);

}
