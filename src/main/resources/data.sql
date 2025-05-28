CREATE TABLE product(
                        id SERIAL primary key,
                        description varchar(100) not null,
                        price DECIMAL(10,2) not null,
                        quantity_in_stock int not null ,
                        wholesale_product boolean not null

);

CREATE TABLE discount_card(
                              id SERIAL primary key ,
                              number numeric(4,0) not null,
                              discount_amount int not null
);
/*DROP TABLE product;
DROP TABLE discount_card;*/