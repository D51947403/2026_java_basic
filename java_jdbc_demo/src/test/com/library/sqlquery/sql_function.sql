-- Create custom function to count all available book stock
DELIMITER //
CREATE FUNCTION count_total_available_stock()
    RETURNS INT
    READS SQL DATA
    DETERMINISTIC
BEGIN
    DECLARE total_stock INT DEFAULT 0;

SELECT COALESCE(SUM(stock_count), 0)
INTO total_stock
FROM library_stock_detail
WHERE is_deleted = 'N';

RETURN total_stock;
END //
DELIMITER ;

-- Test the function
SELECT count_total_available_stock() AS total_available_books;

-- Create function to count stock for specific book
DELIMITER //
CREATE FUNCTION count_book_stock(book_id_param INT)
    RETURNS INT
    READS SQL DATA
    DETERMINISTIC
BEGIN
    DECLARE book_stock INT DEFAULT 0;

SELECT COALESCE(stock_count, 0)
INTO book_stock
FROM library_stock_detail
WHERE book_id = book_id_param AND is_deleted = 'N';

RETURN book_stock;
END //
DELIMITER ;

-- Test the specific book stock function
SELECT count_book_stock(3000) AS book_3000_stock;