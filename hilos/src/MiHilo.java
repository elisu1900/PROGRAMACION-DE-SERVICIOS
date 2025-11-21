public class MiHilo extends Thread {

    private String nombre;
    private Integer inicio;
    private Integer fin;

    public MiHilo(String nombre, Integer inicio, Integer fin) {
        this.nombre = nombre;
        this.inicio = inicio;
        this.fin = fin;
    }

    public void run() {
        for (int i = inicio; i <= fin; i++) {
            if (i <= 1) {
                continue;
            }

            if (i == 2) {
                MainApp.primos.add(i);
                continue;
            }
            boolean esprimo = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    esprimo = false;
                    break;
                }
            }
            if (esprimo) {
                synchronized (MainApp.primos) {
                    MainApp.primos.add(i);
                }
            }
        }
    }
}


