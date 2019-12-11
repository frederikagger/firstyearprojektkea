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

    private int opgaveId;

    private String navn;

    private LocalDate oprettelsesDato;

    private int arrangementId;

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

    private int lejlighedsId;

    private int opgaveLejlighedId;

    public OpgaveOplysninger()
    {
    }

    public OpgaveOplysninger(int opgaveOplysningerId, int opgaveId, String navn, LocalDate oprettelsesDato, int arrangementId, @NotNull(message = "Indtast venligst en beskrivelse af opgaven") String beskrivelse, @NotNull(message = "Indtast venligst varigheden for opgaven") int varighed, @NotNull(message = "Indtast venligst sværheden for opgaven") int svaerhedsgrad, @NotNull(message = "Indtast venligst dato: dd/mm/åååå") LocalDate startDato, @NotNull(message = "Indtast venligst dato: dd/mm/åååå") LocalDate slutDato, LocalDate sidstOpdateret, int lejlighedsId, int opgaveLejlighedId)
    {
        this.opgaveOplysningerId = opgaveOplysningerId;
        this.opgaveId = opgaveId;
        this.navn = navn;
        this.oprettelsesDato = oprettelsesDato;
        this.arrangementId = arrangementId;
        this.beskrivelse = beskrivelse;
        this.varighed = varighed;
        this.svaerhedsgrad = svaerhedsgrad;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.sidstOpdateret = sidstOpdateret;
        this.lejlighedsId = lejlighedsId;
        this.opgaveLejlighedId = opgaveLejlighedId;
    }

    public int getOpgaveId()
    {
        return opgaveId;
    }

    public void setOpgaveId(int opgaveId)
    {
        this.opgaveId = opgaveId;
    }

    public String getNavn()
    {
        return navn;
    }

    public void setNavn(String navn)
    {
        this.navn = navn;
    }

    public LocalDate getOprettelsesDato()
    {
        return oprettelsesDato;
    }

    public void setOprettelsesDato(LocalDate oprettelsesDato)
    {
        this.oprettelsesDato = oprettelsesDato;
    }

    public int getArrangementId()
    {
        return arrangementId;
    }

    public void setArrangementId(int arrangementId)
    {
        this.arrangementId = arrangementId;
    }

    public int getLejlighedsId()
    {
        return lejlighedsId;
    }

    public void setLejlighedsId(int lejlighedsId)
    {
        this.lejlighedsId = lejlighedsId;
    }

    public int getOpgaveLejlighedId()
    {
        return opgaveLejlighedId;
    }

    public void setOpgaveLejlighedId(int opgaveLejlighedId)
    {
        this.opgaveLejlighedId = opgaveLejlighedId;
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
