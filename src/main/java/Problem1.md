DS4300 Assignment 4
Alex Perez

Source.fromFile(filename)

`.getLines` // gets the lines from the given file as a list (the rows)

`.map(_.split(",", -1))`  // for each row, split on commas, giving each field in the row

`.map(a => a.map(z => if (z == "") 1 else 0))`    // for each field, if it is an empty string (null),	 set its value to 1

`.reduce((x, y) => (x zip y).map { case (u, v) => u + v })`    // loop through the resulting data of 1’s and 0’s, summing the values. Because all null fields are 1’s and all non-null fields are 0’s, the sum gives the number of null fields. 
