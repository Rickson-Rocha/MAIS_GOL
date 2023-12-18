CREATE TABLE tb_coach (
    id_coach INT AUTO_INCREMENT PRIMARY KEY,
    name_coach VARCHAR(100) NOT NULL,
    cpf_coach VARCHAR(14)  Unique NOT NULL,
    phone_coach VARCHAR(15) NOT NULL,
    status_coach ENUM('ACTIVE', 'INACTIVE', 'VACATION') NOT NULL,
    birth_date_coach DATE NOT NULL
);
