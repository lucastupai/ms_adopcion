package cl.sanosysalvos.ms_adopcion.dto;

import jakarta.validation.constraints.NotBlank;

public class MascotaAdopcionRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El tipo de mascota es obligatorio")
    private String tipo;

    private String edad;

    @NotBlank(message = "La ubicación es obligatoria")
    private String ubicacion;

    @NotBlank(message = "La fundación o lugar responsable es obligatorio")
    private String fundacion;

    private String descripcion;

    private String aspectosPositivos;

    private String aspectosConsiderar;

    private String imagen;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getFundacion() {
        return fundacion;
    }

    public void setFundacion(String fundacion) {
        this.fundacion = fundacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAspectosPositivos() {
        return aspectosPositivos;
    }

    public void setAspectosPositivos(String aspectosPositivos) {
        this.aspectosPositivos = aspectosPositivos;
    }

    public String getAspectosConsiderar() {
        return aspectosConsiderar;
    }

    public void setAspectosConsiderar(String aspectosConsiderar) {
        this.aspectosConsiderar = aspectosConsiderar;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}