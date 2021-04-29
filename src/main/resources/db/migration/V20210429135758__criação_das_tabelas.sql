create table vendedor
(
    codigo int          not null,
    nome   varchar(255) not null,
    constraint vendedor_pk primary key (codigo)
);

create table produto
(
    codigo int          not null,
    nome   varchar(255) not null,
    constraint produto_pk primary key (codigo)
);

create table cliente
(
    codigo          int          not null,
    nome            varchar(255) not null,
    vendedor_codigo int          not null,
    constraint cliente_pk primary key (codigo),
    constraint cliente_vendedor_codigo_fk foreign key (vendedor_codigo)
        references vendedor (codigo)
);


