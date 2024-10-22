package com.example.LiterAluraFinal.models;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table (name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String autor;
    private Integer nacimiento;

    public Autor(List<DatosAutor> datosAutors){}

        public Autor(DatosAutor datosAutor){
            this.autor = datosAutor.nombreAutor();
            this.nacimiento = datosAutor.fechaDeNacinimiento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAutor(){
        return autor;
    }

    public  void setAutor(String autor){
        this.autor = autor;
    }
    public Integer getNacimiento(){
        return nacimiento;
    }
    public void setNacimiento(Integer nacimiento){
        this.nacimiento = nacimiento;
    }



}
