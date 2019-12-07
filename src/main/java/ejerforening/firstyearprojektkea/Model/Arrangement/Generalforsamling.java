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
    private String ordstyrer;

    public int getGenforsamId() { return genforsamId; }
    public void setGenforsamId(int genforsamId) { this.genforsamId = genforsamId; }
    public String getOrdstyrer() { return ordstyrer; }
    public void setOrdstyrer(String ordstyrer) { this.ordstyrer = ordstyrer; }



}
