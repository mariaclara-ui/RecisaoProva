package com.clinica;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
private LocalDateTime dataHora;

@Entity
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat
    private LocalDateTime dataHora;
    private String motivo;


    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getMotivo() {
        return motivo;
    }
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Pet getPet() {
        return pet;
    }
    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
