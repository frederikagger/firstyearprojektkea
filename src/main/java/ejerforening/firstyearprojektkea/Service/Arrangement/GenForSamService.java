package ejerforening.firstyearprojektkea.Service.Arrangement;

import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import ejerforening.firstyearprojektkea.Repository.Arrangement.IGenForSamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
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

    public List<Generalforsamling> hentAlleGeneralforsamlinger() {
        return iGenForSamRepo.hentAlleGeneralforsamlinger();
    }

    public List<ArrangementOplysninger> findArranOplysninger(int id) {
        return iGenForSamRepo.findArranOplysninger(id);
    }

    public List<Generalforsamling> findGeneralforsamling(int id) {
        return iGenForSamRepo.findGeneralforsamling(id);
    }

    public boolean sletGeneralforsamling(int id) {
        return iGenForSamRepo.sletGeneralforsamling(id);
    }

   public String validereOgOpretGenForSam(Generalforsamling genfor, BindingResult bResult, Model model){
       if(bResult.hasErrors()){
           model.addAttribute("bResult", bResult);
           return "/arrangement/opretGeneralforsamling"; }

       boolean opretGenFor = iGenForSamRepo.opretGeneralforsamling(genfor);

       if(opretGenFor){ return "redirect:/bekraeftetGeneralforsamling";
       }
       else{
           String fejlbesked = "Generalforsamlingen kunne ikke opdrettes";
           model.addAttribute("fejlbesked",fejlbesked);
           return "/arrangement/opretGeneralforsamling";
       }
    }

    public String validereOgOpdatereGenForSam(Generalforsamling genfor, BindingResult binding, ArrangementOplysninger arroplys, BindingResult bResult, Model model) {
        if (binding.hasErrors()) {
            model.addAttribute("binding", binding);
            model.addAttribute("bResult", bResult);
            return "/arrangement/opdateringGeneralforsamling";
        }

        boolean opdateretGenFor = iGenForSamRepo.opdatereGeneralforsamling(genfor, arroplys);

        if (opdateretGenFor) {
            String bekraeftelse = "Generalforsamlingen er opdateret";
            model.addAttribute("bekraeftelse", bekraeftelse);
            return "/arrangement/opdateringGeneralforsamling";
        } else {
            String fejlbesked = "Generalforsamlingen kunne ikke opdateres";
            model.addAttribute("fejlbesked", fejlbesked);
            return "/arrangement/opdateringGeneralforsamling";
        }
    }
}
