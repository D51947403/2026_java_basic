create schema library_db;
use  library_db;
-- create author table
drop table if exists author_detail;
create table author_detail(
                              author_id int primary key auto_increment,
                              author_name varchar(100),
                              author_code varchar(20),
                              author_address varchar(255),
                              author_dob  date,
                              author_email varchar(50),
                              author_education varchar (60),
                              author_university varchar(255)
)AUTO_INCREMENT = 1000 ;

-- Insert 10 sample author records
INSERT INTO author_detail (author_name, author_code, author_address, author_dob, author_email, author_education, author_university) VALUES
('J.K. Rowling', 'JKR001', 'Edinburgh, Scotland', '1965-07-31', 'jk.rowling@example.com', 'BA in French and Classics', 'University of Exeter'),
('Stephen King', 'SKK002', 'Bangor, Maine, USA', '1947-09-21', 'stephen.king@example.com', 'B.A. in English', 'University of Maine'),
('Agatha Christie', 'ACH003', 'Torquay, England', '1890-09-15', 'agatha.christie@example.com', 'Home Education', 'N/A'),
('George Orwell', 'GEO004', 'Motihari, India', '1903-06-25', 'george.orwell@example.com', 'Eton Scholarship', 'Eton College'),
('Jane Austen', 'JAU005', 'Steventon, England', '1775-12-16', 'jane.austen@example.com', 'Home Education', 'N/A'),
('Mark Twain', 'MTW006', 'Florida, Missouri, USA', '1835-11-30', 'mark.twain@example.com', 'Apprenticeship', 'N/A'),
('Virginia Woolf', 'VWF007', 'London, England', '1882-01-25', 'virginia.woolf@example.com', 'Home Education', 'Kings College London'),
('Ernest Hemingway', 'EHM008', 'Oak Park, Illinois, USA', '1899-07-21', 'ernest.hemingway@example.com', 'High School Diploma', 'Oak Park High School'),
('Maya Angelou', 'MAG009', 'St. Louis, Missouri, USA', '1928-04-04', 'maya.angelou@example.com', 'Various Studies', 'Various Institutions'),
('Toni Morrison', 'TMO010', 'Lorain, Ohio, USA', '1931-02-18', 'toni.morrison@example.com', 'M.A. in English', 'Cornell University');
-- select all authors
select * from author_detail;
-- create publisher table
drop table if exists publisher_detail;
create table publisher_detail(
                                 publisher_id int primary key auto_increment,
                                 publisher_name varchar(100),
                                 publisher_code varchar(20),
                                 publisher_address varchar(255),
                                 publisher_email varchar(50),
                                 publisher_phone varchar (60)
)AUTO_INCREMENT = 2000 ;
-- Insert 10 sample publisher records
INSERT INTO publisher_detail (publisher_name, publisher_code, publisher_address, publisher_email, publisher_phone) VALUES
('Penguin Random House', 'PRH001', '1745 Broadway, New York, NY 10019', 'contact@penguinrandomhouse.com', '+1-212-782-9000'),
('HarperCollins Publishers', 'HCP002', '195 Broadway, New York, NY 10007', 'info@harpercollins.com', '+1-212-207-7000'),
('Simon & Schuster', 'SSS003', '1230 Avenue of the Americas, New York, NY 10020', 'contact@simonandschuster.com', '+1-212-698-7000'),
('Hachette Book Group', 'HBG004', '1271 Avenue of the Americas, New York, NY 10020', 'info@hbg.com', '+1-212-764-5000'),
('Macmillan Publishers', 'MAC005', '175 Fifth Avenue, New York, NY 10010', 'contact@macmillan.com', '+1-646-307-5000'),
('Scholastic Corporation', 'SCH006', '557 Broadway, New York, NY 10012', 'info@scholastic.com', '+1-212-343-6100'),
('Wiley Publishing', 'WLP007', '111 River Street, Hoboken, NJ 07030', 'contact@wiley.com', '+1-201-748-6000'),
('Oxford University Press', 'OUP008', '198 Madison Avenue, New York, NY 10016', 'info@oup.com', '+1-212-726-6000'),
('Cambridge University Press', 'CUP009', '32 Avenue of the Americas, New York, NY 10013', 'info@cambridge.org', '+1-212-924-3900'),
('Bloomsbury Publishing', 'BLP010', '1385 Broadway, New York, NY 10018', 'contact@bloomsbury.com', '+1-646-307-5065');

-- select all publishers
select * from publisher_detail ;

-- create book table with foreign keys
drop table if exists book_detail;
create table book_detail(
                         book_id int primary key auto_increment,
                         book_title varchar(255) not null,
                         isbn varchar(20) unique,
                         author_id int,
                         publisher_id int,
                         publication_date date,
                         price decimal(10,2),
                         genre varchar(50),
                         pages int,
                         language varchar(30),
                         foreign key (author_id) references author_detail(author_id),
                         foreign key (publisher_id) references publisher_detail(publisher_id)
)AUTO_INCREMENT = 3000 ;

-- Insert 10 sample book records
INSERT INTO book_detail (book_title, isbn, author_id, publisher_id, publication_date, price, genre, pages, language) VALUES
('Harry Potter and the Sorcerer''s Stone', '978-0-439-70818-8', 1000, 2000, '1997-06-26', 12.99, 'Fantasy', 309, 'English'),
('The Shining', '978-0-385-12167-5', 1001, 2001, '1977-01-28', 15.99, 'Horror', 447, 'English'),
('Murder on the Orient Express', '978-0-00-711931-8', 1002, 2002, '1934-01-01', 13.99, 'Mystery', 256, 'English'),
('1984', '978-0-452-28423-4', 1003, 2003, '1949-06-08', 14.99, 'Dystopian', 328, 'English'),
('Pride and Prejudice', '978-0-14-143951-8', 1004, 2004, '1813-01-28', 9.99, 'Romance', 432, 'English'),
('The Adventures of Tom Sawyer', '978-0-14-303945-5', 1005, 2005, '1876-01-01', 11.99, 'Adventure', 274, 'English'),
('To the Lighthouse', '978-0-15-690739-8', 1006, 2006, '1927-05-05', 13.50, 'Modernist', 209, 'English'),
('The Old Man and the Sea', '978-0-684-80391-9', 1007, 2007, '1952-09-01', 10.99, 'Fiction', 127, 'English'),
('I Know Why the Caged Bird Sings', '978-0-345-50555-3', 1008, 2008, '1969-01-01', 14.99, 'Autobiography', 369, 'English'),
('Beloved', '978-0-452-26446-3', 1009, 2009, '1987-09-01', 15.50, 'Fiction', 324, 'English');

-- select all books
select * from book_detail ;
