DROP PROCEDURE IF EXISTS publisher_crud;
DELIMITER $$
CREATE  PROCEDURE publisher_crud(
    in i_publisher_id int ,
    in i_publisher_name varchar(100),
    in i_publisher_code varchar(20),
    in i_publisher_address varchar(255),
    in i_publisher_email varchar(50),
    in i_publisher_phone varchar(60),
    in i_is_deleted char(1),
    in i_operation_type varchar(20),
    out o_count int ,
    out o_message VARCHAR(255)
)
BEGIN
      SET o_count = 0;
      SET o_message='SQL query not executed';
IF i_operation_type ='READ' then
select publisher_id ,publisher_name, publisher_code, publisher_address, publisher_email, publisher_phone
from publisher_detail where publisher_id=i_publisher_id and is_deleted='N';
SET o_message='Record fetched successfully';
  elseif i_operation_type ='READ_ALL' then
select publisher_id ,publisher_name, publisher_code, publisher_address, publisher_email, publisher_phone
from publisher_detail where is_deleted='N';
SET o_message='All records fetched successfully';
  elseif i_operation_type ='UPDATE' then
update publisher_detail
set publisher_name=i_publisher_name,
    publisher_code=i_publisher_code,
    publisher_address=i_publisher_address,
    publisher_email=i_publisher_email,
    publisher_phone=i_publisher_phone
where publisher_id=i_publisher_id;
SET o_message='Record updated successfully';
  elseif i_operation_type ='DELETE' then
update publisher_detail
set is_deleted=i_is_deleted
where publisher_id=i_publisher_id;
SET o_message='Record deleted successfully';
  elseif i_operation_type ='CREATE' then
     insert into publisher_detail (publisher_name, publisher_code, publisher_address, publisher_email, publisher_phone)
     value (i_publisher_name,i_publisher_code,i_publisher_address,i_publisher_email,i_publisher_phone);
      SET o_message='Record created successfully';
   elseif i_operation_type ='SEARCH' then
select publisher_id ,publisher_name, publisher_code, publisher_address, publisher_email, publisher_phone 
from publisher_detail where publisher_name like concat('%',i_publisher_name,'%')
                                         or publisher_code like concat('PUB',i_publisher_code) and is_deleted='N';
SET o_message='Record searched successfully';
   elseif i_operation_type ='COUNT' then
select count(*) into o_count from publisher_detail  where is_deleted='N';
SET o_message='Record count calculated successfully';
END IF;
END$$;
DELIMITER ;
