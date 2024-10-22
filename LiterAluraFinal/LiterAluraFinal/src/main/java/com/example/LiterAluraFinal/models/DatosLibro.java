package com.example.LiterAluraFinal.models;

import com.example.LiterAluraFinal.models.DatosResultados;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("results") List<DatosResultados>  resultados
) {
}