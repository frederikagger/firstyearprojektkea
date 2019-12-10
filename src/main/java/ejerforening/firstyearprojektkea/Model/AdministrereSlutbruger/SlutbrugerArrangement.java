package ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger;

import javax.persistence.Id;

/**
 * @Author Päivi
 * Klassen repræsenterer mellemtabellen i mange-til-mange forbindelse mellem
 * Slutbruger og Arrangement
 *
 */
public class SlutbrugerArrangement {

    @Id
    private int slutarranId;
    private int slutbrugerId;
    private int arrangementId;

    public SlutbrugerArrangement() {
    }

    public int getSlutarranId() {
        return slutarranId;
    }

    public void setSlutarranId(int slutarranId) {
        this.slutarranId = slutarranId;
    }

    public int getSlutbrugerId() {
        return slutbrugerId;
    }

    public void setSlutbrugerId(int slutbrugerId) {
        this.slutbrugerId = slutbrugerId;
    }

    public int getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(int arrangementId) {
        this.arrangementId = arrangementId;
    }
}
