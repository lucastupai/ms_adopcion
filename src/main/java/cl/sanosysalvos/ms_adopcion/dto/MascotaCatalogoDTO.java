package cl.sanosysalvos.ms_adopcion.dto;

public class MascotaCatalogoDTO {

    private Long id;
    private String nombre;
    private String especie;
    private String raza;
    private String color;
    private Integer edad;
    private String contacto;
    private String ubicacion;
    private String estadoReporte;
    private String descripcion;
    private String reproductivo;
    private String foto;
    private String fecha;
    private Long dueñoId;
    private String vacunas;

    public MascotaCatalogoDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaza() {
        return raza;
    }

    public String getColor() {
        return color;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getContacto() {
        return contacto;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getEstadoReporte() {
        return estadoReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getReproductivo() {
        return reproductivo;
    }

    public String getFoto() {
        return foto;
    }

    public String getFecha() {
        return fecha;
    }

    public Long getDueñoId() {
        return dueñoId;
    }

    public String getVacunas() {
        return vacunas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setEstadoReporte(String estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setReproductivo(String reproductivo) {
        this.reproductivo = reproductivo;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setDueñoId(Long dueñoId) {
        this.dueñoId = dueñoId;
    }

    public void setVacunas(String vacunas) {
        this.vacunas = vacunas;
    }
}