create sequence hibernate_sequence;

CREATE TABLE public.usuario(
	username                    varchar(100) NOT NULL,
	password                    varchar(100),
	account_non_expired         boolean NOT NULL,
	account_non_locked          boolean      NOT NULL,
	credentials_non_expired     boolean NOT NULL,
	enabled                     boolean NOT NULL,
	CONSTRAINT usuario_pk PRIMARY KEY (username)
);

CREATE TABLE public.privilegio
(
	id                       bigint NOT NULL,
	nome                     varchar(100) NOT NULL,
	CONSTRAINT privilegio_pk PRIMARY KEY (id)
);

CREATE TABLE public.pessoa
(
    id                      bigint NOT NULL,
    cpf                     varchar(20) NOT NULL,
    nome                    varchar(500) NOT NULL,
    sexo                    varchar(100),
    email                   varchar(500),
    data_nascimento         timestamp NOT NULL,
    naturalidade            varchar(100),
    nacionalidade           varchar(100),
    data_criacao            timestamp NOT NULL,
    data_ATUALIZACAO          varchar(100),
    CONSTRAINT pessoa_pk    PRIMARY KEY (id),
    CONSTRAINT  uk_cpf      UNIQUE (cpf)
);
