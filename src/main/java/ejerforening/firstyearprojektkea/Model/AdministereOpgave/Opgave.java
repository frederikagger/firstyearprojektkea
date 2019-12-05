package ejerforening.firstyearprojektkea.Model.AdministereOpgave;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Klassen der indeholder attributerne for Opgave
 * @author Signe
 */
public class Opgave
{
    /**
     * Attributerne har validerings annotationerm som definerer:
     *
     * @Id = primary key i tabellen
     * @NotNull = maa ikke være tom
     * @DateTimeFormat = hvordan dato skal staa skrevet
     * LocalDate = datatype der udnytter javaklassen Time som er dato
     * LocalDate.now() = dags dato
     */
    @Id
    private int opgaveId;

    @NotNull(message = "Indtast venligst navnet på opgaven")
    private String navn;

    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalDate oprettelsesDato;

    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalDate opdateringsDato = LocalDate.now();

}
