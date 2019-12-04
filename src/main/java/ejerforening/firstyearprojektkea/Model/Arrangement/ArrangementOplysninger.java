package ejerforening.firstyearprojektkea.Model.Arrangement;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArrangementOplysninger {

    @Id
    private int arranOplysId;
    private String agenda;

    public ArrangementOplysninger(){}

    public ArrangementOplysninger(int arranOplysId, String agenda){
        this.arranOplysId = arranOplysId;
        this.agenda = agenda;
    }

    public int getArranOplysId() {
        return arranOplysId;
    }

    public void setArranOplysId(int arranOplysId) {
        this.arranOplysId = arranOplysId;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }
}
