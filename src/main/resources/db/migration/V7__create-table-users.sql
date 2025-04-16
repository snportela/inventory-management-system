CREATE TABLE users (
    user_id uuid DEFAULT gen_random_uuid() NOT NULL,
    name varchar(100) NOT NULL,
    email varchar(100) NOT NULL,
    password varchar(255) NOT NULL,
    phone varchar(20) NOT NULL,
    role varchar(50) NOT NULL,
    created_at timestamp DEFAULT NOW(),
    updated_at timestamp DEFAULT NOW(),
    deleted_at timestamp NULL,

   CONSTRAINT users_pk PRIMARY KEY (user_id),
   CONSTRAINT users_unique UNIQUE (email)
);