
 create table central_bank_exch_rate (
        id  bigserial not null,
        local_date date not null,
        primary key (id)
   );

 create table central_bank_exch_rate_currencies (
        central_bank_exch_rate_id int8 not null,
        currencies_id int8 not null
  );

 create table currency (
        id  bigserial not null,
        char_code varchar(255) not null,
        denomination int4 not null,
        name varchar(255) not null,
        num_code varchar(255) not null,
        value numeric(19, 4) not null,
        primary key (id)
);

 create table user_role (
        user_id int8 not null,
        roles varchar(255)
);

 create table usr (
        id  bigserial not null,
        username varchar(255) not null,
        password varchar(255) not null,
        primary key (id)
);

 alter table central_bank_exch_rate
        add constraint central_bank_local_date_uk unique (local_date);

 alter table central_bank_exch_rate_currencies
        add constraint central_bank_currencies_currencies_id_uk unique (currencies_id);

 alter table usr
        add constraint usr_username_uk unique (username);

 alter table central_bank_exch_rate_currencies
        add constraint currencies_id_fk
        foreign key (currencies_id) references currency;

 alter table central_bank_exch_rate_currencies
        add constraint central_bank_id_fk
        foreign key (central_bank_exch_rate_id) references central_bank_exch_rate;

 alter table user_role
        add constraint user_id_fk
        foreign key (user_id) references usr;