package ejerforening.firstyearprojektkea.Service.Vasketid;


import ejerforening.firstyearprojektkea.Model.Vasketider.Vasketid;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IVasketidService {

    List<Vasketid> seVasketider();

    boolean book();

    boolean sletBooking();

}
