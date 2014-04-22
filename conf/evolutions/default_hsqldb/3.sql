# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

alter table evento add column caminhoImagem varchar(255)




# --- !Downs

alter table evento drop column caminhoImagem

