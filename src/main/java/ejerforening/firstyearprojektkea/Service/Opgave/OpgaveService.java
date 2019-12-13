package ejerforening.firstyearprojektkea.Service.Opgave;

import ejerforening.firstyearprojektkea.Model.AdministereOpgave.Opgave;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOplysninger;
import ejerforening.firstyearprojektkea.Model.AdministereOpgave.OpgaveOversigt;
import ejerforening.firstyearprojektkea.Repository.Opgave.IOpgaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Klassen implementerer interfacet IOpgaveService, som goer det muligt for OpgaveController at kalde på IOpgaveService og faa udfoert den metode der oenskes.
 *
 * OpgaveService soerger for at der ikke er direkte forbindelse mellem view og databasen.
 * Der kunne vaere yderligere validering i Service, men er ikke valgt i dette tilfaelde eller andre funktionaliter som ikke skulle vaere i Controller eller
 * Repository.
 * @author Signe
 * @since 4-12-2019
 */
@Service
public class OpgaveService implements IOpgaveService
{
    /**
     *  Autowire interfacet fra IOpgaveRepository, som er blevet implementeret af OpgaveRepository.
     *  Kan derved kalde på metoder derfra.
     */
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
    public boolean erOpgaveOpdateret(OpgaveOplysninger opgaveOplysninger)
    {
        boolean erOpgaveOpdateret = opgaveRepository.opdaterOpgave(opgaveOplysninger);
        return true;
    }

    @Override
    public void slet(int opgaveId)
    {
        opgaveRepository.slet(opgaveId);
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
}
