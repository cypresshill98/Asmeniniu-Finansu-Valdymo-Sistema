package ds;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date; 

public class PiniguJudejimas implements Serializable, Comparable<PiniguJudejimas>{
     double suma;
     Kategorija kategorija;
     String aprasymas, komentaras;
     Date data;

    public PiniguJudejimas(double suma, Kategorija kategorija, String aprasymas, Date data, String komentaras) {
        this.suma = suma;
        this.kategorija = kategorija;
        this.aprasymas = aprasymas;
        this.data = data;
        this.komentaras = komentaras;
    }
    
    public double getSuma()
    {
        return suma;
    }
public String getData()
{
    return DataFormat(data);
}
public String getAprasymas()
{
    return aprasymas;
}
public String getKategorija()
{
    return kategorija.getPavadinimas();
}
 public String getKomentaras()
 {
     return komentaras;
 }

 public void setAprasymas(String aprasymas) {
        this.aprasymas = aprasymas;
    }
 
 public void setSuma(Double suma) {
        this.suma = suma;
    }
 public void setData(Date data) {
        this.data = data;
    }
 public void setKomentaras(String komentaras) {
        this.komentaras = komentaras;
    }
    public String DataFormat(Date data){
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
String strDate = dateFormat.format(data); 
       return strDate;
    }
    
    @Override
    public String toString() {
        return "suma=" + suma + ", kategorija=" + kategorija.getPavadinimas() + ", aprasymas=" + aprasymas +", komentaras=" + komentaras + ", data=" + DataFormat(data);
    }
    
    

    @Override
    public int compareTo(PiniguJudejimas o) {
       int lg = this.data.compareTo(o.data);
        if(lg!=0){
            return lg;
        }
        int lt = Double.compare(this.suma, o.suma);
        if (lt != 0) {
            return -lt;
        }    
    return 0;
    }
    
    
}
