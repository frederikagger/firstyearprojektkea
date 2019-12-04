package ejerforening.firstyearprojektkea.Model;

import javax.persistence.Id;
import java.time.LocalDate;

public class Slutbruger
{

    @Id
    private int slutbrugerId;
    private String fornavn;
    private String mellemnavn;
    private String efternavn;
    private LocalDate oprettelsesDato;
    private LocalDate opdateringsDato;

    /**
     * Composition
     */
    private SlutbrugerOplysninger slutbrugerOplysninger = new SlutbrugerOplysninger();

    /**
     * Aggregation
     */
    private Lejlighed lejlighed;

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
        this.lejlighed = lejlighed;
        //slutbrugerOplysninger.SlutbrugerOplysninger(); //Skal composition ind her?
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