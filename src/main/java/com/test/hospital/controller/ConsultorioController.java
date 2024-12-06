package com.test.hospital.controller;

import com.test.hospital.model.Consultorio;
import com.test.hospital.service.ConsultorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Consultorio> crearConsultorio(@RequestBody Consultorio consultorio) {
        // Verificar si el consultorio ya existe con el mismo número
        if (consultorioService.existeConsultorio(consultorio.getNumero())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // 400: Bad Request
        }
        Consultorio nuevoConsultorio = consultorioService.crearConsultorio(consultorio);
        return new ResponseEntity<>(nuevoConsultorio, HttpStatus.CREATED);
    }

    // Eliminar un consultorio por ID
    @DeleteMapping("/{id}")
    public void eliminarConsultorio(@PathVariable Long id) {
        consultorioService.eliminarConsultorio(id);
    }
}