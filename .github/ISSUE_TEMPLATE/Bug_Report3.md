### *Issue Description*

* *Reports on population data are either incomplete or incorrectly displayed when filtering by specific criteria (e.g., continent, region, city).*


### *Steps to Reproduce the Issue*

1. *Attempt to generate reports from the SQL database based on provided population criteria (e.g., countries by largest population, cities by smallest population).*
2. *Specify additional filters, such as continent, region, or top N populated entities.*
3. *Try to display report details like country codes, names, population data, and capital information.*


### *Expected Result*

* *All requested reports display accurate and complete information according to the specified criteria, including population data for countries, cities, and regions.*


### *Actual Result*

* *Certain reports display incomplete data, particularly when filtering by continent or region.*
* *Population data for specific cities or countries is sometimes missing or miscalculated.*
* *Languages spoken and percentage of the global population are not accurately reflected in language reports.*


### *Additional Details / Screenshot*

* *This issue was observed during tests with the SQL database provided by the organization.*
* *Discrepancies in population totals and filtered reports for continents, regions, and cities are noted.*
* *A review of SQL queries and filtering criteria may help resolve missing or inconsistent data.*

