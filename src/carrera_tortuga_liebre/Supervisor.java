package carrera_tortuga_liebre;

import java.util.Collections;

public class Supervisor {

    private int cTortuga;
    private int cLiebre;
    private boolean esperando = false;
    public boolean corriendo = true;

    public synchronized void updatePosicion(String corredor, int value) {
        if (!esperando) {
            if (corredor.equals("tortuga")) cTortuga = value;
            else if (corredor.equals("liebre")) cLiebre = value;
            else System.out.println("Error: El supervisor no reconoce al corredor "+value);

            esperando = true;
            while (esperando) {
                try {
                    wait();
                } catch (InterruptedException ignored) {}
            }
        } else {
            if (corredor.equals("tortuga")) cTortuga = value;
            else if (corredor.equals("liebre")) cLiebre = value;
            else System.out.println("Error: El supervisor no reconoce al corredor "+value);

            esperando = false;
            notifyAll();

            if (cTortuga > cLiebre) System.out.println("\nT: 1ª posición "+printProgress(cTortuga)+"\nL: 2ª posición "+printProgress(cLiebre)+"\n");
            else System.out.println("\nT: 2ª posición "+printProgress(cTortuga)+"\nL: 1ª posición "+printProgress(cLiebre)+"\n");
            if (cTortuga == 70 || cLiebre == 70) {
                if (cTortuga > cLiebre) System.out.println("¡La tortuga ha ganado la carrera!");
                else if (cTortuga < cLiebre) System.out.println("¡La liebre ha ganado la carrera!");
                else System.out.println("¡Empate!");
                corriendo = false;
            }
        }
    }

    private static String printProgress(long current) {
        int percent = (int) (current * 100 / (long) 70);
        return
                String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")) +
                String.format(" %d%% [", percent) +
                String.join("", Collections.nCopies(percent, "=")) +
                '>' +
                String.join("", Collections.nCopies(100 - percent, " ")) +
                ']' +
                String.join("", Collections.nCopies(current == 0 ? (int) (Math.log10((long) 70)) : (int) (Math.log10((long) 70)) - (int) (Math.log10(current)), " ")) +
                String.format(" %d/%d", current, (long) 70);
    }
}
