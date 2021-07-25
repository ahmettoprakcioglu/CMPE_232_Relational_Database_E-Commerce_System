
--Special Query
--Top 3 product category that females prefer according to total amount of money spent by them

SELECT SUM(od.total_price) as "Total Price", ca.category_name as "Category Name"
FROM ((((order_details as od
INNER JOIN orders as o ON od.order_id = o.order_id)
INNER JOIN customers as cu ON cu.customer_id = o.customer_id)
INNER JOIN products as p ON p.product_id = od.product_id)
INNER JOIN categories as ca ON ca.category_id= p.category_id)
WHERE cu.customer_gender='female'
GROUP BY (ca.category_name)
ORDER BY SUM(od.total_price) DESC LIMIT 3;

--Special Query
--The products which have been best seller product 3 consecutive days at least once


--The price of the most expensive item in the fashion category bought by men.

SELECT max(od.unit_price) as "Max Price"
FROM ((((order_details as od
INNER JOIN orders as o ON od.order_id = o.order_id)
INNER JOIN customers as cu ON cu.customer_id = o.customer_id)
INNER JOIN products as p ON p.product_id = od.product_id)
INNER JOIN categories as ca ON ca.category_id= p.category_id)
WHERE cu.customer_gender='male' and ca.category_name = 'Fashion';

-- Name of customers whose name starts with S and has at least 3 letters.

SELECT c.customer_first_name as "Customer First Name" from customers as c
where c.customer_first_name like 'S__%';

-- Categories with a total of more than 3000 prices of products

select c.category_name as "Category Name", sum(p.product_price) as "Sum of Price" from products as p
INNER JOIN categories as c ON p.category_id=c.category_id
group by c.category_name
having sum(p.product_price)>3000;

-- Products that have never been purchased by any customer until now.

select * from products as p 
where p.product_id not in(select product_id from order_details);


-- List of all customers with different first names.

select distinct customer_first_name from customers;



