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
    private int id;
    private int etage;
    private boolean lejlighedsside;

    /**
     * Default constructor
     */
    public Lejlighed()
    {}

    /**
     * @param id
     * @param etage
     * @param lejlighedsside
     */

    public Lejlighed(int id, int etage, boolean lejlighedsside) {
        this.id = id;
        this.etage = etage;
        this.lejlighedsside = lejlighedsside;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
