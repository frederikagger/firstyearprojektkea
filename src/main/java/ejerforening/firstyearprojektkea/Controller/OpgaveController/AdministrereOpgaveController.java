package ejerforening.firstyearprojektkea.Controller.OpgaveController;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;
import ejerforening.firstyearprojektkea.Service.Opgave.IOpgaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Controller der administerer de opgaver en bestyrelsesmedlem kan for opgaver
 * @author Signe
 */
@Controller
public class AdministrereOpgaveController
{
    @Autowired
    private IOpgaveService opgaveService;
    /**
     *Getmetode, som er det foerste man rammer naar man tilgaar punktet Administere opgave på hjemmesiden
     * @return /opgave/oversigt - html
     */
    @GetMapping("/opgave/oversigt")
    public String oversigt(Model model)
    {
        List<OpgaveOversigt> result = opgaveService.hentAlle();
        model.addAttribute("oversigt", result);
        return "/opgave/oversigt";
    }

    /**
     * Getmetode, som man rammer når man klikker på opret opgave
     * @return /opgave/opret - html
     */
    @GetMapping("/opgave/opret")
    public String opret()
    {
        return "/opgave/opret";
    }

    /**
     * Getmetode, som man rammer når man klikker på slet ved opgave
     * @param model
     * @param opgaveId
     * @return /opgave/oversigt - html
     */

    @GetMapping("/opgave/slet")
    public String slet(Model model, int opgaveId)
    {
        opgaveService.slet(opgaveId);
        List<OpgaveOversigt> result = opgaveService.hentAlle();
        model.addAttribute("oversigt", result);
        return "/opgave/oversigt";
    }



}
