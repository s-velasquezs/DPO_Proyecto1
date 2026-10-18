package club.modelo.personal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import club.modelo.enums.EstadoSolicitud;

public class SolicitudCambioTurno {

    private static final int MIN_ENTRENADORES = 2;
    private static final int MIN_FISIOTERAPEUTAS = 1;

    private Empleado empleado;
    private Turno turnoActual;
    private Turno turnoNuevo;
    private String motivo;
    private EstadoSolicitud estado;
    private String razonRechazo;

    public SolicitudCambioTurno(Empleado empleado, Turno turnoActual, Turno turnoNuevo, String motivo) {
        this.empleado = empleado;
        this.turnoActual = turnoActual;
        this.turnoNuevo = turnoNuevo;
        this.motivo = motivo;
        this.estado = EstadoSolicitud.PENDIENTE;
        this.razonRechazo = null;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public Turno getTurnoActual() {
        return turnoActual;
    }

    public Turno getTurnoNuevo() {
        return turnoNuevo;
    }

    public String getMotivo() {
        return motivo;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public String getRazonRechazo() {
        return razonRechazo;
    }

   
    public boolean cumpleMinimoPersonal(List<Empleado> personal) {
        LocalDate fecha = turnoActual.getFecha();
        LocalTime ini = turnoActual.getHoraInicio();
        LocalTime fin = turnoActual.getHoraFin();
        int entrenadores = 0;
        int fisios = 0;

        for (Empleado e : personal) {
            if (!e.estaActivo()) {
                continue;
            }
            boolean cubre;
            if (e == empleado) {
                cubre = turnoNuevo.seSolapaCon(fecha, ini, fin);
            } else {
                cubre = e.tieneTurnoEn(fecha, ini, fin);
            }
            if (cubre) {
                if (e instanceof Entrenador) {
                    entrenadores++;
                } else if (e instanceof Fisioterapeuta) {
                    fisios++;
                }
            }
        }
        return entrenadores >= MIN_ENTRENADORES && fisios >= MIN_FISIOTERAPEUTAS;
    }

   
    public boolean aprobar(List<Empleado> personal) {
        if (estado != EstadoSolicitud.PENDIENTE || !cumpleMinimoPersonal(personal)) {
            return false;
        }
        if (!empleado.reemplazarTurno(turnoActual, turnoNuevo)) {
            return false;
        }
        estado = EstadoSolicitud.APROBADA;
        return true;
    }

    public boolean rechazar(String razon) {
        if (estado != EstadoSolicitud.PENDIENTE) {
            return false;
        }
        estado = EstadoSolicitud.RECHAZADA;
        razonRechazo = razon;
        return true;
    }

