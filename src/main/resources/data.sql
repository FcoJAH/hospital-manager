-- Insertar doctores de ejemplo
INSERT INTO doctor (nombre, apellido_paterno, apellido_materno, especialidad)
VALUES
('Juan', 'Pérez', 'López', 'Cardiología'),
('María', 'González', 'Hernández', 'Neurología'),
('Carlos', 'Ramírez', 'Martínez', 'Pediatría');

-- Insertar consultorios de ejemplo
INSERT INTO consultorio (numero, piso)
VALUES
(101, 1),
(102, 1),
(201, 2),
(202, 2);

-- Insertar citas de ejemplo
INSERT INTO cita (doctor_id, consultorio_id, horario, paciente)
VALUES
(1, 1, '2024-12-06 09:00:00', 'Pedro López'),
(2, 2, '2024-12-06 10:00:00', 'Ana Torres'),
(3, 3, '2024-12-06 11:00:00', 'Luis Gómez');
