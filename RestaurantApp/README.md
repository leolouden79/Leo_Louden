# Restruant App

It allows 2 types of accounts to be created: Restaurant Owners and Health Inspectors. 

Users interact with the app through the console using the scanner class.

When the user has completed a task the application's event loop allows the user to perform a new task.

Restaurant Users can add or delete fridges, add food to fridges,  and grant health inspectors access to those fridges.

Health Inspectors can view the contents of any fridge they have access to and remove items from it. 

Information is persisted using JDBC and Amazon's Relational Database Service. 
