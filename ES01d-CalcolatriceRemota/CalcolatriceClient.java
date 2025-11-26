// sono Grassi Thomas
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CalcolatriceClient {
    private static final String HOST = "localhost";
    private static final int PORTA = 8844;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORTA);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("=== CALCOLATRICE REMOTA ===");
            System.out.println("Formato: NUMERO OPERAZIONE NUMERO");
            System.out.println("Operazioni: + - * /");
            System.out.println("Scrivi 'quit' per uscire");

            while (true) {
                System.out.print("Calcolo > ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("quit")) {
                    break;
                }

                out.println(input);                // invia al server i dati
                String risposta = in.readLine();   // riceve dal server
                System.out.println(risposta);      // stampa a schermo i risultati
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
