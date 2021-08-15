# MyTheresa code test

## Running and testing the app

First, clone the repo to your local workspace

```
git clone https://github.com/echarif/mytheresa-interview.git
```

##### To build the project, run the following command

For linux and macOS
```
./gradlew clean build
```

For Windows
```
gradlew.bat clean build
```

##### To test the project, run the following command

For linux and macOS
```
./gradlew test
```

For Windows
```
gradlew.bat test
```

##### To run the project, run the following command

For linux and macOS
```
./gradlew bootrun
```

For Windows
```
gradlew.bat bootrun
```

*Note for Linux and macOS:

To execute the gradlew script in this way ```./gradlew ...``` it's required to add exec permissions to the file, 
if it's not possible or not wanted, you can exec the script using sh command, like in the following example:

```sh gradlew clean build```

## Decisions made in the project

### DDD project

I used a simple DDD architecture to organize the project, first because the most of my experience I have worked with
this architecture, and I think is a good choice to separate cleanly the layers.

### Rule design pattern

I used the rule design pattern for the discount calculation, first to avoid having a lot of conditions in the service
and also having a view in the future, so if business team requires a new discount type, a new class would be added 
to deal with that specific case. With this solution we keep the code cleaner.

### Search criteria

I used the search criteria here to deal with dynamic queries, as we have an optional param in the application I think is a 
good solution to handle this case.