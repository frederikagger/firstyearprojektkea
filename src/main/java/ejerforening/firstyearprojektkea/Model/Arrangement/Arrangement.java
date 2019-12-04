package ejerforening.firstyearprojektkea.Model.Arrangement;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Klassen er superklassen til Generalforsamling og Arbejdsdag, dvs. annoteret med @Inheritance
 * Den repræsenterer tabellen Arrangement generalforsamling i databasen og indeholder stamdata om et arrangement.
 * Den er abstract, fordi man ikke skal lave instanser af den, kun af subklasserne.
 * @Author Päivi
 */

@Inheritance
@Entity
public abstract class Arrangement {

    /**
     * Felter med validerings-annotationer.
     * Annotationen DateTimeFormat definerer, at felt skal formateres som date eller time. Understoetter ISO datetime-pattern.
     */
    @Id
    private int arrangementId;
    @NotNull (message = "Indtast venligst navnet på generalforsamlingen")
    private String navn;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate oprettelsesDato;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate opdateringsDato;

    public Arrangement() { }

    public int getArrangementId() {
        return arrangementId;
    }

    public void setArrangementId(int arrangementId) {
        this.arrangementId = arrangementId;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public LocalDate getOprettelsesDato() {
        return oprettelsesDato;
    }

    public void setOprettelsesDato(LocalDate oprettelsesDato) {
        this.oprettelsesDato = oprettelsesDato;
    }

    public LocalDate getOpdateringsDato() {
        return opdateringsDato;
    }

    public void setOpdateringsDato(LocalDate opdateringsDato) {
        this.opdateringsDato = opdateringsDato;
    }
}

