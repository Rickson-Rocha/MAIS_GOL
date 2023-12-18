CREATE TABLE tb_group (
    id_group int auto_increment primary key,
    name_group varchar(50) not null UNIQUE,
    description_group varchar(100) not null,
    status_group ENUM('ACTIVE', 'INACTIVE', 'VACATION') NOT NULL
);
