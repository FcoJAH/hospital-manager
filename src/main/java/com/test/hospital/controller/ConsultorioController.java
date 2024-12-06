package com.test.hospital.controller;

import com.test.hospital.model.Consultorio;
import com.test.hospital.service.ConsultorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consultorios")
public class ConsultorioController {

    @Autowired
    private ConsultorioService consultorioService;

    // Obtener todos los consultorios
    @GetMapping
    public List<Consultorio> obtenerConsultorios() {
        return consultorioService.obtenerConsultorios();
    }

    // Obtener un consultorio por ID
    @GetMapping("/{id}")
    public Optional<Consultorio> obtenerConsultorio(@PathVariable Long id) {
        return consultorioService.obtenerConsultorioPorId(id);
    }

    // Crear un nuevo consultorio
    @PostMapping
    public Consultorio crearConsultorio(@RequestBody Consultorio consultorio) {
        return consultorioService.crearConsultorio(consultorio);
    }

    // Eliminar un consultorio por ID
    @DeleteMapping("/{id}")
    public void eliminarConsultorio(@PathVariable Long id) {
        consultorioService.eliminarConsultorio(id);
    }
}