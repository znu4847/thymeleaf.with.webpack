SET search_path = hwinfo_log, "znu", public; -- For current session only

drop table log_data;
drop table desc_mst;
drop table log_info;
-- drop table log_group;
drop table hwinfo_log;
drop table soft_info;
drop table hw_setting;

create table soft_info(
  soft_info_id serial primary key
  , name text
  , description text
);

create table hw_setting(
  hw_setting_id serial primary key
  , name text
  , description text
  , registed_date date
);

create table desc_mst(
  desc_mst_id serial primary key
  , name text
  , data_type text
  , unit text
);

create table hwinfo_log(
  hwinfo_log_id serial primary key
  , soft_info_id bigint
  , hw_setting_id bigint
  , name text
  , description text
  , registed_date date
  , updated_date date
  , foreign key (soft_info_id)
  references soft_info(soft_info_id)
  , foreign key (hw_setting_id)
  references hw_setting(hw_setting_id)
);

create table log_info(
  log_info_id serial primary key
  , hwinfo_log_id bigint
  , file_name text
  , foreign key (hwinfo_log_id)
  references hwinfo_log(hwinfo_log_id)
);

create table log_data(
  log_info_id bigint
  , row_no bigint
  , desc_mst_id bigint
  , value text
  , foreign key (log_info_id)
  references log_info(log_info_id)
  , foreign key (desc_mst_id)
  references desc_mst(desc_mst_id)
  , primary key (log_info_id, row_no, desc_mst_id )
);


-- create table log_group(
--   hwinfo_log_id bigint
--   , seq int
-- );

select * from log_info;