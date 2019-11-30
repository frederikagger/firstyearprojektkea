package ejerforening.firstyearprojektkea.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    private int id;
    @NotNull
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
