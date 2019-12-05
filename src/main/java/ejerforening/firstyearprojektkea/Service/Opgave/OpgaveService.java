package ejerforening.firstyearprojektkea.Service.Opgave;

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
        List<OpgaveOversigt> result = opgaveRepository.hentAlle();
        return result;
    }

    @Override
    public void slet(int opgaveId)
    {
        opgaveRepository.slet(opgaveId);
    }

}
