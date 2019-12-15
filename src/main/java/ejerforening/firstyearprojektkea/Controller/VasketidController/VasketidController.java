package ejerforening.firstyearprojektkea.Controller.VasketidController;

import ejerforening.firstyearprojektkea.Model.Vasketider.Vasketid;
import ejerforening.firstyearprojektkea.Service.Vasketid.IVasketidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * @author Frede
 * @since 11/12/2019
 *
 */

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
        model.addAttribute("vasketider",iVasketidService.seLedigeVasketider());
        return "vasketider/oversigt";
    }

    /**
     *
     * @param vasketid bliver oprettet i viewet og sendt til IVasketidService
     * @param bindingResult
     * @param model fragter alle de ledige vasketider tilbage til viewet
     * @return
     */

    @PostMapping("/vasketider/opdater")
    public String visOpdateringsform(@Valid Vasketid vasketid, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "Fejl indtastning";
        }
        iVasketidService.book(vasketid);
        model.addAttribute("vasketider", iVasketidService.seLedigeVasketider());
        return "vasketider/oversigt";
    }

    @GetMapping("/vasketider/bookedeVasketider")
    public String seBookedeVasketider(Model model){
        model.addAttribute("vasketider",iVasketidService.seBookedeVasketider());
        return "vasketider/bookedeVasketider";
    }


}