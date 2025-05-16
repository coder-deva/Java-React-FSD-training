/* 
Book(id, title, price, author, publication_house,category,book_count,status)

publication_house:- ["Mcgraw Hill", "DreamFolks", "Warner Bros"]
category:- ["FICTION", "WAR", "COMEDY", "SPORTS"]

status:- ["IN STOCK","OUT_OF_STOCK"]


Write following procedures:- 

1. Fetch all Books that are "IN STOCK" and price is less than given value. 
2. Delete books that are from given publication_house. do not activate safe mode. 
3. Update the price of books by given percent based on given category. do not activate safe mode. 

*/

use fsd_hex_may_25;
drop table book;


create table Book(
	id int primary key auto_increment,
    title varchar(255),
    price decimal(10,2),
    author varchar(255),
    publication_house varchar(255),
    category varchar(255),
    book_count int,
    status varchar(20) );
  
  
 insert into Book ( id,title,price,author,publication_house,category,book_count,status) values
(1, 'The War Begins', 250, 'Deva', 'Mcgraw Hill', 'WAR', 5, 'IN STOCK'),
(2, 'Funny Times', 120, 'Ramesh', 'DreamFolks', 'COMEDY', 3, 'IN STOCK'),
(3, 'IPL Legends', 300, 'Sri Ganesh', 'Warner Bros', 'SPORTS', 2, 'IN STOCK'),
(4, 'Fantasy World', 150, 'Praveen', 'Mcgraw Hill', 'FICTION', 10, 'IN STOCK'),
(5, 'Crazy World', 90, 'Bharath', 'DreamFolks', 'COMEDY', 6, 'IN STOCK'),
(6, 'Battlefield Stories', 450, 'Madhavan', 'Warner Bros', 'WAR', 0, 'OUT_OF_STOCK');
 
-- 1. Fetch all Books that are "IN STOCK" and price is less than given value. 
delimiter $$
create procedure FetchInStockBooks(in max_price decimal(10,2))
BEGIN
	select * from book where status='In Stock' and price < max_price;
END;

drop procedure FetchInStockBooks

call FetchInStockBooks(200);

select * from book;

-- 2. Delete books that are from given publication_house. do not activate safe mode. 
delimiter $$
create procedure proc_delete_by_pub(in p_pub_house varchar(255))
BEGIN
	declare v_total_books int default 0;
    declare v_index int default 0;
    declare v_book_id int default 0;
    
	select count(id) into v_total_books 
    from book 
    where publication_house = p_pub_house;

    while v_index < v_total_books do
        select id into v_book_id 
        from book 
        where publication_house = p_pub_house 
        limit v_index, 1;

        delete from book 
        where id = v_book_id;

        set v_index = v_index + 1;
    end while;
END;

call proc_delete_by_pub('Warner Bros');
drop procedure proc_delete_by_pub;



-- 3. Update the price of books by given percent based on given category. do not activate safe mode. 
delimiter $$
create procedure proc_update_price(
    in p_percent double,
    in p_category varchar(255)
)
BEGIN
    declare v_total int default 0;
    declare v_index int default 0;
    declare v_book_id int default 0;

    select count(id) into v_total from book where category = p_category;

    while v_index < v_total do
        select id into v_book_id 
        from book 
        where category = p_category 
        limit v_index, 1;

        update book 
        set price = price + (price * p_percent / 100) 
        where id = v_book_id;

        set v_index = v_index + 1;
    end while;
END;


drop procedure proc_update_price;
call proc_update_price(5,'Sports');




