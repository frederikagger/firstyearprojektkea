package ejerforening.firstyearprojektkea.Controller.OpgaveController;

import ejerforening.firstyearprojektkea.Model.Opgave.Opgave;
import ejerforening.firstyearprojektkea.Model.Opgave.OpgaveOplysninger;
import ejerforening.firstyearprojektkea.Model.Opgave.OpgaveOversigt;
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
 * Controller der administerer de muligheder et bestyrelsesmedlem kan for opgaver.
 *
 * GetMapping = viser de html sider, som man kan ramme
 * PostMapping = tager imod det der kommer fra html siderne
 *
 * Servervalideringer sker i Controller, da det oenskes saa hurtigt som muligt at faa en fejlmeddelse, hvis input fra brugeren indeholder fejl.
 * Det goer det nemmere at debugge og fejldata er kommet saa lidt ind i programmet som muligt.
 *
 * Man kan vaelge at skubbe det ud til Service, for at underbygge at Controller kun modtager input fra view (sender det videre til Service,
 * som skal staa for at validering af inputet), og at retunere et view til brugeren.
 *
 * Der er fordele og ulemper ved begge maader.
 * Det kan også vaelges at baade Controller og Service har hver deres type validering.
 *
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
     * @PathVariable knytter opgaveId, som er kommet fra oversigt (html), til den data som skal bruges for at finde opgaveOplysninger.
     *
     * @param opgaveId valgte opgaves id.
     * @param model fragter den fundet opgave til html.
     * @return /opgave/detaljer - html
     */
    @GetMapping("/opgave/detaljer/{opgaveId}")
    public String detaljer(@PathVariable int opgaveId, Model model)
    {
        OpgaveOplysninger resultfindValgteOpgave = opgaveService.findValgteOpgaveOplysninger(opgaveId);
        model.addAttribute("opgaveOplysninger", resultfindValgteOpgave);
        return "/opgave/detaljer";
    }

    /**
     * Getmetode, hvor der vises de fejlter man kan opdatere for en valgt opgave.
     * @PathVariable knytter opgaveId, som er kommet fra oversigt (html), til den data som skal bruges for at finde opgaveOplysninger.
     *
     * @param opgaveId hvilken opgave der oenskes opdateret
     * @param model fragter de fundet opgaveOplysninger til html.
     * @return /opgave/opdater - html
     */
    @GetMapping("/opgave/opdater/{opgaveId}")
    public String opdater(@PathVariable int opgaveId, Model model)
    {
        OpgaveOplysninger resultfindValgteOpgaveOplysninger = opgaveService.findValgteOpgaveOplysninger(opgaveId);
        model.addAttribute("opdater", resultfindValgteOpgaveOplysninger);
        return "/opgave/opdater";
    }

    /**
     *  Postmetode, der tager imod data fra opdater html.
     *  Dataen bliver valideret i metoden, hvis ingen fejl i inputtet sendes det videre i systemet til opdatering af oplysninger til opgaven.
     *  Naar opdateringen kommer tilbage, tjekkes, der om det lykkedes ved databasen, hvis det gik godt, redirectes man til oversigt.
     *  Hvis der kom en fejlbesked sendes man til opdater (den side man allerede er paa) og faar en fejlbesked udskrevet.
     *
     * @param opgaveOplysninger OpgaveOplysninger klassen definerer hvilke attributer, der bruges for at opdatere oplysninger til opgave.
     * @param bindingResult resultatet efter validering af input som vises, hvis der var fejl.
     * @param model fragter fejlbesked, hvis input eller databasen har fejlet.
     * @return redirect:/opgave/oversigt - html
     */
    @PostMapping("/validere")
    public String validere(@Valid OpgaveOplysninger opgaveOplysninger, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("bindingResult", bindingResult);
            System.out.println("Kommer jeg her?");
            return "/opgave/opdater" ;
        }

        try
        {
            opgaveService.erOpgaveOpdateret(opgaveOplysninger);
        }
        catch (Exception e)
        {
            String fejlbesked = "Opgaven kunne ikke opdateres";
            model.addAttribute("fejlbesked", fejlbesked);
            return "/opgave/opdater";
        }

        return "redirect:/opgave/oversigt";
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
     *
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
     * Postmetode, der tager imod data fra opret html.
     * Dataen bliver valideret i metoden, hvis ingen fejl i inputtet sendes det videre i systemet til oprettelse af opgave.
     * Naar oprettelsen kommer tilbage tjekkes, der om det lykkedes ved databasen, hvis det gik godt, redirectes man til
     * opretOpgaveOplysninger, men hvis der kom en fejlbesked sendes man til opret (den side man allerede er paa) og faar en fejlbesked udskrevet.
     *
     * @param opgave Opgave klassen definerer hvilke attributer, der bruges for at oprette opgave.
     * @param bindingResult resultatet efter validering af input som vises, hvis der var fejl
     * @param model fragter de felter der kan indsaettes data i.
     * @return redirect:/opgave/opretOpgaveOplysninger - html
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

    /**
     * Getmetode, hvor man kan oprette oplysninger til den opgave man er i gang med at oprette.
     *
     * @param opgaveOplysninger OpgaveOplysninger klassen definerer hvilke attributer, der bruges for at oprette oplysninger til opgaven.
     * @param model fragter de felter der kan indsaettes data i.
     * @return /opgave/opretOplysningerIndhold - html
     */
    @GetMapping("/opgave/opretOpgaveOplysninger")
    public String opretOpgaveOplysninger(OpgaveOplysninger opgaveOplysninger, Model model){
        model.addAttribute("opgaveOplysninger", opgaveOplysninger);
        return "/opgave/opretOplysningerIndhold";
    }

    /**
     * Postmetode, der tager imod data fra opretOpgaveOplysninger html.
     * Dataen bliver valideret i metoden, hvis ingen fejl i inputtet sendes det videre i systemet til oprettelse af oplysninger til opgaven.
     * Naar oprettelsen kommer tilbage, tjekkes, der om det lykkedes ved databasen, hvis det gik godt, redirectes man til
     * oversigt, hvor den nyoprettet opgave kan ses.
     * Hvis der kom en fejlbesked sendes man til opretOplysningerIndhold (den side man allerede er paa) og faar en fejlbesked udskrevet.
     *
     * @param opgaveOplysninger OpgaveOplysninger klassen definerer hvilke attributer, der bruges for at oprette oplysninger til opgave.
     * @param bindingResult resultatet efter validering af input som vises, hvis der var fejl.
     * @param model fragter de felter der kan indsaettes data i.
     * @return redirect:/opgave/oversigt - html
     */
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
}
