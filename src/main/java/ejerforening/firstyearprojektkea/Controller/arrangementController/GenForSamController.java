package ejerforening.firstyearprojektkea.Controller.arrangementController;

import ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger.Slutbruger;
import ejerforening.firstyearprojektkea.Model.Arrangement.ArrangementOplysninger;
import ejerforening.firstyearprojektkea.Model.Arrangement.Generalforsamling;
import ejerforening.firstyearprojektkea.Service.Arrangement.IGenForSamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author Päivi
 * @since 10.12.2019
 * Controller for generalforsamling
 * GetMappinger tager imod get-requests, mens PostMappinger tager imod postRequest fra view.
 */

@Controller
public class GenForSamController {

    /**
     * Controller autowirer interface IgenForSamService, som henter metoderne fra den klasse, som
     * implementerer interface. Hvis der var flere implementerende klasser, skulle man bruge qualifier-annotation.
     */
    @Autowired
    IGenForSamService iGenForSamService;

    /**
     * Viser forsiden for arrangementer.
     * @return html-side "arrangementer"
     */
    @GetMapping("/arrangementer")
    public String visArrangementSide() {
        return "/arrangement/arrangementer";
    }

    /**
     * Viser forsiden for generalforsamlinger. Paa siden kan man se alle generalforsamlinger,
     * som fragtes til view i model. Saa metoden faar Model som parameter.
     * @param model fragter generalforsamling til view
     * @return html-siden "generalforsamlinger"
     */
    @GetMapping("/genforsamForside")
    public String visGenForSamSide(Model model) {
        List<Generalforsamling> alleGeneralforsamlinger = iGenForSamService.hentAlleGeneralforsamlinger();
        model.addAttribute("alleGeneralforsamlinger", alleGeneralforsamlinger);
        return "/arrangement/generalforsamlinger";
    }

    /**
     * Viser siden, hvor man kan se flere oplysninger om generalforsamlingen.
     * PathVariable laeser id sendt fra view og knytter data til metoden.
     * @param id arrangementId paa arrangement,som skal vises
     * @param model fragter ArrangementOplysninger og tilmeldte slutbrugere til view
     * @return html-siden "flereOplysningerGeneralForsamling"
     */
    @GetMapping("/flereOplysningerSide/{arrangementId}")
    public String visFlereOplysningerSide(@PathVariable("arrangementId") int id, Model model) {
        List<ArrangementOplysninger> arranOplysninger = iGenForSamService.findArranOplysninger(id);
        List<Slutbruger> tilmeldteSlutbrugere = iGenForSamService.findTilmeldte(id);
        model.addAttribute("arranOplysninger", arranOplysninger);
        model.addAttribute("tilmeldte",tilmeldteSlutbrugere);
        return "/arrangement/flereOplysningerGeneralforsamling";
    }

    /**
     * Tager imod arrangementId fra view (html-siden "generalforsamlinger") med PathVariable og
     * sletter generalforsamlingen med denne id. Returnerer siden med generalforsamlinger,
     * men uden den samme data (generalforsamlingen er jo allerede slettet).
     * redirect sender videre til getMapping, som viser forsiden igen.
     * @param id arrangementId på den generalforsamling, som slettes
     * @return tilbage til GetMapping for forsiden "genforsamForside", men uden den samme data
     */
    @GetMapping ("/sletGeneralforsamling/{arrangementId}")
    public String visSlettetGeneralforsamling(@PathVariable("arrangementId") int id) {
        boolean sletOk = iGenForSamService.sletGeneralforsamling(id);
        return "redirect:/genforsamForside";
    }



    /**
     * Viser html-siden, hvor man kan oprette en generalforsamling
     * @param genfor generalforsamling, som paa dette tidspunkt kun har default-vaerdier
     * @param model model, hvori generalforsamlimgen fragtes til view
     * @return html-siden "opretGeneralforsamling", hvor man kan oprette
     */
    @GetMapping("oprettelseForside")
    public String visOprettelseSide(Generalforsamling genfor, Model model) {
        model.addAttribute("generalforsamling", genfor);
        return "arrangement/opretGeneralforsamling";
    }

    /**
     * Tager imod oplysninger fra view (html-siden "opretGeneralforsamling"),
     * som er knyttet til generalforsamling og som er sendt fra view med post.
     * Controller beder service om at validere og oprette.
     * @param generalforsamling som skal oprettes
     * @param binding BindingResult, som er et objekt til validering
     * @param model model, hvori generalforsamling fragtes til view
     * @return html-siden "opretGeneralforsamling", som viser en eventulel fejl,
     * eller getMapping "opretGeneralforsamlingFortsatGet", hvis oprettelsen gaar godt
     */
    @PostMapping("/dataTilGeneralforsamling")
    public String opretGeneralForsamling(@Valid Generalforsamling generalforsamling, BindingResult binding, Model model) {
        String bedOmValidereOgOprette = iGenForSamService.validereOgOpretGenForSam(generalforsamling, binding, model);
        return bedOmValidereOgOprette;
    }

    /**
     * Tager imod redirect fra metoden validereOgOpretGenForSam() i service, naar
     * den foerste del af oprettelsen er gaet godt og der skal vises den naeste side, hvor
     * man kan indtaste de resterende oplysninger knyttet til ArrangementOplysninger.
     * @param arrOplys arrangementOplysninger til den generalforsamlign, som skal oprettes
     * @param model model, hvori arrangementOplysninger fragtes til view
     * @return html-siden "opretGeneralforsamlingForsat", hvor man kan indtaste de resterende oplysninger
     */
    @GetMapping("/opretGeneralforsamlingFortsatGet")
    public String visOprettelseSideFortsat(ArrangementOplysninger arrOplys, Model model) {
        model.addAttribute("arrangementOplysninger", arrOplys);
        return "arrangement/opretGeneralforsamlingFortsat";
    }

    /**
     * Tager imod oplysninger fra view (html-siden "opretGeneralforsamlingFortsat"), som
     * skal tilfoejes til tabellen ArrangementOplysninger.
     * Beder service om at validere og oprette.
     * @param arrOplys arrangementOplysninger til den generalforsamlign, som skal oprettes
     * @param binding BindingResult, som er et objekt til validering
     * @param model model, hvori arrangementOplysninger fragtes til view
     * @return html-siden "opretGeneralforsamlingFortsat", som enten viser bekraeftelse eller fejlmelding på oprettelse
     */
    @PostMapping("/opretGeneralForsamlingAfslut")
    public String visOprettelseSideMedBekraeftelse(@Valid ArrangementOplysninger arrOplys, BindingResult binding, Model model) {
        String bedOmValidereOgAfslutOpret = iGenForSamService.validereOgOpretAfslut(arrOplys, binding, model);
        return bedOmValidereOgAfslutOpret;
    }



    /**
     * Tager imod arrangementId fra view (fra html-siden "generalforsamlinger") og bruger den
     * til at hente den paagaeldende generalforsamling og arrangementOplysninger.
     * De sendes til html-siden "opdateringsGeneralforsamling" i model.
     * @param id  arrangementId på den generalforsamling, som opdateres
     * @param model fragter generalforsamling og arrangementOplysninger til view
     * @return html-siden "opdateringGeneralforsamling", hvor man kan opdatere oplysninger
     */
    @GetMapping("/visOpdatereGeneralforsamling/{arrangementId}")
    public String visOpdateringsside(@PathVariable("arrangementId") int id, Model model) {
        List<Generalforsamling> generalforsamling = iGenForSamService.findGeneralforsamling(id);
        List<ArrangementOplysninger> arranOplysninger = iGenForSamService.findArranOplysninger(id);
        model.addAttribute("generalforsamling", generalforsamling.get(0));
        model.addAttribute("arrangementOplysninger", arranOplysninger.get(0));
        return "/arrangement/opdateringGeneralforsamling";
    }

    /**
     * Tager imod oplysninger, som er sendt med post fra view (html-siden opdateringGeneralforsamling).
     * Oplysninger er knyttet baade til Generalforsamling og ArrangementOplysninger.
     * Oplysninger skal valideres med @Valid og BindingResult.
     * Metoden beder service om at validere og opdatere. Service returnerer siden, som controller skal
     * soerge for at vise. Idet validering kraever mere arbejde end bare at vaelge view, er denne opgave delegeret til service
     * @param genForSam generalforsamling
     * @param binding BindingResult, som er et objekt til validering
     * @param model model, hvori arrangementOplysinger og generalforsamling blev fragtet fra controller til view
     * @return html-siden, som enten viser bekraeftelse eller fejlmelding på opdatering
     */
    @PostMapping("/opdatereGeneralforsamling")
    public String opdatereGeneralforsamling(@Valid Generalforsamling genForSam, BindingResult binding, @Valid ArrangementOplysninger arrOply, BindingResult bResult, Model model) {
        String bedOmValideringOgOpdatering = iGenForSamService.validereOgOpdatereGenForSam(genForSam, binding, arrOply, bResult, model);
        return bedOmValideringOgOpdatering;
    }


    /**
     * Tager imod arrangementId fra html-siden "generalforsamlinger". PathVariable laeser
     * input og fletter den til metoden. Arrangementid bruges til at finde generalforsamling,
     * hvortil der skal tilfoejes slutbrugere. Controller beder service om at finde generalforsamlingen,
     * som controller sender til view i modellen.
     * @param id arrangementId paa generalforsamlingen
     * @param model model, hvori generalfosamling fragtes til view
     * @return html-siden "tilfoejSlutbrugerTilGenforsam", hvor man kan soege paa slutbrugere
     */

    @GetMapping("/visTilfoejSlutbruger/{arrangementId}")
    public String visTilfoejSlutbrugerSide(@PathVariable("arrangementId") int id, Model model) {
        List<Generalforsamling> generalforsamlinger = iGenForSamService.findGeneralforsamling(id);
        Generalforsamling generalforsamling = generalforsamlinger.get(0);
        model.addAttribute("generalforsamling", generalforsamling);
        return "/arrangement/tilfoejSlutbrugerTilGenforsam";
    }

    /**
     * For at kunne knytte slutbrugere til generalforsamling, skal vi bruge arrangementId.
     * Generalforsamling blev foerst fragter fra controller til html-siden "generalforsamlinger".
     * Derfra blev arrangementId til denne generalforsamling sendt til getMapping "visTilfoejSlutbruger".
     * Den hentede igen generalforsamlingen og sendte den i model til hmtl-siden "tilfoejSlutbrugerTilGenforsam"
     * Paa den maade er generalforsamlingen kommet med fra forsiden til den side, hvor man kan tilfoeje slutbruger
     * til denne generalforsamling.
     * Denne postMapping tager med WebRequest imod to soegekriterier (fornavn og efternavn),
     * som bruger har indtastet paa html-siden "tilfoejSlutbrugerTilGenforsam".
     * Igen skal vi bruge arrangementId til generalforsamling og vi beder service om at finde slutbruger og
     * tilmelde slutbrugeren til denne generalforsamling.
     * Service returnerer den samme side enten med bekraeftelse eller fejlmelding.
     * @param webr WebRequest laeser brugerns input og knytter det til metoden
     * @param generalforsamling, som slutbrugeren skal tilmeldes til.
     * @param model model, hvori generalforsamling fragtes fra controller til view
     * @return html-siden "tilfoejSlutbrugerTilGenforsam" med bekraftelse eller fejlmelding
     */
    @PostMapping("/soegSlutbrugere")
    public String findSlutbruger(WebRequest webr, Generalforsamling generalforsamling, Model model) {
        int arrangementId = generalforsamling.getArrangementId();
        String visSideMedSvar = iGenForSamService.findDeltager(webr,arrangementId,model);
        return visSideMedSvar;
    }
}
