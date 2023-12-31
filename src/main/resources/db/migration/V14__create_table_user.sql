CREATE table tb_user(
    id_user INT not null AUTO_INCREMENT PRIMARY KEY,
    user_name varchar(100) not null unique,
    user_password varchar(100) not null,
    role_app varchar(100) not null
)