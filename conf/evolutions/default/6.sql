
# --- !Ups

create table grupo (
  id                        integer auto_increment not null,
  nome                      varchar(255),
  constraint pk_evento primary key (id))
;

create table banner (
  id                        integer auto_increment not null,
  nome                      varchar(255),
  constraint pk_evento primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table grupo;
drop table banner;

SET FOREIGN_KEY_CHECKS=1;

