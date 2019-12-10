package ejerforening.firstyearprojektkea.Controller;

import ejerforening.firstyearprojektkea.Model.Vasketider.Vasketid;
import ejerforening.firstyearprojektkea.Service.Vasketid.IVasketidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VasketidController {

    @Autowired
    private IVasketidService iVasketidService;

    @GetMapping("/vasketider")
    public String vasketiderForside(){
        return "vasketider/vasketider";
    }

    @GetMapping("/vasketider/oversigt")
    public String seVasketider(Model model){
        model.addAttribute("vasketider",iVasketidService.seVasketider());
        return "vasketider/oversigt";
    }

/*
    @GetMapping("/form_opret_lejlighed")
    public String form_opret_lejligheder(){
        return "/lejlighed/form_opret_lejlighed";
    }

    @GetMapping("/form_sletLejlighed")
    public String form_sletLejlighed(){
        return "/lejlighed/form_sletLejlighed";
    }

    @GetMapping("/form_soegLejlighed")
    public String form_soegLejlighed(){
        return "/lejlighed/form_soegLejlighed";
    }

    @PostMapping("/opret_lejlighed")
    public String opret_lejlighed(@ModelAttribute("lejlighed")@Valid Lejlighed lejlighed, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "Fejl indtastning";
        }
        model.addAttribute("etage",lejlighed.getEtage());
        model.addAttribute("lejlighedsside",lejlighed.isLejlighedsside());
        ilejlighedService.opret(lejlighed);
        return "/bekræftelse";
    }

    @PostMapping("/findLejlighed")
    public String findLejlighed(@ModelAttribute("lejlighed")@Valid Lejlighed lejlighed, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "Fejl indtastning";
        }
        model.addAttribute("lejlighedsid",ilejlighedService.findMedId(lejlighed.getLejlighedsid()));
        return "/lejlighed/visSoegningsSide";
    }
    @PostMapping("/sletLejlighed")
    public String sletLejlighed(@ModelAttribute("lejlighed")@Valid Lejlighed lejlighed, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "Fejl indtastning";
        }
        model.addAttribute("lejlighedsid", lejlighed.getLejlighedsid());
        ilejlighedService.sletLejlighed(lejlighed.getLejlighedsid());
        return "/bekræftelse";
    }
}
*/
}