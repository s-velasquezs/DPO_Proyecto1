package club.modelo.usuarios;

import java.time.LocalDate;

import club.modelo.deportes.Equipo;

public class Inscripcion {

    private JugadorEquipo jugador;
    private Equipo equipo;
    private String posicion;
    private int numeroCamiseta;
    private LocalDate fechaInscripcion;
    private boolean activa;

    public Inscripcion(JugadorEquipo jugador, Equipo equipo, String posicion, int numeroCamiseta) {
        this.jugador = jugador;
        this.equipo = equipo;
        this.posicion = posicion;
        this.numeroCamiseta = numeroCamiseta;
        this.fechaInscripcion = LocalDate.now();
        this.activa = true;
    }

    public JugadorEquipo getJugador() {
        return jugador;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public String getPosicion() {
        return posicion;
    }

    public int getNumeroCamiseta() {
        return numeroCamiseta;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public boolean estaActiva() {
        return activa;
    }

    public void asignarPosicion(String posicion) {
        this.posicion = posicion;
    }

    public boolean asignarNumero(int n) {
        if (n != numeroCamiseta && !equipo.numeroDisponible(n)) {
            return false;
        }
        this.numeroCamiseta = n;
        return true;
    }

    public void darDeBaja() {
        this.activa = false;
    }
}