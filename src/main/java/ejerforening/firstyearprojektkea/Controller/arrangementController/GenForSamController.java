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



}
