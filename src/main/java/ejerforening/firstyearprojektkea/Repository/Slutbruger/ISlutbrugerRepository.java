package ejerforening.firstyearprojektkea.Repository.Slutbruger;

import ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger.SlutbrugerOversigt;

import java.util.List;

public interface ISlutbrugerRepository
{
    List<SlutbrugerOversigt> hentAlle();

    void slet(int slutbrugerId);
}
