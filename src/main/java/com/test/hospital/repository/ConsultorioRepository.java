package com.test.hospital.repository;

import com.test.hospital.model.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    // Método que verifica si ya existe un consultorio con el número dado
    boolean existsByNumero(int numero);
}