package ejerforening.firstyearprojektkea.Service;

import ejerforening.firstyearprojektkea.Model.Lejlighed;
import ejerforening.firstyearprojektkea.Repository.IlejlighedRepo;
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
        return null;
    }

    @Override
    public void opret(Lejlighed lejlighed) {

    }

    @Override
    public Lejlighed findMedId(int id) {
        return null;
    }
}
