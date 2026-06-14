package cl.sanosysalvos.ms_adopcion.dto;

import java.util.List;

public class MascotaAdopcionInfoDTO {

    private Long id;
    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
    private String descripcion;
    private String ubicacion;
    private String estadoReporte;
    private List<String> fotos;

    public MascotaAdopcionInfoDTO() {
    }

    public MascotaAdopcionInfoDTO(Long id, String nombre, String especie, String raza, Integer edad,
                                  String descripcion, String ubicacion, String estadoReporte,
                                  List<String> fotos) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.descripcion = descripcion;
        this.ubicacion = ubicacion;
        this.estadoReporte = estadoReporte;
        this.fotos = fotos;
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

    public Integer getEdad() {
        return edad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getEstadoReporte() {
        return estadoReporte;
    }

    public List<String> getFotos() {
        return fotos;
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

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setEstadoReporte(String estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
}