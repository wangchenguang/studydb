--查询是否有重复数据
select a.gift_id,count(a.gift_id)
from gift_log a
group by a.gift_id
having count(*)>1;

--重置表
update gift_num
SET num = (select count(id)
           from gift)
where id = 1;
update gift
set flag = 0
where flag = 1;
truncate gift_log;

select *
from gift_log;
select *
from gift
where flag = 1;
select *
from gift_num;

--查询事务级别 MySQL 8.0
SELECT @@GLOBAL.transaction_isolation, @@transaction_isolation;
