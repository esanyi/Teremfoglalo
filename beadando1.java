/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beadando1;

import org.omg.PortableInterceptor.AdapterManagerIdHelper;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Beadando1 {

    private static String FileName;

    private static FileClass FC;
    private static int TeremCount = 0;
    private static TermekClass Termek;
    private static final String ERR_FILE_FORMAT = "Hibás fájlformátum";
    private static final String ERR_FILE_ROW = "Hibás adat a sorban";

    public static String removeDuplicateWhitespace(String inputStr) {
        String replaceStr = " ";
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(inputStr);
        return matcher.replaceAll(replaceStr);
    }

    protected static Integer readInt(String question) {
        Boolean finished = false;
        Integer result = 0;
        Scanner myScanner = new Scanner(System.in);
        while (!finished) {
            System.out.println(question);
            String line = myScanner.nextLine();
            try {
                result = Integer.parseInt(line);
                finished = true;
            } catch (NumberFormatException e) {
                finished = false;
            }
        }
        return result;
    }

    protected static Boolean readBool(String question) {
        Boolean finished = false;
        Integer result = 0;
        Scanner myScanner = new Scanner(System.in);
        while (!finished) {
            System.out.println(question);
            System.out.println(new String("1. Igen"));
            System.out.println(new String("2. Nem"));
            String line = myScanner.nextLine();
            try {
                result = Integer.parseInt(line);
                if (result == 1) {
                    return true;
                } else if (result == 2) {
                    return false;
                }
            } catch (NumberFormatException e) {
                finished = false;
            }
        }
        // We won't actually run into this line
        return false;
    }

    public static void main(String[] args) {
        System.out.print("Teremfoglalás kezelő\n");
        System.out.print("--------------------\n\n");
        System.out.print("Egyed Sándor - EGSRAAI.ELTE\n\n");

        FileName = "";

        if (args.length > 0) {
            FileName = args[0];
        }

        Scanner scan = new Scanner(System.in);

        while (FileName.length() == 0) {
            System.out.print("Beolvasandó fájlnév: ");
            FileName = scan.nextLine();
        }

        if (FileName.length() > 0) {
            FC = new FileClass(FileName, false);
            if (FC.hasOpenedFile()) {
                System.out.println("Fájlméret: " + FC.GetFileSize() + " byte\r\n");
                String NL; //next line
                int i, j;
                TeremCount = 0;
                NL = "";
                while (FC.hasNextLine() && NL.length() == 0) {
                    NL = FC.GetLine().trim();
                }
                if (NL.length() > 0) {
                    try {
                        TeremCount = Integer.parseInt(NL);
                    } catch (NumberFormatException e) {
                        System.out.println(ERR_FILE_FORMAT + "\r\nHIBA: " + e.getMessage());
                    }
                } else {
                    System.out.println(ERR_FILE_FORMAT);
                }


                if (TeremCount > 0) {
                    System.out.println("Fájlban meghatározott termek száma: " + TeremCount);
                    Termek = new TermekClass();
                    TeremType TT;
                    int max, egyeb;
                    String name;
                    i = -1;
                    while (FC.hasNextLine()) {
                        NL = removeDuplicateWhitespace(FC.GetLine().trim());
                        if (NL.length() > 0) {
                            String[] TeremRow = NL.split(" ");

                            if (TeremRow.length >= 4) {
                                i++;

                                TT = TeremClass.GetBeingTypeByStr(TeremRow[0]);
                                max = Integer.parseInt(TeremRow[2]);
                                egyeb = Integer.parseInt(TeremRow[3]);
                                Termek.AddTerem(TT, TeremRow[1], max, egyeb);

                            } else {
                                System.out.println("Hibás sor: " + NL);
                            }
                            if (i + 1 == TeremCount) {
                                break;
                            }
                        }
                    }

                    Termek.AllTeremInfo();

                    System.out.println("\n\n\n");
                    int kapacit = readInt("Mekkora kapacítású termet szeretne?");

                    System.out.println("\nMilyen termet szeretne?");
                    System.out.println("1. Előadó");
                    System.out.println("2. Szeminárium");
                    System.out.println("3. Gépterem");

                    Scanner milyenterem = new Scanner(System.in);
                    String t_szam = milyenkapacit.nextLine();
                    int teremtip = Integer.parseInt(t_szam);

                    System.out.println("\nSzeretne projektort a terembe?");
                    System.out.println("1. Igen");
                    System.out.println("2. Nem\n\n");
                    Scanner projektor = new Scanner(System.in);
                    String p2_szam = projektor.nextLine();
                    int p_szam = Integer.parseInt(p2_szam);

                    while (p_szam != 1 && p_szam != 2) {
                        System.out.println("\nNem megfelelő érték!Újra!\n");
                        System.out.println("\nSzeretne projektort a terembe?");
                        Scanner projektor2 = new Scanner(System.in);
                        p_szam = projektor2.nextInt();
                    }
                    System.out.println("\nFilces táblát szeretne?");
                    Scanner filces = new Scanner(System.in);
                    String filc = filces.nextLine();
                    int f_szam = Integer.parseInt(filc);

                    while (f_szam != 1 && f_szam != 2) {
                        System.out.println("\nNem megfelelő érték!Újra!\n");
                        System.out.println("\nFilces táblát szeretne?");
                        Scanner filces2 = new Scanner(System.in);
                        f_szam = filces2.nextInt();
                    }

                }
            } else {
                System.out.println("Nem létezik a megadott fájl");
            }
        }
    }
}
