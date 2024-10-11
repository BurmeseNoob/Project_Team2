#### **USE CASE 1: Organizing Countries in the World by Largest Population to Smallest**
#### **CHARACTERISTIC INFORMATION**u

#### **Goal in Context**

As a data analyst, I can organize all the countries in the world by largest to smallest population to study global population distribution patterns.

#### **Scope**
Organization

#### **Level**
Primary task

#### **Preconditions**
The system accesses the global population data for each country.

#### **Success End Condition**
The system generates a list of countries from the largest to smallest population.

#### **Failed End Condition**
The system shows a “no result” message.

#### **Primary Actor**
Data Analyst

#### **Trigger**
The data analyst needs a global population distribution overview.

#### **MAIN SUCCESS SCENARIO**
Data analysis request the report.
The system selects country population.
The system sorts countries by population size in descending order.
The system outputs the country list ordered by population.
#### **EXTENSIONS**
If population data for certain countries is missing or incomplete, the system will proceed with available data and notify the analyst of the missing entries.
If there’s a tie in population, the system sorts alphabetically by country name as a secondary criterion.

#### **SUB-VARIATIONS**
None.

#### **SCHEDULE**
DUE DATE : Release 1.0