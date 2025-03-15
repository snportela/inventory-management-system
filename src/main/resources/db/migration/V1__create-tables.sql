CREATE OR REPLACE FUNCTION random_string(int) RETURNS TEXT as $$
SELECT substr(md5(random()::text), 0, $1+1);
$$ language sql;

CREATE TABLE customers (
    customer_id uuid DEFAULT gen_random_uuid() NOT NULL,
    name varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    phone varchar(20) NOT NULL,

    CONSTRAINT customers_pk PRIMARY KEY (customer_id),
    CONSTRAINT customers_unique UNIQUE (email)
);

CREATE TABLE customers_adresses (
    customer_adress_id uuid DEFAULT gen_random_uuid() NOT NULL,
    customer_id uuid NOT NULL,
    street varchar(255) NOT NULL,
    district varchar(100) NOT NULL,
    number int NOT NULL,
    city varchar(100) NOT NULL,
    state varchar(100) NOT NULL,
    postal_code varchar(50) NOT NULL,
    details varchar(255),
    receiver_name varchar(100),

    CONSTRAINT customer_adress_pk PRIMARY KEY (customer_adress_id),
    CONSTRAINT customers_fk FOREIGN KEY (customer_id) REFERENCES customers (customer_id)
);

CREATE TABLE product_categories (
    category_id uuid DEFAULT gen_random_uuid() NOT NULL,
    "name" varchar(100) NOT NULL,
    description text NOT NULL,

    CONSTRAINT categories_pk PRIMARY KEY (category_id)
);

CREATE TABLE suppliers (
   supplier_id uuid DEFAULT gen_random_uuid() NOT NULL,
   "name" varchar(100) NOT NULL,
   email varchar(100) NOT NULL,
   phone varchar(20) NOT NULL,
   cnpj varchar(20) NOT NULL,

   CONSTRAINT suppliers_pk PRIMARY KEY (supplier_id),
   CONSTRAINT suppliers_cnpj_unique UNIQUE (cnpj)
);

CREATE TABLE suppliers_adresses (
    supplier_adress_id uuid DEFAULT gen_random_uuid() NOT NULL,
    supplier_id uuid NOT NULL,
    street varchar(255) NOT NULL,
    district varchar(100) NOT NULL,
    "number" int NOT NULL,
    city varchar(100) NOT NULL,
    state varchar(100) NOT NULL,
    postal_code varchar(50) NOT NULL,
    details varchar(255),

    CONSTRAINT supplier_adress_pk PRIMARY KEY (supplier_adress_id),
    CONSTRAINT supplier_adress_fk FOREIGN KEY (supplier_id) REFERENCES suppliers (supplier_id)
);

CREATE TABLE products (
      product_id uuid DEFAULT gen_random_uuid() NOT NULL,
      "name" varchar(100) NOT NULL,
      description varchar(255) NOT NULL,
      price decimal(10,2) NOT NULL,
      quantity int NOT NULL,
      category_id uuid NOT NULL,
      supplier_id uuid NOT NULL,

      CONSTRAINT products_pk PRIMARY KEY (product_id),
      CONSTRAINT products_category_fk FOREIGN KEY (category_id) REFERENCES product_categories (category_id),
      CONSTRAINT products_supplier_fk FOREIGN KEY (supplier_id) REFERENCES suppliers (supplier_id)

);

CREATE TABLE orders (
    order_id uuid DEFAULT gen_random_uuid() NOT NULL,
    customer_id uuid NOT NULL,
    customer_adress_id uuid NOT NULL,
    order_status varchar(100),
    expected_date date NOT NULL,
    actual_date date,
    payment_type varchar(100) NOT NULL,
    payment_status varchar(100) NOT NULL,
    total_price decimal(10,2) NOT NULL,
    tracking_number text DEFAULT random_string(15) NOT NULL,

    CONSTRAINT orders_pk PRIMARY KEY (order_id),
    CONSTRAINT order_customer_fk FOREIGN KEY (customer_id) REFERENCES customers (customer_id),
    CONSTRAINT order_adress_fk FOREIGN KEY (customer_adress_id) REFERENCES customers_adresses (customer_adress_id)
);

CREATE TABLE order_items (
     order_id uuid NOT NULL,
     product_id uuid NOT NULL,
     order_quantity int NOT NULL,

     CONSTRAINT order_items_fk FOREIGN KEY (order_id) REFERENCES orders (order_id),
     CONSTRAINT order_items_product_fk FOREIGN KEY (product_id) REFERENCES products (product_id),
     CONSTRAINT order_items_pk PRIMARY KEY (order_id, product_id)
);

CREATE TABLE transfers (
   transfer_id uuid DEFAULT gen_random_uuid() NOT NULL,
   product_id uuid NOT NULL,
   quantity int NOT NULL,
   transfer_date date NOT NULL,
   "type" varchar(100) NOT NULL,
   description text NOT NULL,

   CONSTRAINT transfer_pk PRIMARY KEY (transfer_id),
   CONSTRAINT transfer_product_fk FOREIGN KEY (product_id) REFERENCES products (product_id)
);



