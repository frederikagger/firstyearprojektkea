package ejerforening.firstyearprojektkea.Model.AdministereOpgave;

import org.springframework.format.annotation.DateTimeFormat;

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
     * @DateTimeFormat = hvordan dato skal staa skrevet
     * LocalDate = datatype der udnytter javaklassen Time som er dato
     * LocalDate.now() = dags dato
     */
    @NotNull(message = "Indtast venligst en beskrivelse af opgaven")
    private String beskrivelse;

    @NotNull(message = "Indtast venligst varigheden for opgaven")
    private int varighed;

    @NotNull (message = "Indtast venligst sværheden for opgaven")
    private int svaerhedgrad;

    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalTime startDato;

    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalTime slutDato;

    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalDate opdateringsDato = LocalDate.now();

    public OpgaveOplysninger()
    {
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

    public LocalDate getOpdateringsDato()
    {
        return opdateringsDato;
    }

    public void setOpdateringsDato(LocalDate opdateringsDato)
    {
        this.opdateringsDato = opdateringsDato;
    }
}
