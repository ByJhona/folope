create table usuario(
    id SERIAL not null,
    nome varchar(100),
    email varchar(100),
    senha varchar(100),

    primary key(id)
);
create table relac_usuario_genero_curtido(
    id SERIAL not null,
    id_usuario integer not null,
    id_genero integer not null,
    primary key(id)
);

INSERT INTO usuario (id, nome, email, senha)
VALUES
(0, 'Jhonatan', 'jhonatan@gmail.com', '12345678'),
(1, 'Felipe', 'felipe@icloud.com', '12345678')

