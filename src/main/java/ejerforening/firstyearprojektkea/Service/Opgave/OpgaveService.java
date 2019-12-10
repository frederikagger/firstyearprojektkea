package ejerforening.firstyearprojektkea.Service.Opgave;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.Opgave;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOplysninger;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;
import ejerforening.firstyearprojektkea.Repository.Opgave.IOpgaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klassen implementerer metoderne fra interfacet IOpgaveService og autowirer interfacet fra IOpgaveRepository.
 * OpgaveService kalder på metoder fra repository.
 * @author Signe
 */
@Service
public class OpgaveService implements IOpgaveService
{
    @Autowired
    private IOpgaveRepository opgaveRepository;


    @Override
    public List<OpgaveOversigt> hentAlle()
    {
        List<OpgaveOversigt> resultHentAlle = opgaveRepository.hentAlle();
        return resultHentAlle;
    }


    @Override
    public OpgaveOplysninger findValgteOpgaveOplysninger(int opgaveId)
    {
        OpgaveOplysninger resultfindValgteOpgaveOplysninger = opgaveRepository.findValgteOpgaveOplysninger(opgaveId);
        return resultfindValgteOpgaveOplysninger;
    }

    @Override
    public boolean OpretOpgave (Opgave opgave)
    {
        boolean opretOpgave = opgaveRepository.opretOpgave(opgave);
        return true;
    }

    @Override
    public boolean OpgaveOplysningerOprettet(OpgaveOplysninger opgaveOplysninger)
    {
        boolean OpgaveOplysningerOprettet = opgaveRepository.opretOplysninger(opgaveOplysninger);

        return true;
    }


    @Override
    //Metoden returnere om der er fejl i nogle af valideringerne ud fra at om _result stringen er tom eller ej.
    public boolean erOpgaveOpdateret(OpgaveOplysninger opgaveOplysninger)
    {
        boolean erOpgaveOpdateret = opgaveRepository.opdaterOpgave(opgaveOplysninger);
        return erOpgaveOpdateret;
    }


    @Override
    public void slet(int opgaveId)
    {
        opgaveRepository.slet(opgaveId);
    }

}
