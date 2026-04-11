DROP PROCEDURE IF EXISTS author_crud;
DELIMITER $$
CREATE  PROCEDURE author_crud(
    in i_author_id int ,
    in i_author_name varchar(100),
    in i_author_code varchar(20),
    in i_author_address varchar(255),
    in i_author_dob date,
    in i_author_email varchar(50),
    in i_author_education varchar(60),
    in i_author_university varchar(255),
    in i_is_deleted char(1),
    in i_operation_type varchar(20),
    out o_count int ,
    out o_message VARCHAR(255)
)
BEGIN
      SET o_count = 0;
      SET o_message='SQL query not executed';
IF i_operation_type ='READ' then
select author_id ,author_name, author_code, author_address, author_dob, author_email, author_education, author_university
from author_detail where author_id=i_author_id and is_deleted='N';
SET o_message='Record fetched successfully';
  elseif i_operation_type ='READ_ALL' then
select author_id ,author_name, author_code, author_address, author_dob, author_email, author_education, author_university
from author_detail where is_deleted='N';
SET o_message='All records fetched successfully';
  elseif i_operation_type ='UPDATE' then
update author_detail
set author_name=i_author_name,
    author_code=i_author_code,
    author_address=i_author_address,
    author_dob=i_author_dob,
    author_email=i_author_email,
    author_education=i_author_education,
    author_university=i_author_university
where author_id=i_author_id;
SET o_message='Record updated successfully';
  elseif i_operation_type ='DELETE' then
update author_detail
set is_deleted=i_is_deleted
where author_id=i_author_id;
SET o_message='Record deleted successfully';
  elseif i_operation_type ='CREATE' then
     insert into author_detail (author_name, author_code, author_address, author_dob, author_email, author_education, author_university)
     value (i_author_name,i_author_code,i_author_address,i_author_dob,i_author_email,i_author_education,i_author_university);
      SET o_message='Record created successfully';
   elseif i_operation_type ='SEARCH' then
select author_id ,author_name, author_code, author_address, author_dob, author_email, author_education, author_university 
from author_detail where author_name like concat('%',i_author_name,'%')
                                         or author_code like concat('AUT',i_author_code) and is_deleted='N';
SET o_message='Record searched successfully';
   elseif i_operation_type ='COUNT' then
select count(*) into o_count from author_detail  where is_deleted='N';
SET o_message='Record count calculated successfully';
END IF;
END$$;
DELIMITER ;
