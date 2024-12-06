package com.test.hospital.controller;

import com.test.hospital.model.Doctor;
import com.test.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
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
}