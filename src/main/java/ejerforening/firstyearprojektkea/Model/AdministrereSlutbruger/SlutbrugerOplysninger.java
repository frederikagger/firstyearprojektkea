package ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Klassen der indeholder attributerne for SlutbrugerOplysninger
 * @author Signe
 */

public class SlutbrugerOplysninger
{
    /**
     * Attributerne har validerings annotationerm som definerer:
     *
     * @NotNull = maa ikke være tom
     * @DateTimeFormat = hvordan dato skal staa skrevet
     * !!!!@AssertFalse = Skal navnes hvad det er!!!
     * LocalDate = datatype der udnytter javaklassen Time som er dato
     * LocalDate.now() = dags dato
     */
    @NotNull(message = "Indtast venligst emailen")
    private String email;

    @NotNull (message = "Indtast venligst telefonnummeret")
    private String tlfnummer;

    @AssertFalse //Vil være som udgangspunkt at en slutbruger ikke er medlem af bestyrelsen
    private boolean erBestyrelsesmedlem;

    @DateTimeFormat(pattern="dd/MM/YYYY")
    private LocalDate opdateringsDato = LocalDate.now();


    public SlutbrugerOplysninger()
    {}

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getTlfnummer()
    {
        return tlfnummer;
    }

    public void setTlfnummer(String tlfnummer)
    {
        this.tlfnummer = tlfnummer;
    }

    public Boolean getErBestyrelsesmedlem()
    {
        return erBestyrelsesmedlem;
    }

    public void setErBestyrelsesmedlem(Boolean erBestyrelsesmedlem)
    {
        this.erBestyrelsesmedlem = erBestyrelsesmedlem;
    }

    public LocalDate getOpdateringsDato()
    {
        return opdateringsDato;
    }

    public void setOpdateringsDato(LocalDate opdateringsDato)
    {
        this.opdateringsDato = opdateringsDato;
    }
}
