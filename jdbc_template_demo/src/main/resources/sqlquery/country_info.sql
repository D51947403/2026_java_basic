-- Create table for country information
create schema national_geograhy;

use national_geograhy ;

DROP TABLE IF EXISTS country_info;

CREATE TABLE country_info (
    country_id INT PRIMARY KEY AUTO_INCREMENT ,
    country_name VARCHAR(100) NOT NULL,
    country_code VARCHAR(10) NOT NULL,
    country_map_image BLOB,
    country_flag VARCHAR(255),
    country_flag_image BLOB,
    country_currency VARCHAR(50),
    country_language VARCHAR(100),
    country_continent VARCHAR(50),
    country_capital VARCHAR(100),
    country_population INT,
    country_area DECIMAL(10, 2),
    country_timezone VARCHAR(50),
    country_calling_code VARCHAR(20),
    independence_day DATE,
    republic_day DATE,
    national_symbol VARCHAR(100),
    national_animal VARCHAR(100),
    first_president VARCHAR(100),
    first_prime_minister VARCHAR(100),
    current_president VARCHAR(100),
    current_prime_minister VARCHAR(100)
)AUTO_INCREMENT = 100;

-- Insert data for India
INSERT INTO country_info (
    country_name, 
    country_code, 
    country_flag, 
    country_currency, 
    country_language, 
    country_continent, 
    country_capital, 
    country_population, 
    country_area, 
    country_timezone, 
    country_calling_code, 
    independence_day, 
    republic_day, 
    national_symbol, 
    national_animal, 
    first_president, 
    first_prime_minister, 
    current_president, 
    current_prime_minister
) VALUES (
    'India', 
    'IND', 
    '🇮🇳', 
    'Indian Rupee', 
    'Hindi',
    'Asia', 
    'New Delhi', 
    1428627663, 
    3287263.00, 
    'UTC+5:30', 
    '+91', 
    '1947-08-15', 
    '1950-01-26', 
    'Ashoka Chakra', 
    'Bengal Tiger', 
    'Dr. Rajendra Prasad', 
    'Jawaharlal Nehru', 
    'Droupadi Murmu', 
    'Narendra Modi'
);

select * from country_info;

