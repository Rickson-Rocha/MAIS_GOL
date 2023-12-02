CREATE TABLE tb_address (
    id_address int auto_increment primary key,
    street_address varchar(255) not null,
    number_address int not null,
    city_address varchar(100) not null,
    state_address varchar(2) not null,
    neighborhood__address varchar(255) not null,
    cep_address VARCHAR(9) NOT NULL UNIQUE,
    block_address VARCHAR(50),
    floor_address varchar(3)
);