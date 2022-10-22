# API Bank Project

A Java Bank API that allows to create users, accounts, make transactions and delete accounts. Every type of users have concrete
specifications, and also are allowed to do certain actions.
We can add, two kind of roles, to users. Admin and Users roles.
The applications also have different types of accounts. Depends on the specification of the user are allowed to have 
any of those accounts.

To get a general vision of the app, here are the Class Diagram:
![](../Class Diagram Banking System.jpeg)

## REST API

### Get list of things:
#### Get list:

/holdersAccessBalanceSavingsAccount/{holderId} -> Allows holders users to Access to their Balance Savings Account.

/holdersAccessBalanceCheckingAccount/{holderId} -> Allows holders users to Access to their Balance Checking Account.

/holdersAccessBalanceCreditCardAccount/{holderId} -> Allows holders users to Access to their Balance Credit Card Account.

/adminAccessBalanceAccount/{accountId} -> Admin users are able to access any holders account.

#### Create a new Account: (Post)
/admin-createSavingsAccount/ -> Only Admin users are able to create Accounts, on that case, savings Account.

/admin-createCheckingAccount/ -> Only Admin users are able to create Accounts, on that case, checking Account. If the holder
have less than 24 years old, the app automatically generates a Student Checking Account.

/admin-createCreditCardAccount/ -> Only Admin users are able to create Accounts, on that case, Credit Card Account.

/admin-createThirdParty-DataBase/ -> Only Admin users are able to create Third party Users to get their info on Data Base.

#### Modify balance of Accounts: (Patch)
/holdersMakeTransfer/ -> Only the primary Owner of the account are allowed to do transfers.

/admin/modifyBalanceAccounts/{accountId} -> Admin users also can modify the Balance of the holders Accounts.

#### Delete Accounts: (Delete)
/adminDeleteAccount/{accountId} -> Only Admin users can delete accounts.


## Structure of the packages:

Every package ara separate in two different packages, the ones that have references with Users, and the ones that have
references of Accounts.

#### controllers:
Here we can find the DTO's classes, controllers to get the routs and the controller interfaces.

#### entity:
On that Package are the principal classes of the project. Will fins normal classes and abstract classes.

#### enums:
On that package are the status of the accounts, Active status by default, and Frozen status.

#### repositories:
This Package is full of interfaces.

#### security:
Here are the two classes about the security of the project.

#### services:
On that package are the service interfaces and services. The first have the methods, the second one have the methods with logic.

#### MidtermProjectApplication
This one is not a package is just a main class to execute the project.

#### resources:
On the file application.properties, inside this package we can find the concrete properties. Highlight that the correct
name of the SQL schema is bank. 

#### test:
Three kind of test, the one that test Admin functionality, other that test holders functionality and the last one that test
third party functionality

