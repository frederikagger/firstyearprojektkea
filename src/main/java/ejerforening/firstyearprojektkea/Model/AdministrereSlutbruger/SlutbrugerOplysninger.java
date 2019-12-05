package ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger;

import java.time.LocalDate;

public class SlutbrugerOplysninger
{
    private String email;
    private String tlfnummer;
    private Boolean erBestyrelsesmedlem;
    private LocalDate opdateringsDato;

    public SlutbrugerOplysninger()
    {}

    public SlutbrugerOplysninger(String email, String tlfnummer, Boolean erBestyrelsesmedlem, LocalDate opdateringsDato)
    {
        this.email = email;
        this.tlfnummer = tlfnummer;
        this.erBestyrelsesmedlem = erBestyrelsesmedlem;
        this.opdateringsDato = opdateringsDato;
    }

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
