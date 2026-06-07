package cl.sanosysalvos.ms_adopcion.dto;

import jakarta.validation.constraints.NotNull;

public class AdopcionRequest {

    @NotNull(message = "El ID de la mascota es obligatorio")
    private Long mascotaId;

    @NotNull(message = "El ID del usuario interesado es obligatorio")
    private Long usuarioInteresadoId;

    @NotNull(message = "El ID del usuario publicador es obligatorio")
    private Long usuarioPublicadorId;

    private String mensaje;

    public Long getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Long mascotaId) {
        this.mascotaId = mascotaId;
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
}