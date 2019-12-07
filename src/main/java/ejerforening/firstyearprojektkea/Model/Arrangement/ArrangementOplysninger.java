package ejerforening.firstyearprojektkea.Model.Arrangement;

import org.springframework.stereotype.Component;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author paivi
 * Klassen er en uadskillelig del af Arrangement (composition, jf. forklaring i Arrangement)
 * Den er annoteret som Component for at markere, at den er en komponent i en anden klasse.
 */
@Component
public class ArrangementOplysninger{

    /**
     * Datatypen LocalDate og LocalTime er javaklasser, som repraesenterer dato og tid.
     * Time skrives i databasen saaledes: fx kl 14.00 er 140000. Så bliver det 14.00 med LocalTime.
     * arrangementId er i databasen baade PK og FK, der knytter ArrangementOplysninger
     * til tabellen Arrangement med en-til-en -relation.
     */
    @Id
    private int arranOplysId;
    private int arrangementId;
    private String agenda;
    private LocalDate dato;
    private LocalTime startTidspunkt;
    private LocalTime slutTidspunkt;
    private String sted;
    private LocalDate tilmeldingsfrist;
    private LocalDate sidstOpdateret;

    public ArrangementOplysninger(){}

    public int getArranOplysId() {
        return arranOplysId;
    }

    public void setArranOplysId(int arranOplysId) {
        this.arranOplysId = arranOplysId;
    }

    public int getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(int arrangementId) {
        this.arrangementId = arrangementId;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public LocalDate getDato() {
        return dato;
    }

    public void setDato(LocalDate dato) {
        this.dato = dato;
    }

    public LocalTime getStartTidspunkt() {
        return startTidspunkt;
    }

    public void setStartTidspunkt(LocalTime startTidspunkt) {
        this.startTidspunkt = startTidspunkt;
    }

    public LocalTime getSlutTidspunkt() {
        return slutTidspunkt;
    }

    public void setSlutTidspunkt(LocalTime slutTidspunkt) {
        this.slutTidspunkt = slutTidspunkt;
    }

    public String getSted() {
        return sted;
    }

    public void setSted(String sted) {
        this.sted = sted;
    }

    public LocalDate getTilmeldingsfrist() {
        return tilmeldingsfrist;
    }

    public void setTilmeldingsfrist(LocalDate tilmeldingsfrist) {
        this.tilmeldingsfrist = tilmeldingsfrist;
    }

    public LocalDate getSidstOpdateret() {
        return sidstOpdateret;
    }

    public void setSidstOpdateret(LocalDate sidstOpdateret) {
        this.sidstOpdateret = sidstOpdateret;
    }
}
