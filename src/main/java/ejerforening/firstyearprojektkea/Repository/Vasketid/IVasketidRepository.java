package ejerforening.firstyearprojektkea.Repository.Vasketid;

import ejerforening.firstyearprojektkea.Model.Vasketider.Vasketid;

import java.util.List;

/**
 * @author Frede
 */

public interface IVasketidRepository {

    List<Vasketid> hentLedigeVasketider();

    void book(Vasketid vasketid);

    boolean sletBooking();

    Vasketid findbyVaskeId(int vaskeid);

    List<Vasketid> hentBookedeVasketider();
}
