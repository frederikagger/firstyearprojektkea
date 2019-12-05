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
     * Felter til klassen. Jf. Forklaringer til annotationerne findes i klassen Arrangement.
     */
    @Id
    private int arranOplysId;
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
    public int getArranOplysId() { return arranOplysId; }
    public void setArranOplysId(int arranOplysId) { this.arranOplysId = arranOplysId; }
    public String getAgenda() { return agenda; }
    public void setAgenda(String agenda) { this.agenda = agenda; }

}
