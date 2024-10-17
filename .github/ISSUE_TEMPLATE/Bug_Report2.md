### *Issue Description*

*When displaying data using an ArrayList in a table, null values sometimes cause the table to render as empty.*


### *Steps to Reproduce the Issue*

1. *Populate an ArrayList with data, including some null values.*
2. *Attempt to display the ArrayList contents in a table format.*


### *Expected Result*

*The table should display available data, leaving fields empty only where values are null.*


### *Actual Result*

* *The table displays as empty when null values are present in the ArrayList.*


### *Additional Details / Screenshot*

* *When null data is in the ArrayList, the table shows no data instead of displaying partial rows.*
* *This issue was encountered while testing with various datasets containing incomplete information.*
* *A workaround is needed to handle null values without impacting table rendering.*
