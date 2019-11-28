package ejerforening.firstyearprojektkea.Controller;

import ejerforening.firstyearprojektkea.Service.LejlighedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LejlighedsController {

    @Autowired
    private LejlighedService lejlighedService;

    @GetMapping("/lejligheder")
    public String Lejligheder(Model model){
        model.addAttribute("lejligheder", lejlighedService.hentAlle());
        return "lejligheder";

    }
}
