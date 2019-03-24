package ds;

import java.io.Serializable;
import java.util.Date;


public class Pajamos extends PiniguJudejimas implements Serializable {
    
    public Pajamos(double suma, Kategorija kategorija, String aprasymas, Date data, String kom) {
        super(suma, kategorija,aprasymas,data,kom);
    }
}
