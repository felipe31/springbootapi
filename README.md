## Spring Boot API

This is an exercise to build a REST API, import CSV data to the H2 database through JPA and test it.

## Requirements
This project was built using Maven 3.9.3 and JDK 20.0.1.

## Usage
1. To build the project, run:

```shell
$ mvn clean install
```

It will build it and run the tests.

2. To start the project, run:
```shell
$ mvn spring-boot:run
```
Once running, it will import all data in the `movielist.csv`.

To customize it, edit the `csv.file.path` field of the [`src/main/resources/application.properties`](src/main/resources/application.properties) file.  

### Endpoints
By default, Tomcat starts the web server on port 8080.

You can list all movies by accessing:
```
GET /movies
```

You can list all winning producers here:
```
GET /winning-producers
```

You can list all producers that won a prize consecutively by minimum and maximum interval here:
```
GET /consecutive-winners
```

The latter comes in the following format:

```json
{
    "min": [
        {
            "producer": "Producer 1",
            "interval": 1,
            "previousWin": 2001,
            "followingWin": 2002
        }
    ],
    "max": [
        {
            "producer": "Producer 2",
            "interval": 3,
            "previousWin": 2000,
            "followingWin": 2003
        }
    ]
}
```

## Improvements

The `producers` column in the `csv` file includes more than one producer in most cases.
Therefore, a nice improvement would be to parse them into a `Producers` table.

This new table would make it easier to search for individual producer information.