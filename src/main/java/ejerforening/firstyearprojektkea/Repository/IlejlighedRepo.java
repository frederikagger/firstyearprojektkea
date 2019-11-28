package ejerforening.firstyearprojektkea.Repository;

import ejerforening.firstyearprojektkea.Model.Lejlighed;

import java.util.List;

public interface IlejlighedRepo {

    List<Lejlighed> hentAlle();

    Lejlighed findMedId(int id);

    void opret();






}
