-- Create table for state information
use national_geograhy ;

DROP TABLE IF EXISTS state_info;

CREATE TABLE state_info (
    state_id INT PRIMARY KEY AUTO_INCREMENT,
    state_name VARCHAR(100) NOT NULL,
    state_code VARCHAR(10) NOT NULL,
    state_map_image BLOB,
    state_capital VARCHAR(100) NOT NULL,
    primary_language VARCHAR(50),
    secondary_language VARCHAR(50),
    population INT,
    area INT,
    formation_day DATE,
    first_governor VARCHAR(100),
    current_governor VARCHAR(100),
    first_chief_minister VARCHAR(100),
    current_chief_minister VARCHAR(100),
    number_of_districts INT,
    major_cities VARCHAR(255),
    country_id INT NOT NULL,
    FOREIGN KEY (country_id) REFERENCES country_info(country_id)
)AUTO_INCREMENT = 200;

select * from state_info;
