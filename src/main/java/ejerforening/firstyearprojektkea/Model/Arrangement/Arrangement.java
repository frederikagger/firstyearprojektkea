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
 * @Author Paivi
 * @since 8-12-2019
 * Klassen er superklassen til Generalforsamling og Arbejdsdag, dvs. annoteret med @Inheritance.
 * Klassen repraesenterer tabellen Arrangement i databasen
 * og indeholder stamdata (navn og oprettelsesdato) om et arrangement.
 */

@Inheritance
public class Arrangement {

    /**
     * Felter med validerings-annotationer.
     * Annotationen Id angiver, hvilken kolonne er primary key i tabellen. Id genereres i databasen.
     * Datatypen LocalDate er en javaklasse, som repraesenterer dato.
     * Feltet har ikke noget validering-annotation, fordi den genereres i systemet i stedet for at vaere bruger-input.
     * Klassen faar ogsaa ArrangementOplysninegr som felt (composition, dvs. hvis man sletter Arrangement,
     * sletter man ogsaa ArranegementOplysninger)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

