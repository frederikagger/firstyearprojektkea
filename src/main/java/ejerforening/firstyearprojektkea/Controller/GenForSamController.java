package ejerforening.firstyearprojektkea.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenForSamController {
    @GetMapping ("/generalforsamlinger")
    public String visGenForSamSide(Model model){
        return "/generalforsamlinger";
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
