package ds;

import java.util.Date;

public class Islaidos extends PiniguJudejimas {
    private String cekioNr;
    
    public Islaidos(double suma, Date data, String pav, Kategorija kat, String kom, String cekis){
        super(suma,kat,pav,data,kom );
        cekioNr = cekis;
    }

public double getIslaiduSuma()
{
    return suma;
}
public String getIslaidosData()
{
    return DataFormat(data);
}
public String getIslaidosAprasymas()
{
    return aprasymas;
}
public String getIslaidosKategorija()
{
    return kategorija.getPavadinimas();
}
 public String getIslaidosKomentaras()
 {
     return komentaras;
 }
 public String getCekioNr()
 {
     return cekioNr;
 }
         @Override
    public String toString() {
        return "Islaida=" + suma + ", kategorija=" + kategorija.getPavadinimas() + ", islaidos aprasymas=" + aprasymas+ ", komentaras="+ komentaras + ", data=" + DataFormat(data) + ", Cekio Nr=" + cekioNr;
    }
        
}


