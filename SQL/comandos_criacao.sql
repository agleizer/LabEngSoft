CREATE TABLE rua (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    bairro VARCHAR(255)
);

CREATE TABLE public.calcada (
    id BIGSERIAL PRIMARY KEY,
	rua_id BIGINT,
    nome character varying(255),
    avaliacao_media double precision,
    latitude_ini double precision,
    latitude_fim double precision,
    longitude_ini double precision,
    longitude_fim double precision,
    bairro character varying(255),
    CONSTRAINT fk_rua
        FOREIGN KEY (rua_id)
        REFERENCES rua (id)
        ON DELETE CASCADE
);


CREATE TABLE avaliacao (
    id BIGSERIAL PRIMARY KEY,
	calcada_id BIGINT,
    nota_geral REAL,
    nota_idoso REAL,
    nota_cego REAL,
    nota_cadeirante REAL,
    nota_carrinho REAL,
    data_aval DATE,
    comentario TEXT,
    presenca_piso_tatil BOOLEAN,
    rebaixamento_guia BOOLEAN,
    sem_obstaculos BOOLEAN,
    iluminacao_noturna BOOLEAN,
    CONSTRAINT fk_calcada
        FOREIGN KEY (calcada_id)
        REFERENCES calcada (id)
        ON DELETE CASCADE
);

CREATE TABLE relatorio (
    id BIGSERIAL PRIMARY KEY,
    data_geracao_relatorio DATE,
    area VARCHAR(255),
    autor VARCHAR(255),
    media_notas VARCHAR(255)
);

CREATE TABLE relatorio_avaliacao (
    relatorio_id BIGINT,
    avaliacao_id BIGINT,
    PRIMARY KEY (relatorio_id, avaliacao_id),
    FOREIGN KEY (relatorio_id) REFERENCES relatorio (id) ON DELETE CASCADE,
    FOREIGN KEY (avaliacao_id) REFERENCES avaliacao (id) ON DELETE CASCADE
);

