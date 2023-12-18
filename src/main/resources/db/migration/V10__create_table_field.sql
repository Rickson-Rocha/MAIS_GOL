CREATE TABLE tb_field (
    id_field int auto_increment primary key,
    number_field int not null,
    status_field ENUM('ACTIVE', 'INACTIVE') NOT NULL,
    description_field varchar(100) not null
);