
package beadando1;

import java.util.ArrayList;

public class TermekClass {
    private ArrayList<TeremClass> Termek;
    public static final int MaxTermek = 100;

    public TermekClass() {
        Termek = new ArrayList();
    }


    public int GetTeremByname(String tname) {
        int i;
        int out = -1;
        if (tname.length() > 0) {
            for (i = 0; i < Termek.size(); i++) {
                if (Termek.get(i).GetTeremName().equalsIgnoreCase(tname)) {
                    out = i;
                    break;
                }
            }
        }
        return out;
    }

    public boolean AddTerem(TeremType type, String TeremName, int MaxSzam, int Egyeb) {
        boolean out = false;
        if (Termek.size() < MaxTermek) {

            int i = this.GetTeremByname(TeremName);

            out = Termek.add(new TeremClass(type, TeremName, MaxSzam, Egyeb));
        } else {
            System.out.println("Nem engedek több termet!");
        }
        return out;
    }


    public void AllTeremInfo() {
        int i;
        if (Termek.size() == 1) {
            System.out.println("Csak egy terem van megadva:)");
        } else if (Termek.size() > 1) {
            System.out.println("Termek adatai");
        } else {
            System.out.println("Nincsenek teredm adatok!");
        }

        for (i = 0; i < Termek.size(); i++) {
            if ((i + 1) % 11 == 0) {
            }

            System.out.println((i + 1) + Termek.get(i).TeremInfo());
        }
    }

}

