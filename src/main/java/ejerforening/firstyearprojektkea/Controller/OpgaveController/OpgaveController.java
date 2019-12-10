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
 * Controller der administerer de opgaver et bestyrelsesmedlem kan for opgaver
 * GetMapping = viser de html sider, som man kan ramme
 * PostMapping = tager imod det der kommer fra html siderne
 * @author Signe
 * @since 4/12/2019
 */
@Controller
public class OpgaveController
{
    /**
     *  Autowire interfacet fra IOpgaveService, som er blevet implementeret af OpgaveService.
     *  Kan derved kalde på metoder derfra.
     */
    @Autowired
    private IOpgaveService opgaveService;


    /**
     * Getmetode, som er det foerste man rammer naar man tilgaar punktet "Oversigt over opgaver" på hjemmesiden
     * Metoden har Model som parameter, som har fragtet vha. addAttribute det view brugeren skal se, som er
     * alle de opgaver der er oprettet.
     * Opgaverne er blevet hentet vha. metoden hentAlle().
     *
     * @param model fragter de hentet opgaver til html.
     * @return /opgave/oversigt - html.
     */
    @GetMapping("/opgave/oversigt")
    public String oversigt(Model model)
    {
        List<OpgaveOversigt> resultHentAlle = opgaveService.hentAlle();
        model.addAttribute("opgaveOversigt", resultHentAlle);
        return "/opgave/oversigt";
    }


    /**
     * Getmetode, hvor der vises detaljer for valgt opgave.
     * @PathVariable knytter opgaveId, som er kommet fra html, til den data som skal bruges for at finde opgaveOplysninger.
     *
     * @param opgaveId valgte opgaves id.
     * @param model fragter den fundet opgave til html.
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
     * Getmetode, som sletter valgt opgave og retunerer vha. redirect til oversigt, hvor den slettet opgave ikke laengere kan ses.
     *
     * @PathVariable knytter opgaveId, som er kommet fra html, til den data som skal bruges for at slette opgave.
     * @param opgaveId
     * @return /opgave/oversigt - html
     */
    @GetMapping("/opgave/slet/{opgaveId}")
    public String slet(@PathVariable int opgaveId)
    {
        opgaveService.slet(opgaveId);
        return "redirect:/opgave/oversigt";
    }


    /**
     * Getmetode, hvor man kan oprette en opgave.
     * @param opgave Opgave klassen definerer hvilke attributer, der bruges for at oprette opgave.
     * @param model fragter de felter der kan indsaettes data i.
     * @return /opgave/opret - html
     */
    @GetMapping("/opgave/opret")
    public String opret(Opgave opgave, Model model)
    {
        model.addAttribute("opgave", opgave);
        return "/opgave/opret";
    }


    /**
     * Postmetode, der tager imod de data, som
     * @param opgave
     * @param bindingResult
     * @param model
     * @return
     */
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
        return "redirect:/opgave/opretOpgaveOplysninger";
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
