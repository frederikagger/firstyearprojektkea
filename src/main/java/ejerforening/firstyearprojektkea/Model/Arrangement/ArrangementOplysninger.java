package ejerforening.firstyearprojektkea.Model.Arrangement;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import javax.persistence.Id;
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
     * Forklaringen til DateTimeFormat og LocalDate findes i klassen Arrangement.
     * arrangementId er i databasen baade PK og FK, der knytter ArrangementOplysninger
     * til tabellen Arrangement med en-til-en -relation.
     */
    @Id
    private int arrangementId;
    private String agenda;
    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalDate dato;
    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalTime starttidspunkt;
    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalTime sluttidspunkt;
    private String sted;
    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalDate tilmeldingsfrist;
    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalDate opdateringsDato = LocalDate.now();

    public ArrangementOplysninger(){}

    public LocalDate getDato() { return dato; }
    public void setDato(LocalDate dato) { this.dato = dato; }
    public LocalTime getStarttidspunkt() { return starttidspunkt; }
    public void setStarttidspunkt(LocalTime starttidspunkt) { this.starttidspunkt = starttidspunkt; }
    public LocalTime getSluttidspunkt() { return sluttidspunkt; }
    public void setSluttidspunkt(LocalTime sluttidspunkt) { this.sluttidspunkt = sluttidspunkt; }
    public String getSted() { return sted; }
    public void setSted(String sted) { this.sted = sted; }
    public LocalDate getTilmeldingsfrist() { return tilmeldingsfrist; }
    public void setTilmeldingsfrist(LocalDate tilmeldingsfrist) { this.tilmeldingsfrist = tilmeldingsfrist; }
    public LocalDate getOpdateringsDato() { return opdateringsDato; }
    public void setOpdateringsDato(LocalDate opdateringsDato) { this.opdateringsDato = opdateringsDato; }
    public String getAgenda() { return agenda; }
    public void setAgenda(String agenda) { this.agenda = agenda; }
    public int getArrangementId() { return arrangementId; }
    public void setArrangementId(int arrangementId) { this.arrangementId = arrangementId; }
}
