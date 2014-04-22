# --- !Ups

alter table evento add caminho_imagem varchar(255);




# --- !Downs

alter table evento drop column caminho_imagem;

