create table usuario(
    id SERIAL not null,
    nome varchar(100),
    email varchar(100),
    senha varchar(100),

    primary key(id)
);

create table sala_match(
    id SERIAL not null,
    status_solicitacao varchar(100) not null,
    id_anfitriao integer not null,
    id_hospede integer not null,

    primary key(id),
    FOREIGN KEY (id_anfitriao) REFERENCES usuario(id),
    FOREIGN KEY (id_hospede) REFERENCES usuario(id)
);
create table relac_sala_filme_match(
    id SERIAL not null,
    id_sala_match integer not null,
    id_filme integer not null,

    primary key(id),
    FOREIGN KEY (id_sala_match) REFERENCES sala_match(id) ON DELETE CASCADE
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
(1, 'Felipe', 'felipe@icloud.com', '12345678');

INSERT INTO sala_match (id, status_solicitacao, id_anfitriao, id_hospede)
VALUES
(0, 'Aceito', 0, 1),
(1, 'Pendente', 1, 0);
