package ejerforening.firstyearprojektkea.Model.Slutbruger;

import ejerforening.firstyearprojektkea.Model.Lejlighed.Lejlighed;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Klassen der indeholder attributerne for Slutbruger
 * @author Signe
 */

public class Slutbruger
{
    /**
     * Attributerne har validerings annotationerm som definerer:
     *
     * @Id = primary key i tabellen
     * @NotNull = maa ikke være tom
     * LocalDate = datatype der udnytter javaklassen Time som er dato
     * !!!!!@Column(unique=true) = skal naevnes hvad det, hvis den bliver brugt!!!!!!
     */
    @Id
    private int slutbrugerId;

    @NotNull(message = "Indtast venligst fornavnet")
    private String fornavn;

    //Skal ikke være NotNull ikke alle har et mellemnavn
    private String mellemnavn;

    @NotNull(message = "Indtast venligst efternavnet")
    private String efternavn;

    private LocalDate oprettelsesDato;

    private LocalDate sidstOpdateret;

    //@Column(unique=true) -- Skal måske være her, da jeg mener mellem lejlighed og slutbruger er en-til-en relation
    private Lejlighed lejlighed;;


    /**
     * Default constructor - ved ikke om der er brug for den endnu
     */
    public Slutbruger()
    {}

    /**
     * Overloadet constructer med paramaterne:
     * @param slutbrugerId
     * @param fornavn
     * @param mellemnavn
     * @param efternavn
     * @param oprettelsesDato
     * @param sidstOpdateret
     * @param lejlighed
     */
    public Slutbruger(int slutbrugerId, String fornavn, String mellemnavn, String efternavn, LocalDate oprettelsesDato, LocalDate sidstOpdateret, Lejlighed lejlighed)
    {
        this.slutbrugerId = slutbrugerId;
        this.fornavn = fornavn;
        this.mellemnavn = mellemnavn;
        this.efternavn = efternavn;
        this.oprettelsesDato = oprettelsesDato;
        this.sidstOpdateret = sidstOpdateret;
    }


    public int getSlutbrugerId()
    {
        return slutbrugerId;
    }

    public void setslutbrugerId(int slutbrugerId)
    {
        this.slutbrugerId = slutbrugerId;
    }

    public String getFornavn()
    {
        return fornavn;
    }

    public void setFornavn(String fornavn)
    {
        this.fornavn = fornavn;
    }

    public String getMellemnavn()
    {
        return mellemnavn;
    }

    public void setMellemnavn(String mellemnavn)
    {
        this.mellemnavn = mellemnavn;
    }

    public String getEfternavn()
    {
        return efternavn;
    }

    public void setEfternavn(String efternavn)
    {
        this.efternavn = efternavn;
    }

    public LocalDate getOprettelsesDato()
    {
        return oprettelsesDato;
    }

    public void setOprettelsesDato(LocalDate oprettelsesDato)
    {
        this.oprettelsesDato = oprettelsesDato;
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