package ejerforening.firstyearprojektkea.Model.AdministereOpgave;

import ejerforening.firstyearprojektkea.Model.Arrangement.Arbejdsdag;
import ejerforening.firstyearprojektkea.Model.Lejlighed.Lejlighed;



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

    private Arbejdsdag arbejdsdag;

    private OpgaveOplysninger opgaveOplysninger;



    /**
     * Der laves en instans af OpgaveOplysninger i constructoren.
     */
    public Opgave(){
        opgaveOplysninger = new OpgaveOplysninger();
    }

    public Opgave(int opgaveId, @NotNull(message = "Indtast venligst navnet på opgaven") String navn, LocalDate oprettelsesDato, Arbejdsdag arbejdsdag)
    {
        this.opgaveId = opgaveId;
        this.navn = navn;
        this.oprettelsesDato = oprettelsesDato;
        this.arbejdsdag = arbejdsdag;
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

    public OpgaveOplysninger getOpgaveOplysninger()
    {
        return opgaveOplysninger;
    }

}
