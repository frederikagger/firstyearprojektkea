package ejerforening.firstyearprojektkea.Model.Arrangement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Klassen er superklassen til Generalforsamling og Arbejdsdag, dvs. annoteret med @Inheritance. Dog har annotation
 * kun symbolsk betydning her for at markere superklassen, idet systemet ikke bruger hibernate eller anden ORM, som
 * flere af annotationerne er knyttet til. Dermed er klasserne heller ikke annoteret som entity.
 * Klassen repraesenterer tabellen Arrangement i databasen og indeholder stamdata om et arrangement.
 * Den er abstract, fordi man ikke skal lave instanser af den, kun af subklasserne.
 * @Author Paivi
 */

@Inheritance
public abstract class Arrangement {

    /**
     * Felter med validerings-annotationer.
     * Annotationen Id angiver, hvilken kolonne er primary key i tabellen.
     * Annotationen DateTimeFormat definerer, hvordan datoen skal formateres.
     * Datatypen LocalDate er en javaklasse, som repraesenterer dato.
     * Klassen faar ogsaa ArrangementOplysninegr som felt (composition, dvs. hvis man sletter Arrangement,
     * sletter man ogsaa ArranegementOplysninger)
     */
    @Id
    private int arrangementId;
    @NotNull (message = "Indtast venligst navnet på generalforsamlingen")
    private String navn;
    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalDate oprettelsesDato;
    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalDate opdateringsDato;
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
    public LocalDate getOprettelsesDato() { return oprettelsesDato; }
    public void setOprettelsesDato(LocalDate oprettelsesDato) { this.oprettelsesDato = oprettelsesDato; }
    public LocalDate getOpdateringsDato() { return opdateringsDato; }
    public void setOpdateringsDato(LocalDate opdateringsDato) { this.opdateringsDato = opdateringsDato; }
    public ArrangementOplysninger getArrangementOplysninger() { return arrangementOplysninger; }

}

