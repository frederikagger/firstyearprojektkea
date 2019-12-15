package ejerforening.firstyearprojektkea.Model.Vasketider;

import javax.persistence.*;

/**
 * @author frede
 */

@Entity
public class Vasketid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vaskeId;

    private UGEDAG ugedag;

    private boolean erFormiddag;

    private boolean erBooket;

    private int lejlighedsId;

    private int vaskeugeId;

    public Vasketid() {
    }

    public Vasketid(UGEDAG ugedag, boolean erFormiddag, boolean erBooket, int lejlighedsId, int vaskeugeId) {
        this.ugedag = ugedag;
        this.erFormiddag = erFormiddag;
        this.erBooket = erBooket;
        this.lejlighedsId = lejlighedsId;
        this.vaskeugeId = vaskeugeId;
    }

    public int getVaskeId() {
        return vaskeId;
    }

    public void setVaskeId(int vaskeId) {
        this.vaskeId = vaskeId;
    }

    public UGEDAG getUgedag() {
        return ugedag;
    }

    public void setUgedag(UGEDAG ugedag) {
        this.ugedag = ugedag;
    }

    public boolean isErFormiddag() {
        return erFormiddag;
    }

    public void setErFormiddag(boolean erFormiddag) {
        this.erFormiddag = erFormiddag;
    }

    public boolean isErBooket() {
        return erBooket;
    }

    public void setErBooket(boolean erBooket) {
        this.erBooket = erBooket;
    }

    public int getLejlighedsId() {
        return lejlighedsId;
    }

    public void setLejlighedsId(int lejlighedsId) {
        this.lejlighedsId = lejlighedsId;
    }

    public int getVaskeugeId() {
        return vaskeugeId;
    }

    public void setVaskeugeId(int vaskeugeId) {
        this.vaskeugeId = vaskeugeId;
    }
}
