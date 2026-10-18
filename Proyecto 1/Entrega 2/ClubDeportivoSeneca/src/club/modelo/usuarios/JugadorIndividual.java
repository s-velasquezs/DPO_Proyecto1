package club.modelo.usuarios;


import java.util.ArrayList;
import java.util.List;

import club.modelo.deportes.DeporteIndividual;
import club.modelo.enums.Nivel;

public class JugadorIndividual extends Jugador {

    private List<InscripcionIndividual> inscripciones;
    private boolean tambienEntrenador;

    public JugadorIndividual(String username, String password, String nombre, int edad,
                             boolean tambienEntrenador) {
        super(username, password, nombre, edad);
        this.inscripciones = new ArrayList<InscripcionIndividual>();
        this.tambienEntrenador = tambienEntrenador;
    }

    public boolean esTambienEntrenador() {
        return tambienEntrenador;
    }
    public boolean inscribirseDeporte(DeporteIndividual deporte, Nivel nivel) {
        if (!estaActivo() || getInscripcion(deporte) != null) {
            return false;
        }
        inscripciones.add(new InscripcionIndividual(this, deporte, nivel));
        return true;
    }
    public boolean inscribirseDeporte(DeporteIndividual deporte, Nivel nivel) {
        if (!estaActivo() || getInscripcion(deporte) != null) {
            return false;
        }
        inscripciones.add(new InscripcionIndividual(this, deporte, nivel));
        return true;
    }
    public InscripcionIndividual getInscripcion(DeporteIndividual deporte) {
        for (InscripcionIndividual i : inscripciones) {
            if (i.estaActiva() && i.getDeporte() == deporte) {
                return i;
            }
        }
        return null;
    }

    public Nivel getNivel(DeporteIndividual deporte) {
        InscripcionIndividual i = getInscripcion(deporte);
        return (i == null) ? null : i.getNivel();
    }

    public RankingELO getRanking(DeporteIndividual deporte) {
        InscripcionIndividual i = getInscripcion(deporte);
        return (i == null) ? null : i.getRanking();
    }
    public boolean esRivalValido(JugadorIndividual rival, DeporteIndividual deporte) {
        Nivel miNivel = getNivel(deporte);
        Nivel suNivel = rival.getNivel(deporte);
        if (miNivel == null || suNivel == null) {
            return false;
        }
        if (tambienEntrenador || rival.esTambienEntrenador()) {
            return true;
        }
        return miNivel.esIgualOAdyacente(suNivel);
    }

    public List<InscripcionIndividual> getInscripciones() {
        return new ArrayList<InscripcionIndividual>(inscripciones);
    }
}
    
