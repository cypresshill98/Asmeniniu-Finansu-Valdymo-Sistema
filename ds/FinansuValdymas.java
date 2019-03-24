package ds;

import static Lab2.Lab2.toDate;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class FinansuValdymas implements Serializable {

    private ArrayList<Kategorija> kategorijos = new ArrayList();
    private ArrayList<PiniguJudejimas> judejimas = new ArrayList();
    private ArrayList<Islaidos> islaid = new ArrayList();

    public boolean pridetiKategorija(String pav, String aprasas) {

        Kategorija nauja = new Kategorija(pav, aprasas);
        if (!kategorijos.contains(nauja)) {
            kategorijos.add(nauja);
            return true;
        } else {
            System.out.println("tokia kategorija jau egzistuoja");
            return false;
        }

    }

    public double Balansas() {
        double zzz = 0;
        double zzz1 = 0;
        for (PiniguJudejimas j : judejimas) {
            zzz = zzz + j.getSuma();
        }
        for (Islaidos i : islaid) {
            zzz1 = zzz1 + i.getIslaiduSuma();
        }
        return (zzz - zzz1);
    }

    public double gautiBalansa() {
        return 0.0;
    }

    public double gautiBalansa(String kategorija) {
        return 0.0;
    }

    public double gautiBalansa(Date nuo, Date iki) {
        return 0.0;
    }

    public void pridetiIslaida(double suma, Date data, String pav, String kat, String kom, String cekis) {
        Kategorija k = rastiKategorijaPagalPavadinima(kat);
        Islaidos is = new Islaidos(suma, data, pav, k, kom, cekis);
        k.getJudejimas().add(is);
        this.islaid.add(is);
    }

    /*public void pridetiPajama(double suma, Date data, String pav, Kategorija kat, String kom)
    {
        Pajamos p = new Pajamos(suma,kat,pav,data);
        kat.getJudejimas().add(p);
        this.judejimas.add(p);
    }*/
    public void pridetiPajama(double suma, Date data, String pav, String kat, String kom) {
        Kategorija k = rastiKategorijaPagalPavadinima(kat);
        Pajamos p = new Pajamos(suma, k, pav, data, kom);
        k.getJudejimas().add(p);
        this.judejimas.add(p);

    }

    public void salintiKategorija(String katPav) {
        Kategorija sal = new Kategorija(katPav, null);
        kategorijos.remove(sal);
    }

    public void salintiPajama(double suma, Date data, String pav, String kat) {
        String sum = Double.toString(suma);
        String dataStr = DataFormat(data);
        for (PiniguJudejimas paj : judejimas) {
            if (paj.toString().contains(sum) && paj.toString().contains(kat)
                    && paj.toString().contains(pav) && paj.toString().contains(dataStr)) {
                //k.getJudejimas().remove(paj);
                this.judejimas.remove(paj);
                break;
            }

        }

    }
    
    public void redaguotiPajama(String suma, String data, String pav, String kat,
                                String nsuma, String ndata, String npav){
        Double sum = Double.parseDouble(suma);
        Double nsum = Double.parseDouble(nsuma);
        for(PiniguJudejimas jud : judejimas){
            if(jud.getKategorija().equals(kat)&&jud.getAprasymas().equals(pav)
                    &&jud.getData().equals(data)&&(jud.getSuma()==sum)){
                jud.setAprasymas(npav);
                jud.setData(toDate(ndata));
                jud.setSuma(nsum);
            }
        }
    }

    public void salintiIslaida(double suma, Date data, String pav, String kat) {

        String sum = Double.toString(suma);
        String dataStr = DataFormat(data);
        for (Islaidos i : islaid) {
            if (i.toString().contains(sum) && i.toString().contains(kat)
                    && i.toString().contains(pav) && i.toString().contains(dataStr)) {
                this.islaid.remove(i);
                break;
            }

        }
    }
    
    public void redaguotiIslaida(String suma, String data, String pav, String kat,
                                String nsuma, String ndata, String npav){
        Double sum = Double.parseDouble(suma);
        Double nsum = Double.parseDouble(nsuma);
        for(Islaidos isl : islaid){
            if(isl.getKategorija().equals(kat)&&isl.getAprasymas().equals(pav)
                    &&isl.getData().equals(data)&&(isl.getSuma()==sum)){
                isl.setAprasymas(npav);
                isl.setData(toDate(ndata));
                isl.setSuma(nsum);
            }
        }
    }

    public boolean keistiKategorijosPavadinima(String kokiaKat, String naujasPav) {
        boolean a = true;
        Kategorija ka = new Kategorija(naujasPav, null);
        if (!kategorijos.contains(ka)) {
            for (Kategorija k : kategorijos) {
                if (kokiaKat.equals(k.getPavadinimas())) {
                    k.setPavadinimas(naujasPav);
                    a = true;
                }
            }
        } else {
            System.out.println("Negalima keisti, nes toks pavadinimas egzistuoja");
            a = false;
        }
        return a;
    }

    public Kategorija rastiKategorijaPagalPavadinima(String pav) {
        for (Kategorija k : kategorijos) {
            if (k.getPavadinimas().equals(pav)) {
                return k;
            }
        }
        return null;
    }

    public ArrayList<PiniguJudejimas> gautiPajamuSarasa() {
        return judejimas;
    }

    public ArrayList<Islaidos> gautiIslaiduSarasa() {
        return islaid;
    }

    public void gautiIslaida(String pavad) {
        if (islaid.toString().contains("kategorija=" + pavad)) {
            for (Islaidos number : islaid) {
                String bb = number.toString();
                if (bb.contains("kategorija=" + pavad)) {
                    System.out.println(bb);
                }
            }
        } else {
            System.out.println("kategorija nerasta");
        }
    }

    public void gautiPajama(String pavad) {
        if (judejimas.toString().contains("kategorija=" + pavad)) {
            for (PiniguJudejimas number : judejimas) {
                String bb = number.toString();
                if (bb.contains("kategorija=" + pavad)) {
                    System.out.println(bb);
                }
            }
        } else {
            System.out.println("kategorija nerasta");
        }
    }

    public ArrayList<Kategorija> gautiKategorijuSarasa() {
        return kategorijos;
    }

    public void spausdintiVisusIrasus() {
        int nr = 1;
        for (PiniguJudejimas p : this.judejimas) {
            System.out.println(nr + " " + p);
        }
    }

    public void surikiuotiPajamas() {
        Collections.sort(judejimas);
    }

    public void surikiuotiPajamas(String kat) {
        Kategorija k = rastiKategorijaPagalPavadinima(kat);
        if (k != null) {
            Collections.sort(k.getJudejimas());
        } else {
            System.out.println("Tokia kategorija egzistuoja");
        }
    }

    public String DataFormat(Date data) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(data);
        return strDate;
    }

}
