package ejerforening.firstyearprojektkea.Model.Lejlighed;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Oprettet af Frederik Agger
 * og Signe Nørløv Eskildsen
 * d. 28/11/2019
 */
@Entity
public class Lejlighed {

    /**
     * Hvilke attributer som lejlighed har
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int lejlighedsid;
    @NotNull(message = "Indtast venligst etagen på lejligheden")
    @Min(0)
    @Max(5)
    private int etage;
    @NotNull
    private boolean lejlighedsside;

    /**
     * Default constructor
     */
    public Lejlighed()
    {}

    /**
     * Constructor for at opgave kan få fat i lejlighedsid
     * @param lejlighedsid
     */
    public Lejlighed(int lejlighedsid)
    {
        this.lejlighedsid = lejlighedsid;
    }

    /**
     *
     * @param etage
     * @param lejlighedsside
     */


    public Lejlighed(@NotNull(message = "Indtast venligst etagen på lejligheden") @Min(0) @Max(5) int etage, @NotNull boolean lejlighedsside) {
        this.etage = etage;
        this.lejlighedsside = lejlighedsside;
    }




    public int getLejlighedsid() {
        return lejlighedsid;
    }

    public void setLejlighedsid(int lejlighedsid) {
        this.lejlighedsid = lejlighedsid;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public boolean isLejlighedsside() {
        return lejlighedsside;
    }

    public void setLejlighedsside(boolean lejlighedsside) {
        this.lejlighedsside = lejlighedsside;
    }
}
