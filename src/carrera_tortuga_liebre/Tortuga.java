package carrera_tortuga_liebre;

public class Tortuga extends Thread {

    private final Supervisor supervisor;

    public Tortuga(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public void run() {
        int casilla = 1;
        while (supervisor.corriendo) {
            double suceso = Math.random()*100+1;
            if (suceso <= 50) {
                System.out.println("T: Avance rápido (+3)");
                casilla = casilla + 3;
            } else if (suceso <= 70) {
                System.out.println("T: Resvalón (-6)");
                casilla = casilla - 6;
            } else {
                System.out.println("T: Avance lento (+1)");
                casilla = casilla + 1;
            }

            if (casilla < 1) casilla = 1;
            if (casilla > 70) casilla = 70;
            supervisor.updatePosicion("tortuga", casilla);

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
