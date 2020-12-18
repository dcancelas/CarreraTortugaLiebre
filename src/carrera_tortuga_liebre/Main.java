package carrera_tortuga_liebre;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Supervisor supervisor = new Supervisor();
        Tortuga tortuga = new Tortuga(supervisor);
        Liebre liebre = new Liebre(supervisor);

        System.out.println("¡Empieza la carrera!\n");
        Thread.sleep(1000);
        tortuga.start();
        liebre.start();
    }
}
