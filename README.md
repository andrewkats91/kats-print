# kats-print
A simple console application to calculate predefined print costs.

## How To run
In order to run the project, you must first build the package using maven.
After creating the .JAR file, open console and navigate to the file directory if required.
The default location of the package is the ```targets/``` folder.

In order to run the project in console:
```
java -jar katsprint-*.jar sample.csv
```
Ensure you enter the location of a valid CSV file as a parameter, such as in the example above.
A ```sample.csv``` has been provided in the ```examples/``` folder.

## Project Layout
Core src files located at: ```src/main/java/com/andrewkats/katsprint```
The project is split into three key sections.
### calc
Contains methods and tools for calculations and processing.
### data
Contains data types and object structure related information.
Change internal pricing and other data related aspects here.
### parsing
Contains functions for reading in local files and processing data into a readable format for use in print job calculations.

## Technologies
Ensure you have install the required technologies and they are functioning correctly.

* [Java SE Development Kit](https://www.oracle.com/technetwork/java/javase/downloads/index.html) (13.0.1)
* [Maven](https://maven.apache.org/) (3.6.2)

## Building Package
To package into JAR:

```
mvn clean package
```

## Running Tests
To run tests:

```
mvn test
```

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

