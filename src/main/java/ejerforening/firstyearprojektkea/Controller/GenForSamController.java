package ejerforening.firstyearprojektkea.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class GenForSamController {
    @GetMapping ("/generalforsamlinger")
    public String visGenForSamSide(Model model){
        return "/generalforsamlinger";
    }
}
