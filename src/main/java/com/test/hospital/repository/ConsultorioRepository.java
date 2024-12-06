package com.test.hospital.repository;

import com.test.hospital.model.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    // Puedes agregar métodos personalizados aquí si los necesitas
}