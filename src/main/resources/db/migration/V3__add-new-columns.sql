ALTER TABLE customers ADD created_at timestamp DEFAULT NOW();
ALTER TABLE customers ADD updated_at timestamp DEFAULT NOW();
ALTER TABLE customers ADD deleted_at timestamp NULL;

ALTER TABLE customers_adresses ADD created_at timestamp DEFAULT NOW();
ALTER TABLE customers_adresses ADD updated_at timestamp DEFAULT NOW();
ALTER TABLE customers_adresses ADD deleted_at timestamp NULL;

ALTER TABLE order_items ADD created_at timestamp DEFAULT NOW();
ALTER TABLE order_items ADD updated_at timestamp DEFAULT NOW();
ALTER TABLE order_items ADD deleted_at timestamp NULL;

ALTER TABLE orders ADD created_at timestamp DEFAULT NOW();
ALTER TABLE orders ADD updated_at timestamp DEFAULT NOW();
ALTER TABLE orders ADD deleted_at timestamp NULL;

ALTER TABLE product_categories ADD created_at timestamp DEFAULT NOW();
ALTER TABLE product_categories ADD updated_at timestamp DEFAULT NOW();
ALTER TABLE product_categories ADD deleted_at timestamp NULL;

ALTER TABLE products ADD created_at timestamp DEFAULT NOW();
ALTER TABLE products ADD updated_at timestamp DEFAULT NOW();
ALTER TABLE products ADD deleted_at timestamp NULL;

ALTER TABLE suppliers ADD created_at timestamp DEFAULT NOW();
ALTER TABLE suppliers ADD updated_at timestamp DEFAULT NOW();
ALTER TABLE suppliers ADD deleted_at timestamp NULL;

ALTER TABLE suppliers_adresses ADD created_at timestamp DEFAULT NOW();
ALTER TABLE suppliers_adresses ADD updated_at timestamp DEFAULT NOW();
ALTER TABLE suppliers_adresses ADD deleted_at timestamp NULL;

ALTER TABLE transfers ADD created_at timestamp DEFAULT NOW();
ALTER TABLE transfers ADD updated_at timestamp DEFAULT NOW();
ALTER TABLE transfers ADD deleted_at timestamp NULL;




