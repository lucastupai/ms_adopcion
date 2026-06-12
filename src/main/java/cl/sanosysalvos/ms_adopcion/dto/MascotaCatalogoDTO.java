package cl.sanosysalvos.ms_adopcion.dto;

public class MascotaCatalogoDTO {

    private Long id;
    private String nombre;
    private String tipo;
    private String edad;
    private String ubicacion;
    private String descripcion;
    private String imagen;
    private String estado;

    public MascotaCatalogoDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEdad() {
        return edad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}