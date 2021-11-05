create table perfis
(
    id   bigint       not null auto_increment,
    nome varchar(100) not null,
    primary key (id)
);

create table perfis_usuarios
(
    usuario_id bigint not null,
    perfil_id  bigint not null,
    primary key (usuario_id, perfil_id),
    foreign key (usuario_id) references usuarios(id),
    foreign key (perfil_id) references perfis(id)
);

insert into perfis values(1, 'ROLE_ADMIN');
insert into perfis values(2, 'ROLE_COMUM');