# SmartCart
JAVA - Object Oriented Design Project
 

Name: Arpita Rai Email: rai.a@northeastern.edu

 

Name: Chethan Mahindrakar Email: mahindrakar.c@northeastern.edu

 

Name: Jhalak Surve Email: surve.j@northeastern.edu

 

#Steps to run the project

 

Import the project into Eclipse.
Download and install the MySQL server
Download the MySQL JDBC driver and extract the JAR and add it as Library to the Build Path in project's properties.
Run the below script to create PRODUCTS, USER_DETAILS and ORDER_HISTORY tables store and fetch the data.

 

Create Database SmartCart;

 

Use SmartCart;

 

create table PRODUCTS(productId varchar(10), productName varchar(50), productPrice float, productQty int, catalog varchar(10), PRIMARY KEY (productId));

 

create table USER_DETAILS(userName varchar(20), fullName varchar(50), contactNumber varchar(20), email varchar(50), password varchar(30), PRIMARY KEY (userName));

 

create table ORDER_HISTORY(userName varchar(20), productId varchar(10), purchasedPrice float, catalog varchar(20), order_timestamp varchar(20));

 

INSERT INTO PRODUCTS values("FR001", "Apple", 2.18, 100, "Fruits");
INSERT INTO PRODUCTS values("FR002", "Avocado", 3.78, 100, "Fruits");
INSERT INTO PRODUCTS values("FR003", "Banana", 1.28, 100, "Fruits");
INSERT INTO PRODUCTS values("FR004", "Blueberries", 2.58, 100, "Fruits");
INSERT INTO PRODUCTS values("FR005", "Guava", 1.25, 100, "Fruits");
INSERT INTO PRODUCTS values("FR006", "Oranges", 3.46, 100, "Fruits");
INSERT INTO PRODUCTS values("FR007", "Plum", 2.75, 100, "Fruits");
INSERT INTO PRODUCTS values("FR008", "Strawberries", 3.62, 100, "Fruits");
INSERT INTO PRODUCTS values("FR009", "Watermelon", 2.28, 100, "Fruits");

 

INSERT INTO PRODUCTS values("BAK001","Whole Wheat Bread", 3.54, 100, "Bakery");
INSERT INTO PRODUCTS values("BAK002","CheeseCake", 3.1, 100, "Bakery");
INSERT INTO PRODUCTS values("BAK003","Chocolate Chip Muffin", 2.6, 100, "Bakery");
INSERT INTO PRODUCTS values("BAK004","Coookie", 2.8, 100, "Bakery");
INSERT INTO PRODUCTS values("BAK005","Tiramisu", 3, 100, "Bakery");
INSERT INTO PRODUCTS values("BAK006","Honey Bread", 4.4, 100, "Bakery");
INSERT INTO PRODUCTS values("BAK007","Strawberry ShortCake", 2.75, 100, "Bakery");
INSERT INTO PRODUCTS values("BAK008","Donut", 3.26, 100, "Bakery");
INSERT INTO PRODUCTS values("BAK009","Crossaint", 4.2, 100, "Bakery");

 

INSERT INTO PRODUCTS values("DRI001", "Water", 2, 100, "Drinks");
INSERT INTO PRODUCTS values("DRI002", "Diet Coke", 3.2, 100, "Drinks");
INSERT INTO PRODUCTS values("DRI003", "Sprite", 2.5, 100, "Drinks");
INSERT INTO PRODUCTS values("DRI004", "Fruit Punch", 2.3, 100, "Drinks");
INSERT INTO PRODUCTS values("DRI005", "Mountain Dew", 3, 100, "Drinks");
INSERT INTO PRODUCTS values("DRI006", "Orange Juice", 3.4, 100, "Drinks");
INSERT INTO PRODUCTS values("DRI007", "Pepsi", 1.75, 100, "Drinks");
INSERT INTO PRODUCTS values("DRI008", "Red Bull", 2.26, 100, "Drinks");
INSERT INTO PRODUCTS values("DRI009", "Dunkin Coffee", 2.86, 100, "Drinks");

 

INSERT INTO PRODUCTS values("PHA001","BandAid", 3.54, 100, "Pharmacy");
INSERT INTO PRODUCTS values("PHA002","Advil", 3.1, 100, "Pharmacy");
INSERT INTO PRODUCTS values("PHA003","Paracetamol", 2.6, 100, "Pharmacy");
INSERT INTO PRODUCTS values("PHA004","Vicks", 2.8, 100, "Pharmacy");
INSERT INTO PRODUCTS values("PHA005","Antacid", 3, 100, "Pharmacy");
INSERT INTO PRODUCTS values("PHA006","Antibiotic", 4.4, 100, "Pharmacy");
INSERT INTO PRODUCTS values("PHA007","Vaseline", 2.75, 100, "Pharmacy");
INSERT INTO PRODUCTS values("PHA008","Benadryl", 3.26, 100, "Pharmacy");
INSERT INTO PRODUCTS values("PHA009","Nasal Spray", 4.2, 100, "Pharmacy");

 

INSERT INTO PRODUCTS values("SN001", "Protien Bar", 0.78, 100, "Snacks"); 
INSERT INTO PRODUCTS values("SN002", "Cheetos", 2.15, 100, "Snacks"); 
INSERT INTO PRODUCTS values("SN003", "Chips", 2.24, 100, "Snacks"); 
INSERT INTO PRODUCTS values("SN004", "Chocolate", 0.38, 100, "Snacks"); 
INSERT INTO PRODUCTS values("SN005", "Onion Dip", 2.1, 100, "Snacks"); 
INSERT INTO PRODUCTS values("SN006", "Popcorn", 3.18, 100, "Snacks"); 
INSERT INTO PRODUCTS values("SN007", "Pringles", 4.2, 100, "Snacks"); 
INSERT INTO PRODUCTS values("SN008", "Tostitos", 2.86, 100, "Snacks"); 
INSERT INTO PRODUCTS values("SN009", "Doritos", 2.64, 100, "Snacks");

 

INSERT INTO PRODUCTS values("VG001", "Bell Peppers", 2.18, 100, "Vegetables"); 
INSERT INTO PRODUCTS values("VG002", "Carrot", 1.5, 100, "Vegetables"); 
INSERT INTO PRODUCTS values("VG003", "Cauliflower", 3.54, 100, "Vegetables"); 
INSERT INTO PRODUCTS values("VG004", "Mushrooms", 3.5, 100, "Vegetables"); 
INSERT INTO PRODUCTS values("VG005", "Onions", 4.5, 100, "Vegetables"); 
INSERT INTO PRODUCTS values("VG006", "Potatoes", 3.25, 100, "Vegetables"); 
INSERT INTO PRODUCTS values("VG007", "Broccoli", 2.75, 100, "Vegetables"); 
INSERT INTO PRODUCTS values("VG008", "Spinach", 3.55, 100, "Vegetables"); 
INSERT INTO PRODUCTS values("VG009", "Tomatoes", 4.74, 100, "Vegetables");

 


1. Download and add the jar for the animation to the Build Path: https://mvnrepository.com/artifact/io.github.typhon0/AnimateFX/1.2.0
2. Add JavaFX library to the Build Path.
3. Update the url, username and password in the SmartCart project under path src.constants.DatabaseConstants as per one's Database configuration.
4. Run the Main.java file.
5. Go to Register Page and register the user.
6. Upon successful registration, login into your account.
7. Voila!! Start your grocery shopping on our platform SmartCart.

has context menu
