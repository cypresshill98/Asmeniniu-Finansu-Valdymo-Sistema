package Lab2;

import ds.FinansuValdymas;
import ds.Islaidos;
import ds.Kategorija;
import ds.PiniguJudejimas;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lab2 {

    public static void main(String[] args) {

        FinansuValdymas afv = new FinansuValdymas();

        try {
            ObjectInputStream out = new ObjectInputStream(new FileInputStream("duom.afv"));
            afv = (FinansuValdymas) out.readObject();
            out.close();
        } catch (Exception e) {
            System.out.println("Nenuskaityti duomenys");

        }

        //afv.pridetiKategorija("Maistas", "Maisto islaidos");
        //afv.pridetiKategorija("Mokesciai", "Mokesciai uz sildyma");
        ArrayList<Kategorija> kat = afv.gautiKategorijuSarasa();

        Scanner sc = new Scanner(System.in);

        String komanda = "";

        while (!komanda.equals("q")) {
            System.out.println("Pasirinkite komanda:\n"
                    + "\tk. Kategoriju valdymas\n"
                    + "\ti. Islaidu valdymas\n"
                    + "\tp. pajamu valdymas\n"
                    + "\ta. Apibendrinancios suvestines\n"
                    + "\tq. Baigti darba");
            komanda = sc.nextLine();
            switch (komanda) {
                case "k":
                    kategorijuValdymas(sc, afv);
                    break;
                case "i":
                    islaiduValdymas(sc, afv);
                    break;
                case "p":
                    pajamuValdymas(sc, afv);
                    break;
                case "a":
                    apibValdymas(sc, afv);
                    break;
                case "q":
                    System.out.println("Viso gero.");
                    try {
                        ObjectOutputStream out
                                = new ObjectOutputStream(new FileOutputStream("duom.afv"));
                        out.writeObject(afv);
                        out.close();
                    } catch (Exception e) {
                    }
                    break;
                default:
                    System.out.println("Komanda negalima");
                    break;
            }

        }

    }

    public static void kategorijuValdymas(Scanner klava, FinansuValdymas v) {
        String subk = "";
        while (!subk.equals("quit")) {
            System.out.println("pasirinkite ka norite daryti su kategorija:\n "
                    + "\tprint - spausdinti\n"
                    + "\tadd - prideti kategorija\n"
                    + "\tdelete (pavadinimas) - salinti kategorija\n"
                    + "\tedit - redaguoti katagorija\n"
                    + "\tsave - issaugoti katagorija i faila\n"
                    + "\tload - paleisti katagorija is failo\n"
                    + "\tquit - gryzti");
            subk = klava.nextLine();
            String pavadinimas = "";
            if (subk.startsWith("delete")) {
                if (subk.length() > 7) {
                    pavadinimas = subk.substring(7);
                    subk = "delete";
                } else {
                    subk = "";
                    System.out.println("Butina nurodyti salinamos kategorijos pavadinima");
                }
            }

            switch (subk) {
                case "print":
                    ArrayList<Kategorija> kat = v.gautiKategorijuSarasa();
                    int nr = 1;
                    for (Kategorija k : kat) {
                        System.out.printf("%3d. '%s'\n", nr, k.getPavadinimas() + "-" + k.getAprasymas());
                        nr++;
                    }
                    break;
                case "add":
                    System.out.println("Iveskite kategorijos pavadinima: ");
                    String kpav = klava.nextLine();
                    System.out.println("Iveskite kategorijos aprasyma: ");
                    String kapr = klava.nextLine();
                    v.pridetiKategorija(kpav, kapr);
                    break;
                case "delete":
                    System.out.println("Salinsime '" + pavadinimas + "'");
                    v.salintiKategorija(pavadinimas);
                    break;
                case "edit":
                    System.out.println("Iveskite pavadinima, kuri norite pakeisti");
                    kpav = klava.nextLine();
                    System.out.println("Iveskite nauja pavadinima");
                    kapr = klava.nextLine();
                    v.keistiKategorijosPavadinima(kpav, kapr);
                    break;

                case "save":
                    ArrayList<Kategorija> kate = v.gautiKategorijuSarasa();
                    try {
                        FileWriter faila = new FileWriter(new File("kategorijos.txt"));
                        for (Kategorija k : kate) {
                            faila.write(k.getPavadinimas() + ";" + k.getAprasymas() + ";\n");
                        }
                        faila.close();
                    } catch (Exception e) {
                        System.out.println("Klaida saugant duomenis i faila");
                    }
                    break;
                case "load":
                    try {
                        Scanner failas = new Scanner(new File("kategorijos.txt"));
                        while (failas.hasNext()) {
                            String eilute = failas.nextLine();
                            String[] dalys = eilute.split(";");
                            if (dalys.length <= 2) {
                                String pavad = dalys[0];
                                String apras = dalys[1];
                                v.pridetiKategorija(pavad, apras);
                            }
                        }
                        failas.close();
                    } catch (Exception e) {
                        System.out.println("Klaida nuskaitant is failo");
                    }
                    break;
                case "quit":
                    subk = "quit";
                    break;
                default:
                    System.out.println("Tokia komanda negalima");
            }
        }

    }

    public static void islaiduValdymas(Scanner klava, FinansuValdymas vv) {
        String pasi = "";
        while (!pasi.equals("quit")) {
            System.out.println("pasirinkite ka norite daryti su islaidomis:\n "
                    + "\tadd - pasirinktą kategorija pridėti naują islaidu irasa\n"
                    + "\tprint - Spausdinti pasirinktos kategorijos islaidas\n"
                    + "\tprintall - Spausdinti visu kategoriju islaidas\n"
                    + "\tsave - Issaugoti pasirinkta islaidu sarasa faile\n"
                    + "\tload - Nuskaityti islaidu irašus iš failo\n"
                    + "\tquit - gryzti");
            pasi = klava.nextLine();
            switch (pasi) {
                case "add":
                    System.out.println("Iveskite islaidos data (yyyy-MM-dd)");
                    String ives = klava.nextLine();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date dataa = toDate(ives);
                    System.out.println("Iveskite islaidos suma");
                    ives = klava.nextLine();
                    double suma = Double.parseDouble(ives);
                    System.out.println("Iveskite islaidos pavadinima");
                    ives = klava.nextLine();
                    System.out.println("Iveskite kategorijos pavadinima");
                    String katp = klava.nextLine();
                    System.out.println("Iveskite islaidos komentara");
                    String kom = klava.nextLine();
                    System.out.println("Iveskite cekio numeri");
                    String ceknr = klava.nextLine();
                    vv.pridetiIslaida(suma, dataa, ives, katp, kom, ceknr);
                    break;
                    
                    
                case "print":
                    System.out.println("Iveskite kategorijos pavadinima");
                    ives = klava.nextLine();
                    vv.gautiIslaida(ives);
                    break;
                case "printall":
                    ArrayList<Islaidos> isl = vv.gautiIslaiduSarasa();
                    int n = vv.gautiIslaiduSarasa().size();
                    for (int i = 0; i < n; i++) {
                        System.out.print((i + 1) + ". ");
                        System.out.println(isl.get(i));
                    }
                    break;
                case "save":
                    isl = vv.gautiIslaiduSarasa();
                    try {
                        FileWriter faila = new FileWriter(new File("Islaidos.txt"));
                        for (Islaidos is : isl) {
                            faila.write(is.getIslaiduSuma() + ";" + is.getIslaidosKategorija()
                                    + ";" + is.getIslaidosAprasymas() + ";" + is.getIslaidosKomentaras() + ";"
                                    + is.getIslaidosData() + ";"+ is.getCekioNr() + ";");
                        }
                        faila.close();
                    } catch (Exception e) {
                        System.out.println("Klaida saugant duomenis i faila");
                    }
                    break;
                case "load":
                    try {
                        Scanner failas = new Scanner(new File("Islaidos.txt"));
                        while (failas.hasNext()) {
                            String eilute = failas.nextLine();
                            String[] dalys = eilute.split(";");
                            if (dalys.length <= 6) {
                                double sum = Double.parseDouble(dalys[0]);
                                String kat = dalys[1];
                                String apr = dalys[2];
                                String kome = dalys[3];
                                Date date1 = toDate(dalys[4]);
                                String cekionr = dalys[5];
                                vv.pridetiIslaida(sum, date1, apr, kat, kome, cekionr);
                            }
                        }
                        failas.close();
                    }catch (Exception e) {
                        System.out.println("Klaida nuskaitant is failo");
                    }
                    break;
                case "quit":
                    pasi = "quit";
                    break;
                default:
                    System.out.println("Tokia komanda negalima");
            }

        }
    }

    public static void pajamuValdymas(Scanner klava, FinansuValdymas vvv) {
        String pasp = "";
        while (!pasp.equals("quit")) {
            System.out.println("pasirinkite ka norite daryti su pajamomis:\n "
                    + "\tadd - I pasirinkta kategorija prideti nauja pajamu irasa\n"
                    + "\tprint - Spausdinti pasirinktos kategorijos pajamas\n"
                    + "\tprintall - Spausdinti visu kategoriju pajamas\n"
                    + "\tsave - Issaugoti pasirinkta pajamu sarasą faile\n"
                    + "\tload - Nuskaityti pajamu irasus is failo\n"
                    + "\tquit - gryzti");
            pasp = klava.nextLine();
            switch (pasp) {
                case "add":
                    System.out.println("Iveskite pajamos suma");
                    String ives = klava.nextLine();
                    double suma = Double.parseDouble(ives);
                    System.out.println("Iveskite islaidos data (yyyy-MM-dd)");

                    ives = klava.nextLine();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                    Date dataaa = null;
                    try {
                        dataaa = format.parse(ives);
                        //System.out.println(data);
                    } catch (ParseException ex) {
                        Logger.getLogger(Lab2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Iveskite pajamos pavadinima");
                    String katpp = klava.nextLine();
                    System.out.println("Iveskite kategorijos pavadinima");
                    String kpp = klava.nextLine();
                    System.out.println("Iveskite pajamos komentara");
                    String kom = klava.nextLine();
                    vvv.pridetiPajama(suma, dataaa, katpp, kpp, kom);
                    break;
                case "print":
                    System.out.println("Iveskite kategorijos pavadinima");
                    ives = klava.nextLine();
                    vvv.gautiPajama(ives);
                    break;
                case "printall":
                    ArrayList<PiniguJudejimas> pin = vvv.gautiPajamuSarasa();
                    int n = vvv.gautiPajamuSarasa().size();
                    for (int i = 0; i < n; i++) {
                        System.out.print((i + 1) + ". ");
                        System.out.println(pin.get(i));
                    }
                    break;
                case "save":
                     pin = vvv.gautiPajamuSarasa();
                        try {
                            FileWriter faila = new FileWriter(new File("Pajamos.txt"));
                            for (PiniguJudejimas pj : pin) {
                                faila.write(pj.getSuma() + ";" + pj.getKategorija() 
                                + ";" + pj.getAprasymas() + ";" + pj.getKomentaras() + ";" 
                                + pj.getData() + ";\n");
                            }
                            faila.close();
                        } catch (Exception e) {
                            System.out.println("Klaida saugant duomenis i faila");
                        }
                    break;
                case "load":
                    try {
                        Scanner failas = new Scanner(new File("Pajamos.txt"));
                        while (failas.hasNext()) {
                            String eilute = failas.nextLine();
                            String[] dalys = eilute.split(";");
                            if (dalys.length <= 5) {
                                double sum = Double.parseDouble(dalys[0]);
                                String kat = dalys[1];
                                String apr = dalys[2];
                                String kome = dalys[3];
                                Date date1 = toDate(dalys[4]);
                                vvv.pridetiPajama(sum, date1, apr, kat, kome);
                            }
                        }
                        failas.close();
                    }catch (Exception e) {
                        System.out.println("Klaida nuskaitant is failo");
                    }
                    break;
                case "quit":
                    pasp = "quit";
                    break;
                default:
                    System.out.println("Tokia komanda negalima");
            }
        }
    }
    public static void apibValdymas(Scanner klava, FinansuValdymas vvvv)
    {
        String pasa = "";
        while (!pasa.equals("quit")) {
            System.out.println("Apibendrinancios suvestines:\n "
                    + "\tprint - Spausdinti bendrą sistemos balansą\n"
                    + "\tprintall - Spausdinti visus irasus\n"
                    + "\tsave - Visus sistemos duomenis (kategorijos, islaidos, pajamos) issaugoti faile\n"
                    + "\tload - Visus sistemos duomenis (kategorijos, islaidos, pajamos) nuskaityti is failo\n"
                    + "\tquit - gryzti\n");
            pasa = klava.nextLine();
            switch (pasa) {
                case "print":
                    System.out.println("bendras balancas: ");
                    double balans;
                    balans = vvvv.Balansas();
                    System.out.println(balans);
                    break;
                case "save":
                    ArrayList<Islaidos> isl = vvvv.gautiIslaiduSarasa();
                    ArrayList<PiniguJudejimas> pin = vvvv.gautiPajamuSarasa();
                    ArrayList<Kategorija> kat = vvvv.gautiKategorijuSarasa();
                    try {
                        FileWriter faila = new FileWriter(new File("Duomenys.afv"));
                        for (Kategorija k : kat) {
                            faila.write(k.getPavadinimas() + ";" + k.getAprasymas() + ";"+ "\r\n");
                        }
                        //faila.write("\r\n");
                        for (Islaidos is : isl) {
                            faila.write(is.getIslaiduSuma() + ";" + is.getIslaidosKategorija()
                                    + ";" + is.getIslaidosAprasymas() + ";" + is.getIslaidosKomentaras() + ";"
                                    + is.getIslaidosData() + ";"+ is.getCekioNr() + ";" + "\r\n");
                        }
                        //faila.write("\r\n");
                        for (PiniguJudejimas pj : pin) {
                            faila.write(pj.getSuma() + ";" + pj.getKategorija()
                                    + ";" + pj.getAprasymas() + ";" + pj.getKomentaras() + ";"
                                    + pj.getData() + ";" + "\r\n");
                        }
                        faila.close();
                    } catch (Exception e) {
                        System.out.println("Klaida saugant duomenis i faila");
                    }
                    
                    break;
                case "printall":
                    System.out.println("Islaidos");
                    isl = vvvv.gautiIslaiduSarasa();
                    int n = vvvv.gautiIslaiduSarasa().size();
                    for (int i = 0; i < n; i++) {
                        System.out.print((i + 1) + ". ");
                        System.out.println(isl.get(i));
                    }
                    System.out.println("Pajamos");
                    pin = vvvv.gautiPajamuSarasa();
                    int m = vvvv.gautiPajamuSarasa().size();
                    for (int i = 0; i < m; i++) {
                        System.out.print((i + 1) + ". ");
                        System.out.println(pin.get(i));
                    }
                    break;
                case "load":
                    try {
                        Scanner failas = new Scanner(new File("Duomenys.afv"));
                        while (failas.hasNext()) {
                            String eilute = failas.nextLine();
                            String[] dalys = eilute.split(";");
                            if (dalys.length == 2) {
                                String pavad = dalys[0];
                                String apras = dalys[1];
                                vvvv.pridetiKategorija(pavad, apras);
                            }
                            String[] dalys1 = eilute.split(";");
                            if (dalys1.length == 6) {
                                double sum = Double.parseDouble(dalys1[0]);
                                String katt = dalys1[1];
                                String apr = dalys1[2];
                                String kome = dalys1[3];
                                Date date1 = toDate(dalys1[4]);
                                String cekionr = dalys1[5];
                                vvvv.pridetiIslaida(sum, date1, apr, katt, kome, cekionr);
                            }
                            String[] dalys2 = eilute.split(";");
                            if (dalys2.length == 5) {
                                double sum = Double.parseDouble(dalys2[0]);
                                String katp = dalys2[1];
                                String apr = dalys2[2];
                                String kome = dalys2[3];
                                Date date1 = toDate(dalys2[4]);
                                vvvv.pridetiPajama(sum, date1, apr, katp, kome);
                            }
                        }
                        failas.close();
                    }catch (Exception e) {
                        System.out.println("Klaida nuskaitant is failo");
                    }
                    break;
                case "quit":
                    pasa = "quit";
                    break;
                default:
                    System.out.println("Komanda negalima");
                    break;
            }
        }
    }
    public static Date toDate(String strDate){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date data = null;
                    try {
                        data = format.parse(strDate);
                    } catch (ParseException ex) {
                        Logger.getLogger(Lab2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return data;
    }
    
    

}
