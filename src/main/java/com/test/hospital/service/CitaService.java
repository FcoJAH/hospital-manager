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
    private DoctorRepository doctorRepository;

    @Autowired
    private ConsultorioRepository consultorioRepository;

    @Autowired
    private CitaRepository citaRepository;

    // Método para obtener todas las citas
    public List<Cita> obtenerTodasLasCitas() {
        return citaRepository.findAll();  // Utiliza el repositorio para obtener todas las citas
    }

    // Método para obtener una cita por su ID
    public Cita obtenerCitaPorId(Long id) {
        return citaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cita no encontrada"));
    }

    // Método para crear una cita
    public void crearCita(Cita cita) throws Exception {
        // Validar la cita antes de guardarla
        validarCita(cita);

        // Asignar el estado de la cita como pendiente
        cita.setEstado(Cita.EstadoCita.PENDIENTE);

        // Guardar la cita en la base de datos
        citaRepository.save(cita);
    }

    // Método para editar una cita
    public void editarCita(Cita cita) throws Exception {
        // Validar la cita antes de actualizarla
        validarCita(cita);

        // Guardar la cita actualizada
        citaRepository.save(cita);
    }

    // Método para cancelar una cita
    public void cancelarCita(Long id) {
        Cita cita = obtenerCitaPorId(id);
        cita.setEstado(Cita.EstadoCita.CANCELADA);
        citaRepository.save(cita);  // Guardar la cita después de cambiar su estado a CANCELADA
    }

    // Método para validar la cita antes de guardarla
    private void validarCita(Cita cita) throws Exception {
        // 1. Verificar que no haya otra cita en el mismo consultorio a la misma hora
        if (citaRepository.existsByConsultorioAndHorario(cita.getConsultorio(), cita.getHorario())) {
            throw new Exception("El consultorio ya tiene una cita agendada en ese horario.");
        }

        // 2. Verificar que el doctor no tenga otra cita a la misma hora
        if (citaRepository.existsByDoctorAndHorario(cita.getDoctor(), cita.getHorario())) {
            throw new Exception("El doctor ya tiene una cita agendada en ese horario.");
        }

        // 3. Verificar que el paciente no tenga otra cita en el mismo día a menos de 2 horas de diferencia
        List<Cita> citasPaciente = citaRepository.findByPaciente(cita.getPaciente());
        for (Cita c : citasPaciente) {
            if (c.getHorario().toLocalDate().equals(cita.getHorario().toLocalDate())) {
                if (c.getHorario().isBefore(cita.getHorario().plusHours(2))) {
                    throw new Exception("El paciente ya tiene una cita en ese horario o con menos de 2 horas de diferencia.");
                }
            }
        }

        // 4. Verificar que el doctor no tenga más de 8 citas en el día
        LocalDateTime inicioDelDia = cita.getHorario().toLocalDate().atStartOfDay();
        LocalDateTime finDelDia = cita.getHorario().toLocalDate().atTime(23, 59);
        long citasDelDoctor = citaRepository.countByDoctorAndHorarioBetween(cita.getDoctor(), inicioDelDia, finDelDia);
        if (citasDelDoctor >= 8) {
            throw new Exception("El doctor no puede tener más de 8 citas en el día.");
        }
    }

    // Método para contar cuántas citas tiene un doctor en un rango de tiempo
    public long contarCitasDelDoctorEnRango(Cita cita) {
        LocalDateTime inicioDelDia = cita.getHorario().toLocalDate().atStartOfDay();
        LocalDateTime finDelDia = cita.getHorario().toLocalDate().atTime(23, 59);
        return citaRepository.countByDoctorAndHorarioBetween(cita.getDoctor(), inicioDelDia, finDelDia);
    }
}
