# 205697139 
# kerzhne
compile: bin
	javac -cp biuoop-1.4.jar:src -d bin src/*.java

	
jar: compile
	jar cfm ass7game.jar manifest.mf -C bin . -C resources/ .

run:	
	java -cp biuoop-1.4.jar:bin:resources Ass7Game

bin:
	mkdir bin