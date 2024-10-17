### *Issue Description*

* *Database connection keeps disconnecting unexpectedly.*


### *Steps to Reproduce the Issue*

1. *Verify the current database port number.*
2. *Adjust to the correct port number if needed.*


### *Expected Result*

* *The database server should connect successfully.*


### *Actual Result*

* *Successfully connects to SQL.*


###  *Additional Details / Screenshot*

* *While running the Docker Compose, the SQL connection disconnects intermittently.*
* *First, we reviewed the code and rechecked the SQL port number.*
* *Initially, we overlooked a missing digit in the SQL port number.*
* *This oversight led to spending an entire evening troubleshooting.*
* *After several checks, we discovered a missing zero in the port number.*

