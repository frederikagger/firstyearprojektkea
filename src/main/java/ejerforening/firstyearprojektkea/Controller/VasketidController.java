package ejerforening.firstyearprojektkea.Controller;

import ejerforening.firstyearprojektkea.Model.Vasketider.Vasketid;
import ejerforening.firstyearprojektkea.Service.Vasketid.IVasketidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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

    @PostMapping("/vasketider/opdater")
    public String visOpdateringsform(@Valid Vasketid vasketid, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "Fejl indtastning";
        }
        iVasketidService.book(vasketid);
        return "vasketider/oversigt";
    }

}