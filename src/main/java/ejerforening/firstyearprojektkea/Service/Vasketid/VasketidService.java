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
    public boolean book() {
        return false;
    }

    @Override
    public boolean sletBooking() {
        return false;
    }
}
