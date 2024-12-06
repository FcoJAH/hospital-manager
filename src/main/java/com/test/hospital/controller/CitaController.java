package com.test.hospital.controller;

import com.test.hospital.model.Cita;
import com.test.hospital.model.Doctor;
import com.test.hospital.model.Consultorio;
import com.test.hospital.service.CitaService;
import com.test.hospital.service.DoctorService;
import com.test.hospital.service.ConsultorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private DoctorService doctorService;  // Servicio para obtener doctores

    @Autowired
    private ConsultorioService consultorioService;  // Servicio para obtener consultorios

    // Método para mostrar el formulario de creación de citas
    @GetMapping("/crear")
    public String mostrarFormularioDeCrearCita(Model model) {
        List<Doctor> doctores = doctorService.obtenerDoctores();
        List<Consultorio> consultorios = consultorioService.obtenerConsultorios();

        // Obtener fecha y hora actual para calcular los valores dinámicos de min y max
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime inicio = ahora.withHour(8).withMinute(0).withSecond(0).withNano(0);  // 8 AM hoy
        LocalDateTime fin = ahora.withHour(22).withMinute(0).withSecond(0).withNano(0);   // 10 PM hoy

        // Formatear como cadena en formato 'yyyy-MM-ddTHH:mm'
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String minHora = inicio.format(formatter);
        String maxHora = fin.format(formatter);

        model.addAttribute("doctores", doctores);
        model.addAttribute("consultorios", consultorios);
        model.addAttribute("minHora", minHora);
        model.addAttribute("maxHora", maxHora);
        model.addAttribute("cita", new Cita());  // Inicializar un objeto Cita vacío
        return "citas/crear";
    }

    // Método para crear una cita
    @PostMapping("/crear")
    public String crearCita(@ModelAttribute Cita cita, @RequestParam(name = "accion", required = false) String accion, Model model) {
        try {
            if ("confirmar".equals(accion)) {
                cita.setEstado(Cita.EstadoCita.CONFIRMADA);  // Asignar el estado de "Confirmada"
            }
            citaService.crearCita(cita);
            return "redirect:/citas"; // Redirigir a la lista de citas
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("cita", cita);
            return "citas/crear";
        }
    }

    // Ver citas
    @GetMapping
    public String verCitas(Model model) {
        List<Cita> citas = citaService.obtenerTodasLasCitas();
        model.addAttribute("citas", citas);
        return "citas/lista";  // Verifica que esta sea la vista correcta
    }

    @PostMapping("/editar")
    public String editarCita(@ModelAttribute Cita cita, @RequestParam(name = "accion", required = false) String accion, Model model) {
        try {
            if ("confirmar".equals(accion)) {
                cita.setEstado(Cita.EstadoCita.CONFIRMADA);  // Asignar el estado de "Confirmada"
            }
            citaService.editarCita(cita);
            return "redirect:/citas"; // Redirigir a la lista de citas
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("cita", cita);
            return "citas/editar";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarCita(@PathVariable Long id, Model model) {
        Cita cita = citaService.obtenerCitaPorId(id); // Asegúrate de que esta línea devuelva la cita correcta
        List<Doctor> doctores = doctorService.obtenerDoctores(); // Lista de doctores
        List<Consultorio> consultorios = consultorioService.obtenerConsultorios(); // Lista de consultorios

        model.addAttribute("cita", cita);
        model.addAttribute("doctores", doctores);
        model.addAttribute("consultorios", consultorios);
        return "citas/editar"; // La vista de edición
    }

    // Confirmar cita
    @PostMapping("/confirmar/{id}")
    public String confirmarCita(@PathVariable Long id, Model model) {
        try {
            Cita cita = citaService.obtenerCitaPorId(id);  // Obtener la cita por ID
            cita.setEstado(Cita.EstadoCita.CONFIRMADA);  // Cambiar estado a CONFIRMADA
            citaService.editarCita(cita);  // Guardar los cambios
            return "redirect:/citas";  // Redirigir a la lista de citas
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "citas/lista";  // Si hay error, mostrar la lista con el mensaje de error
        }
    }
}

