DROP PROCEDURE IF EXISTS admin_crud;
DELIMITER $$
CREATE  PROCEDURE admin_crud(
    in i_admin_id int ,
    in i_admin_name varchar(100),
    in i_admin_code varchar(20),
    in i_admin_address varchar(255),
    in i_admin_email varchar(50),
    in i_admin_phone varchar(60),
    in i_admin_salary int,
    in i_is_deleted char(1),
    in i_operation_type varchar(20),
    out o_count int ,
    out o_message VARCHAR(255)
)
BEGIN
      SET o_count = 0;
      SET o_message='SQL query not executed';
IF i_operation_type ='READ' then
select admin_id ,admin_name, admin_code, admin_address, admin_email,admin_phone,admin_salary
from admin_detail where admin_id=i_admin_id and is_deleted='N';
SET o_message='Record fetched successfully';
  elseif i_operation_type ='READ_ALL' then
select admin_id ,admin_name, admin_code, admin_address, admin_email,admin_phone,admin_salary
from admin_detail where is_deleted='N';
SET o_message='All records fetched successfully';
  elseif i_operation_type ='UPDATE' then
update admin_detail
set admin_name=i_admin_name,
    admin_code=i_admin_code,
    admin_address=i_admin_address,
    admin_email=i_admin_email,
    admin_phone=i_admin_phone,
    admin_salary=i_admin_salary
where admin_id=i_admin_id;
SET o_message='Record updated successfully';
  elseif i_operation_type ='DELETE' then
update admin_detail
set is_deleted=i_is_deleted
where admin_id=i_admin_id;
SET o_message='Record deleted successfully';
  elseif i_operation_type ='CREATE' then
     insert into admin_detail (admin_name, admin_code,admin_address,admin_email,admin_phone,admin_salary)
     value (i_admin_name,i_admin_code,i_admin_address,i_admin_email,i_admin_phone,i_admin_salary);
      SET o_message='Record created successfully';
   elseif i_operation_type ='SEARCH' then
select admin_id ,admin_name, admin_code, admin_address, admin_email,admin_phone,
       admin_salary from admin_detail where admin_name like concat('%',i_admin_name,'%')
                                         or admin_code like concat('ADM',i_admin_code) and is_deleted='N';
SET o_message='Record searched successfully';
   elseif i_operation_type ='COUNT' then
select count(*) into o_count from admin_detail  where is_deleted='N';
SET o_message='Record count calculated successfully';
END IF;
END$$;
DELIMITER ;
