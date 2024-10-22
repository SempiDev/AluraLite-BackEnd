package com.example.LiterAluraFinal.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true )
public record DatosResultados(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors")List<DatosAutor> listaAutor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Integer numeroDeDescargas
) {
}
