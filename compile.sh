#!/bin/sh

cd java/src/

javac ac/a14ehsr/platform/NumberGuessingGame.java

javac ac/a14ehsr/sample_ai/attack/A_SameAsk.java
javac ac/a14ehsr/sample_ai/attack/A_RandomAsk.java
javac ac/a14ehsr/sample_ai/defence/D_SameDeclare.java
javac ac/a14ehsr/sample_ai/defence/D_RandomDeclare.java

#cd ../../