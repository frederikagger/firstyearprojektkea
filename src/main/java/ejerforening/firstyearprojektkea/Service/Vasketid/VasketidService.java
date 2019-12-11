package ejerforening.firstyearprojektkea.Service.Vasketid;

import ejerforening.firstyearprojektkea.Model.Vasketider.Vasketid;
import ejerforening.firstyearprojektkea.Repository.Vasketid.IVasketidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VasketidService implements IVasketidService {

    @Autowired
    private IVasketidRepository iVasketidRepository;

    @Override
    public List<Vasketid> seVasketider() {
        return iVasketidRepository.hentVasketider();
    }

    @Override
    public void book(Vasketid vasketid) {
        iVasketidRepository.book(vasketid);
    }

    @Override
    public Vasketid findbyVaskeId(int vaskeid) {
        return iVasketidRepository.findbyVaskeId(vaskeid);
    }

    @Override
    public boolean sletBooking() {
        return false;
    }
}
