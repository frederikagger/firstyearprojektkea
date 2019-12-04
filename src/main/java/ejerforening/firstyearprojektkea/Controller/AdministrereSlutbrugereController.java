package ejerforening.firstyearprojektkea.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SlutbrugerController
{
    /**
     *Getmetode, som er det foerste man rammer naar man tilgaar hjemmesiden
     * @return htmlsiden forside
     */
    @GetMapping("/slutbrugerforside")
    public String visSlutbrugerForside()
    {
        return "/slutbruger/slutbrugerforside";
    }
}
