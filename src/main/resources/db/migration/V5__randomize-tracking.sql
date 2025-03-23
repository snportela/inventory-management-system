CREATE OR REPLACE FUNCTION random_string(int) RETURNS TEXT as $$
SELECT substr(md5(random()::text), 0, $1+1);
$$ language sql;

ALTER TABLE orders DROP COLUMN tracking_number;

ALTER TABLE orders ADD tracking_number text DEFAULT random_string(15) NOT NULL;