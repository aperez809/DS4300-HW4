# DS4300-HW4

#### Problem 1: 
Source.fromFile(filename)

.getLines // gets the lines from the given file as a list (the rows)

.map(_.split(",", -1)) // for each row, split on commas, giving each field in the row

.map(a => a.map(z => if (z == "") 1 else 0)) // for each field, if it is an empty string (null), set its value to 1

.reduce((x, y) => (x zip y).map { case (u, v) => u + v }) // loop through the resulting data of 1’s and 0’s, summing the values. Because all null fields are 1’s and all non-null fields are 0’s, the sum gives the number of null fields.

#### Problem 2: 

`getBinary(10, 8)` returns 00001010
`weight('00001010')` returns 2

Having trouble getting graphs to work with Breeze.

#### Problem 3: 

`moved(100000, 100, 107)` returns 0.99001

#### Problem 4: 

N/A 

#### Problem 5: 

Operations for adding to Graph seem to work as expected, shortestPath is broken and I can't figure out how to fix it.
