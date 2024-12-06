package com.test.hospital.controller;

import com.test.hospital.model.Cita;
import com.test.hospital.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @PostMapping
    public Cita agendarCita(@RequestBody Cita cita) {
        return citaService.agendarCita(cita);
    }
}
