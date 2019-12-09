package ejerforening.firstyearprojektkea.Model.Vasketider;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity()
public class Vasketid {

    @Id
    private int vaskeId;

    private UGEDAG ugedag;

    private boolean erFormiddag;

    private boolean erBooket;

    public Vasketid() {
    }

    public Vasketid(int vaskeId, UGEDAG ugedag, boolean erFormiddag, boolean erBooket) {
        this.vaskeId = vaskeId;
        this.ugedag = ugedag;
        this.erFormiddag = erFormiddag;
        this.erBooket = erBooket;
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
}
