package com.example.w6d4t.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class BlogPostRequest {
    @NotNull(message = "contenuto obbligatorio")
    @NotEmpty(message="contenuto non vuoto")
    private String contenuto;
    @NotNull(message = "titolo obbligatorio")
    @NotEmpty(message="titolo non vuoto")
    private String titolo;
    @NotNull(message = "categoria obbligatorio")
    @NotEmpty(message="categotia non vuoto")
    private String categoria;
    @NotNull(message = "tempo lettura obbligatorio")

    private int tempoLettura;
    @NotNull(message = "id obbligatorio")
    private int idAutore;
}
