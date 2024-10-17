### *Issue Description*

* *The application failed to connect to database, displaying the error message "Process finished with exit code 1".*

### *Steps to Reproduce the Issue*

1. *Attempt to run the application and connect to the MySQL database*
2. *Ensure that "localhost:3306" is used in the database connection*
3. *Observe the application failing to connect, ending with "Process finished with exit code 1"*


### *Expected Result*

* *The Application should successfully establish a connection to the MySQL database, allowing data operations to proceed normally.*

### *Actual Result*

* *The application terminates with "Process finished with exit code 1", indicating a failure in establishing the database connection.*


### *Additional Details / Screenshot*

* *After correcting the hostname "localhost:3306" to "db:3306", the database connection was established successfully without further issues.*
* *No other changes were required in the code or database configuration.*

