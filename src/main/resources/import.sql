insert into product(id, product_name, price) values (1, 'Geanta', 299.99);
insert into product(id, product_name, price) values (2, 'Pantalon', 699.99);
insert into product(id, product_name, price) values (3, 'Camasa', 199.99);
insert into product(id, product_name, price) values (4, 'Pantofi', 999.99);

insert into customer(id, first_name, last_name) values (1,'Ana','Florea');
insert into customer(id, first_name, last_name) values (2,'Laurentiu','Ecovescu');

insert into shipping_address(id,street,city,country,customer_id) values(1,'7 Spinis','Bucharest','Romania',1);
insert into shipping_address(id,street,city,country,customer_id) values(2,'9 Campului','Baia De Fier','Romania',2);


insert into orders(id, date, customer_id) values (1,'2023-6-9',1);
insert into orders(id, date, customer_id) values (2,'2023-6-3',1);
insert into orders(id, date, customer_id) values (3,'2023-4-2',2);

insert into payment(id,date,order_id) value (1,'2023-6-10',1);
insert into payment(id,date,order_id) value (2,'2023-6-30',2);
insert into payment(id,date,order_id) value (3,'2023-4-20',3);

insert into order_item(order_id, product_id) values (1,1);
insert into order_item(order_id, product_id) values (1,2);
insert into order_item(order_id, product_id) values (1,3);
insert into order_item(order_id, product_id) values (2,3);
insert into order_item(order_id, product_id) values (2,4);