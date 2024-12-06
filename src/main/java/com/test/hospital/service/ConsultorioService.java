package com.test.hospital.service;

import com.test.hospital.model.Consultorio;
import com.test.hospital.repository.ConsultorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultorioService {

    @Autowired
    private ConsultorioRepository consultorioRepository;

    // Obtener todos los consultorios
    public List<Consultorio> obtenerConsultorios() {
        return consultorioRepository.findAll();
    }

    // Obtener consultorio por ID
    public Optional<Consultorio> obtenerConsultorioPorId(Long id) {
        return consultorioRepository.findById(id);
    }

    // Crear un nuevo consultorio
    public Consultorio crearConsultorio(Consultorio consultorio) {
        return consultorioRepository.save(consultorio);
    }

    // Eliminar un consultorio por ID
    public void eliminarConsultorio(Long id) {
        consultorioRepository.deleteById(id);
    }
}