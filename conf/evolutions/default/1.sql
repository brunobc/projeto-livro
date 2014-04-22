
# --- !Ups

create table evento (
  id                        integer auto_increment not null,
  email_para_contato        varchar(255),
  estado                    varchar(16),
  descricao                 text,
  site                      varchar(255),
  twitter                   varchar(255),
  nome                      varchar(255),
  constraint ck_evento_estado check (estado in ('ACRE','ALAGOAS','AMAZONAS','BAHIA','CEARA','DISTRITO_FEDERAL','ESPIRITO_SANTO','GOIAS','MINAS_GERAIS','MATO_GROSSO','MATO_GROSSO_SUL','PARA','PARAIBA','PERNAMBUCO','PIAUI','PARANA','RIO_DE_JANEIRO','RONDONIA','RIO_GRANDE_SUL','RORAIMA','SANTA_CATARINA','SERGIPE','SAO_PAULO','TOCANTINS')),
  constraint pk_evento primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table evento;

SET FOREIGN_KEY_CHECKS=1;

