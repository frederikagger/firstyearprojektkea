package ejerforening.firstyearprojektkea.Model.AdministereOpgave;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Klassen der indeholder attributerne for Opgave
 * @author Signe
 */
public class Opgave
{
    /**
     * Attributerne har validerings annotationerm som definerer:
     *
     * @Id = primary key i tabellen
     * @NotNull = maa ikke være tom
     * LocalDate = datatype der udnytter javaklassen Time som er dato
     */
    @Id
    private int opgaveId;

    @NotNull(message = "Indtast venligst navnet på opgaven")
    private String navn;

    private LocalDate oprettelsesDato;

    // Er ikke blevet implementeret
    //private Arbejdsdag arbejdsdag;


    public Opgave()
    {
    }

    public Opgave(int opgaveId, @NotNull(message = "Indtast venligst navnet på opgaven") String navn, LocalDate oprettelsesDato)
    {
        this.opgaveId = opgaveId;
        this.navn = navn;
        this.oprettelsesDato = oprettelsesDato;
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


}
