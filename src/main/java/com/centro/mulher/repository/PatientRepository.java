package com.centro.mulher.repository;

import com.centro.mulher.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByCpf(String spf);
    boolean existsByCpf(String cpf);
    boolean existsById(Long id);
}
