package cl.sanosysalvos.ms_adopcion.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mascotas_adopcion")
public class MascotaAdopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String tipo;

    private String edad;

    private String ubicacion;

    private String fundacion;

    @Column(length = 1000)
    private String descripcion;

    @Column(length = 1000)
    private String aspectosPositivos;

    @Column(length = 1000)
    private String aspectosConsiderar;

    private String imagen;

    @Enumerated(EnumType.STRING)
    private EstadoMascotaAdopcion estado;

    public MascotaAdopcion() {
    }

    public MascotaAdopcion(String nombre, String tipo, String edad, String ubicacion, String fundacion,
                           String descripcion, String aspectosPositivos, String aspectosConsiderar, String imagen) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.edad = edad;
        this.ubicacion = ubicacion;
        this.fundacion = fundacion;
        this.descripcion = descripcion;
        this.aspectosPositivos = aspectosPositivos;
        this.aspectosConsiderar = aspectosConsiderar;
        this.imagen = imagen;
        this.estado = EstadoMascotaAdopcion.DISPONIBLE;
    }

    public Long getId() {
        return id;
    }

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

    public EstadoMascotaAdopcion getEstado() {
        return estado;
    }

    public void setEstado(EstadoMascotaAdopcion estado) {
        this.estado = estado;
    }
}