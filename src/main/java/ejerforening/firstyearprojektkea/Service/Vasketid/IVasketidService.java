package ejerforening.firstyearprojektkea.Service.Vasketid;

import ejerforening.firstyearprojektkea.Model.Vasketider.Vasketid;

import java.util.List;

/**
 * @author Frede
 */

public interface IVasketidService {

    Vasketid findbyVaskeId(int vaskeid);

    List<Vasketid> seLedigeVasketider();

    void book(Vasketid vasketid);

    boolean sletBooking();

    List<Vasketid> seBookedeVasketider();
}
