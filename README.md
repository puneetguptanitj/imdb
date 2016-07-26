## To run directly  
```
docker run -it puneetguptanitj/imdb
```
## Building jar/image locally
Pre-requisite javac

## Building
### jar
```
make build
```
### docker image
```
make docker
```
## Running
### jar
```
java -jar imdb.jar
```
### Container
```
docker run -it imdb
```

#Example Execution
	imdb> SET a 10
	imdb> BEGIN
	imdb> BEGIN
	imdb> GET a
	10                   <-- GET keeps traversing transaction boundaries till it gets the key
	imdb> GET c          
	null                 <-- returns null if it doesn't find the key
	imdb> SET a 15
	imdb> ROLLBACK     
	imdb> GET a
	10                   <-- Basic ROLLBACK
	imdb> SET b 10
	imdb> COUNT 10
	2                    <-- COUNT 
	imdb> DELETE a       
	imdb> COUNT 10
	1                    <-- DELETE reduces the count
	imdb> ROLLBACK
	imdb> GET a
	10                   <-- ROLLBACK nullifies DELETE
	imdb> COMMIT
	NO TRANSACTION        
	imdb> ROLLBACK
	NO TRANSACTION       <-- Basic NO TRANSACTION check
	imdb> BEGIN
	imdb> SET b 15
	imdb> SET a 20
	imdb> COMMIT
	imdb> GET a
	20
	imdb> GET b
	15                   <-- Basic COMMIT check
	imdb> COUNT 20
	1
	imdb> BEGIN
	imdb> SET b 25
	imdb> BEGIN
	imdb> SET b 35
	imdb> COMMIT
	imdb> GET b
	35
	imdb> COMMIT         
	NO TRANSACTION       <--- Single COMMIT commits all transactions
	imdb>

