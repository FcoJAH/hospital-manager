package com.test.hospital.controller;

import com.test.hospital.model.Doctor;
import com.test.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> obtenerDoctores() {
        return doctorService.obtenerDoctores();
    }

    // Endpoint para crear un nuevo doctor
    @PostMapping
    public ResponseEntity<Doctor> crearDoctor(@RequestBody Doctor doctor) {
        Doctor nuevoDoctor = doctorService.crearDoctor(doctor);
        return new ResponseEntity<>(nuevoDoctor, HttpStatus.CREATED);
    }

    // Eliminar un doctor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDoctor(@PathVariable Long id) {
        boolean eliminado = doctorService.eliminarDoctor(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204: No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404: Not Found
        }
    }
}