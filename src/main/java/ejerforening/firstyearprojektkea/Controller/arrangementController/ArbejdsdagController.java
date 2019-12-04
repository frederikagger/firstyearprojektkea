package ejerforening.firstyearprojektkea.Controller.arrangementController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for arbejdsdag
 * @Author Päivi
 */
@Controller
public class ArbejdsdagController {

    @GetMapping("/arbejdsdagForside")
    public String visGenForSamSide(){
        return "/arrangement/arbejdsdage";
    }

}
