CREATE TABLE tb_training (
    id_training int auto_increment primary key,
    id_group int,
    id_field int,
    id_coach int,
    FOREIGN KEY (id_group) REFERENCES tb_group(id_group),
    FOREIGN KEY (id_coach) REFERENCES tb_coach(id_coach),
    FOREIGN KEY (id_field) REFERENCES tb_field(id_field)
);