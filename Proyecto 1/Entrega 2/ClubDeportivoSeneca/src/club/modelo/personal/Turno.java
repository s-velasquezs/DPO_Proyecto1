package club.modelo.personal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import club.modelo.comercio.ProgramaFidelidad;
import club.modelo.usuarios.Usuario;

public class Empleado extends Usuario {

    private String nombre;
    private boolean activo;
    private String codigoDescuento;
    private List<Turno> turnos;
    private List<SolicitudCambioTurno> solicitudes;
    private ProgramaFidelidad programaFidelidad;

    public Empleado(String username, String password, String nombre) {
        super(username, password);
        this.nombre = nombre;
        this.activo = true;
        this.codigoDescuento = "DESC-" + username.toUpperCase();
        this.turnos = new ArrayList<Turno>();
        this.solicitudes = new ArrayList<SolicitudCambioTurno>();
        this.programaFidelidad = new ProgramaFidelidad(this);
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaActivo() {
        return activo;
    }

    public String getCodigoDescuento() {
        return codigoDescuento;
    }

    public ProgramaFidelidad getProgramaFidelidad() {
        return programaFidelidad;
    }

    public boolean puedeComprarConDescuento() {
        return activo && codigoDescuento != null;
    }

    public boolean agregarTurno(Turno t) {
        if (t.getEmpleado() != this) {
            return false;
        }
        for (Turno otro : turnos) {
            if (otro.seSolapaCon(t)) {
                return false;
            }
        }
        turnos.add(t);
        return true;
    }

    // Ordenados por fecha y hora; lista vacía si no hay turnos en el periodo
    public List<Turno> consultarTurnos(LocalDate desde, LocalDate hasta) {
        List<Turno> lista = new ArrayList<Turno>();
        for (Turno t : turnos) {
            if (!t.getFecha().isBefore(desde) && !t.getFecha().isAfter(hasta)) {
                lista.add(t);
            }
        }
        lista.sort(Comparator.comparing(Turno::getFecha).thenComparing(Turno::getHoraInicio));
        return lista;
    }

    public boolean tieneTurnoEn(LocalDate fecha, LocalTime ini, LocalTime fin) {
        for (Turno t : turnos) {
            if (t.seSolapaCon(fecha, ini, fin)) {
                return true;
            }
        }
        return false;
    }


    public boolean reemplazarTurno(Turno actual, Turno nuevo) {
        int pos = turnos.indexOf(actual);
        if (pos < 0) {
            return false;
        }
        turnos.set(pos, nuevo);
        return true;
    }


    public SolicitudCambioTurno solicitarCambioTurno(Turno actual, Turno nuevo, String motivo,
                                                     List<Empleado> personal) {
        if (!activo || !turnos.contains(actual) || nuevo.getEmpleado() != this
                || motivo == null || motivo.isBlank()) {
            return null;
        }
        for (Turno t : turnos) {
            if (t != actual && t.seSolapaCon(nuevo)) {
                return null;
            }
        }
        SolicitudCambioTurno s = new SolicitudCambioTurno(this, actual, nuevo, motivo);
        if (!s.cumpleMinimoPersonal(personal)) {
            return null;
        }
        solicitudes.add(s);
        return s;
    }

    public List<SolicitudCambioTurno> getSolicitudes() {
        return new ArrayList<SolicitudCambioTurno>(solicitudes);
    }

    @Override
    public String toString() {
        return nombre + " (" + getUsername() + ")";
    }
}