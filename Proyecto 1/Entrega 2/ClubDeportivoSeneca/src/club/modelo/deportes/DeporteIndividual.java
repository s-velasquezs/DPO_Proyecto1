package club.modelo.deportes;

public class DeporteIndividual extends Deporte {
	private int maxInstalacionesSimultaneas;

    public DeporteIndividual(String nombre, int maxInstalacionesSimultaneas) {
        super(nombre);
        this.maxInstalacionesSimultaneas = maxInstalacionesSimultaneas;
    }

    public int getMaxInstalaciones() {
        return maxInstalacionesSimultaneas;
    }
}

