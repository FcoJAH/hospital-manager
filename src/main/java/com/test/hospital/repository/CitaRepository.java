package com.test.hospital.repository;

import com.test.hospital.model.Cita;
import com.test.hospital.model.Consultorio;
import com.test.hospital.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    // Verifica si ya existe una cita en el mismo consultorio a la misma hora
    boolean existsByConsultorioAndHorario(Consultorio consultorio, LocalDateTime horario);

    // Verifica si ya existe una cita para el mismo doctor a la misma hora
    boolean existsByDoctorAndHorario(Doctor doctor, LocalDateTime horario);

    // Encuentra todas las citas de un paciente
    List<Cita> findByPaciente(String paciente);

    // Cuenta las citas de un doctor en un rango de tiempo
    long countByDoctorAndHorarioBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);
}