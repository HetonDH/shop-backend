create table if not exists products (
    id           bigserial primary key,
    type         varchar(16) not null default 'SIMPLE', -- SIMPLE / VARIANT / SERVICE
    title        varchar(255) not null,
    description  text,
    price        numeric(12,2) not null check (price >= 0),
    sku          varchar(64) unique,
    status       varchar(16) not null default 'INACTIVE', -- ACTIVE / INACTIVE
    created_at   timestamptz not null default now(),
    updated_at   timestamptz not null default now()
    );

create table if not exists inventory (
    product_id   bigint primary key references products(id) on delete cascade,
    stock        int not null default 0 check (stock >= 0)
    );
