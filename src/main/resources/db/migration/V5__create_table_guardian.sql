CREATE TABLE tb_guardian (
    id_guardian int auto_increment primary key,
    name_guardian varchar(100) not null,
    cpf_guardian varchar(14) unique not null,
    guardian_birth_date date not null,
    phone_guardian varchar(15) not null
);