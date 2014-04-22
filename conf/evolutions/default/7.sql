
# --- !Ups

create table participacao (
  id                        integer auto_increment not null,
  evento_id                 integer not null,
  usuario_email				varchar(255) not null,
  constraint pk_evento primary key (id))
;


# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table participacao;

SET FOREIGN_KEY_CHECKS=1;

