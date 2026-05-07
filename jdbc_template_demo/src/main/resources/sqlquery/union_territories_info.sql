-- Create table for union territories information
use national_geograhy ;

DROP TABLE IF EXISTS union_territories_info;

CREATE TABLE union_territories_info (
    union_territory_id INT PRIMARY KEY AUTO_INCREMENT,
    union_territory_name VARCHAR(100) NOT NULL,
    union_territory_code VARCHAR(10) NOT NULL,
    union_territory_map_image BLOB,
    union_territory_capital VARCHAR(100) NOT NULL,
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
)AUTO_INCREMENT = 300;

select * from union_territories_info;
