package com.centro.mulher.model;

import com.centro.mulher.model.enumPatient.MaritalStatus;
import com.centro.mulher.model.enumPatient.Sex;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CPF(message = "Campo inválido.")
    @NotBlank(message = "Campo obrigatório.")
    private String cpf;

    @NotBlank(message = "Campo obrigatório.")
    private String susCardNumber;

    @NotBlank(message = "Campo obrigatório.")
    private String patientName;

    @NotBlank(message = "Campo obrigatório.")
    private String motherName;

    private String fatherName;

    private Sex sex;

    private MaritalStatus maritalStatus;

    private String birthplace;

    private String profession;

    private String street;

    private String district;

    private String number;

    private String city;

    private String state;

    private String cep;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
