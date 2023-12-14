package util;

public class CommonConstants {
	
	/** Constant for file path of config.properties */
	public static final String PROPERTY_FILE = "config.properties";

	/** Constant for url key of MySQL database in config.properties */
	public static final String URL = "url";

	/** Constant for user name key of MySQL database in config.properties */
	public static final String USERNAME = "username";

	/** Constant for password key of MySQL database in config.properties */
	public static final String PASSWORD = "password";

	/** Constant for query tag in EmployeeQuery.xml */
	public static final String TAG_NAME = "query";

	/** Constant for query id in EmployeeQuery.xml */
	public static final String ATTRIB_ID = "id";

	/** Constant for driver name key of MySQL database in config.properties */
	public static final String DRIVER_NAME = "driverName";
	
	/** Constant for cart id prefix */
	public static final String CART_ID_PREFIX = "C300";
	
	/** Constant for cart id prefix */
	public static final String WISHLIST_ID_PREFIX = "W300";
	
	/** Constant for review id prefix */
	public static final String REVIEW_ID_PREFIX = "R300";
	
	/** Constant for item id prefix */
	public static final String ITEM_ID_PREFIX = "I300";
	
	/** Constant for question id prefix */
	public static final String QUESTION_ID_PREFIX = "Q300";

	/** Constant for employee id prefix */
	public static final String EMPLOYEE_ID_PREFIX = "E300";
	
	/** Constant for feedback id prefix */
	public static final String FEEDBACK_ID_PREFIX = "F300";
	
	/** Constant for Supplier id prefix */
	public static final String SUPPLIER_ID_PREFIX = "S300";
	
	/** Constant for voucher id prefix */
	public static final String VOUCHER_ID_PREFIX = "V300";
	
	/** Constant for delivery fee */
	public static final double DELIVERY_FEE = 200;
	
	/** Constant for get all items */
	public static final String QUERY_ID_GET_ALL_ITEMS = "SELECT i.id, min(s.unit_price), name, brand, img, i.description, s.stock FROM item i, item_img img, item_size s where i.id = img.id and i.id = s.id GROUP BY i.id ORDER BY i.name;";
	
	/** Constant for select cart id's */
	public static final String QUERY_ID_LOGIN = "SELECT * FROM customer where email=? and Password=?;";
	
	/** Constant for select cart id's */
	public static final String QUERY_ID_LOGIN_ADMIN = "SELECT * FROM admin where Email=? and password=?;";
	
	
	/** Constant for select cart id's */
	public static final String QUERY_ID_SELECT_CART_IDS = "SELECT cid FROM cart;";
	
	/** Constant for select employee id's */
	public static final String QUERY_ID_SELECT_EMPLOYEE_IDS = "select empNo from cart;";
	
	/** Constant for select customer id's*/
	public static final String QUERY_ID_SELECT_ALL_CUSTOMERS = "SELECT * FROM customer;";
	
	/** Constant for select item id's*/

	public static final String QUERY_ID_SELECT_ALL_Stock = "SELECT id, name, category, brand, price, quantity, description, mf_date, exp_date, warrentyType, warrentyNum, warrentyPeriod FROM item_m;";

	//public static final String QUERY_ID_SELECT_ALL_Stock = "SELECT id, name, category, brand,quantity, price, description, mf_date, exp_date, warrentyType, warrentyNum, warrentyPeriod FROM item_m;";

	// dynamic table query
	//public static final String QUERY_ID_GET_Stock_ITEMS = "SELECT i.id, i.name, i.type, i.subtype ,i.description, i.brand, i.mf_date, i.exp_date, s.size, s.stock, s.unit_price, img.img, war.warrentyType, war.warrentyNum, war.warrentyPeriod from item i, item_size s, item_img img, item_warrenty war WHERE i.id = s.id and i.id = img.id and i.id = war.id group by i.id;";
	

	public static final String QUERY_ID_GET_Stock_ITEMS = "SELECT id, name, category, brand, quantity, price, description,mf_date, exp_date,warrentyType, warrentyNum, warrentyPeriod from item_m;";

	/** Constant for select voucher id's*/
	
	public static final String QUERY_ID_SELECT_ALL_Voucher = "SELECT * FROM voucher;";
	
	
	/** Constant for select searched item id's*/
	public static final String QUERY_ID_SELECT_SEARCHED_ALL_Stock = "SELECT id, name, category, brand, quantity, price,description,mf_date, exp_date,warrentyType,warrentyNum, warrentyPeriod FROM item_m where id LIKE '%?%' or name LIKE '%?%' or category LIKE '%?% or brand LIKE '%?%' or description LIKE '%?%';";
	
	/** Stock items sort by */
	public static final String QUERY_ID_SORTBY_ID = "SELECT id, name, category, description, brand, quantity, price, mf_date, exp_date, warrentyType, warrentyNum, warrentyPeriod from item_m ORDER BY id;";
	
	public static final String QUERY_ID_SORTBY_NAME = "SELECT id, name, category, description, brand, quantity, price, mf_date, exp_date, warrentyType, warrentyNum, warrentyPeriod from item_m ORDER BY name;";
	
	public static final String QUERY_ID_SORTBY_Cat = "SELECT id, name, category, description, brand, quantity, price, mf_date, exp_date, warrentyType, warrentyNum, warrentyPeriod from item_m ORDER BY category;";

	public static final String QUERY_ID_SORTBY_MF = "SELECT id, name, category, description, brand, quantity, price, mf_date, exp_date, warrentyType, warrentyNum, warrentyPeriod from item_m ORDER BY mf_date;";
	
	public static final String QUERY_ID_SORTBY_EXP = "SELECT id, name, category, description, brand, quantity, price, mf_date, exp_date, warrentyType, warrentyNum, warrentyPeriod from item_m ORDER BY exp_date;";
	
	public static final String QUERY_ID_SORTBY_PRICE = "SELECT id, name, category, description, brand, quantity, price, mf_date, exp_date, warrentyType, warrentyNum, warrentyPeriod from item_m ORDER BY price;";
	
	/** Stock Vouchers sort by */
	public static final String QUERY_ID_SORTBY_Code = "SELECT id, code, amount, exp FROM voucher ORDER BY code;";
	
	public static final String QUERY_ID_SORTBY_Amount = "SELECT id, code, amount, exp FROM voucher ORDER BY amount;";
	
	public static final String QUERY_ID_SORTBY_Exp = "SELECT id, code, amount, exp FROM voucher ORDER BY exp;";

	
	
	
	/** Constant for add customer details */
	public static final String QUERY_ID_ADD_TO_CUSTOMER = "INSERT INTO customer(email, Password, phone, address) VALUES(?, ?, ?, ?);";	
	
	/** Constant for add stock details */

	public static final String QUERY_ID_ADD_TO_stock_item = "INSERT INTO item_m(id, name, category, description, brand, price, quantity, mf_date, exp_date, warrentyType, warrentyNum, warrentyPeriod) VALUES(?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?);";

	//public static final String QUERY_ID_ADD_TO_stock_item = "INSERT INTO item_m(id, name, type, description, brand, price, quantity, mf_date, exp_date,warrentyType, warrentyNum, warrentyPeriod ) VALUES(?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?);";

	//public static final String QUERY_ID_ADD_TO_stock_item_size = "INSERT INTO item_(id, size, unit_price, stock) VALUES(?, ?, ?, ?);";
	
	public static final String QUERY_ID_ADD_TO_stock_item_War = "INSERT INTO item_warrenty(id, WarrentyType, warrentyNum, warrentyPeriod) VALUES(?, ?, ?, ?);";
	
	public static final String QUERY_ID_ADD_TO_stock_item_IMG = "INSERT INTO item_img(id, img) VALUES(?, ?);";
	
	/** Constant for add stock details advance */
	
	
	// INSERT INTO item_warrenty(id, warrentyType, warrentyNum, warrentyPeriod) VALUES (LAST_INSERT_ID(),'None',0, 'None');
	
	/** Constant for add voucher details */
	public static final String QUERY_ID_ADD_TO_voucher = "INSERT INTO voucher (id, code, amount, exp) VALUES(?, ?, ?, ?);";
	
	
	public static final String QUERY_ID_CREATE_CUSTOMER = "INSERT INTO customer(email) VALUES(?);";
	
	/** Constant for update customer */
	public static final String QUERY_ID_UPDATE_CUSTOMERS = "UPDATE customer SET email = ?, Password = ?, phone = ? , address = ?, WHERE = email = ? ";
	
	/** Constant for clear customer */
	public static final String QUERY_ID_CLEAR_CUSTOMER = "DELETE FROM customer WHERE email = ?";
	
	/** Constant for select cart id's */
	public static final String QUERY_ID_SELECT_ALL_EMPLOYEES = "SELECT * FROM employee;";
	
	/** Constant for add to cart */
	public static final String QUERY_ID_ADD_TO_EMPLOYEE = "INSERT INTO employee(empNo, name, email, designation, phoneNum, address, gender, date, wage, salary) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	
	/** Constant for select cart id's */
	public static final String QUERY_ID_SELECT_ALL_EMPLOYEE_IDS = "SELECT empNo FROM employee;";
	
	/** Constant for select stock id's */
	public static final String QUERY_ID_SELECT_ALL_Stock_IDS = "SELECT id FROM item_m;";
	
	public static final String QUERY_ID_insert_item = "insert into value FROM item_m;";
	
	/** Constant for select voucher id's */
	public static final String QUERY_ID_SELECT_ALL_Voucher_IDS = "SELECT id FROM voucher;";
	
	public static final String QUERY_ID_CREATE_EMPLOYEE = "INSERT INTO employee(empNo) VALUES(?);";
	
	/** Constant for get other item details */
	public static final String QUERY_ID_GET_ITEM_DETAILS = "SELECT id name, brand, description, (SELECT img FROM item_img WHERE Id = ? limit 1) as 'img', (SELECT unit_price FROM item_size WHERE id = ? and size = ?) as 'price', (SELECT stock FROM item_size WHERE id = ? and size = ?) as 'stock' FROM item WHERE id = ?;";
	
	
	
	/** Constant for update item */
	
	public static final String QUERY_ID_UPDATE_ITEM_NAME = "UPDATE item_m SET name = ? WHERE id = ?;";
	
	public static final String QUERY_ID_UPDATE_ITEM_CAT = "UPDATE item_m SET category = ? WHERE id = ?;";
	
	public static final String QUERY_ID_UPDATE_ITEM_BRAND = "UPDATE item_m SET brand = ? WHERE id = ?;";
	
	public static final String QUERY_ID_UPDATE_ITEM_PRICE = "UPDATE item_m SET price = ? WHERE id = ?;";
	
	//public static final String QUERY_ID_UPDATE_ITEM_QUANTITY = "UPDATE item_m SET stock = ? WHERE id = ?;";
	
	public static final String QUERY_ID_UPDATE_ITEM_DESCRIPTION = "UPDATE item_m SET description = ? WHERE id = ?;";
	
	public static final String QUERY_ID_UPDATE_ITEM_MF = "UPDATE item_m SET mf_date = ? WHERE id = ?;";
	
	public static final String QUERY_ID_UPDATE_ITEM_EXP = "UPDATE item_m SET exp_date = ? WHERE id = ?;";
	
	public static final String QUERY_ID_UPDATE_ITEM_WARTYPE = "UPDATE item_m SET warrentyType = ? WHERE id = ?;";
	
	public static final String QUERY_ID_UPDATE_ITEM_WARNUM = "UPDATE item_m SET warrentyNum = ? WHERE id = ?;";
	
	public static final String QUERY_ID_UPDATE_ITEM_WARPERIOD = "UPDATE item_m SET warrentyPeriod = ? WHERE id = ?;";
	
	//public static final String QUERY_ID_UPDATE_ITEM_SIZE = "UPDATE item_size SET size = ? WHERE id = ?;";
	
	//public static final String QUERY_ID_UPDATE_ITEM_SUBTYPE = "UPDATE item SET subtype = ? WHERE id = ?;";
	
	//public static final String QUERY_ID_UPDATE_ITEM_img = "UPDATE item_img SET img = ? WHERE id = ?;";
	
	/** Constant for update voucher */
	
	public static final String QUERY_ID_UPDATE_VOUCHER_code = "UPDATE voucher SET code = ? WHERE id = ?;";
	
	public static final String QUERY_ID_UPDATE_VOUCHER_amount = "UPDATE voucher SET amount= ? WHERE id = ?;";
	
	public static final String QUERY_ID_UPDATE_VOUCHER_EXP = "UPDATE voucher SET exp = ? WHERE id = ?;";
	
	
	
	/** Constant for update employees */
	
	public static final String QUERY_ID_UPDATE_EMPLOYEES_NAME = "UPDATE employee SET name = ? WHERE empNo = ?;";
	
	public static final String QUERY_ID_UPDATE_EMPLOYEES_EMAIL = "UPDATE employee SET email = ? WHERE empNo = ?;";
	
	public static final String QUERY_ID_UPDATE_EMPLOYEES_DESIGNATION = "UPDATE employee SET designation = ? WHERE empNo = ?;";
	
	public static final String QUERY_ID_UPDATE_EMPLOYEES_PHONENUM = "UPDATE employee SET phoneNum = ? WHERE empNo = ?;";
	
	public static final String QUERY_ID_UPDATE_EMPLOYEES_ADDRESS = "UPDATE employee SET address = ? WHERE empNo = ?;";
	
	public static final String QUERY_ID_UPDATE_EMPLOYEES_GENDER = "UPDATE employee SET gender = ? WHERE empNo = ?;";
	
	public static final String QUERY_ID_UPDATE_EMPLOYEES_DATE = "UPDATE employee SET date = ? WHERE empNo = ?;";
	
	public static final String QUERY_ID_UPDATE_EMPLOYEES_WAGE = "UPDATE employee SET wage = ? WHERE empNo = ?;";
	
	public static final String QUERY_ID_UPDATE_EMPLOYEES_SALARY = "UPDATE employee SET salary = ? WHERE empNo = ?;";
	
	/** Constant for clear employees */
	public static final String QUERY_ID_CLEAR_EMPLOYEES = "DELETE FROM employee WHERE empNo = ?";
	
	/** Constant for clear employees */
	public static final String QUERY_ID_CLEAR_StockItem = "DELETE FROM item_m WHERE id = ?";
	
	/** Constant for clear employees */
	public static final String QUERY_ID_CLEAR_Voucher = "DELETE FROM voucher WHERE id = ?";
	
	/** Constant for select cart id's */
	public static final String QUERY_ID_SELECT_ALL_SUPPLIER_IDS = "SELECT supNo FROM supplier;";
	
	/** Constant for select cart id's */
	public static final String QUERY_ID_SELECT_ALL_SUPPLIERS = "SELECT * FROM supplier;";
	
	/** Constant for add supplier */
	public static final String QUERY_ID_ADD_TO_SUPPLIER = "INSERT INTO supplier(supNo, name, email, phoneNum, description, sup_type) VALUES(?, ?, ?, ?, ?, ?);";
	
	public static final String QUERY_ID_CREATE_SUPPLIER = "INSERT INTO supplier(supNo) VALUES(?);";
	
	/** Constant for clear employees */
	public static final String QUERY_ID_CLEAR_SUPPLIERS = "DELETE FROM supplier WHERE supNo = ?";
	
	
	public static final String QUERY_ID_UPDATE_SUPPLIERS_NAME = "UPDATE supplier SET name = ? WHERE supNo = ?;";
	
	public static final String QUERY_ID_UPDATE_SUPPLIERS_EMAIL = "UPDATE supplier SET email = ? WHERE supNo = ?;";
	
	public static final String QUERY_ID_UPDATE_SUPPLIERS_DESCRIPTION = "UPDATE supplier SET description = ? WHERE supNo = ?;";
	
	public static final String QUERY_ID_UPDATE_SUPPLIERS_DEBIT = "UPDATE supplier SET sup_type = ? WHERE supNo = ?;";
	
	/** Constant for get items in suppliers */
	public static final String QUERY_ID_GET_EMAILED_SUPPLIER_DETAILS = "SELECT supNo, description, sup_type, email FROM supplier WHERE supNo = ?;";
			
	/** Constant for select feedback id's */
	public static final String QUERY_ID_SELECT_FEEDBACK_IDS = "select feedid from feedback;";
	
	public static final String QUERY_ID_SELECT_ALL_FEEDBACKS = "SELECT * FROM feedback;";
	
	public static final String QUERY_ID_SELECT_ALL_FEEDBACK_IDS = "SELECT feedid FROM feedback;";
	
	/** Constant for add to feedback */
	public static final String QUERY_ID_ADD_TO_FEEDBACKS = "INSERT INTO feedback(feedid, email, subject, feedback) VALUES(?, ?, ?, ?);";
	
	public static final String QUERY_ID_CREATE_FEEDBACKS = "INSERT INTO feedback(feedid) VALUES(?);";
	
	/** Constant for clear feedback */
	public static final String QUERY_ID_CLEAR_FEEDBACKS = "DELETE FROM feedback WHERE feedid = ?";
	
	
	/** Constant for create a cart */
	public static final String QUERY_ID_CREATE_CART = "INSERT INTO cart(cid,email) VALUES(?,?);";
	
	/** Constant for add to cart */
	public static final String QUERY_ID_ADD_TO_CART = "INSERT INTO cart_item(cid, itId, qty, size, date) VALUES(?, ?, ?, ?, CURDATE());";
	
	/** Constant for get stock */
	public static final String QUERY_ID_GET_STOCK = "SELECT stock FROM item_size WHERE Id = ? AND size = ?;";
	
	/** Constant for get quantity */
	public static final String QUERY_ID_GET_QUANTITY = "SELECT qty FROM cart_item WHERE cid = ? AND itId = ? AND size = ?;";
	
	/** Constant for get all item sizes */
	public static final String QUERY_ID_GET_ITEM_SIZES = "SELECT size FROM item_size WHERE Id = ? ORDER BY unit_price ASC;";
	
	/** Constant for get all item counts for cartChart */
	public static final String QUERY_ID_GET_ITEM_COUNT = "SELECT SUM(qty) FROM cart_chart WHERE itId = ? AND size = ? AND month(date) = ?;";
	
	/** Constant for edit stock */
	public static final String QUERY_ID_EDIT_STOCK = "UPDATE item_size SET stock = ? WHERE Id = ? AND size = ?;";
	
	/** Constant for get specific cart id */
	public static final String QUERY_ID_GET_SPECIFIC_CART_ID = "SELECT cid FROM cart WHERE email = ?;";
	
	/** Constant for update cart quantity */
	public static final String QUERY_ID_UPDATE_QUANTITY = "UPDATE cart_item SET qty = ? WHERE cid = ? AND itId = ? AND size = ?;";
	
	/** Constant for clear specific item cart */
	public static final String QUERY_ID_CLEAR_SPECIFIC_ITEM_FROM_CART = "DELETE FROM cart_item WHERE cid = ? AND itId = ? AND size = ?;";
	
	/** Constant for clear cart */
	public static final String QUERY_ID_CLEAR_CART = "DELETE FROM cart_item WHERE cid = ?;";

	/** Constant for set cart total */
	public static final String QUERY_ID_SET_CART_TOTAL = "UPDATE cart SET total = ? WHERE cid = ?;";
	
	/** Constant for get items in cart */
	public static final String QUERY_ID_GET_CART = "SELECT itId, qty, size FROM cart_item WHERE cid = ?;";
	
	/** Constant for get default size */
	public static final String QUERY_ID_GET_DEFAULT_SIZE = "SELECT size FROM item_size WHERE Id = ? ORDER BY unit_price LIMIT 1;";
	
	/** Constant for get items sizes and relevant prices */
	public static final String QUERY_ID_GET_SIZES_AND_PRICES = "SELECT size, unit_price FROM item_size WHERE Id = ? ORDER BY unit_price;";
	
	/** Constant for get items sizes and relevant stock */
	public static final String QUERY_ID_GET_SIZES_AND_STOCK = "SELECT size, stock FROM item_size WHERE Id = ? ORDER BY unit_price;";
	
	/** Constant for get other item details for cart */
	public static final String QUERY_ID_GET_OTHER_ITEM_DETAILS_FOR_CART = "SELECT name, brand, description, (SELECT img FROM item_img WHERE Id = ? limit 1) as 'img', (SELECT unit_price FROM item_size WHERE id = ? and size = ?) as 'price', (SELECT stock FROM item_size WHERE id = ? and size = ?) as 'stock' FROM item WHERE id = ?;";
	
	/** Constant for select wishlist id's */
	public static final String QUERY_ID_SELECT_WISHLIST_IDS = "select wid from wishlist;";
	
	/** Constant for get specific wishlist id */
	public static final String QUERY_ID_GET_SPECIFIC_WISHLIST_ID = "SELECT wid FROM wishlist WHERE email = ?;";
	
	/** Constant for create a wishlist */
	public static final String QUERY_ID_CREATE_WISHLIST = "INSERT INTO wishlist(wid, email) VALUES(?, ?);";
	
	/** Constant for add to wishlist */
	public static final String QUERY_ID_ADD_TO_WISHLIST = "INSERT INTO wishlist_item(wid, itId, size) VALUES(?, ?, ?);";
	
	/** Constant for clear specific item from wishlist */
	public static final String QUERY_ID_CLEAR_SPECIFIC_ITEM_FROM_WISHLIST = "DELETE FROM wishlist_item WHERE wid = ? AND itId = ? AND size = ?;";
	
	/** Constant for get items in wishlist */
	public static final String QUERY_ID_GET_WISHLIST = "SELECT * FROM wishlist_item WHERE wid = ?;";
	
	/** Constant for get other item details for wishlist */
	public static final String QUERY_ID_GET_OTHER_ITEM_DETAILS_FOR_WISHLIST = "SELECT name, brand, img, (SELECT min(unit_price) FROM item_size where id = ?) as 'price' FROM item i, item_img img, item_size s where i.id = img.id and i.id = s.id and i.id = ? GROUP BY i.id;";
	
	/** Constant for get all other item details for wishlist */
	public static final String QUERY_ID_GET_OTHER_DETAILS_FOR_WISHLIST = "SELECT name, brand, img, (SELECT unit_price FROM item_size where id = ? AND size = ?) as 'price', description, s.stock FROM item i, item_img img, item_size s where i.id = img.id and i.id = s.id and i.id = ? GROUP BY i.id;";
	
	/** Constant for get emails of customers for wishlist */
	public static final String QUERY_ID_GET_CUSTOMER_EMAILS_FOR_WISHLIST = "SELECT w.email FROM wishlist w, wishlist_item wi WHERE w.wid = wi.wid AND wi.itId = ?;";
	
	/** Constant for get items in wishlist */
	public static final String QUERY_ID_GET_EMAILED_WISHLIST_ITEM_DETAILS = "SELECT name, (SELECT img FROM item_img where Id = ? limit 1) as 'image' FROM item WHERE id = ?;";
	
	/** Constant for check if an item is in wishlist */
	public static final String QUERY_ID_CHECK_WISHLIST = "SELECT size FROM wishlist_item WHERE wid = ? AND itId = ?;";
	
	/** Constant for get all item counts for wishlistChart  */
	public static final String QUERY_ID_GET_ITEM_WISHLIST_COUNT = "SELECT count(id) FROM wishlist_chart WHERE itId = ? AND size = ? AND month(date) = ?;";
	
	/** Constant for get new arrivals */
	public static final String QUERY_ID_GET_NEW_ARRIVALS = "SELECT name, brand, description FROM item ORDER BY id DESC limit 8;";
	
	/** Constant for get images for new arrivals */
	public static final String QUERY_ID_GET_NEW_ARRIVALS_IMAGES = "SELECT img FROM item_img GROUP BY Id ORDER BY id DESC limit 8;";
	
	/** Constant for get stock for new arrivals */
	public static final String QUERY_ID_GET_NEW_ARRIVALS_STOCK = "SELECT stock FROM item_size GROUP BY Id ORDER BY id DESC limit 8;";
	
	/** Constant for get minimum price for new arrivals */
	public static final String QUERY_ID_GET_MINIMUM_PRICE = "SELECT id, min(unit_price) FROM item_size GROUP BY id ORDER BY id DESC limit 8;";
	
	/** Constant for get related items for product single page */
	public static final String QUERY_ID_GET_RELATED_ITEMS = "SELECT i.id, min(s.unit_price), name, brand, img, i.description, s.stock FROM item i, item_img img, item_size s where i.id = img.id and i.id = s.id and i.type = (select type from item where id = ?) and i.id != ? GROUP BY i.id ORDER BY s.unit_price;";
	
	/** Constant for get all item images for product single page */
	public static final String QUERY_ID_GET_ALL_ITEM_IMAGES = "SELECT img FROM item_img WHERE Id = ?;";
	
	/** Constant for get main search results */
	public static final String QUERY_ID_GET_MAIN_SEARCH_RESULTS = "SELECT id, name, description FROM item WHERE name LIKE ?;";
	
	/** Constant for get images for main search results */
	public static final String QUERY_ID_GET_MAIN_SEARCH_RESULTS_IMAGES = "SELECT img FROM item_img WHERE Id = ? LIMIT 1;";
	
	/** Constant for select review ids */
	public static final String QUERY_ID_SELECT_REVIEW_IDS = "SELECT reviewID FROM review";
	
	/** Constant for add a review */
	public static final String QUERY_ID_ADD_REVIEW = "INSERT INTO review(reviewID, email, ItID, description, stars, date) VALUES(?, ?, ?, ?, ?, CURDATE());";
	
	/** Constant for add review images */
	public static final String QUERY_ID_ADD_REVIEW_IMAGES = "INSERT INTO review_img(reviewID, img) VALUES(?, ?);";
	
	/** Constant for get all item review counts for reviewChart */
	public static final String QUERY_ID_GET_ITEM_REVIEW_COUNT = "SELECT count(reviewID) FROM review WHERE itId = ? AND stars = ?;";
	
	/** Constant for get items for shop page */
	public static final String QUERY_ID_GET_ITEM_DETAILS_FOR_SHOP = "SELECT i.id, min(s.unit_price), name, brand, img, i.description, s.stock FROM item i, item_img img, item_size s where i.id = img.id and i.id = s.id and i.name LIKE ? GROUP BY i.id ORDER BY s.unit_price;";
	
	/** Constant for get relevant item size for shop page */
	public static final String QUERY_ID_GET_RELEVANT_ITEM_SIZE_FOR_SHOP = "SELECT size FROM item_size WHERE Id = ? AND unit_price = ?;";
	
	/** Constant for get main categories for shop page */
	public static final String QUERY_ID_GET_MAIN_CATEGORIES_FOR_SHOP = "SELECT type FROM item;";
	
	/** Constant for get sub categories for shop page */
	public static final String QUERY_ID_GET_SUB_CATEGORIES_FOR_SHOP = "SELECT subType FROM item WHERE type = ?;";
	
	/** Constant for get maximum item price and minimum item price */
	public static final String QUERY_ID_GET_MAX_AND_MIN_ITEM_PRICE_FOR_SHOP = "SELECT max(unit_price), min(unit_price) FROM item_size;";
	
	/** Constant for get main categories for shop page order by price asc */
	public static final String QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_PRICE_ASC = "SELECT i.id, min(s.unit_price), name, brand, img, i.description, s.stock FROM item i, item_img img, item_size s where i.id = img.id and i.id = s.id and i.type like ? and s.unit_price BETWEEN ? AND ? AND i.name LIKE ? AND i.brand LIKE ? and i.subType like ? and s.stock > 0 GROUP BY i.id ORDER BY s.unit_price;";
	
	/** Constant for get main categories for shop page order by price desc */
	public static final String QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_PRICE_DESC = "SELECT i.id, min(s.unit_price), name, brand, img, i.description, s.stock FROM item i, item_img img, item_size s where i.id = img.id and i.id = s.id and i.type like ? and s.unit_price BETWEEN ? AND ? AND i.name LIKE ? AND i.brand LIKE ? and i.subType like ? and s.stock > 0 GROUP BY i.id ORDER BY s.unit_price DESC;";
	
	/** Constant for get main categories for shop page newest arrivals */
	public static final String QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_NEWEST_ARRIVALS = "SELECT i.id, min(s.unit_price), name, brand, img, i.description, s.stock FROM item i, item_img img, item_size s where i.id = img.id and i.id = s.id and i.type like ? and s.unit_price BETWEEN ? AND ? AND i.name LIKE ? AND i.brand LIKE ? and i.subType like ? and s.stock > 0 GROUP BY i.id ORDER BY i.id DESC;";
	
	/** Constant for get main categories for shop page ratings desc */
	public static final String QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_RATING_DESC = "SELECT i.id, min(s.unit_price), name, brand, img, i.description, s.stock FROM item i, item_img img, item_size s, review r where i.id = img.id and i.id = s.id and i.id = r.ItID and i.type like ? and s.unit_price BETWEEN ? AND ? AND i.name LIKE ? AND i.brand LIKE ? and i.subType like ? and s.stock > 0 GROUP BY i.id ORDER BY avg(r.stars) DESC;";
	
	/** Constant for get main categories for shop page order by price asc including outof stock */
	public static final String QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_PRICE_ASC_INCLUDING_OUTOFSTOCK = "SELECT i.id, min(s.unit_price), name, brand, img, i.description, s.stock FROM item i, item_img img, item_size s where i.id = img.id and i.id = s.id and i.type like ? and s.unit_price BETWEEN ? AND ? AND i.name LIKE ? AND i.brand LIKE ? and i.subType like ? and s.stock >= 0 GROUP BY i.id ORDER BY s.unit_price;";
	
	/** Constant for get main categories for shop page order by price desc including outof stock */
	public static final String QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_PRICE_DESC_INCLUDING_OUTOFSTOCK = "SELECT i.id, min(s.unit_price), name, brand, img, i.description, s.stock FROM item i, item_img img, item_size s where i.id = img.id and i.id = s.id and i.type like ? and s.unit_price BETWEEN ? AND ? AND i.name LIKE ? AND i.brand LIKE ? and i.subType like ? and s.stock >= 0 GROUP BY i.id ORDER BY s.unit_price DESC;";
	
	/** Constant for get main categories for shop page newest arrivals including outof stock */
	public static final String QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_NEWEST_ARRIVALS_INCLUDING_OUTOFSTOCK = "SELECT i.id, min(s.unit_price), name, brand, img, i.description, s.stock FROM item i, item_img img, item_size s where i.id = img.id and i.id = s.id and i.type like ? and s.unit_price BETWEEN ? AND ? AND i.name LIKE ? AND i.brand LIKE ? and i.subType like ? and s.stock >= 0 GROUP BY i.id ORDER BY i.id DESC;";
	
	/** Constant for get main categories for shop page ratings desc including outof stock */
	public static final String QUERY_ID_GET_ITEMS_BY_MAIN_CATEGORY_RATING_DESC_INCLUDING_OUTOFSTOCK = "SELECT i.id, min(s.unit_price), name, brand, img, i.description, s.stock FROM item i, item_img img, item_size s, review r where i.id = img.id and i.id = s.id and i.id = r.ItID and i.type like ? and s.unit_price BETWEEN ? AND ? AND i.name LIKE ? AND i.brand LIKE ? and i.subType like ? and s.stock >= 0 GROUP BY i.id ORDER BY avg(r.stars) DESC;";
	
	/** Constant for get item size list for shop page */
	public static final String QUERY_ID_GET_ITEM_SIZE_LIST_FOR_SHOP = "select size from item_size where Id = ? order by unit_price;";
	
	/** Constant for get main categories for shop page */
	public static final String QUERY_ID_GET_BRAND_LIST_FOR_SHOP = "SELECT brand FROM item WHERE type LIKE ? AND subType LIKE ?;";
	
	/** Constant for get average item rating */
	public static final String QUERY_ID_GET_ITEM_AVERAGE_RATING = "select avg(stars) from review where itID = ?;";
	
	/** Constant for get rating count for an item */
	public static final String QUERY_ID_GET_ITEM_RATING_COUNT = "select count(reviewID) from review where ItID = ?;";
	
	/** Constant for get details for rating percentage for an item */
	public static final String QUERY_ID_GET_DETAILS_FOR_ITEM_RATING_PERCENTAGE = "select stars from review where ItID = ?;";
	
	/** Constant for get all ratings for an item */
	public static final String QUERY_ID_GET_ALL_RATINGS_FOR_AN_ITEM = "SELECT reviewID, email, description, stars, date_format(date, '%d %M %Y') as 'date' FROM review where ItID = ?;";
	
	/** Constant for get rating images for an item */
	public static final String QUERY_ID_GET_RATING_IMAGES_FOR_AN_ITEM = "SELECT img FROM review_img where reviewID = ?;";
	
	/** Constant for select question ids */
	public static final String QUERY_ID_SELECT_QUESTION_IDS = "SELECT qID FROM question;";
	
	/** Constant for create a question */
	public static final String QUERY_ID_CREATE_QUESTION = "INSERT INTO question(qID, question, qDate, itID, cusEmail) VALUES(?, ?, NOW(), ?, ?);";
	
	/** Constant for answer a question */
	public static final String QUERY_ID_ANSWER_QUESTION = "UPDATE question SET answer = ?, aDate = NOW(), adEmail = ? WHERE qID = ?;";

	/** Constant for get all questions and answers by itemID */
	public static final String QUERY_ID_GET_QST_AND_ANS_BY_ITEMID = "SELECT qID, question, answer, date_format(qDate, '%M %d, %Y AT %h:%i%p') AS 'qDate', date_format(aDate, '%M %d, %Y AT %h:%i%p'), itID, cusEmail, adEmail FROM question WHERE itID = ? ORDER BY qDate DESC;";
	
	/** Constant for get new questions */
	public static final String QUERY_ID_GET_NEW_QUESTIONS = "SELECT qID, question, date_format(qDate, '%M %d, %Y AT %h:%i%p') AS 'qDate', itID, cusEmail FROM question WHERE answer IS NULL AND aDate IS NULL AND adEmail IS NULL ORDER BY qDate DESC;";
	
	/** Constant for get answered questions */
	public static final String QUERY_ID_GET_ANSWERED_QUESTIONS = "SELECT qID, question, answer, date_format(qDate, '%M %d, %Y AT %h:%i%p') AS 'qDate', date_format(aDate, '%M %d, %Y AT %h:%i%p') AS 'aDate', itID, cusEmail FROM question WHERE answer IS NOT NULL AND aDate IS NOT NULL AND adEmail IS NOT NULL ORDER BY aDate DESC;";
	
	/** Constant for get answered questions */
	public static final String QUERY_ID_EDIT_ANSWERED_QUESTIONS = "UPDATE question SET answer = ?, aDate = NOW() WHERE qID = ?;";
	
	/** Constant for delete a question */
	public static final String QUERY_ID_DELETE_QUESTION = "DELETE FROM question WHERE qID = ?;";
	
	/** Constant for Column index one */
	public static final int COLUMN_INDEX_ONE = 1;

	/** Constant for Column index two */
	public static final int COLUMN_INDEX_TWO = 2;

	/** Constant for Column index three */
	public static final int COLUMN_INDEX_THREE = 3;

	/** Constant for Column index four */
	public static final int COLUMN_INDEX_FOUR = 4;

	/** Constant for Column index five */
	public static final int COLUMN_INDEX_FIVE = 5;

	/** Constant for Column index six */
	public static final int COLUMN_INDEX_SIX = 6;

	/** Constant for Column index seven */
	public static final int COLUMN_INDEX_SEVEN = 7;

	/** Constant for Column index eight */
	public static final int COLUMN_INDEX_EIGHT = 8;
	
	/** Constant for Column index eight */
	public static final int COLUMN_INDEX_NINE = 9;
	
	/** Constant for Column index eight */
	public static final int COLUMN_INDEX_TEN = 10;
	
	/** Constant for Column index eight */
	public static final int COLUMN_INDEX_ELEVEN = 11;
	
	/** Constant for Column index twelve */
	public static final int COLUMN_INDEX_TWELVE = 12;
	/*---------------------------------------------------
	 * 
	 * 
	 * 
	 */
	/** Constant for get item by id */
	public static final String QUERY_ID_GET_ITEM_BY_ID = "SELECT * FROM item i, item_img img, item_size size where i.id = img.Id and i.id = size.Id and i.id = ?;";


	
	
}