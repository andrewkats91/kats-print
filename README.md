# kats-print

A simple console application to calculate predefined print costs.

## Building Package
In order to run the project, you must first build the package using maven.
The default location of the package is the ```targets/``` folder.

To package into JAR:

```
mvn clean package
```

## Running Tests
To run tests:

```
mvn test
```

## Running the Project
After creating the .JAR package, open console and navigate to the file directory. 
If you open the .JAR file from elsewhere you may have to enter the full path to your CSV file.
After navigating to your JAR in console, the following will run the jar and attempt to read csv file located at the same location as the JAR file.

```
java -jar katsprint-*VERSION*.jar sample.csv
```

Ensure you enter the location of a valid CSV file as a parameter, such as in the example above.
A ```sample.csv``` has been provided in the ```examples/``` folder.
Additionally, entering a value of 0-2 as a second parameter will allow you to change the detail of output information.

## Project Layout
Core src files located at: ```src/main/java/com/andrewkats/katsprint```
The project is split into three key sections.

#### Calculations - calc
Contains methods and tools for calculations and processing.
#### Object Data - data
Contains data types and object structure related information.
Change internal pricing and other data related aspects here.
You can add more page sizes via the Paper data class.
#### Parsing and Processing - parser
Contains functions for reading in local files and processing data into a readable format for use in print job calculations.

## Technologies
Ensure you have install the required technologies and they are functioning correctly.

* [Java SE Development Kit](https://www.oracle.com/technetwork/java/javase/downloads/index.html) (13.0.1)
* [Maven](https://maven.apache.org/) (3.6.2)

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

