package ejerforening.firstyearprojektkea.Model.Arrangement;

import javax.persistence.Id;

/**
 * @author Paivi
 * Klassen er en subklasse til Arrangement (extends). Den arver alt fra
 * superklassenm, men har et felt, som kun tilhoerer den selv.
 */

public class Generalforsamling extends Arrangement{

    @Id
    private int genforsamId;
    private int referatId;
    private String emne;

    public int getGenforsamId() {
        return genforsamId;
    }

    public void setGenforsamId(int genforsamId) {
        this.genforsamId = genforsamId;
    }

    public int getReferatId() {
        return referatId;
    }

    public void setReferatId(int referatId) {
        this.referatId = referatId;
    }

    public String getEmne() { return emne; }
    public void setEmne(String emne) { this.emne = emne; }


}
