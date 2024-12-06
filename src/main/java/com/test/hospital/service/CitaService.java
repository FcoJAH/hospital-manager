package com.test.hospital.service;

import com.test.hospital.model.Cita;
import com.test.hospital.repository.CitaRepository;
import com.test.hospital.repository.ConsultorioRepository;
import com.test.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private ConsultorioRepository consultorioRepository;

    public Cita agendarCita(Cita cita) {
        // Regla 1: No puede haber cita en el mismo consultorio a la misma hora
        if (citaRepository.existsByConsultorioAndHorario(cita.getConsultorio(), cita.getHorario())) {
            throw new IllegalArgumentException("Ya hay una cita en este consultorio a esta hora.");
        }

        // Regla 2: No puede haber cita para el mismo doctor a la misma hora
        if (citaRepository.existsByDoctorAndHorario(cita.getDoctor(), cita.getHorario())) {
            throw new IllegalArgumentException("Este doctor ya tiene una cita a esta hora.");
        }

        // Regla 3: Un paciente no puede tener citas con menos de 2 horas de diferencia
        List<Cita> citasPaciente = citaRepository.findByPaciente(cita.getPaciente());
        for (Cita c : citasPaciente) {
            if (Duration.between(c.getHorario(), cita.getHorario()).toMinutes() < 120) {
                throw new IllegalArgumentException("El paciente no puede tener citas con menos de 2 horas de diferencia.");
            }
        }

        // Regla 4: Un doctor no puede tener más de 8 citas al día
        long citasDoctorEnElDia = citaRepository.countByDoctorAndHorarioBetween(cita.getDoctor(),
                LocalDateTime.now().toLocalDate().atStartOfDay(),
                LocalDateTime.now().toLocalDate().plusDays(1).atStartOfDay());
        if (citasDoctorEnElDia >= 8) {
            throw new IllegalArgumentException("El doctor ya tiene 8 citas hoy.");
        }

        // Si pasa todas las validaciones, se guarda la cita
        return citaRepository.save(cita);
    }
}
