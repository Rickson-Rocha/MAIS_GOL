ALTER TABLE tb_address
ADD COLUMN id_coach int,
ADD COLUMN id_guardian int,
ADD CONSTRAINT fk_coach_address FOREIGN KEY (id_coach) REFERENCES tb_coach(id_coach),
ADD CONSTRAINT fk_guardian_address FOREIGN KEY (id_guardian) REFERENCES tb_guardian(id_guardian);