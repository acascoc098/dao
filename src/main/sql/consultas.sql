SELECT * FROM reserva;
SELECT * FROM usuario;
SELECT * FROM horario;
SELECT * FROM instalacion;

SELECT * 
FROM usuario,reserva,horario,instalacion 
WHERE reserva.usuario = usuario.id 
AND reserva.horario = horario.id 
AND horario.instalacion = instalacion.id;

SELECT reserva.id as ReservaId, reserva.fecha, usuario.id as UsuarioId, usuario.nombre as UsuNombre, usuario.apellido as UsuApellido, usuario.email, horario.id as HorarioId, horario.hora_inicio, horario.hora_fin, instalacion.id as InstalacionId, instalacion.nombre
FROM usuario,reserva,horario,instalacion 
WHERE reserva.usuario = usuario.id 
AND reserva.horario = horario.id 
AND horario.instalacion = instalacion.id;