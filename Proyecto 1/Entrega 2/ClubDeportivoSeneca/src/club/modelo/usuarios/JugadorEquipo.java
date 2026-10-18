package club.modelo.usuarios;

import java.util.ArrayList;
import java.util.List;

import club.modelo.deportes.DeporteEquipo;
import club.modelo.deportes.Equipo;
import club.modelo.enums.Categoria;

public class JugadorEquipo extends Jugador {

    private Categoria categoria;
    private List<Inscripcion> inscripciones;
    
    public JugadorEquipo(String username, String password, String nombre, int edad) {
        super(username, password, nombre, edad);
        this.categoria = Categoria.paraEdad(edad);
        this.inscripciones = new ArrayList<Inscripcion>();
    }

    public Categoria getCategoria() {
        return categoria;
    }
    public boolean inscribirseEquipo(Equipo equipo, String posicion, int numero) {
        if (!estaActivo() || !equipo.estaActivo()) {
            return false;
        }
        if (categoria == null || categoria != equipo.getCategoria()) {
            return false;
        }
        if (tieneEquipoEn(equipo.getDeporte())) {
            return false;
        }
        if (!equipo.hayCupo() || !equipo.numeroDisponible(numero)) {
            return false;
        }
        Inscripcion i = new Inscripcion(this, equipo, posicion, numero);
        inscripciones.add(i);
        equipo.agregarInscripcion(i);
        return true;
    }
    public Inscripcion getInscripcion(DeporteEquipo deporte) {
        for (Inscripcion i : inscripciones) {
            if (i.estaActiva() && i.getEquipo().getDeporte() == deporte) {
                return i;
            }
        }
        return null;
    }

    public boolean tieneEquipoEn(DeporteEquipo deporte) {
        return getInscripcion(deporte) != null;
    }

    public Equipo getEquipo(DeporteEquipo deporte) {
        Inscripcion i = getInscripcion(deporte);
        return (i == null) ? null : i.getEquipo();
    }
    public String getPosicion(Equipo equipo) {
        for (Inscripcion i : inscripciones) {
            if (i.estaActiva() && i.getEquipo() == equipo) {
                return i.getPosicion();
            }
        }
        return null;
    }
    public int getNumeroCamiseta(Equipo equipo) {
        for (Inscripcion i : inscripciones) {
            if (i.estaActiva() && i.getEquipo() == equipo) {
                return i.getNumeroCamiseta();
            }
        }
        return 0;
    }

    public List<Inscripcion> getInscripciones() {
        return new ArrayList<Inscripcion>(inscripciones);
    }
}
