# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table evento (
  id                        integer not null,
  email_para_contato        varchar(255),
  estado                    varchar(16),
  descricao                 text,
  site                      varchar(255),
  twitter                   varchar(255),
  nome                      varchar(255),
  data_de_inicio            timestamp,
  data_de_fim               timestamp,
  caminho_imagem            varchar(255),
  constraint ck_evento_estado check (estado in ('ACRE','ALAGOAS','AMAZONAS','BAHIA','CEARA','DISTRITO_FEDERAL','ESPIRITO_SANTO','GOIAS','MINAS_GERAIS','MATO_GROSSO','MATO_GROSSO_SUL','PARA','PARAIBA','PERNAMBUCO','PIAUI','PARANA','RIO_DE_JANEIRO','RONDONIA','RIO_GRANDE_SUL','RORAIMA','SANTA_CATARINA','SERGIPE','SAO_PAULO','TOCANTINS')),
  constraint pk_evento primary key (id))
;

create sequence evento_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists evento;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists evento_seq;

