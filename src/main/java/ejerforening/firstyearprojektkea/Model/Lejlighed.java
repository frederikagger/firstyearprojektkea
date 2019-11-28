package ejerforening.firstyearprojektkea.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

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
    private int ID;
    private int etage;
    private boolean lejlighedsside;

    /**
     * Default constructor
     */
    public Lejlighed()
    {}

    /**
     * Overloadet constructor med parameter
     * @param ID
     * @param etage
     * @param lejlighedsside
     */
    public Lejlighed(int ID, int etage, boolean lejlighedsside)
    {
        this.ID = ID;
        this.etage = etage;
        this.lejlighedsside = lejlighedsside;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
