package ejerforening.firstyearprojektkea.Model.Opgave;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Klassen Opgave Oplysninger attributter kommer af at skulle kunne indeholde de kolonner fra tabellerne opgave, opgaveoplysninger, lejlighed
 * og id'et fra mange til mange forbindelsen mellem opgave og lejlighed, som databasen har.
 *
 * Det hjaelper ogsaa naar opgave skal opdateres, uden at skulle koble 3 objekter sammen, for at Stored Procedure SP_opdaterOpgave
 * faar de korrekte vaerdier med til databasen. Og brugeren ikke skal igennem 3 undersider ved opdatering af opgaven.
 *
 * @author Signe
 * @since 4-12-2019
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

    @NotBlank(message = "Indtast venligst navnet på opgaven")
    private String navn;

    private LocalDate oprettelsesDato;

    private int arbejdsdagId;

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

    public OpgaveOplysninger(int opgaveOplysningerId, int opgaveId, String navn, LocalDate oprettelsesDato, int arbejdsdagId, @NotNull(message = "Indtast venligst en beskrivelse af opgaven") String beskrivelse, @NotNull(message = "Indtast venligst varigheden for opgaven") int varighed, @NotNull(message = "Indtast venligst sværheden for opgaven") int svaerhedsgrad, @NotNull(message = "Indtast venligst dato: dd/mm/åååå") LocalDate startDato, @NotNull(message = "Indtast venligst dato: dd/mm/åååå") LocalDate slutDato, LocalDate sidstOpdateret, int lejlighedsId, int opgaveLejlighedId)
    {
        this.opgaveOplysningerId = opgaveOplysningerId;
        this.opgaveId = opgaveId;
        this.navn = navn;
        this.oprettelsesDato = oprettelsesDato;
        this.arbejdsdagId = arbejdsdagId;
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

    public int getArbejdsdagId()
    {
        return arbejdsdagId;
    }

    public void setArbejdsdagId(int arbejdsdagId)
    {
        this.arbejdsdagId = arbejdsdagId;
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
