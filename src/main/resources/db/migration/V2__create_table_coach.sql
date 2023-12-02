CREATE TAB tb_coach (
    id_coach int auto_increment primary key,
    name_coach varchar(100) not null,
    cpf_coach varchar(14) not null,
    phone_coach varchar(15) not null,
    status_coach boolean not null,
    birth_date_coach Date not null,
);