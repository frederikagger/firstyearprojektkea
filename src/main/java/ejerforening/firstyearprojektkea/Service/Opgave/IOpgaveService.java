package ejerforening.firstyearprojektkea.Service.Opgave;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;

import java.util.List;
import java.util.Set;

public interface IOpgaveService
{
    List<OpgaveOversigt> hentAlle();

    void slet(int opgaveId);

}
