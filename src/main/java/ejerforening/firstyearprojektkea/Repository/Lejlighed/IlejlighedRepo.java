package ejerforening.firstyearprojektkea.Repository.Lejlighed;

import ejerforening.firstyearprojektkea.Model.Lejlighed.Lejlighed;

import java.util.List;

public interface IlejlighedRepo {

    List<Lejlighed> hentAlle();

    Lejlighed findMedId(int id);

    void opret(Lejlighed lejlighed);

    void sletLejlighed(int id);







}
