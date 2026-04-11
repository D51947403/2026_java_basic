-- MySQL CONCAT Function Examples
-- Demonstrates various ways to concatenate strings in MySQL

-- Basic CONCAT function
SELECT CONCAT('Hello', ' ', 'World') AS greeting;

-- CONCAT with table columns (using admin_detail table)
SELECT 
    admin_id,
    CONCAT(admin_name, ' (', admin_code, ')') AS admin_with_code,
    CONCAT('Email: ', admin_email, ', Phone: ', admin_phone) AS contact_info
FROM admin_detail 
WHERE is_deleted = 'N';

-- CONCAT_WS (CONCAT With Separator) - more efficient for multiple strings
SELECT 
    admin_id,
    CONCAT_WS(' - ', admin_name, admin_email, admin_phone) AS admin_details,
    CONCAT_WS(', ', admin_name, admin_address, admin_email) AS full_contact
FROM admin_detail 
WHERE is_deleted = 'N';

-- CONCAT with NULL handling
SELECT 
    CONCAT('Name: ', admin_name) AS basic_concat,
    CONCAT('Address: ', admin_address) AS with_address,
    CONCAT('Phone: ', admin_phone, ' (Extension: ', NULL, ')') AS with_null -- NULL will make result NULL
FROM admin_detail 
WHERE admin_id = 1;

-- CONCAT_WS handles NULL values better (ignores NULL parts)
SELECT 
    CONCAT_WS(' | ', admin_name, admin_address, NULL, admin_email) AS safe_concat,
    CONCAT_WS(' ', admin_name, admin_address, admin_email) AS full_info
FROM admin_detail 
WHERE admin_id = 1;

-- CONCAT with numbers and dates
SELECT 
    admin_id,
    CONCAT('Admin ID: ', admin_id, ', Salary: $', admin_salary) AS salary_info,
    CONCAT('Created: ', DATE_FORMAT(NOW(), '%Y-%m-%d %H:%i:%s')) AS formatted_date
FROM admin_detail 
WHERE is_deleted = 'N';

-- Practical examples for reporting
-- Example 1: Full address concatenation
SELECT 
    admin_id,
    admin_name,
    CONCAT(
        COALESCE(admin_address, 'N/A'), ', ',
        COALESCE(admin_email, 'N/A'), ', ',
        COALESCE(admin_phone, 'N/A')
    ) AS complete_address
FROM admin_detail 
WHERE is_deleted = 'N';

-- Example 2: Email formatting
SELECT 
    admin_id,
    admin_name,
    CONCAT(LOWER(REPLACE(admin_name, ' ', '.')), '@company.com') AS formatted_email,
    admin_email AS actual_email
FROM admin_detail 
WHERE is_deleted = 'N';

-- Example 3: Dynamic SQL building (for stored procedures)
SET @sql = CONCAT(
    'SELECT * FROM admin_detail WHERE is_deleted = ''N''',
    CASE 
        WHEN 1 = 1 THEN ' AND admin_salary > 50000'
        ELSE ''
    END
);
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- Example 4: JSON-like string creation
SELECT 
    admin_id,
    CONCAT(
        '{',
        '"id":', admin_id, ',',
        '"name":"', admin_name, '",',
        '"code":"', admin_code, '",',
        '"email":"', admin_email, '",',
        '"salary":', admin_salary,
        '}'
    ) AS admin_json
FROM admin_detail 
WHERE is_deleted = 'N' AND admin_id = 1;

-- Example 5: Conditional concatenation with CASE
SELECT 
    admin_id,
    admin_name,
    admin_salary,
    CONCAT(
        admin_name,
        CASE 
            WHEN admin_salary > 70000 THEN ' (Senior)'
            WHEN admin_salary > 50000 THEN ' (Mid-level)'
            ELSE ' (Junior)'
        END
    ) AS admin_with_level,
    CONCAT_WS(
        ' - ',
        admin_name,
        CASE 
            WHEN admin_salary > 70000 THEN 'High Salary'
            WHEN admin_salary > 50000 THEN 'Medium Salary'
            ELSE 'Standard Salary'
        END,
        admin_email
    ) AS detailed_info
FROM admin_detail 
WHERE is_deleted = 'N';

-- Example 6: String functions combined with CONCAT
SELECT 
    admin_id,
    CONCAT(UPPER(LEFT(admin_name, 1)), LOWER(SUBSTRING(admin_name, 2))) AS proper_name,
    CONCAT('Admin ', admin_name, ' has code ', UPPER(admin_code)) AS formal_intro,
    CONCAT(LENGTH(admin_name), ' characters in name: ', admin_name) AS name_length_info
FROM admin_detail 
WHERE is_deleted = 'N';

-- Performance tip: Use CONCAT_WS when you have multiple strings with separators
-- CONCAT_WS is generally faster than multiple CONCAT calls with separators

-- Example 7: Building dynamic messages
SELECT 
    admin_id,
    admin_name,
    admin_salary,
    CONCAT(
        'Admin ',
        admin_name,
        ' (ID: ',
        admin_id,
        ') earns $',
        admin_salary,
        ' and can be reached at ',
        admin_email
    ) AS admin_summary
FROM admin_detail 
WHERE is_deleted = 'N';

-- Example 8: Handling empty strings vs NULL
SELECT 
    'Test with empty string' AS test_type,
    CONCAT('Hello', '', 'World') AS concat_empty, -- Works fine
    CONCAT('Hello', NULL, 'World') AS concat_null; -- Returns NULL

-- Using IFNULL or COALESCE to handle NULL values
SELECT 
    'Using IFNULL' AS method,
    CONCAT('Hello', IFNULL(NULL, ' '), 'World') AS with_ifnull,
    CONCAT('Hello', COALESCE(NULL, ' '), 'World') AS with_coalesce;
