package ejerforening.firstyearprojektkea.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LejlighedsController {

    @GetMapping("/lejligheder")
    public String Lejligheder(Model model){
        model.addAttribute("lejligheder", lejlighedService.hentAlle);
        return "lejligheder";

    }
}
