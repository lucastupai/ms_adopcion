package cl.sanosysalvos.ms_adopcion.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "adopciones")
public class Adopcion {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private Long MascotaId;
    private Long usuarioInteresadoId;
    private Long usuarioPublicadorId;
    private String mensaje;
    @Enumerated(EnumType.STRING)
    private EstadoAdopcion estado;
    private LocalDateTime fechaSolicitud;

    public Adopcion() {
    }
    public Adopcion(Long mascotaId, Long usuarioInteresadoId, Long usuarioPublicadorId, String mensaje) {
    this.MascotaId = mascotaId;
    this.usuarioInteresadoId = usuarioInteresadoId;
    this.usuarioPublicadorId = usuarioPublicadorId;
    this.mensaje = mensaje;
    this.estado = EstadoAdopcion.PENDIENTE;
    this.fechaSolicitud = LocalDateTime.now();
}
    public Long getId() {
        return id;
    }

    public Long getMascotaId() {
        return MascotaId;
    }

    public Long getUsuarioInteresadoId() {
        return usuarioInteresadoId;
    }

    public void setUsuarioInteresadoId(Long usuarioInteresadoId) {
        this.usuarioInteresadoId = usuarioInteresadoId;
    }

    public Long getUsuarioPublicadorId() {
        return usuarioPublicadorId;
    }

    public void setUsuarioPublicadorId(Long usuarioPublicadorId) {
        this.usuarioPublicadorId = usuarioPublicadorId;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }   

    public EstadoAdopcion getEstado() {
        return estado;
    }

    public void setEstado(EstadoAdopcion estado) {
        this.estado = estado;
    }   

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    

}
