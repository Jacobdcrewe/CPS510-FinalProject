# CPS510-FinalProject
Overview<br />
The CPS510-Final Project involves creating a database from start to finish. The database chosen was The Travel Management System. This maintains the bookings and cancellations of a travel agency. It can be used by a corporation to keep track of their travel bookings (by air, land or water) or give access to their customers to book trips themselves

The content is my homework so please do not copy but feel free to use it as a reference.

<br />
Installation <br />
1. Download the zip file submitted in Assignment 9<br />
2. Extract the zip file to desired location<br />
3. Open Netbeans 8.2<br />
4. Click File > Open Project<br />
5. Select the project inside the extracted folder<br />
6. Click Run and select main.mainApplication to be the main file.<br /><br />

Program Overview
Login Screen Functions

Once the program is started the login screen will be opened. There are 5 options on this screen.
Sign Up
Table Options
Sign in as Admin
Sign in as Customer
Exit
Sign up 

When the sign up button is pressed the create account window will open. This will prompt the user to create an account by inputting a username, a password, and a credit card number. Once the create account option is selected this will access the customers table in the database and add a new customer.
Table Options and Functions

When the table options button is pressed the table management window will open. This will prompt the user to select one of the 5 buttons. 
The Drop Tables button will drop all tables cascading all constraints. 
The Create Tables button will create all new tables of the ones dropped. 
The Populate Tables button will fill all of those tables with dummy data. 
The Query Tables button will output two separate queries into the output textfield. 
The back button will open the login window again.
Sign in as admin

For the user to sign in as admin they must fill in the username textfield with ‘admin’ and the password textfield with ‘admin’ and then press the sign in button. This will open the Selection Page window. This will prompt the user with five options

Booking Management

This will bring the user to a bookings management page where the admin will be able to add new trips and update and delete current bookings.


Customer Management1

This will bring the user to a customer management page where the admin will be able to add new customers and update and delete current customers


Tour Management

Cancellations

Back

When the back button is pressed it will bring you back to the login screen.


Sign in as Customer

When the user inputs their username and password and then presses the sign-in button the command prompt will output “User is signin in!” when the username and password is correct.


Exit

When the exit button is pressed the program will close.


Selection Page Functions Detailed

Bookings Management

When the bookings management button is pressed the window above will be displayed. On the far left side a new booking can be added by filling in the departure, destination, and transport textfields and then selecting the add booking button. The clear button will clear those textfields. On the right side the admin can see the bookings table with all of the data for each booking and textfields prompting the users for the updated values. For the search, all the updates, delete, and the get return trip buttons the service number textfield is required to be filled. The following functions of each button on the right side are:
The search button will search for the input service number value and return the information in the table
The update departure button will take the service number and the new departure value and update the booking, with the associated service number, with the new departure 
value.

The update destination button will do a similar function as the update departure button but will do the update using the new destination textfield.

The update transport button is similar to the last two but with transport instead.

The delete button will delete the booking associated with the input service number.

The show all bookings button will show all the bookings in the table.

The get return trip button requires the create return trip button to be clicked first if there are no return values for the bookings. This button will take the input service number and return the booking that is associated with the service number that is equal to the return number of the input service number.

The create return trip button will autopopulate the return values of each booking.

The back button will bring you back to the selection page.

Customer Management

When the customer management button is pressed the window above will be displayed. This window is quite similar to the Bookings Management window where the far left prompts the admin to fill the required information and the add customer button will add a new customer with that information. The clear button will clear the textfields that are used to add a new customer. The far right of the window has a table that displays all the customers and their associated information. The following are the functionalities of the buttons on the right side of the window

The search button will take the customer id input and display the user with the associated user id in the table. 

The update password button will take the customer id input and the new password input and update the user with the associated customer id with the new password.

The update card number button will do the same but with the card number.

The update points button will do the same but with the loyalty points values.

The search all customers button will display all the users in the table.

The back button will bring you back to the selection page.
