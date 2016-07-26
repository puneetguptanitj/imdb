all: build docker

build:
	javac *.java
	jar -cvfm  imdb.jar META-INF/MANIFEST.MF *.class 
	rm *.class

docker: build
	docker build -t imdb .
