
# --- !Ups

create table usuario (
  email                        varchar(255) not null,
  senha        varchar(255),
  constraint pk_usuario primary key (email))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table usuario;

SET FOREIGN_KEY_CHECKS=1;

