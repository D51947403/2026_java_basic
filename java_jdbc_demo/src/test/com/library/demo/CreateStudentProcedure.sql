-- MySQL Stored Procedure to Insert Student with Date
-- This procedure inserts a new student record into the student_detail table

-- Drop procedure if it exists
DROP PROCEDURE IF EXISTS InsertStudentWithDate;

DELIMITER //

CREATE PROCEDURE InsertStudentWithDate(
    IN p_student_name VARCHAR(100),
    IN p_student_email VARCHAR(100),
    IN p_updated_date DATE
)
BEGIN
    DECLARE v_student_id INT;
    
    -- Insert the student record
    INSERT INTO student_detail (student_name, student_email, updated_date)
    VALUES (p_student_name, p_student_email, p_updated_date);
    
    -- Get the generated student ID
    SET v_student_id = LAST_INSERT_ID();
    
    -- Return the generated ID (you can use this in your Java code)
    SELECT v_student_id AS student_id;
    
    -- Optional: Return a success message
    SELECT CONCAT('Student inserted successfully with ID: ', v_student_id) AS message;
END //

DELIMITER ;

-- Alternative procedure with error handling
DELIMITER //

CREATE PROCEDURE InsertStudentWithDateSafe(
    IN p_student_name VARCHAR(100),
    IN p_student_email VARCHAR(100),
    IN p_updated_date DATE,
    OUT p_result INT,
    OUT p_message VARCHAR(255)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
        p_message = MESSAGE_TEXT;
        SET p_result = -1;
    END;
    
    -- Check if email already exists
    IF EXISTS (SELECT 1 FROM student_detail WHERE student_email = p_student_email) THEN
        SET p_result = 0;
        SET p_message = 'Email already exists';
    ELSE
        -- Insert the student record
        INSERT INTO student_detail (student_name, student_email, updated_date)
        VALUES (p_student_name, p_student_email, p_updated_date);
        
        SET p_result = LAST_INSERT_ID();
        SET p_message = 'Student inserted successfully';
    END IF;
END //

DELIMITER ;

-- Procedure to get students by date range
DELIMITER //

CREATE PROCEDURE GetStudentsByDateRange(
    IN p_start_date DATE,
    IN p_end_date DATE
)
BEGIN
    SELECT 
        student_id,
        student_name,
        student_email,
        updated_date
    FROM student_detail
    WHERE updated_date BETWEEN p_start_date AND p_end_date
    ORDER BY updated_date DESC;
END //

DELIMITER ;

-- Procedure to update student information
DELIMITER //

CREATE PROCEDURE UpdateStudent(
    IN p_student_id INT,
    IN p_student_name VARCHAR(100),
    IN p_student_email VARCHAR(100),
    IN p_updated_date DATE,
    OUT p_result INT,
    OUT p_message VARCHAR(255)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
        p_message = MESSAGE_TEXT;
        SET p_result = -1;
    END;
    
    -- Check if student exists
    IF NOT EXISTS (SELECT 1 FROM student_detail WHERE student_id = p_student_id) THEN
        SET p_result = 0;
        SET p_message = 'Student not found';
    ELSE
        -- Update the student record
        UPDATE student_detail 
        SET 
            student_name = p_student_name,
            student_email = p_student_email,
            updated_date = p_updated_date
        WHERE student_id = p_student_id;
        
        SET p_result = ROW_COUNT();
        SET p_message = 'Student updated successfully';
    END IF;
END //

DELIMITER ;

-- Procedure to delete a student
DELIMITER //

CREATE PROCEDURE DeleteStudent(
    IN p_student_id INT,
    OUT p_result INT,
    OUT p_message VARCHAR(255)
)
BEGIN
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
        p_message = MESSAGE_TEXT;
        SET p_result = -1;
    END;
    
    -- Check if student exists
    IF NOT EXISTS (SELECT 1 FROM student_detail WHERE student_id = p_student_id) THEN
        SET p_result = 0;
        SET p_message = 'Student not found';
    ELSE
        -- Delete the student record
        DELETE FROM student_detail WHERE student_id = p_student_id;
        
        SET p_result = ROW_COUNT();
        SET p_message = 'Student deleted successfully';
    END IF;
END //

DELIMITER ;

-- Usage examples:
-- CALL InsertStudentWithDate('John Doe', 'john@example.com', CURDATE());
-- CALL InsertStudentWithDateSafe('Jane Smith', 'jane@example.com', '2023-12-25', @result, @message);
-- SELECT @result, @message;
-- CALL GetStudentsByDateRange('2023-01-01', '2023-12-31');
-- CALL UpdateStudent(1, 'Updated Name', 'updated@example.com', CURDATE(), @result, @message);
-- SELECT @result, @message;
-- CALL DeleteStudent(1, @result, @message);
-- SELECT @result, @message;
