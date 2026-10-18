package club.modelo.salud;
import java.util.ArrayList;
import java.util.List;

public class HistorialMedico {

    private boolean lesionado;
    private List<String> observaciones;

    public HistorialMedico() {
        this.lesionado = false;
        this.observaciones = new ArrayList<String>();
    }
    public boolean registrarLesion(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            return false;
        }
        lesionado = true;
        observaciones.add("LESION: " + descripcion);
        return true;
    }
    public boolean darAlta(String nota) {
        if (!lesionado) {
            return false;
        }
        lesionado = false;
        observaciones.add("ALTA: " + nota);
        return true;
    }

    public boolean tieneLesionActiva() {
        return lesionado;
    }

    public void agregarObservacion(String texto) {
        observaciones.add(texto);
    }
    public List<String> getObservaciones() {
        return new ArrayList<String>(observaciones);
    }
}
