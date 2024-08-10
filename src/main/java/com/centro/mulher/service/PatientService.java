package com.centro.mulher.service;

import com.centro.mulher.exception.CpfAlreadyExistsException;
import com.centro.mulher.exception.PatientNotFoundException;
import com.centro.mulher.model.Patient;
import com.centro.mulher.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository repository;

    public List<Patient> getAllPatients() {
        return repository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return repository.findById(id);
    }

    public Optional<Patient> getPatientByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public Patient savePatient(Patient patient) {
        if (patient.getId() == null) {
            if (repository.existsByCpf(patient.getCpf())) {
                throw new CpfAlreadyExistsException("CPF já existe: " + patient.getCpf());
            }
            return repository.save(patient);
        } else {
            Optional<Patient> existingPatientOpt = repository.findById(patient.getId());
            if (existingPatientOpt.isPresent()) {

                Patient existingPatient = existingPatientOpt.get();

                existingPatient.setCpf(patient.getCpf());
                existingPatient.setPatientName(patient.getPatientName());
                existingPatient.setBirthplace(patient.getBirthplace());
                existingPatient.setFatherName(patient.getFatherName());
                existingPatient.setMotherName(patient.getMotherName());
                existingPatient.setProfession(patient.getProfession());
                existingPatient.setMaritalStatus(patient.getMaritalStatus());
                existingPatient.setSex(patient.getSex());
                existingPatient.setSusCardNumber(patient.getSusCardNumber());
                existingPatient.setUpdatedAt(patient.getUpdatedAt());
                existingPatient.setCreatedAt(patient.getCreatedAt());
                existingPatient.setStreet(patient.getStreet());
                existingPatient.setDistrict(patient.getDistrict());
                existingPatient.setNumber(patient.getNumber());
                existingPatient.setCity(patient.getCity());
                existingPatient.setState(patient.getState());
                existingPatient.setCep(patient.getCep());

                return repository.save(existingPatient);
            } else {
                throw new PatientNotFoundException("Paciente não encontrado com ID: " + patient.getId());
            }
        }
    }

    public void deletePatient(Long id) {
        repository.deleteById(id);
    }
}
