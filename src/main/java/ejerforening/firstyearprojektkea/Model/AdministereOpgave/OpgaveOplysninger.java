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
    private int svaerhedgrad;

    private LocalTime startDato;

    private LocalTime slutDato;

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

    public int getSvaerhedgrad()
    {
        return svaerhedgrad;
    }

    public void setSvaerhedgrad(int svaerhedgrad)
    {
        this.svaerhedgrad = svaerhedgrad;
    }

    public LocalTime getStartDato()
    {
        return startDato;
    }

    public void setStartDato(LocalTime startDato)
    {
        this.startDato = startDato;
    }

    public LocalTime getSlutDato()
    {
        return slutDato;
    }

    public void setSlutDato(LocalTime slutDato)
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
