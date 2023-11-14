DELIMITER ;;

DROP TRIGGER `reserva_diaria`;;
DROP TRIGGER `reserva_semanal`;;
DROP TRIGGER `reserva_actualizar_pasado`;;
DROP TRIGGER `reserva_pasado`;;
DROP TRIGGER `reserva_grabar_hora_bitacora`;;

CREATE TRIGGER `reserva_diaria` 
BEFORE INSERT ON `reserva` 
FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM `reserva` WHERE `reserva`.`fecha` = NEW.`fecha` AND `reserva`.`usuario`= NEW.`usuario`) > 0 
    THEN
      SIGNAL sqlstate '45001' 
        SET message_text = 'Sólo se permite una reserva al día por usuario.'; 
    END IF;
END;;

CREATE TRIGGER `reserva_semanal` 
BEFORE INSERT ON `reserva` 
FOR EACH ROW
BEGIN
    IF ( NEW.`fecha` < CURDATE())
    THEN
      SIGNAL sqlstate '45002'
        SET message_text = 'No se permite reservar en una fecha anterior a la actual.';
    ELSEIF ( NEW.`fecha` > DATE_ADD(CURDATE(), INTERVAL 14 DAY) )
    THEN
        SIGNAL sqlstate '45003'
          SET message_text = 'No se permite reservar con más de dos semanas de antelación.';
    END IF;
  END;;

CREATE TRIGGER `reserva_actualizar_pasado` 
BEFORE UPDATE ON `reserva` 
FOR EACH ROW
BEGIN
    IF ( OLD.`fecha` <= CURDATE())
    THEN
      SIGNAL sqlstate '45004'
        SET message_text = 'No se permite actualizar una reserva ya pasada o en el día de la misma.';
    END IF;
END;;

CREATE TRIGGER `reserva_pasado` 
BEFORE DELETE ON `reserva` 
FOR EACH ROW
BEGIN
    IF ( OLD.`fecha` < CURDATE())
    THEN
      SIGNAL sqlstate '45004'
        SET message_text = 'No se permite eliminar una fecha pasada.';
    END IF;
END;;

CREATE TRIGGER `reserva_grabar_hora_bitacora` 
BEFORE INSERT ON `reserva` 
FOR EACH ROW
BEGIN
    SET NEW.fecha_hora_hecha = NOW();
END;;


DELIMITER ;