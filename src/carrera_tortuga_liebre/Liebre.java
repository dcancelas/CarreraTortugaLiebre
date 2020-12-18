package carrera_tortuga_liebre;

public class Liebre extends Thread {

    private final Supervisor supervisor;

    public Liebre(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public void run() {
        int casilla = 1;
        while (supervisor.corriendo) {
            double suceso = Math.random()*100+1;
            if (suceso <= 20) {
                System.out.println("L: Duerme");
            } else if (suceso <= 40) {
                System.out.println("L: Salto grande (+9)");
                casilla = casilla + 9;
            } else if (suceso <= 50){
                System.out.println("L: Resvalón grande (-12)");
                casilla = casilla - 12;
            } else if (suceso <= 80) {
                System.out.println("L: Salto pequeño (+1)");
                casilla = casilla + 1;
            } else {
                System.out.println("L: Resvalón pequeño (-2)");
                casilla = casilla - 2;
            }

            if (casilla < 1) casilla = 1;
            if (casilla > 70) casilla = 70;
            supervisor.updatePosicion("liebre", casilla);

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
