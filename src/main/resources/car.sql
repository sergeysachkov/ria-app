CREATE SCHEMA `cars` ;

 create table cars (
     id bigint not null auto_increment,
     body varchar(255),
     doors smallint not null,
     model varchar(255),
     year datetime,
     engine_id bigint not null,
     primary key (id),
     unique (engine_id)
)

create table engine (
     id bigint not null auto_increment,
     cylinders smallint not null,
     fuel varchar(255),
     size varchar(255),
     transmission varchar(255),
     primary key (id)
 )

alter table cars
      add index cars_engine (id),
      add constraint cars_engine
      foreign key (id)
      references engine (id)

