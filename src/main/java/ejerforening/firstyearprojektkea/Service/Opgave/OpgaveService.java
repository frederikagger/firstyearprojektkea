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
    public List<Opgave> findOpgave(int opgaveId)
    {
        List<Opgave> resultFindOpgave = opgaveRepository.findOpgave(opgaveId);
        return resultFindOpgave;
    }

    @Override
    public List<OpgaveOplysninger> findValgteOpgave(int opgaveId)
    {
        List<OpgaveOplysninger> resultfindValgteOpgave = opgaveRepository.findValgteOpgave(opgaveId);
        return resultfindValgteOpgave;
    }

    @Override
    public boolean erOpgaveOprettet(Opgave opgave)
    {
        boolean erOpgaveOprettet = opgaveRepository.opretOpgave(opgave);

        return erOpgaveOprettet;
    }

    @Override
    public boolean erOpgaveOplysningerOprettet(OpgaveOplysninger opgaveOplysninger)
    {
        boolean erOpgaveOplysningerOprettet = opgaveRepository.opretOplysninger(opgaveOplysninger);

        return erOpgaveOplysningerOprettet;
    }


    @Override
    //Metoden returnere om der er fejl i nogle af valideringerne ud fra at om _result stringen er tom eller ej.
    public boolean erOpgaveOpdateret(Opgave opgave, OpgaveOplysninger opgaveOplysninger)
    {
        boolean erOpgaveOpdateret = opgaveRepository.opdaterOpgave(opgave, opgaveOplysninger);
        return erOpgaveOpdateret;
    }


    @Override
    public void slet(int opgaveId)
    {
        opgaveRepository.slet(opgaveId);
    }

}
