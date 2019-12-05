package ejerforening.firstyearprojektkea.Controller.arrangementController;

import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import ejerforening.firstyearprojektkea.Service.Arrangement.IGenForSamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
     * Viser forsiden for generalforsamlinger. Paa siden kan man se alle generalforsamlinger og
     * arragementOplysninger, som fragtes til view i model, jf. metoden addAttribute().
     * Saa metoden faar Model som parameter.
     * @return html-siden "generalforsamlinger"
     */
    @GetMapping ("/genforsamForside")
    public String visGenForSamSide(Model model){
        List<Generalforsamling> alleGeneralforsamlinger = iGenForSamService.hentAlleGeneralforsamlinger();
        model.addAttribute("alleGeneralforsamlinger", alleGeneralforsamlinger);
        List<ArrangementOplysninger> alleArrangementOplysninger = iGenForSamService.hentAlleArranOplysninger();
        model.addAttribute("alleArranOplysninger", alleArrangementOplysninger);
        return "/arrangement/generalforsamlinger";
    }

}
