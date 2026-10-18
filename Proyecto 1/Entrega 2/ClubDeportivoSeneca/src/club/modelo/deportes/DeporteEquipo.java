package club.modelo.deportes;

public class DeporteEquipo extends Deporte {
	private int minimoJugadoresHabilitados;

    public DeporteEquipo(String nombre, int minimoJugadoresHabilitados) {
        super(nombre);
        this.minimoJugadoresHabilitados = minimoJugadoresHabilitados;
    }

    public int getMinimoJugadoresHabilitados() {
        return minimoJugadoresHabilitados;
    }
}