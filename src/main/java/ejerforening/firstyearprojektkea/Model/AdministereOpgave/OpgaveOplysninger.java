package ejerforening.firstyearprojektkea.Model.AdministereOpgave;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Klassen der indeholder attributerne for Opgave Oplysninger
 * @author Signe
 */

public class OpgaveOplysninger
{
    /**
     * Attributerne har validerings annotationerm som definerer:
     *
     * @NotNull = maa ikke være tom
     * LocalDate = datatype der udnytter javaklassen Time som er dato
     */
    @Id
    private int opgaveOplysningerId;

    @NotNull(message = "Indtast venligst en beskrivelse af opgaven")
    private String beskrivelse;

    @NotNull(message = "Indtast venligst varigheden for opgaven")
    private int varighed;

    @NotNull (message = "Indtast venligst sværheden for opgaven")
    private int svaerhedsgrad;

    @NotNull(message = "Indtast venligst dato: dd/mm/åååå")
    private LocalDate startDato;

    @NotNull(message = "Indtast venligst dato: dd/mm/åååå")
    private LocalDate slutDato;

    private LocalDate sidstOpdateret;

    public OpgaveOplysninger()
    {
    }

    public int getOpgaveOplysningerId()
    {
        return opgaveOplysningerId;
    }

    public void setOpgaveOplysningerId(int opgaveOplysningerId)
    {
        this.opgaveOplysningerId = opgaveOplysningerId;
    }

    public String getBeskrivelse()
    {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse)
    {
        this.beskrivelse = beskrivelse;
    }

    public int getVarighed()
    {
        return varighed;
    }

    public void setVarighed(int varighed)
    {
        this.varighed = varighed;
    }

    public int getSvaerhedsgrad()
    {
        return svaerhedsgrad;
    }

    public void setSvaerhedsgrad(int svaerhedsgrad)
    {
        this.svaerhedsgrad = svaerhedsgrad;
    }

    public LocalDate getStartDato()
    {
        return startDato;
    }

    public void setStartDato(LocalDate startDato)
    {
        this.startDato = startDato;
    }

    public LocalDate getSlutDato()
    {
        return slutDato;
    }

    public void setSlutDato(LocalDate slutDato)
    {
        this.slutDato = slutDato;
    }

    public LocalDate getSidstOpdateret()
    {
        return sidstOpdateret;
    }

    public void setSidstOpdateret(LocalDate sidstOpdateret)
    {
        this.sidstOpdateret = sidstOpdateret;
    }
}
