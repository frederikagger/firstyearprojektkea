package ejerforening.firstyearprojektkea.Repository.Vasketid;

import ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger.Slutbruger;
import ejerforening.firstyearprojektkea.Model.Lejlighed.Lejlighed;
import ejerforening.firstyearprojektkea.Model.Vasketider.Vasketid;

import java.util.List;

public interface IVasketidRepository {

    List<Vasketid> hentVasketider();

    void book(Vasketid vasketid);

    boolean sletBooking();

    Vasketid findbyVaskeId(int vaskeid);
}
