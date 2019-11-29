package ejerforening.firstyearprojektkea.Controller;

import ejerforening.firstyearprojektkea.Model.Lejlighed;
import ejerforening.firstyearprojektkea.Service.IlejlighedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**Oprettet af Frederik Agger og Signe Nørløv Eskildsen
 * d. 28/11/2019
 **/
@Controller
public class LejlighedsController {

    @Autowired
    private IlejlighedService ilejlighedService;

    @GetMapping("/seLejligheder")
    public String SeLejligheder(Model model){
        model.addAttribute("lejligheder",ilejlighedService.hentAlle());
        return "seAlleLejligheder";
    }

    @GetMapping("/form_opret_lejlighed")
    public String Form_opret_lejligheder(){
        return "form_opret_lejlighed";
    }

    @GetMapping("/bekræftelse")
    public String Bekræfelse(){
        return "bekræftelse";
    }

    @PostMapping("/opret_lejlighed")
    public String Opret_lejlighed(@ModelAttribute("lejlighed") Lejlighed lejlighed, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "error";
        }
        model.addAttribute("id",lejlighed.getId());
        model.addAttribute("etage",lejlighed.getEtage());
        model.addAttribute("lejlighedsside",lejlighed.isLejlighedsside());
        ilejlighedService.opret(lejlighed);
        return "bekræftelse";
    }
}
