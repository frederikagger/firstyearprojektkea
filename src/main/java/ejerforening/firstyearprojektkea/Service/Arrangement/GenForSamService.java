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
 * @since 08-12-2019
 * Klassen implementerer interface for service.
 * Arbejdsdelingen mellem Controller og Service: Controller viser, hvilke html-sider skal vises, men
 * i de tilfaelde, hvor det indebaerer tungere funktionalitet (jf. de tre sidste metoder),
 * aflaster Service og giver navnet paa html-siden til controller, som kan soerge for, at den bliver vist.
 */

@Service
public class GenForSamService implements IGenForSamService {

    /**
     * Klassen autowirer interface for repository, fordi den beder repository om at
     * udfoere operationer (repository danner kontakt til databasen).
     */
    @Autowired
    IGenForSamRepo iGenForSamRepo;

    @Override
    public List<Generalforsamling> hentAlleGeneralforsamlinger() {
        return iGenForSamRepo.hentAlleGeneralforsamlinger();
    }
    @Override
    public List<ArrangementOplysninger> findArranOplysninger(int id) {
        return iGenForSamRepo.findArranOplysninger(id);
    }
    @Override
    public List<Slutbruger> findTilmeldte(int id){
        return iGenForSamRepo.findTilmeldte(id);
    }
    @Override
    public List<Generalforsamling> findGeneralforsamling(int id) {
        return iGenForSamRepo.findGeneralforsamling(id);
    }
    @Override
    public boolean sletGeneralforsamling(int id) {
        return iGenForSamRepo.sletGeneralforsamling(id);
    }

    /**
     * Metoden validerer bruger-input, som kommer fra html-siden "opretGeneralforsamling" og opretter
     * generalforsamling. Den returnerer navnet på det view, som controller skal soerge for at vise.
     * Hvis der er fejl i input, vises siden igen med en fejlmelding om den.
     * Ellers oprettes generalforsamling. Metoden opretGeneralforsamling() returnerer boolean.
     * Er oprettelsen gaet godt, returneres den naeste oprettelsesside, men uden den samme data (redirect),
     * fordi den nye side skal bruges til nye oplysninger. Naar man redirecter, gaar det foerst igennem en getMapping.
     * Ellers returneres den samme side med en fejlbesked.
     * @param genfor generalforsamling, som skal oprettes
     * @param bResult BindingResult, som er et objekt til validering
     * @param model model, hvori generalforsamling blev fragtet fra controller til view
     * @return navnet paa html-siden, som controller skal soerge for at vise.
     */
    @Override
   public String validereOgOpretGenForSam(Generalforsamling genfor, BindingResult bResult, Model model){
       if(bResult.hasErrors()){
           model.addAttribute("bResult", bResult);
           return "/arrangement/opretGeneralforsamling"; }
       boolean opretGenFor = iGenForSamRepo.opretGeneralforsamling(genfor);
       if(opretGenFor){ return "redirect:/opretGeneralforsamlingFortsatGet"; }
       else{
           String fejlbesked = "Generalforsamlingen kunne ikke opdrettes. Ret venligst på oplysninger.";
           model.addAttribute("fejlbesked",fejlbesked);
           return "/arrangement/opretGeneralforsamling";
       }
    }

    /**
     * Efter at generalforsamlimngen er oprettet, skal ogsaa arrangementOplysninger, oprettes.
     * Metoden validerer arrangementOplysninger og opretter dem.
     * Den returnerer navnet på det view, som controller skal soerge for at vise.
     * Hvis der er fejl i input, vises siden igen med en fejlmelding om den.
     * Ellers oprettes generalforsamling. Metoden opretGeneralforsamlingAfslut() returnerer boolean.
     * Metoden har tjekket, at arrangementOplysninger ikke eksisterer i forvejen.
     * Er oprettelsen gaet godt, returneres den samme html-side med en bekraeftelse.
     * Ellers returneres den samme side med en fejlbesked.
     * @param arrOplys arrangementOplysninger, som skal oprettes
     * @param binding BindingResult, som er et objekt til validering
     * @param model model, hvori arrangementOplysninger blev fragtet fra controller til view
     * @return navnet paa den html-side, som controller skal vise
     */
    @Override
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

    /**
     * Metoden validerer bruger input og opdaterer generalforsamling paa html-siden "opdateringGeneralforsamling".
     * Den indeholder baade oplysninger i Generalforsamling og ArrangementOplysninger.
     * Hvis der ikke er fejl i validering, oprettes baade generalforsamling og arrangementOplysninger.
     * Metoden returnerer boolean. Er opdatering gaet godt, returneres bekraeftelse, ellers fejlmelding.
     * Saa kan man rette og opdatere igen.
     * @param genfor generalforsamling
     * @param binding BindingResult knyttet til generalforsamling
     * @param arroplys arrangementOplysninger
     * @param bResult BindingResult knyttet til arrangementOplysninger
     * @param model model, hvori generalforsamling og arrangementOplysninger fragtes fra controller til view
     * @return navnet paa den html-side, som controller skal vise
     */
    @Override
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

    /**
     * Metoden finder slutbruger paa html-siden "tilfoejSlutbrugerTilGenforsam"
     * og tilfoejer slutbruger til en generalforsamling.
     * Metoden faar fornavnet og efternavnet fra view og WebRequest laeser dem og knytter dem til metoden.
     * Metoden findSlutbruger() returnerer slutbrugeren i List. Hvis slutbrugeren ikke eksisterer, gives en
     * fejlmelding og brugeren kan proeve med et andet navn.
     * Ellers vises slutbrugeren paa siden og knyttes til arrangement med metoden knytSlutbrugerOgArrangement().
     * Hvis alt gaar vel, vises siden med bekraeftelse, og ellers med en besked om, at slutbrugeren allerede er tilmeldt.
     * Saa kan man proeve med en anden slutbruger.
     * @param webr WebRequest laeser input fra view og knytter det til metoden
     * @param arrangementId arrangementId paa generalforsamlingen
     * @param model model, hvori generalforsamling fragtes fra controller til view
     * @return navnet paa den html-side, som controller skal vise
     */
    @Override
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
