package ejerforening.firstyearprojektkea.Model.Slutbruger;

import javax.persistence.Id;

/**
 * Klassen er den model der vises ud til htmlsiden, saa man kan se det som en model view klasse.
 * Det er de felter bestyrelsen har brug for at se, for at kunne vaelge hvem de vil rette, se detaljer eller slette.
 * Tabellerne fra databasen der vises er opgave og lejlighed.
 * @Author Signe
 */
public class SlutbrugerOversigt
{

    /**
     * Attributerne har validerings annotationerm som definerer:
     *
     * @Id = primary key i tabellen
    */
    @Id
    private int slutbrugerId;
    private String navn;
    private int lejlighedsId;

    public SlutbrugerOversigt()
    {

    }

    public SlutbrugerOversigt(int slutbrugerId, String navn, int lejlighedsId)
    {
        this.slutbrugerId = slutbrugerId;
        this.navn = navn;
        this.lejlighedsId = lejlighedsId;
    }

    public int getSlutbrugerId()
    {
        return slutbrugerId;
    }

    public String getNavn()
    {
        return navn;
    }

    public int getLejlighedsId()
    {
        return lejlighedsId;
    }

}
