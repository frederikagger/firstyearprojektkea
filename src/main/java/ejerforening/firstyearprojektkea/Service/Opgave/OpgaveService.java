package ejerforening.firstyearprojektkea.Service.Opgave;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;
import ejerforening.firstyearprojektkea.Repository.Opgave.IOpgaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
