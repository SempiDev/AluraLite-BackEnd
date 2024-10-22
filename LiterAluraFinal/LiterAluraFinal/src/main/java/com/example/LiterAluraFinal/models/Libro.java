package com.example.LiterAluraFinal.models;
import jakarta.persistence.*;

@Entity
@Table (name = "libros")
public class Libro {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String autor;
    private String titulo;
    private String idioma;
    private Integer numeroDescarga;

    public Libro(){}

   public Libro (DatosResultados datosLibro){
        this.titulo = datosLibro.titulo();
        this.autor = datosLibro.listaAutor().get(0).nombreAutor();
        this.idioma = datosLibro.idiomas().get(0);
        this.numeroDescarga = datosLibro.numeroDeDescargas();
   }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public  String getTitulo(){
        return titulo;
    }
    public String getAutor(){
        return autor;
    }
    public Integer getNumeroDescarga(){
        return numeroDescarga;
    }
    public String getIdioma(){
        return idioma;
    }
    public void setNumeroDescarga(Integer descarga){
        this.numeroDescarga = descarga;
    }


}










