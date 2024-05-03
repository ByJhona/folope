create table usuario(
    id SERIAL not null,
    nome varchar(100),
    email varchar(100) unique,
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
create table relac_sala_match_filmes(
    id SERIAL not null,
    id_sala_match integer not null,
    id_filme integer not null,

    primary key(id),
    FOREIGN KEY (id_sala_match) REFERENCES sala_match(id) ON DELETE CASCADE
);

create table relac_sala_match_filme_curtido(
    id SERIAL not null,
    id_sala_match integer not null,
    id_usuario integer not null,
    id_filme integer not null,

    primary key(id),
    FOREIGN KEY (id_sala_match) REFERENCES sala_match(id) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

create table relac_usuario_genero_curtido(
    id SERIAL not null,
    id_usuario integer not null,
    id_genero integer not null,
    primary key(id),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

create table relac_usuario_filme_curtido(
    id SERIAL not null,
    id_usuario integer not null,
    id_filme integer not null,
    primary key(id),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);


