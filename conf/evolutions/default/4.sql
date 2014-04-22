# --- !Ups

alter table evento add aprovado tinyint default 0




# --- !Downs

alter table evento drop column aprovado;

