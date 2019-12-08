package ejerforening.firstyearprojektkea.Model.Arrangement;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Paivi
 * Klassen er en subklasse til Arrangement (extends). Den arver alt fra
 * superklassenm, men har nogle felt, som kun tilhoerer den selv.
 */

public class Generalforsamling extends Arrangement{

    /**
     * Felter til generalforsamling.
     * Id markerer, hvilket felt er PK-kolonne i databasen. Id er autogenereret i databasen.
     * Naar ordinaer er false, betyder det, at det er ektraordinaer.
     * Validerings annotationer hjælper til at data er korrekt og konsistent i databasen.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int genforsamId;
    @NotBlank(message = "Indtast venligst navnet på ordstyrer")
    private String ordstyrer;
    private boolean ordinaer;

    public int getGenforsamId() { return genforsamId; }
    public void setGenforsamId(int genforsamId) { this.genforsamId = genforsamId; }
    public String getOrdstyrer() { return ordstyrer; }
    public void setOrdstyrer(String ordstyrer) { this.ordstyrer = ordstyrer; }
    public boolean isOrdinaer() { return ordinaer; }
    public void setOrdinaer(boolean ordinaer) { this.ordinaer = ordinaer; }

}
