SELECT * FROM reserva;
SELECT * FROM usuario;
SELECT * FROM horario;
SELECT * FROM instalacion;

SELECT * 
FROM usuario,reserva,horario,instalacion 
WHERE reserva.usuario = usuario.id 
AND reserva.horario = horario.id 
AND horario.instalacion = instalacion.id;

SELECT reserva.id as ReservaId, reserva.fecha, usuario.id as UsuarioId, usuario.username, usuario.nombre as UsuNombre, usuario.apellido as UsuApellido, usuario.email, horario.id as HorarioId, horario.hora_inicio, horario.hora_fin, instalacion.id as InstalacionId, instalacion.nombre
FROM usuario,reserva,horario,instalacion 
WHERE reserva.usuario = usuario.id 
AND reserva.horario = horario.id 
AND horario.instalacion = instalacion.id;

-- seleccionar reservas de un usuario
-- por username "usuario1"

SELECT reserva.id as ReservaId, reserva.fecha, usuario.id as UsuarioId, usuario.username, usuario.nombre as UsuNombre, usuario.apellido as UsuApellido, usuario.email, horario.id as HorarioId, horario.hora_inicio, horario.hora_fin, instalacion.id as InstalacionId, instalacion.nombre
FROM usuario,reserva,horario,instalacion 
WHERE reserva.usuario = usuario.id 
AND reserva.horario = horario.id 
AND horario.instalacion = instalacion.id AND usuario.username = "usuario1";

--Selecciona todas las reservas para una instalacion
--con nombre "instalacion1" y fecha 02/11/2023

SELECT reserva.id as ReservaId, reserva.fecha, usuario.id as UsuarioId, usuario.username, usuario.nombre as UsuNombre, usuario.apellido as UsuApellido, usuario.email, horario.id as HorarioId, horario.hora_inicio, horario.hora_fin, instalacion.id as InstalacionId, instalacion.nombre
FROM usuario,reserva,horario,instalacion 
WHERE reserva.usuario = usuario.id 
AND reserva.horario = horario.id 
AND horario.instalacion = instalacion.id AND instalacion.nombre = "Instalacion1" AND reserva.fecha = "2023-11-02";

--Selecciona las horas sin reservas para una instalacion
--con nombre "instalacion1" y fecha 02/11/2023
SELECT horario.id as HorarioId, horario.hora_inicio, horario.hora_fin, instalacion.id as InstalacionId, instalacion.nombre
FROM usuario,reserva,horario,instalacion 
WHERE reserva.usuario = usuario.id 
AND reserva.horario = horario.id 
AND horario.instalacion = instalacion.id AND instalacion.nombre = "Instalacion1" AND reserva.fecha = "2023-11-02" 
AND horario.id NOT IN (SELECT horario.id as HorarioId FROM usuario,reserva,horario,instalacion 
                        WHERE reserva.usuario = usuario.id 
                        AND reserva.horario = horario.id 
                        AND horario.instalacion = instalacion.id AND instalacion.nombre = "Instalacion1" AND reserva.fecha = "2023-11-02";);