package ejerforening.firstyearprojektkea.Service.Slutbruger;

import ejerforening.firstyearprojektkea.Model.AdministrereSlutbruger.SlutbrugerOversigt;
import ejerforening.firstyearprojektkea.Repository.Slutbruger.ISlutbrugerRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Klassen implementerer metoderne fra interfacet ISlutbrugerService og autowirer interfacet fra ISlutbrugerRepository.
 * SlutbrugerService kalder på metoder fra repository.
 * @author Signe
 */
@Service
public class SlutbrugerService implements ISlutbrugerService
{

    @Autowired
    private ISlutbrugerRepository slutbrugerRepository;

    @Override
    public List<SlutbrugerOversigt> hentAlle()
    {
        List<SlutbrugerOversigt> result = slutbrugerRepository.hentAlle();
        return result;
    }

    @Override
    public void slet(int slutbrugerId)
    {
        slutbrugerRepository.slet(slutbrugerId);
    }

}
