ALTER TABLE orders DROP COLUMN tracking_number;

ALTER TABLE orders ADD tracking_number text NOT NULL;