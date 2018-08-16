create table gift
(
  id         int auto_increment
    primary key,
  `key`      varchar(255)                       null,
  createtime datetime default CURRENT_TIMESTAMP null,
  flag       tinyint default '0'                null,
  constraint gift_key_uindex
  unique (`key`)
);
