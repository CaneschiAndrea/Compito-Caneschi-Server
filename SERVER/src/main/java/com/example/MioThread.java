import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.List;

import javax.swing.text.html.parser.Element;

public class MioThread extends Thread{
    private Socket s;
    private List<MioThread> threads;

    public MioThread(Socket s, List<MioThread> threads) {
        this.s = s;
        this.threads = threads;
    }

    @Override
    public void run() {
        
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String stringaRicevuta = new String();
        
            System.out.println("sono qui 0");
            do{
                System.out.println("sono dentro il do");
                stringaRicevuta = in.readLine();
                
                System.out.println("sono dentro il do 2");
                //Integer risposta = Integer.parseInt(stringaRicevuta);
                System.out.println( "Client sceglie : " + stringaRicevuta);
                System.out.println("sono qui 1");
              
                out.writeBytes(stringaRicevuta);
                //System.out.println(List.of(stringaRicevuta));
                
                

                /*if (risposta == 0) {
                    System.out.println("sono qui 2");

                    System.out.println("Aggiunta della nota sulla lista");  
                    
                    System.out.println("sono qui 3");

                } else if (risposta == 1) {
                    System.out.println("Stampa della lista");
                    System.out.println();
                }*/

            }while(stringaRicevuta != null);
            s.close();
            threads.remove(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            threads.remove(this);
        }
    }
}
