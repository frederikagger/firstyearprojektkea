package ejerforening.firstyearprojektkea.Service.Lejlighed;

import ejerforening.firstyearprojektkea.Model.Lejlighed.Lejlighed;
import ejerforening.firstyearprojektkea.Repository.Lejlighed.IlejlighedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**Oprettet af Frederik Agger og Signe Nørløv Eskildsen
 * d. 28/11/2019
 **/

@Service
public class LejlighedService implements IlejlighedService {

    @Autowired
    private IlejlighedRepo ilejlighedRepo;

    @Override
    public List<Lejlighed> hentAlle() {
        return ilejlighedRepo.hentAlle();
    }

    @Override
    public void opret(Lejlighed lejlighed) {
        ilejlighedRepo.opret(lejlighed);
    }

    @Override
    public Lejlighed findMedId(int id) {
        return ilejlighedRepo.findMedId(id);
    }

    @Override
    public void sletLejlighed(int id) {
        ilejlighedRepo.sletLejlighed(id);
    }
}
