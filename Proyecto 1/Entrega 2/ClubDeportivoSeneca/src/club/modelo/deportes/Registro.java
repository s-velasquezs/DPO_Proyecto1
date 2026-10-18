package club.modelo.deportes;
import java.time.LocalDate;

public class Registro {
	private LocalDate fechaRegistro;
    private boolean activo;

    public Registro() {
        this.fechaRegistro = LocalDate.now();
        this.activo = true;
    }

    public boolean estaRegistrado() {
        return activo;
    }

    public void darDeBaja() {
        this.activo = false;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }
}
