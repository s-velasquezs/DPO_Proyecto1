package club.modelo.usuarios;
import java.util.List;

import club.modelo.comercio.ProgramaFidelidad;
import club.modelo.deportes.Registro;
import club.modelo.estadisticas.EstadisticasJugador;
import club.modelo.salud.HistorialMedico;

public abstract class Jugador extends Usuario {

    private String nombre;
    private int edad;
    private boolean activo;
    private Registro registro;
    private HistorialMedico historialMedico;
    private EstadisticasJugador estadisticas;
    private ProgramaFidelidad programaFidelidad;

    public Jugador(String username, String password, String nombre, int edad) {
        super(username, password);
        this.nombre = nombre;
        this.edad = edad;
        this.activo = true;
        this.registro = new Registro();
        this.historialMedico = new HistorialMedico();
        this.estadisticas = new EstadisticasJugador();
        this.programaFidelidad = new ProgramaFidelidad(this);
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public boolean estaActivo() {
        return activo;
    }

    public Registro getRegistro() {
        return registro;
    }

    public HistorialMedico getHistorialMedico() {
        return historialMedico;
    }

    public EstadisticasJugador consultarEstadisticas() {
        return estadisticas;
    }

    public ProgramaFidelidad getProgramaFidelidad() {
        return programaFidelidad;
    }

    public boolean estaSuspendido() {
        return estadisticas.tieneSancionActiva();
    }
    public boolean estaHabilitado() {
        return activo && !estaSuspendido() && !historialMedico.tieneLesionActiva();
    }

    public List<String> consultarSanciones() {
        return estadisticas.getSanciones();
    }
    public boolean darDeBaja() {
        if (estadisticas.tieneSancionActiva()) {
            return false;
        }
        activo = false;
        registro.darDeBaja();
        return true;
    }

    @Override
    public String toString() {
        return nombre + " (" + getUsername() + ")";
    }
}
