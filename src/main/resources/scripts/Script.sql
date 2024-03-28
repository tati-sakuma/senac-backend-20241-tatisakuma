CREATE table if not EXISTS exemplos.Pessoa (
	ID INTEGER auto_increment NOT NULL,
	NOME varchar(255) NOT NULL,
	CPF varchar(11) NOT null unique,
	SEXO CHAR (1) not null,
	DATANASCIMENTO DATE NOT NULL,
	TIPO varchar (255) not null,
	ID_PAIS INTEGER not null,
	CONSTRAINT IDPESSOA_PK PRIMARY KEY (ID),
	CONSTRAINT IDPAIS_FK foreign key (ID_PAIS) references EXEMPLOS.PAIS(ID)
);

drop table exemplos.pessoa ;

INSERT INTO exemplos.pessoa
(nome, cpf, sexo, data_nascimento, tipo)
VALUES('Edson Arantes do Nascimento', '01012301010', 'M', '1940-10-23', 'PESQUISADOR'); 

INSERT INTO exemplos.pessoa
(nome, cpf, sexo, data_nascimento, tipo)
VALUES('Marcos Evangelista de Morais', '2220002255', 'M', '1970-06-07', 'VOLUNTARIO');

INSERT INTO exemplos.pessoa
(nome, cpf, sexo, data_nascimento, tipo)
VALUES('Manoel Francisco dos Santos', '77711122277', 'M', '1933-10-28', 'PUBLICO_GERAL');

select * from exemplos.Pessoa;

CREATE table if not EXISTS exemplos.Vacina(
	ID INTEGER auto_increment NOT NULL,
	NOME varchar(255) NOT NULL,
	ID_PAIS INTEGER NOT NULL,
	ID_PESQUISADOR integer NOT NULL,
	DATA_INICIO_PESQUISA DATE NOT NULL,
	estagio integer NOT null comment '1- Inicial; 2- Teste; 3- Aplicação em massa',
	CONSTRAINT IDVACINA_pk PRIMARY KEY (ID),
	CONSTRAINT IDPESQUISADOR_FK FOREIGN KEY (ID_PESQUISADOR) references exemplos.PESSOA(ID),
	CONSTRAINT IDPAIS2_FK foreign key (ID_PAIS) references EXEMPLOS.PAIS(ID)
);

drop table exemplos.Vacina ;
select * from exemplos.Vacina;
SELECT * FROM exemplos.vacina WHERE id = 4;


CREATE table if not EXISTS exemplos.Aplicacao_vacina(
	ID INTEGER auto_increment NOT NULL,
	ID_PESSOA integer not null,
	ID_VACINA integer not null,
	DATA_VACINA DATE NOT NULL,
	AVALIACAO INTEGER comment '1- Péssimo; 5- Ótimo',
	CONSTRAINT IDAPLICACAO_pk PRIMARY KEY (ID),
	CONSTRAINT IDPESSOA_FK FOREIGN KEY (ID_PESSOA) references exemplos.PESSOA(ID),
	CONSTRAINT IDVACINA_FK FOREIGN KEY (ID_VACINA) references exemplos.Vacina(ID)	
);

drop table exemplos.aplicacao_vacina ;
select * from exemplos.aplicacao_vacina;

create table if not exists exemplos.PAIS (
	ID INTEGER auto_increment NOT NULL,
	NOME varchar(255) not null,
	SIGLA varchar(2) not NULL,
	CONSTRAINT IDPAIS_PK primary key (ID)
);

UPDATE exemplos.aplicacao_vacina SET  AVALIACAO= 3 wHERE ID=2;

UPDATE aplicacao_vacina SET ID_PESSOA=3, ID_VACINA=5, DATA_VACINA= '2023-05-05', AVALIACAO=3 WHERE ID=2

INSERT INTO exemplos.pais
(NOME, SIGLA)
VALUES('Argentina', 'AR');

select * from exemplos.pais p ;

SELECT ID, NOME, SIGLA
FROM exemplos.pais where NOME = 'Brasil' or SIGLA = 'BR';

drop table exemplos.PAIS


