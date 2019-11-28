package ejerforening.firstyearprojektkea.Service;

import ejerforening.firstyearprojektkea.Model.Lejlighed;
import ejerforening.firstyearprojektkea.Repository.IlejlighedRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LejlighedService {

    @Autowired
    private IlejlighedRepo ilejlighedRepo;

    public List<Lejlighed> hentAlle(){
        return ilejlighedRepo.hentAlle();
    }

    public void opret(Lejlighed lejlighed){
        ilejlighedRepo.opret(lejlighed);
    }
}
