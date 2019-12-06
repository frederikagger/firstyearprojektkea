package ejerforening.firstyearprojektkea.Service.Arrangement;

import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import ejerforening.firstyearprojektkea.Repository.Arrangement.IGenForSamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

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
    public List<Generalforsamling> findGeneralforsamling(int id){
        return iGenForSamRepo.findGeneralforsamling(id);
    }
    public boolean sletGeneralforsamling(int id){
        return iGenForSamRepo.sletGeneralforsamling(id);
    }

    public String validereOgOpdatereGenForSam(Generalforsamling genfor, ArrangementOplysninger arroplys, BindingResult bResult, Model model){
       if(bResult.hasErrors()){
           model.addAttribute("bResult", bResult);
           System.out.println("kommer jeg her");
           return "/arrangement/opdateringGeneralforsamling"; }

       boolean opdateretGenFor = iGenForSamRepo.opdatereGeneralforsamling(genfor);
       boolean opdateretArranOplys = iGenForSamRepo.opdatereArranOplys(arroplys);

       if(opdateretGenFor && opdateretArranOplys){
            String bekraeftelse = "Generalforsamlingen er opdateret";
            model.addAttribute("bekraeftelse", bekraeftelse);
            return "/arrangement/opdateringGeneralforsamling";
       }
       else{
           String fejlbesked = "Generalforsamlingen kunne ikke opdateres";
           model.addAttribute("fejlbesked",fejlbesked);
           return "/arrangement/opdateringGeneralforsamling";
       }
    }

}
