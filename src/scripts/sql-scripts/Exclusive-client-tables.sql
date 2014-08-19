CREATE TABLE `S_auth` (
  `partner_id` varchar(4) NOT NULL,
  `api_key` varchar(64) NOT NULL,
  `comments` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`partner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `S_session` (
  `session_key` varchar(32) NOT NULL,
  `session_secret` varchar(32) NOT NULL,
  `uid` int(10) unsigned NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`session_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `S_user_favourite_stores` (
  `uid` int(10) unsigned NOT NULL,
  `fav_stores` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/**Triggers*/
CREATE TRIGGER session_last_update_timestamp BEFORE INSERT ON S_session 
FOR EACH ROW
SET NEW.last_update_date = NOW();

CREATE TRIGGER session_last_update_timestamp1 BEFORE UPDATE ON S_session 
FOR EACH ROW
SET NEW.last_update_date = NOW();

CREATE TRIGGER stores_last_update_timestamp BEFORE INSERT ON stores 
FOR EACH ROW
SET NEW.last_update_date = NOW();

CREATE TRIGGER stores_last_update_timestamp2 BEFORE UPDATE ON stores 
FOR EACH ROW
SET NEW.last_update_date = NOW();
/**Altering stores table*/
alter table stores add(creation_date timestamp DEFAULT CURRENT_TIMESTAMP, last_update_date timestamp);
