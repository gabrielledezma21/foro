create table respuestas(

    id                  bigint       not null GENERATED BY DEFAULT AS IDENTITY (INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1),
    topico              bigint       not null,
    mensaje             varchar(255) not null,
    fecha_creacion      timestamp    not null,
    usuario_id          bigint       not null,
    solucionado         boolean      not null,

    primary key (id),
    constraint fk_respuestas_usuario_id foreign key (usuario_id) references usuarios(id),
    constraint fk_respuestas_topico_id foreign key (topico_id) references topicos(id)

    );