run:
	javac src/ceci/lucas/gold/*.java -d bin/ -cp bin/
	java -cp bin/ ceci.lucas.gold.Main
	
clean:
	rm bin/*.class
	
