package ejerforening.firstyearprojektkea.Controller.OpgaveController;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.Opgave;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOplysninger;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;
import ejerforening.firstyearprojektkea.Service.Opgave.IOpgaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.util.List;

/**
 * Controller der administerer de opgaver en bestyrelsesmedlem kan for opgaver
 * @author Signe
 */
@Controller
public class OpgaveController
{
    @Autowired
    private IOpgaveService opgaveService;
    /**
     *Getmetode, som er det foerste man rammer naar man tilgaar punktet Administere opgave på hjemmesiden
     * Man ser alle de opgaver som er oprettet
     * @return /opgave/oversigt - html
     */
    @GetMapping("/opgave/oversigt")
    public String oversigt(Model model)
    {
        List<OpgaveOversigt> resultHentAlle = opgaveService.hentAlle();
        model.addAttribute("opgaveOversigt", resultHentAlle);
        return "/opgave/oversigt";
    }

    /**
     *
     * @param opgaveId
     * @param model
     * @return /opgave/detaljer - html
     */
    @GetMapping("/opgave/detaljer/{opgaveId}")
    public String detaljer(@PathVariable int opgaveId, Model model)
    {
        OpgaveOplysninger resultfindValgteOpgave = opgaveService.findValgteOpgaveOplysninger(opgaveId);
        model.addAttribute("detaljer", resultfindValgteOpgave);
        return "/opgave/detaljer";
    }


    /**
     * Getmetode, som man rammer når man klikker på slet ved opgave
     * @param model
     * @param opgaveId
     * @return /opgave/oversigt - html
     */
    @GetMapping("/opgave/slet/{opgaveId}")
    public String slet(@PathVariable int opgaveId, Model model)
    {
        opgaveService.slet(opgaveId);
        List<OpgaveOversigt> result = opgaveService.hentAlle();
        model.addAttribute("oversigt", result);
        return "redirect:/opgave/oversigt";
    }


    /**
     * Getmetode, som man rammer når man klikker på opret opgave
     * @return /opgave/opret - html
     */
    @GetMapping("/opgave/opret")
    public String opret(Opgave opgave, Model model)
    {
        model.addAttribute("opgave", opgave);
        return "/opgave/opret";
    }

    @PostMapping("/opgave/opretOpgaveIndhold")
    public String opretOpgaveIndhold(@Valid Opgave opgave, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("bindingResult", bindingResult);
            System.out.println("Kommer jeg her?");
            return "/opgave/opret";
        }

        try
        {
            opgaveService.OpretOpgave(opgave);
        }
        catch (Exception e)
        {
            String fejlbesked = "Opgaven kunne ikke oprettes";
            model.addAttribute("fejlbesked", fejlbesked);
            return "/opgave/opret";
        }
        return "redirect:/opgave/oversigt";
    }

    @GetMapping("/opgave/opretOpgaveOplysninger")
    public String opretOpgaveOplysninger(OpgaveOplysninger opgaveOplysninger, Model model){
        model.addAttribute("opgaveOplysninger", opgaveOplysninger);
        return "/opgave/opretOplysningerIndhold";
    }


    @PostMapping("/opgave/opretOpgaveOplysningerIndhold")
    public String opretOpgaveOplysningerIndhold(@Valid OpgaveOplysninger opgaveOplysninger, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("bindingResult", bindingResult);
            System.out.println("Kommer jeg her?");
            return "/opgave/opretOplysningerIndhold";
        }

        try
        {
            opgaveService.OpgaveOplysningerOprettet(opgaveOplysninger);
        }
        catch (Exception e)
        {
            String fejlbesked = "Oplysningerne til opgaven kunne ikke oprettes";
            model.addAttribute("fejlbesked", fejlbesked);
            return "/opgave/opretOplysningerIndhold";
        }

            return "redirect:/opgave/oversigt";
    }




    @GetMapping("/opgave/opdater/{opgaveId}")
    public String opdater(@PathVariable int opgaveId, Model model)
    {
        OpgaveOplysninger resultfindValgteOpgaveOplysninger = opgaveService.findValgteOpgaveOplysninger(opgaveId);
        model.addAttribute("opdater", resultfindValgteOpgaveOplysninger);
        return "/opgave/opdater";
    }


    @PostMapping("/validere")
    public String validere(@Valid OpgaveOplysninger opgaveOplysninger, BindingResult bResult, Model model)
    {

        if(bResult.hasErrors())
        {
            model.addAttribute("bResult", bResult);
            System.out.println("Kommer jeg her?");
            return "/opgave/opdater" ;
        }

        boolean erOpgaveopdateret = opgaveService.erOpgaveOpdateret(opgaveOplysninger);
        if(erOpgaveopdateret)
        {
            return "redirect:/opgave/oversigt";
        }
        else
        {
            String fejlbesked = "Opgaven kunne ikke opdateres";
            model.addAttribute("fejlbesked", fejlbesked);

 }

            return "redirect:/opgave/oversigt";

    }
}
