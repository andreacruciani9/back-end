package com.example.w6d4t.model;


import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;

@Data
public class AutoreRequest {
@NotNull(message = "mome obbligatorio")
@NotEmpty(message="nome non vuoto")
    private String nome;
    @NotNull(message = "cognome obbligatorio")
    @NotEmpty(message="cognome non vuoto")
    private String cognome;
    @NotNull(message = "email obbligatorio")
    @NotEmpty(message="email non vuoto")
    private String email;
    @NotNull(message = "data di nascita  obbligatorio")

    private LocalDate dataNascita;
    private Integer idAutore;
}
