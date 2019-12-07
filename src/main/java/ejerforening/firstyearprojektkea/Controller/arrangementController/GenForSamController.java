package ejerforening.firstyearprojektkea.Controller.arrangementController;

import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import ejerforening.firstyearprojektkea.Service.Arrangement.IGenForSamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for generalforsamling
 * @Author Päivi
 */

@Controller
public class GenForSamController {

    @Autowired
    IGenForSamService iGenForSamService;

    /**
     * Viser forsiden forarrangementer.
     * @return html-side "arrangementer"
     */
    @GetMapping ("/arrangementer")
    public String visArrangementSide(){
        return "/arrangement/arrangementer";
    }

    /**
     * Viser forsiden for generalforsamlinger. Paa siden kan man se alle generalforsamlinger,
     * som fragtes til view i model, jf. metoden addAttribute().Saa metoden faar Model som parameter.
     * @param model fragter generalforsamling til view
     * @return html-siden "generalforsamlinger"
     */
    @GetMapping ("/genforsamForside")
    public String visGenForSamSide(Model model){
        List<Generalforsamling> alleGeneralforsamlinger = iGenForSamService.hentAlleGeneralforsamlinger();
        model.addAttribute("alleGeneralforsamlinger", alleGeneralforsamlinger);
        return "/arrangement/generalforsamlinger";
    }

    /**
     * Viser siden mere flere oplysninger om generalforsamlingen fra ArrangementOplysninger
     * PathVariable laeser id sendt fra view og knytter data til metoden.
     * @param id arrangementId paa arrangement,som skal vises
     * @param model fragter oplysninger til view
     * @return html-side flereOplysningerGeneralForsamling
     */

    @GetMapping("/flereOplysningerSide/{arrangementId}")
    public String visFlereOplysningerSide(@PathVariable("arrangementId") int id, Model model) {
        List<ArrangementOplysninger> arranOplysninger = iGenForSamService.findArranOplysninger(id);
        model.addAttribute("arranOplysninger", arranOplysninger);
        return "/arrangement/flereOplysningerGeneralforsamling";
    }

    /**
     * Sletter generalforsamling og sender til getMapping for genforsamForside, men uden data,
     * fordi sletning er udfoert og data skal ikke vaere der, naar siden oploades igen (redirect)
     * PathVariable laeser id sendt fra view og knytter data til metoden.
     * @param id arrangementId på den generalforsamling, som slettes
     * @return til GetMapping for genforsamForside
     */
    @GetMapping("/sletGeneralforsamling/{arrangementId}")
    public String visSlettetGeneralforsamling(@PathVariable("arrangementId") int id){
        boolean sletOk = iGenForSamService.sletGeneralforsamling(id);
        return "redirect:/genforsamForside";
    }

    /**
     * PathVariable laeser id sendt fra view og knytter data til metoden.
     * Foerst skal vi hente generalforsamling og arrangementOplysninger fra databasen.
     * Viser siden, hvor man kan opdatere oplysninger.
     * @param id arrangementId på den generalforsamling, som opdateres
     * @param model fragter oplysninger til view
     * @return html-siden opdateringGeneralforsamling, hvor oplysninger kan rettes
     */
    @GetMapping("/visOpdatereGeneralforsamling/{arrangementId}")
    public String visOpdateringsside(@PathVariable("arrangementId") int id, Model model){
        List<Generalforsamling> generalforsamling = iGenForSamService.findGeneralforsamling(id);
        List<ArrangementOplysninger> arranOplysninger = iGenForSamService.findArranOplysninger(id);
        model.addAttribute("generalforsamling", generalforsamling.get(0));
        model.addAttribute("arrangementOplysninger", arranOplysninger.get(0));
        return "/arrangement/opdateringGeneralforsamling";
    }

    /**
     * Tager imod post fra view - der sendes baade Generalfosamling og ArrangementOplysninger
     * Beder iGenForSamService om at validere og returnere view, som skal vises. Metoden returnerer saa dette view.
     * Idet validering kraever mere arbejde end at vaelge view, er denne opgave delegeret til service
     * @param genForSam
     * @param binding
     * @param model
     * @return
     */

    @PostMapping("/opdatereGeneralforsamling")
    public String opdatereGeneralforsamling(@Valid Generalforsamling genForSam, BindingResult binding, @Valid ArrangementOplysninger arrOply, BindingResult bResult, Model model){
        String bedOmValideringOgOpdatering = iGenForSamService.validereOgOpdatereGenForSam(genForSam, binding, arrOply, bResult,model);
        return bedOmValideringOgOpdatering;
    }

    @GetMapping ("oprettelseForside")
    public String visOprettelseSide(Generalforsamling genfor, Model model){
        model.addAttribute("generalforsamling", genfor);
        return "arrangement/opretGeneralforsamling";
    }

    @PostMapping("/dataTilGeneralforsamling")
    public String opretGeneralForsamling(@Valid Generalforsamling generalforsamling, BindingResult binding, Model model){
        String bedOmValidereOgOprette = iGenForSamService.validereOgOpretGenForSam(generalforsamling,binding,model);
        return bedOmValidereOgOprette;
    }

    @GetMapping("/opretGeneralforsamlingFortsatGet")
    public String visOprettelseSideFortsat(ArrangementOplysninger arrOplys, Model model){
        model.addAttribute("arrangementOplysninger", arrOplys);
        return "arrangement/opretGeneralforsamlingFortsat";
    }


   @PostMapping("/opretGeneralForsamlingAfslut")
    public String visOprettelseSideMedBekraeftelse(@Valid ArrangementOplysninger arrOplys, BindingResult binding, Model model) {
       String bedOmValidereOgAfslutOpret = iGenForSamService.validereOgOpretAfslut(arrOplys, binding, model);
       return bedOmValidereOgAfslutOpret;
   }

}
