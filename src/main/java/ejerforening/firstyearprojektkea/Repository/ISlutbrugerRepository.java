package ejerforening.firstyearprojektkea.Repository;

import ejerforening.firstyearprojektkea.Model.AdministreSlutbruger.SlutbrugerOversigt;

import java.util.List;

public interface ISlutbrugerRepository {
    List<SlutbrugerOversigt> hentAlle();

    void slet(int slutbrugerId);
}
