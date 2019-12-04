package ejerforening.firstyearprojektkea.Service;

import ejerforening.firstyearprojektkea.Model.AdministreSlutbruger.SlutbrugerOversigt;
import ejerforening.firstyearprojektkea.Repository.ISlutbrugerRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlutbrugerService implements ISlutbrugerService {

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
