package ejerforening.firstyearprojektkea.Service.Arrangement;

import ejerforening.firstyearprojektkea.Repository.Arrangement.IArrangementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenForSamService implements IArrangementService {

    @Autowired
    IArrangementRepo iArrangementRepo;

}
