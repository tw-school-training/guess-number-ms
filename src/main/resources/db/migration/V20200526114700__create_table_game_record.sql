create table game_record(
  id varchar(64) not null primary key,
  game_round varchar(64) not null,
  left_times int not null,
  is_winning bool default false,
  compare_result varchar(4) not null,
  user_guess varchar(4) not null
);