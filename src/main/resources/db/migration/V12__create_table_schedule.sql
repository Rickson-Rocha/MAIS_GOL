CREATE TABLE tb_schedule (
    id_schedule INT AUTO_INCREMENT PRIMARY KEY,
    day_of_week ENUM('SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY') NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    training_id INT,
    FOREIGN KEY (training_id) REFERENCES tb_training(id_training)

);
