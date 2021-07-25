CREATE TABLE public."customers"
(
    customer_id serial NOT NULL,
    customer_first_name character varying(100) NOT NULL,
    customer_last_name character varying(100) NOT NULL,
    customer_user_name character varying(50) NOT NULL,
    customer_password character varying(50) NOT NULL,
    customer_gender character varying(20) NOT NULL,
    customer_address text NOT NULL,
    customer_phone_number character varying(20) NOT NULL,
    CONSTRAINT pk_customers PRIMARY KEY (customer_id),
    CONSTRAINT uk_customers_user_name UNIQUE (customer_user_name),
    CONSTRAINT uk_customers_phone_number UNIQUE (customer_phone_number)
);

CREATE TABLE public."categories"
(
    category_id smallserial NOT NULL,
    category_name character varying(100) NOT NULL,
    CONSTRAINT pk_categories PRIMARY KEY (category_id),
    CONSTRAINT uk_categories_category_name UNIQUE (category_name)
);

CREATE TABLE public."products"
(
    product_id bigserial NOT NULL,
    product_name character varying(200) NOT NULL,
    product_price numeric(15, 2) NOT NULL,
    stock_amount integer NOT NULL,
    category_id smallint NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY (product_id),
    CONSTRAINT fk_products_categories FOREIGN KEY (category_id)
        REFERENCES public."categories" (category_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);

CREATE TABLE public."product_comments"
(
    comment_id bigserial NOT NULL,
    customer_id integer NOT NULL,
    product_id bigint NOT NULL,
    comment_content text NOT NULL,
    created_at date NOT NULL DEFAULT CURRENT_DATE,
    CONSTRAINT pk_product_comments PRIMARY KEY (comment_id),
    CONSTRAINT uk_product_comments_customer_id_product_id UNIQUE (product_id, customer_id),
    CONSTRAINT fk_product_comments_customers FOREIGN KEY (customer_id)
        REFERENCES public."customers" (customer_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT fk_product_comments_products FOREIGN KEY (product_id)
        REFERENCES public."products" (product_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);

CREATE TABLE public."payment_info"
(
    payment_info_id serial NOT NULL,
    credit_card_no character varying(20) NOT NULL,
    customer_id integer NOT NULL,
    CONSTRAINT pk_payment_info PRIMARY KEY (payment_info_id),
    CONSTRAINT fk_payment_info_customers FOREIGN KEY (customer_id)
        REFERENCES public."customers" (customer_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);

CREATE TABLE public."orders"
(
    order_id bigserial NOT NULL,
    customer_id integer NOT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (order_id),
    CONSTRAINT fk_orders_customers FOREIGN KEY (customer_id)
        REFERENCES public."customers" (customer_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);

CREATE TABLE public."order_details"
(
    order_detail_id serial NOT NULL,
    product_id bigint NOT NULL,
    order_id bigint NOT NULL,
    payment_info_id integer NOT NULL,
    order_date date NOT NULL DEFAULT CURRENT_DATE,
    total_price numeric(30, 2) NOT NULL,
    CONSTRAINT pk_order_details PRIMARY KEY (order_detail_id),
    CONSTRAINT uk_order_id_payment_info_id_product_id UNIQUE (product_id, order_id, payment_info_id),
    CONSTRAINT fk_order_details_products FOREIGN KEY (product_id)
        REFERENCES public."products" (product_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT fk_order_details_payment_info FOREIGN KEY (payment_info_id)
        REFERENCES public."payment_info" (payment_info_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT fk_order_details_orders FOREIGN KEY (order_id)
        REFERENCES public."orders" (order_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);

CREATE TABLE public."products_rate"
(
    product_rate_id bigserial NOT NULL,
    product_id bigint NOT NULL,
    customer_id integer NOT NULL,
    product_rate_number smallint NOT NULL,
    CONSTRAINT pk_product_rate PRIMARY KEY (product_rate_id),
    CONSTRAINT uk_product_id_customer_id UNIQUE (product_id, customer_id),
    CONSTRAINT fk_products_rate_customers FOREIGN KEY (customer_id)
        REFERENCES public."customers" (customer_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID,
    CONSTRAINT fk_products_rate_products FOREIGN KEY (product_id)
        REFERENCES public."products" (product_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
);


ALTER TABLE public."order_details"
    ADD COLUMN quantity integer NOT NULL;

ALTER TABLE public."order_details"
    ADD COLUMN unit_price numeric(30, 2) NOT NULL;

ALTER TABLE public.order_details
    ADD CONSTRAINT uk_order_id UNIQUE (order_id);