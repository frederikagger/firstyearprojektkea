package ejerforening.firstyearprojektkea.Controller.arrangementController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping ("/genforsamForside")
    public String visGenForSamSide(){
        return "/arrangement/generalforsamlinger";
    }

    /**
     * Controlleren er flyttet hertil fra startController, da den godt kan mappe sig hertil fra forside
     * GetMapping, som indeholder en metode, som returnerer arrangementSiden
     * @return html-side "arrangementer"
     */
    @GetMapping ("/arrangementer")
    public String visArrangementSide(){
        return "/arrangement/arrangementer";
    }

}
