package ejerforening.firstyearprojektkea.Controller.SlutbrugerController;

import ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger.SlutbrugerOversigt;
import ejerforening.firstyearprojektkea.Service.Slutbruger.ISlutbrugerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Controller der administerer de opgaver en bestyrelsesmedlem kan for slutbruger
 * @author Signe
 */

@Controller
public class AdministrereSlutbrugereController
{
    @Autowired
    private ISlutbrugerService slutbrugerService;

    /**
     *Getmetode, som er det foerste man rammer naar man tilgaar punktet Administere slutbrugere på hjemmesiden
     * @return /slutbruger/oversigt - html
     */
    @GetMapping("/slutbruger/oversigt")
    public String oversigt(Model model)
    {
        List<SlutbrugerOversigt> result = slutbrugerService.hentAlle();
        model.addAttribute("oversigt", result);
        return "/slutbruger/oversigt";
    }

    /**
     * Getmetode, som man rammer når man klikker på slet ved slutbruger
     * @param model
     * @param slutbrugerId
     * @return /slutbruger/oversigt - html
     */
    @GetMapping("/slutbruger/slet")
    public String slet(Model model, int slutbrugerId)
    {
        slutbrugerService.slet(slutbrugerId);
        List<SlutbrugerOversigt> result = slutbrugerService.hentAlle();
        model.addAttribute("oversigt", result);
        return "/slutbruger/oversigt";
    }

    /*
    @GetMapping("/slutbruger/opdater")
    public String opdater()
    {
        return "/slutbruger/opdater";
    }

    @GetMapping("/slutbruger/detaljer")
    public String detaljer()
    {
        return "/slutbruger/detaljer";
    }

     */
}
