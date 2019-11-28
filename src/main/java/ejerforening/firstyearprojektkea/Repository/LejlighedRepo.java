package ejerforening.firstyearprojektkea.Repository;

import ejerforening.firstyearprojektkea.Model.Lejlighed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**Oprettet af Frederik Agger og Signe Nørløv Eskildsen
 * d. 28/11/2019
 **/

@Repository
public class LejlighedRepo implements IlejlighedRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Lejlighed> hentAlle() {
        return null;
    }

    @Override
    public Lejlighed findMedId(int id) {
        return null;
    }

    @Override
    public void opret(Lejlighed lejlighed) {
    }
}
