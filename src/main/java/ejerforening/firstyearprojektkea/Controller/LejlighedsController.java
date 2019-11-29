package ejerforening.firstyearprojektkea.Controller;

import ejerforening.firstyearprojektkea.Service.IlejlighedService;
import ejerforening.firstyearprojektkea.Service.LejlighedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**Oprettet af Frederik Agger og Signe Nørløv Eskildsen
 * d. 28/11/2019
 **/
@Controller
public class LejlighedsController {

    @Autowired
    private IlejlighedService ilejlighedService;

    @GetMapping("/lejligheder")
    public String Lejligheder(Model model){
        model.addAttribute("lejligheder", ilejlighedService.hentAlle());
        return "lejligheder";

    }
}
