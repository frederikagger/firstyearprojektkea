package ejerforening.firstyearprojektkea.Service.Arrangement;

import ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger.Slutbruger;
import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import ejerforening.firstyearprojektkea.Repository.Arrangement.IGenForSamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.WebRequest;

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
    public List<Slutbruger> findTilmeldte(int id){
        return iGenForSamRepo.findTilmeldte(id);
    }

    public List<Generalforsamling> findGeneralforsamling(int id) {
        return iGenForSamRepo.findGeneralforsamling(id);
    }

    public boolean sletGeneralforsamling(int id) {
        return iGenForSamRepo.sletGeneralforsamling(id);
    }

    //returnerer html-sider, fordi opgaven er delegeret fra controller her til service
   public String validereOgOpretGenForSam(Generalforsamling genfor, BindingResult bResult, Model model){
       if(bResult.hasErrors()){
           model.addAttribute("bResult", bResult);
           return "/arrangement/opretGeneralforsamling"; }

       boolean opretGenFor = iGenForSamRepo.opretGeneralforsamling(genfor);

       if(opretGenFor){ return "redirect:/opretGeneralforsamlingFortsatGet";

       }
       else{
           String fejlbesked = "Generalforsamlingen kunne ikke opdrettes. Ret venligst på oplysninger.";
           model.addAttribute("fejlbesked",fejlbesked);
           return "/arrangement/opretGeneralforsamling";
       }
    }

    public String validereOgOpretAfslut(ArrangementOplysninger arrOplys, BindingResult binding, Model model){
        if(binding.hasErrors()){
            model.addAttribute("binding", binding);
            return "arrangement/opretGeneralforsamlingFortsat";
        }
        boolean opdateretGenfor = iGenForSamRepo.opretGeneralforsamlingAfslut(arrOplys);

        if (opdateretGenfor) {
            String bekraeftelse = "Generalforsamlingen er oprettet";
            model.addAttribute("bekraeftelse", bekraeftelse);
            return "/arrangement/opretGeneralforsamlingFortsat";
        } else {
            String fejlbesked = "Generalforsamlingen kunne ikke oprettes";
            model.addAttribute("fejlbesked", fejlbesked);
            return "/arrangement/opretGeneralforsamlingFortsat";
        }
    }


    //returnerer html-sider, fordi opgaven er delegeret fra controller her til service
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

    public String findDeltager(WebRequest webr, int arrangementId, Model model){
        String fornavnet = webr.getParameter("fornavn");
        String efternavnet = webr.getParameter("efternavn");
        List<Slutbruger> deltager = iGenForSamRepo.findSlutbruger(fornavnet,efternavnet);
        if(deltager.size() == 0){
            String fejlbesked = "Ingen søgeresultater, prøv igen";
            model.addAttribute("fejlbesked", fejlbesked);
        }
        else {
            model.addAttribute("deltager", deltager);
            int slutbrugerId = deltager.get(0).getSlutbrugerId();
            boolean tilfoej = iGenForSamRepo.knytSlutbrugerOgArrangement(slutbrugerId,arrangementId);
            System.out.println("tilfoej " + tilfoej);
            if(tilfoej) {
                String bekraeftelse = "Deltageren er tilføjet";
                model.addAttribute("bekraeftelse", bekraeftelse);
            }else{
                String fejl = "Deltageren er allerede på listen; prøv en anden deltager";
                model.addAttribute("fejl", fejl);
            }
        }
        return "/arrangement/tilfoejSlutbrugerTilGenforsam";
    }

}
