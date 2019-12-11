package ejerforening.firstyearprojektkea.Service.Vasketid;


import ejerforening.firstyearprojektkea.Model.Lejlighed.Lejlighed;
import ejerforening.firstyearprojektkea.Model.Vasketider.Vasketid;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IVasketidService {

    Vasketid findbyVaskeId(int vaskeid);

    List<Vasketid> seVasketider();

    void book(Vasketid vasketid);

    boolean sletBooking();

}
