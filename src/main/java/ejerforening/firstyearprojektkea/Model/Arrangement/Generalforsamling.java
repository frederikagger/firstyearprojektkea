package ejerforening.firstyearprojektkea.Model.Arrangement;

import java.time.LocalDate;


public class Generalforsamling extends Arrangement{

    public Generalforsamling(){};
    private String emne;

    public Generalforsamling(String emne){
        this.emne = emne;
    }

    /* Jeg er lidt usikker paa, om jeg skal bruge denne - jeg maa teste. Konstruktoer uden parametre
    * nedarves ikke*/

    public Generalforsamling(int arrangementId, String navn, LocalDate oprettelsesDato, LocalDate opdateringsDato, String emne/*, int arranOplysId*/){
        super(arrangementId, navn, oprettelsesDato, opdateringsDato/*, arranOplysId*/);
        this.emne = emne;
    }


    public String getEmne() {
        return emne;
    }

    public void setEmne(String emne) {
        this.emne = emne;
    }
}
