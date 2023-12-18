CREATE TABLE tb_athlete (
    id_athlete int auto_increment primary key,
    name_athlete varchar(100) not null,
    cpf varchar(14) unique,
    height_athlete float not null,
    weight_athlete float not null,
    status_athletes ENUM('ACTIVE', 'INACTIVE') NOT NULL,
    birth_date_athlete date not null,
    cpf_guardian varchar(14) not null,
    group_id int not null,
    FOREIGN KEY (cpf_guardian) REFERENCES tb_guardian(cpf_guardian),
    FOREIGN KEY (group_id) REFERENCES tb_group(id_group)
);
