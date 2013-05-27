/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beadando1;

import java.io.*;

/**
 *
 * @author egyed.sandor_1
 */
public final class FileClass {
    private String FileName=null; 
    private BufferedReader br; 
    private String NextLine=null;
    private final String DEF_ENCODE="ISO-8859-2";
    
    public boolean hasNextLine() { 
        if ( NextLine == null && br != null ) { 
            try {
                NextLine=br.readLine(); 
            } catch (IOException e) {
                System.out.println("HIBA: " + e.getMessage());
            }            
        }
        if ( NextLine != null ) { return true; }
        else { return false; }
    }
    public boolean hasOpenedFile() { 
        if (br != null && FileName != null && FileName.length() > 0 ) { return true; }
        else { return false; }
    }
    public String GetLine() { 
        String out=null;
        if (br != null) {
            if ( NextLine != null  ) { out = NextLine; NextLine=null; }
            else {
                try {
                    out=br.readLine(); 
                } catch (IOException e) {
                    System.out.println("HIBA: " + e.getMessage());
                }            
            }
        } 
        return out;
    }
    public String GetFileName() { 
        if ( FileName != null && FileName.length() > 0 ) { return FileName; }
        else { return ""; }
    }
    public long GetFileSize() {  
        long out=0;
        if ( FileName != null && FileName.length() > 0 ) {             
            File f;
            f = new File(FileName);
            if (f.exists() == true) { out = f.length(); }
        }
        return out;
    }

    private void reopen() { OpenFile(FileName,false); } 
    public void RewindFilePointer() { reopen(); } 
    public void CloseFile() { 
        if (br != null) {
            try {
                br.close();
                System.out.println("'"+FileName + "' fájl bezárva");
            } catch (IOException e) {
                System.out.println("HIBA: " + e.getMessage());
            }            
            br=null;
        }
        FileName=null;
        NextLine=null;
    }
    
    public boolean OpenFile(String FN, boolean CreateNew) { //fájl megnyitása
        File f;
        boolean out=false;
        CloseFile();
        if ( FN.length() > 0 ) { 
            try {
                f = new File(FN);
                if (f.exists() == false) { 
                    if (CreateNew==true) { f.createNewFile(); System.out.println( "'"+FN+"' fájl létrehozása"); }
                } 
                else { System.out.println("'"+FN+"' fájl megnyitása"); }
                if (f.canRead()== true) { 
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(f),DEF_ENCODE));
                }
                if (br != null) { FileName=FN; out=true; }
            } catch (IOException e) {
                System.out.println("HIBA: " + e.getMessage());
            }            
        }
        return out;
    }        
    public FileClass(String FN, boolean CreateNew) { OpenFile(FN,CreateNew); } 
    
}
