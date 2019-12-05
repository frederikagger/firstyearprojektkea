package ejerforening.firstyearprojektkea.Service;

import ejerforening.firstyearprojektkea.Model.AdministreSlutbruger.SlutbrugerOversigt;

import java.util.List;

public interface ISlutbrugerService {
    List<SlutbrugerOversigt> hentAlle();

    void slet(int slutbrugerId);
}
