package ejerforening.firstyearprojektkea.Repository.Vasketid;

import ejerforening.firstyearprojektkea.Model.Vasketider.Vasketid;

import java.util.List;

public interface IVasketidRepository {

    List<Vasketid> hentVasketider();

    boolean book();

    boolean sletBooking();
}
