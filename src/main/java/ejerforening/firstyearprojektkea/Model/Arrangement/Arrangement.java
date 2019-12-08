package ejerforening.firstyearprojektkea.Model.Arrangement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Inherited;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Klassen er superklassen til Generalforsamling og Arbejdsdag, dvs. annoteret med @Inheritance.
 * Klassen repraesenterer tabellen Arrangement i databasen og indeholder stamdata om et arrangement.
 * @Author Paivi
 */

@Inheritance
public class Arrangement {

    /**
     * Felter med validerings-annotationer.
     * Annotationen Id angiver, hvilken kolonne er primary key i tabellen.
     * Datatypen LocalDate er en javaklasse, som repraesenterer dato. Feltet har ikke noget
     * validering-annotation, fordi den genereres i systemet i stedet for bruger-input.
     * Klassen faar ogsaa ArrangementOplysninegr som felt (composition, dvs. hvis man sletter Arrangement,
     * sletter man ogsaa ArranegementOplysninger)
     */
    @Id
    private int arrangementId;
    @NotBlank (message = "Indtast venligst navnet på generalforsamlingen")
    private String navn;
    private LocalDate oprettelsesDato;
    private ArrangementOplysninger arrangementOplysninger;

    /**
     * Der laves en instans af ArrangementOplysninger i konstruktoeren.
     */
    public Arrangement(){
        arrangementOplysninger = new ArrangementOplysninger();
    }

    public int getArrangementId() { return arrangementId; }
    public void setArrangementId(int arrangementId) { this.arrangementId = arrangementId; }
    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }
    public LocalDate getOprettelsesDato() {
        return oprettelsesDato;
    }
    public void setOprettelsesDato(LocalDate oprettelsesDato) {
        this.oprettelsesDato = oprettelsesDato;
    }
    public ArrangementOplysninger getArrangementOplysninger() { return arrangementOplysninger; }

}

