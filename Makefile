all: testBalls testConway testImmigration testSchelling testBoids doc

testBalls:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/tests/TestBallsSimulator.java

exeBalls:
	java -classpath bin:bin/gui.jar tests/TestBallsSimulator

testConway:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/tests/TestConway.java

exeConway:
	java -classpath bin:bin/gui.jar tests/TestConway

testImmigration:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/tests/TestImmigration.java

exeImmigration:
	java -classpath bin:bin/gui.jar tests/TestImmigration

testSchelling:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/tests/TestSchelling.java

exeSchelling:
	java -classpath bin:bin/gui.jar tests/TestSchelling

testBoids:
	javac -d bin -classpath bin/gui.jar -sourcepath src src/tests/TestBoidSimulator.java

exeBoids:
	java -classpath bin:bin/gui.jar tests/TestBoidSimulator

doc:
	javadoc -d doc -classpath bin:bin/gui.jar -sourcepath src balls boids cells events geometry simulator tests 
