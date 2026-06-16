-- drop table if exists application_logs;

CREATE TABLE application_logs (
                                  log_id INT AUTO_INCREMENT PRIMARY KEY,
                                  log_date_time TIMESTAMP NOT NULL,
                                  thread_name VARCHAR(255),
                                  log_level VARCHAR(10) NOT NULL,
                                  logger_name VARCHAR(255) NOT NULL,
                                  log_message TEXT NOT NULL,
                                  exception_trace TEXT
)AUTO_INCREMENT = 100;

select * from application_logs ;