package com.test.hospital.service;

import com.test.hospital.model.Doctor;
import com.test.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> obtenerDoctores() {
        return doctorRepository.findAll();
    }

    public Doctor crearDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Eliminar un doctor por ID
    public boolean eliminarDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
