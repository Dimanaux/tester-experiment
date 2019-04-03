#!/usr/bin/env sh

# compiles project and runs junit tests
# junit 4.12 -- non project requirement
# hamcrest-all 1.3 -- non project requirement

# assumes this structure
#tests/
#    1/
#        origin/
#            src/main
#                Spec.java
#        1/ <- call from here
#            src/main
#                Spec.java
#                Solution.java
#    junit.sh

# create out/ dir
mkdir -p out

# add this dir, lib/, all jar files from lib/ and out/ to the CLASSPATH
CLASSPATH=".:lib:lib/*:out:$CLASSPATH"

# run compilation on all files from src/
javac -cp "$CLASSPATH" -d out src/*

# run all @Junit.Test classes
java -cp "$CLASSPATH" org.junit.runner.JUnitCore main.SolutionTest
