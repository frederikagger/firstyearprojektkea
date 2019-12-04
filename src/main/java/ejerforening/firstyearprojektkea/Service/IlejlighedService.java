package ejerforening.firstyearprojektkea.Service;

import ejerforening.firstyearprojektkea.Model.Lejlighed;

import java.util.List;

public interface IlejlighedService {

    List<Lejlighed> hentAlle();

    Lejlighed findMedId(int id);

    void opret(Lejlighed lejlighed);

    void sletLejlighed(Lejlighed lejlighed);
}
