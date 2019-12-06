package ejerforening.firstyearprojektkea.Service.Arrangement;

import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import ejerforening.firstyearprojektkea.Repository.Arrangement.IGenForSamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author paivi
 * Klassen implementerer interface for service og den autowirer interface for repository for generalforsamling.
 * Service kalder services fra repository.
 */

@Service
public class GenForSamService implements IGenForSamService {

    @Autowired
    IGenForSamRepo iGenForSamRepo;

    public List<Generalforsamling> hentAlleGeneralforsamlinger(){
        return iGenForSamRepo.hentAlleGeneralforsamlinger();
    }

    public List<ArrangementOplysninger> findArranOplysninger(int id){
        return iGenForSamRepo.findArranOplysninger(id);
    }

    public boolean sletGeneralforsamling(int id){
        return iGenForSamRepo.sletGeneralforsamling(id);
    }

}
