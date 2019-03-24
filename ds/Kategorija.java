package ds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Kategorija implements Serializable  {
    private ArrayList<PiniguJudejimas> judejimas = new ArrayList();
     private ArrayList<Islaidos> islaid = new ArrayList();
    private String pavadinimas, aprasymas;

    public void setPavadinimas(String pavadinimas) {
        this.pavadinimas = pavadinimas;
    }

    public void setAprasymas(String aprasymas) {
        this.aprasymas = aprasymas;
    }

    public String getPavadinimas() {
        return pavadinimas;
    }

    public String getAprasymas() {
        return aprasymas;
    }

    Kategorija(String pav, String aprasas) {
        this.pavadinimas = pav;
        this.aprasymas = aprasas;
    }

    public ArrayList<PiniguJudejimas> getJudejimas() {
        return judejimas;
    }
    public ArrayList<Islaidos> getIslaidos() {
        return islaid;
        
        
    }
    
    @Override
    public String toString() {
        return "pavadinimas=" + pavadinimas + ", aprasymas=" + aprasymas + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.pavadinimas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kategorija other = (Kategorija) obj;
        if (!Objects.equals(this.pavadinimas, other.pavadinimas)) {
            return false;
        }
        return true;
    }
    
}
