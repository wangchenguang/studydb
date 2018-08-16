create table gift_log
(
  id         int auto_increment
    primary key,
  gift_id    int                                null,
  mobile     varchar(32)                        not null,
  createtime datetime default CURRENT_TIMESTAMP null
);

