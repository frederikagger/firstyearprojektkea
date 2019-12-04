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

import javax.validation.Valid;

/**Oprettet af Frederik Agger og Signe Nørløv Eskildsen
 * d. 28/11/2019
 **/
@Controller
public class LejlighedsController {

    @Autowired
    private IlejlighedService ilejlighedService;

    /**
     * Lavet af FA 29-11-2019.
     * Denne metode linker til html siden seAlleLejligheder
     * som bliver opdateret med en tabel over
     * alle lejligheder i databasen
     * @param model
     * @return
     */
    @GetMapping("/seLejligheder")
    public String seLejligheder(Model model){
        model.addAttribute("lejligheder",ilejlighedService.hentAlle());
        return "seLejligheder";
    }

    @GetMapping("/form_opret_lejlighed")
    public String form_opret_lejligheder(){
        return "form_opret_lejlighed";
    }

    @GetMapping("/form_sletLejlighed")
    public String form_sletLejlighed(){
        return "form_sletLejlighed";
    }

    @GetMapping("/form_soegLejlighed")
    public String form_soegLejlighed(){
        return "form_soegLejlighed";
    }

    @PostMapping("/opret_lejlighed")
    public String opret_lejlighed(@ModelAttribute("lejlighed")@Valid Lejlighed lejlighed, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "Fejl indtastning";
        }
        model.addAttribute("etage",lejlighed.getEtage());
        model.addAttribute("lejlighedsside",lejlighed.isLejlighedsside());
        ilejlighedService.opret(lejlighed);
        return "bekræftelse";
    }

    @PostMapping("/findLejlighed")
    public String findLejlighed(@ModelAttribute("lejlighed")@Valid Lejlighed lejlighed, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "Fejl indtastning";
        }
        model.addAttribute("lejlighedsid",ilejlighedService.findMedId(lejlighed.getId()));
        return "visSoegningsSide";
    }
    @PostMapping("/sletLejlighed")
    public String sletLejlighed(@ModelAttribute("lejlighed")@Valid Lejlighed lejlighed, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "Fejl indtastning";
        }
        model.addAttribute("lejlighedsid", lejlighed.getId());
        ilejlighedService.sletLejlighed(lejlighed.getId());
        return "bekræftelse";
    }
}
