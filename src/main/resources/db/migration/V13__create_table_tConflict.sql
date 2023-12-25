

DELIMITER $$

CREATE TRIGGER schedule_conflict_trigger
BEFORE INSERT ON tb_schedule FOR EACH ROW
BEGIN
    DECLARE conflict_count INT;
    SET conflict_count = (
        SELECT COUNT(*) FROM tb_schedule
        WHERE coach_id = NEW.coach_id
        AND day_of_week = NEW.day_of_week
        AND (
            (NEW.start_time BETWEEN start_time AND end_time)
            OR (NEW.end_time BETWEEN start_time AND end_time)
            OR (NEW.start_time <= start_time AND NEW.end_time >= end_time)
        )
    );

    IF conflict_count > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Conflito de horário para o coach.';
    END IF;
END$$

DELIMITER ;
