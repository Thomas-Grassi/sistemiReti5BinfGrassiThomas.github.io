//sono Grassi Thomas
import java.io.*;
import java.net.*;

public class CalcolatriceServer {
    private static final int PORTA = 8844;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Server avviato sulla porta " + PORTA);

            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Client connesso: " + client.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);

                String richiesta;
                while ((richiesta = in.readLine()) != null) {
                    try {
                        double risultato = calcola(richiesta);
                        out.println("Risultato: " + risultato);
                    } catch (Exception e) {
                        out.println("ERRORE: " + e.getMessage());
                    }
                }

                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Metodo per eseguire il calcolo
    private static double calcola(String richiesta) throws Exception {
        String[] parti = richiesta.split(" ");
                if (parti.length != 3) {
            throw new Exception("Formato non valido. Usa: numero operatore numero");
        }

        double num1 = Double.parseDouble(parti[0]);
        String operazione = parti[1];
        double num2 = Double.parseDouble(parti[2]);

        switch (operazione) {
            case "+": return num1 + num2;
            case "-": return num1 - num2;
            case "*": return num1 * num2;
            case "/":
                if (num2 == 0) throw new ArithmeticException("Divisione per zero");
                return num1 / num2;
            default: throw new IllegalArgumentException("Operazione non supportata");
        }
    }
}
