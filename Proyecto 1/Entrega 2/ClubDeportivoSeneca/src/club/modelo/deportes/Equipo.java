package club.modelo.deportes;

import java.util.ArrayList;
import java.util.List;

import club.modelo.enums.Categoria;
import club.modelo.usuarios.Inscripcion;
import club.modelo.usuarios.JugadorEquipo;

public class Equipo {

    private String nombre;
    private Categoria categoria;
    private DeporteEquipo deporte;
    private int cupoMaximo;
    private boolean activo;
    private Registro registro;
    private List<Inscripcion> inscripciones;

    public Equipo(String nombre, Categoria categoria, DeporteEquipo deporte, int cupoMaximo) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.deporte = deporte;
        this.cupoMaximo = cupoMaximo;
        this.activo = true;
        this.registro = new Registro();
        this.inscripciones = new ArrayList<Inscripcion>();
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public DeporteEquipo getDeporte() {
        return deporte;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public boolean estaActivo() {
        return activo;
    }

    public Registro getRegistro() {
        return registro;
    }

    private int contarActivas() {
        int n = 0;
        for (Inscripcion i : inscripciones) {
            if (i.estaActiva()) {
                n++;
            }
        }
        return n;
    }

    public boolean hayCupo() {
        return contarActivas() < cupoMaximo;
    }

    public boolean numeroDisponible(int n) {
        for (Inscripcion i : inscripciones) {
            if (i.estaActiva() && i.getNumeroCamiseta() == n) {
                return false;
            }
        }
        return true;
    }

    public void agregarInscripcion(Inscripcion i) {
        inscripciones.add(i);
    }

    public boolean tieneJugador(JugadorEquipo j) {
        for (Inscripcion i : inscripciones) {
            if (i.estaActiva() && i.getJugador() == j) {
                return true;
            }
        }
        return false;
    }

    public List<JugadorEquipo> jugadoresHabilitados() {
        List<JugadorEquipo> lista = new ArrayList<JugadorEquipo>();
        for (Inscripcion i : inscripciones) {
            if (i.estaActiva() && i.getJugador().estaHabilitado()) {
                lista.add(i.getJugador());
            }
        }
        return lista;
    }

    public boolean cumpleMinimoParaJugar() {
        return jugadoresHabilitados().size() >= deporte.getMinimoJugadoresHabilitados();
    }

    public List<Inscripcion> getInscripciones() {
        return new ArrayList<Inscripcion>(inscripciones);
    }
    public void darDeBaja() {
        this.activo = false;
        this.registro.darDeBaja();
    }		
    
    @Override
    public String toString() {
        return nombre + " (" + deporte + ", " + categoria + ")";
    }
}

