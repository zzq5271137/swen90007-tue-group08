# swen90007-tue-group08
This is the repository for SWEN90007 - Software Design and Architecture project. It is used by Tue_Group_08: He Zhu (ID: 812206) and Zhengqing Zhu (ID: 820064).

****************************** Parcel Delivery System ******************************

* * * * * * * * * * * * * * * * * * * *    Entrance and Log in    * * * * * * * * * * * * * * * * * * * *

A welcome message displays in the screen as the first page of the system. Click the ‘Login’ button to direct to the user Login page. 

* Customer Login
Please use the following account to login our system and feel free to try other password to test the error handling function. 
 (1). Login name: customer1, Password: 123456
 (2). Login name: customer2, Password: 456789

* Courier Login
Please use the following account to login our system and feel free to try other password to test the error handling function. 
 (1). Login name: courier1, Password: 123456
 (2). Login name: courier2, Password: 456789
 
The system displays a login successful message if the above account has been used. Click the ‘View Your Orders’ button to direct to the home page of this user. 

* * * * * * * * * * * * * * * * * * * *    Customer operations    * * * * * * * * * * * * * * * * * * * *

The system displays all the orders created by this user after the customer logs in.

Click the ‘Add New Order’ to create a new order —> input two numbers for item size and weight, and a string for destination
    - Note the size and weight should be within 1 ~ 999, and 0-2 decimal number is permitted. Only input alphabetic characters in destination. -> Click ‘Confirm’ to go back to home page.

Click the ‘Change Detail’ to make changes to some changes. 

Click the ‘Delete’ to remove the order from database.

Click the ‘View Your History Target Address’ to find out all addresses.

* * * * * * * * * * * * * * * * * * * *    Courier operations    * * * * * * * * * * * * * * * * * * * *

The system displays all the orders assigned to this user after the courier logs in.

Click the ‘Pick Orders to Deliver’ to pick new orders from the orders pool 

Click the ‘View Your Delivering Order List’ to view how many parcels have been delivered in a particular day.

Click the ‘Delete Log' to remove the log from database.

Click the ‘Finish Deliver’ to change the status of an order.
