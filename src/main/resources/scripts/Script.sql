CREATE table if not EXISTS exemplos.Pessoa (
	ID INTEGER auto_increment NOT NULL,
	NOME varchar(255) NOT NULL,
	CPF varchar(11) NOT null unique,
	SEXO CHAR (1) not null,
	DATANASCIMENTO DATE NOT NULL,
	TIPO varchar (255) not null,
	CONSTRAINT IDPESSOA_pk PRIMARY KEY (ID)
);

drop table exemplos.pessoa ;

create table if not exists EXEMPLOS.VACINA (
	ID INTERGER auto_increment not null,
	
);

select * from exemplos.Pessoa;

CREATE table if not EXISTS exemplos.Vacina(
	ID INTEGER auto_increment NOT NULL,
	NOME varchar(255) NOT NULL,
	PAIS_ORIGEM varchar(11) NOT null,
	ID_PESQUISADOR integer not null,
	DATA_INICIO_PESQUISA DATE NOT NULL,
	estagio integer NOT NULL,
	CONSTRAINT IDVACINA_pk PRIMARY KEY (ID),
	CONSTRAINT IDPESQUISADOR_FK FOREIGN KEY (ID_PESQUISADOR) references exemplos.PESSOA(ID)	
);

drop table exemplos.Vacina ;

CREATE table if not EXISTS exemplos.Aplicacao_vacina(
	ID INTEGER auto_increment NOT NULL,
	ID_PESSOA integer not null,
	ID_VACINA integer not null,
	DATA_VACINA DATE NOT NULL,
	AVALIACAO INTEGER not null,
	CONSTRAINT IDAPLICACAO_pk PRIMARY KEY (ID),
	CONSTRAINT IDPESSOA_FK FOREIGN KEY (id_pessoa) references exemplos.PESSOA(ID),
	CONSTRAINT IDVACINA_FK FOREIGN KEY (ID_VACINA) references exemplos.Vacina(ID)	
);

drop table exemplos.aplicacao_vacina ;

