package ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger;


public class SlutbrugerOversigt
{
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
