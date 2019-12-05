package ejerforening.firstyearprojektkea.Controller.arrangementController;

import ejerforening.firstyearprojektkea.Model.Arrangement.Arrangement;
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
    /**
     * Viser forsiden for generalforsamlinger
     * @return html-siden "generalforsamlinger"
     */
    @Autowired
    IGenForSamService iGenForSamService;

    /**
     * Controlleren er flyttet hertil fra startController, da den godt kan mappe sig hertil fra forside
     * GetMapping, som indeholder en metode, som returnerer arrangementSiden
     * @return html-side "arrangementer"
     */
    @GetMapping ("/arrangementer")
    public String visArrangementSide(){
        return "/arrangement/arrangementer";
    }

    @GetMapping ("/genforsamForside")
    public String visGenForSamSide(Model model){
        List<Generalforsamling> alleGeneralforsamlinger = iGenForSamService.hentAlleGeneralforsamlinger();
        model.addAttribute("alleGeneralforsamlinger", alleGeneralforsamlinger);
        return "/arrangement/generalforsamlinger";
    }

}
