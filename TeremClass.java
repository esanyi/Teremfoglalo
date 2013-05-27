
package beadando1;

/**
 * @author egyed.sandor_1
 */
public final class TeremClass {
    private TeremType t_type; //Terem tipus
    private String t_name; //Terem neve
    private int t_maxszam; //Terem max létszáma
    private int b_parameter; // Terem egyéb attributum, projektor vagy gepek

    public TeremClass(TeremType type, String TeremName, int MaxSzam, int Egyeb) { //konstruktor
        t_name = TeremName.trim();
        if (t_name.length() == 0) {
        } //random név generálás

        t_type = type;
        t_maxszam = MaxSzam;
        b_parameter = Egyeb;

    }


    public String GetTeremName() {
        return t_name;
    }

    public TeremType GetTeremType() {
        return t_type;
    }

    public static TeremType GetBeingTypeByStr(String TT) { //vissza adja a teremtipust sztring alapján
        if (TT.equalsIgnoreCase("0")) {
            return TeremType.eloado;
        } else if (TT.equalsIgnoreCase("1")) {
            return TeremType.szeminarium;
        } else if (TT.equalsIgnoreCase("2")) {
            return TeremType.gepterem;
        } else {
            return null;
        }
    }

    public String TeremInfo() { //Termek allapota

        return "   Típus: " + t_type + "  |  Teremnév: " + t_name + "  |  Maxlétszám: " + t_maxszam + "  |  Egyéb jellemzők: " + b_parameter;
    }

}
