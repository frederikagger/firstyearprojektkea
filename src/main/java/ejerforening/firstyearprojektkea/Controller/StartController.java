package ejerforening.firstyearprojektkea.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Signe
 *
 * @since 30-11-2019
 * Klassen er det foerste man rammer, når man tilgår hjemmesiden
 */

@Controller
public class StartController
{
    /**
     *Getmetode, som er det foerste man rammer naar man tilgaar hjemmesiden
     * @return htmlsiden forside
     */
    @GetMapping("/")
    public String visForside(){
        return "/forside";
    }

    /**
     * Getmetode
     * Skal have tjekket hvorfor det her endelig virker, mens man staar paa forsiden og klikker på forside saa
     * @return html forsiden
     */
    @GetMapping("/forside")
    public String retunereVisForside()
    {
        return "/forside";
    }
}
