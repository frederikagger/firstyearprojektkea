package ejerforening.firstyearprojektkea.Service.Arrangement;

import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import ejerforening.firstyearprojektkea.Repository.Arrangement.IGenForSamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenForSamService implements IGenForSamService {

    @Autowired
    IGenForSamRepo iGenForSamRepo;

    public List<Generalforsamling> hentAlleGeneralforsamlinger(){
        return iGenForSamRepo.hentAlleGeneralforsamlinger();
    }

}
