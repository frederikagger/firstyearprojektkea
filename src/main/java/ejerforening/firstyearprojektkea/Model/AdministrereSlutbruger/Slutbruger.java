package ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger;

import ejerforening.firstyearprojektkea.Model.Lejlighed;
import org.springframework.format.annotation.DateTimeFormat;

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
     * @DateTimeFormat = hvordan dato skal staa skrevet
     * LocalDate = datatype der udnytter javaklassen Time som er dato
     * LocalDate.now() = dags dato
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

    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalDate oprettelsesDato;

    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalDate opdateringsDato = LocalDate.now();

    //@Column(unique=true) -- Skal måske være her, da jeg mener mellem lejlighed og slutbruger er en-til-en relation
    private int lejlighedsId;


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
     * @param opdateringsDato
     * @param lejlighed
     */
    public Slutbruger(int slutbrugerId, String fornavn, String mellemnavn, String efternavn, LocalDate oprettelsesDato, LocalDate opdateringsDato, Lejlighed lejlighed)
    {
        this.slutbrugerId = slutbrugerId;
        this.fornavn = fornavn;
        this.mellemnavn = mellemnavn;
        this.efternavn = efternavn;
        this.oprettelsesDato = oprettelsesDato;
        this.opdateringsDato = opdateringsDato;
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

    public LocalDate getOpdateringsDato()
    {
        return opdateringsDato;
    }

    public void setOpdateringsDato(LocalDate opdateringsDato)
    {
        this.opdateringsDato = opdateringsDato;
    }
}