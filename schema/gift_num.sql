create table gift_num
(
  id         int auto_increment
    primary key,
  type       varchar(255)                       null,
  num        int                                null,
  start_time datetime default CURRENT_TIMESTAMP null,
  end_time   datetime default CURRENT_TIMESTAMP null
);

