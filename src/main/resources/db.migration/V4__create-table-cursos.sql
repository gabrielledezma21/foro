create table usuarios(

    id                  bigint       not null GENERATED BY DEFAULT AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1),
    nombre              varchar(100) not null,
    categoria           varchar(255) not null,
    activo              boolean,

    primary key (id),

    );