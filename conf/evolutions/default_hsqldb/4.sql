# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

alter table evento add column dataDeFim DATE




# --- !Downs

alter table evento drop column dataDeFim

