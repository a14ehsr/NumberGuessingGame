#!/bin/sh

java -classpath java/src ac.a14ehsr.platform.NumberGuessingGame -a "java -classpath ./ai_programs/attack A_SameAsk" -d "java -classpath ./ai_programs/defence D_RandomDeclare" -vs_result true