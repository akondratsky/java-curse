create table categories (
    id integer primary key,
    name varchar,
    description varchar
);

create table order_statuses (
    id integer primary key,
    name varchar
);

create table suppliers (
   id serial primary key,
   company_name varchar,
   contact_person varchar,
   phone varchar,
   address varchar
);

create table products (
    id serial primary key,
    name varchar,
    description varchar,
    price money,
    stock_quantity integer,
    category_id integer,
    supplier_id integer,
    constraint fk_category
      foreign key (category_id)
          references categories(id)
          on delete cascade,
    constraint fk_supplier
      foreign key (supplier_id)
          references suppliers(id)
          on delete cascade
);

create table customers (
    id serial primary key,
    firstName varchar,
    lastName varchar,
    email varchar,
    phone varchar,
    address varchar
);

create table orders (
    id serial primary key,
    customer_id integer,
    ordered_at timestamp,
    status integer,
    constraint fk_customer
        foreign key (customer_id)
            references customers(id)
            on delete cascade,
    constraint fk_status
        foreign key (status)
            references order_statuses(id)
            on delete cascade
);

create table order_items (
     id serial primary key,
     order_id integer,
     product_id integer,
     quantity integer,
     price integer,
     constraint fk_order
         foreign key (order_id)
             references customers(id)
             on delete cascade,
     constraint fk_product
         foreign key (product_id)
             references products(id)
             on delete cascade
);
